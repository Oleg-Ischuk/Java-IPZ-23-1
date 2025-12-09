package com.education.ztu.mockito;

import com.education.ztu.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceMockitoTest {

    @Mock
    private ProductRepository mockRepository;

    @InjectMocks
    private ProductService productService;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    private final Product testProduct =
            new Product("Apple", "iPhone 14", 2023, 999.99);

    @Test
    public void testGetProductById_Found() {
        when(mockRepository.findById("1")).thenReturn(testProduct);

        Product result = productService.getProductById("1");

        assertNotNull(result);
        assertEquals("iPhone 14", result.getModel());
        verify(mockRepository, times(1)).findById("1");
    }

    @Test
    public void testGetProductById_NotFound() {
        when(mockRepository.findById("2")).thenReturn(null);

        Product result = productService.getProductById("2");

        assertNull(result);
        verify(mockRepository, times(1)).findById("2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetProductById_InvalidId() {
        productService.getProductById("");
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = Arrays.asList(
                testProduct,
                new Product("Samsung", "S23", 2023, 899.99)
        );

        when(mockRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
        verify(mockRepository, atLeastOnce()).findAll();
    }

    @Test
    public void testUpdateProductPrice_Success() {
        when(mockRepository.findById("1")).thenReturn(testProduct);
        doNothing().when(mockRepository).save(any(Product.class));

        boolean result = productService.updateProductPrice("1", 1100);

        assertTrue(result);
        assertEquals(1100, testProduct.getPrice(), 0.01);

        verify(mockRepository).findById("1");
        verify(mockRepository).save(testProduct);
    }

    @Test
    public void testUpdateProductPrice_NotFound() {
        when(mockRepository.findById("5")).thenReturn(null);

        boolean result = productService.updateProductPrice("5", 1200);

        assertFalse(result);

        verify(mockRepository, times(1)).findById("5");
        verify(mockRepository, never()).save(any(Product.class));
    }

    @Test(expected = RuntimeException.class)
    public void testCreateNewProduct_Exception() {
        doThrow(new RuntimeException("Database error"))
                .when(mockRepository).save(testProduct);

        productService.createNewProduct(testProduct);
    }

    @Test
    public void testCreateNewProduct_Captor() {
        doNothing().when(mockRepository).save(any(Product.class));

        productService.createNewProduct(testProduct);

        verify(mockRepository).save(productCaptor.capture());

        Product savedProduct = productCaptor.getValue();

        assertEquals("Apple", savedProduct.getBrand());
        assertEquals("iPhone 14", savedProduct.getModel());
    }

    @Test
    public void testAvailability_VerifyModes() {
        when(mockRepository.findById("A")).thenReturn(testProduct);
        when(mockRepository.findById("B")).thenReturn(null);

        assertTrue(productService.isProductAvailable("A"));
        assertFalse(productService.isProductAvailable("B"));
        assertTrue(productService.isProductAvailable("A"));

        verify(mockRepository, atLeast(2)).findById("A");
        verify(mockRepository, times(1)).findById("B");
        verify(mockRepository, never()).findById("C");
        verify(mockRepository, atMost(3)).findById(anyString());
    }
}
