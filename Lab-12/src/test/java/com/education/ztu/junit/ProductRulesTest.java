package com.education.ztu.junit;

import com.education.ztu.Product;
import org.junit.*;
import org.junit.rules.*;

import java.io.File;

import static org.junit.Assert.*;

public class ProductRulesTest {

    private Product product;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public TestName testName = new TestName();

    @Rule
    public Timeout timeout = Timeout.seconds(2);

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Rule
    public Verifier verifier = new Verifier() {
        @Override
        protected void verify() {
            System.out.println("Verifier check after test: " + testName.getMethodName());
        }
    };

    @Before
    public void setUp() {
        product = new Product("Apple", "iPhone 14", 2023, 999.99);
    }

    @Test
    public void testTemporaryFolder() throws Exception {
        File file = folder.newFile("test.txt");
        assertTrue(file.exists());
    }

    @Test
    public void testExpectedException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Year cannot be negative");

        if (product.getYear() < 0) {
            throw new IllegalArgumentException("Year cannot be negative");
        }

        throw new IllegalArgumentException("Year cannot be negative");
    }

    @Test
    public void testErrorCollector() {
        try {
            assertTrue(product.getPrice() > 0);
            assertEquals("Apple", product.getBrand());
        } catch (Throwable t) {
            collector.addError(t);
        }
    }

    @Test
    public void testNameRule() {
        System.out.println("Running test: " + testName.getMethodName());
        assertNotNull(product);
    }
}
