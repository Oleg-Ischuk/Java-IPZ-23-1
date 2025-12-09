package com.education.ztu.junit;

import com.education.ztu.Product;
import org.junit.*;

import static org.junit.Assert.*;

public class ProductJUnitTest {

    private Product product;

    @BeforeClass
    public static void beforeAll() {
        System.out.println("===== @BeforeClass: START TESTING =====");
    }

    @AfterClass
    public static void afterAll() {
        System.out.println("===== @AfterClass: FINISH TESTING =====");
    }

    @Before
    public void setUp() {
        System.out.println("---- @Before executed");
        product = new Product("Apple", "iPhone 14", 2023, 999.99);
    }

    @After
    public void tearDown() {
        System.out.println("---- @After executed");
        product = null;
    }

    @Test
    public void positiveTest_getters() {
        assertEquals("Apple", product.getBrand());
        assertEquals("iPhone 14", product.getModel());
        assertEquals(2023, product.getYear());
        assertEquals(999.99, product.getPrice(), 0.01);
    }

    @Test
    public void positiveTest_setters() {
        product.setBrand("Samsung");
        product.setModel("S23");
        product.setYear(2024);
        product.setPrice(1200);

        assertEquals("Samsung", product.getBrand());
        assertEquals("S23", product.getModel());
        assertEquals(2024, product.getYear());
        assertEquals(1200, product.getPrice(), 0.01);
    }

    @Test
    public void testNotNull() {
        assertNotNull(product);
    }

    @Test
    public void testNull() {
        Product p = null;
        assertNull(p);
    }

    @Test
    public void testSame() {
        Product p2 = product;
        assertSame(product, p2);
    }

    @Test
    public void testNotSame() {
        Product p2 = new Product("Apple", "iPhone 14", 2023, 999.99);
        assertNotSame(product, p2);
    }

    @Test
    public void testEquals() {
        Product p2 = new Product("Apple", "iPhone 14", 2023, 999.99);
        assertTrue(product.equals(p2));
    }

    @Test
    public void negativeTest_equals() {
        Product p2 = new Product("Samsung", "S23", 2023, 999.99);
        assertTrue(!product.equals(p2));
    }

    @Test
    public void failExample() {
        if (product.getPrice() < 0) {
            fail("Price cannot be negative!");
        }
    }

    @Ignore("Demonstration of @Ignore")
    @Test
    public void ignoredTest() {
        fail("This test is ignored");
    }
}
