package com.education.ztu.junit;

import com.education.ztu.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ProductParameterizedTest {

    private double price;

    public ProductParameterizedTest(double price) {
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {100.0},
                {200.5},
                {999.99},
                {0.1}
        });
    }

    @Test
    public void testDifferentPrices() {
        Product product = new Product("Brand", "Model", 2024, price);
        assertEquals(price, product.getPrice(), 0.01);
    }
}
