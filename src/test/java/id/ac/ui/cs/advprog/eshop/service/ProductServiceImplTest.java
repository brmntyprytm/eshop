package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // This initializes the @Mock annotated fields
        productService = new ProductServiceImpl();
        productService.productRepository = productRepository;
    }

    @Test
    void createProduct() {
        Product product = new Product();
        product.setProductId("1234567890");
        product.setProductName("Gehrman, The First Hunter");
        product.setProductQuantity(100);

        when(productRepository.create(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals(product.getProductId(), createdProduct.getProductId());
        assertEquals(product.getProductName(), createdProduct.getProductName());
        assertEquals(product.getProductQuantity(), createdProduct.getProductQuantity());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void findAllProducts() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("1234567890");
        product1.setProductName("Fear The Old Blood");
        product1.setProductQuantity(100);

        Product product2 = new Product();
        product2.setProductId("0987654321");
        product2.setProductName("Paleblood");
        product2.setProductQuantity(50);

        productList.add(product1);
        productList.add(product2);

        when(productRepository.findAll()).thenReturn(productList.iterator());

        List<Product> foundProducts = productService.findAll();

        assertNotNull(foundProducts);
        assertEquals(productList.size(), foundProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void editProduct() {
        Product product = new Product();
        product.setProductId("1234567890");
        product.setProductName("Lady Maria of The Astral Clocktower");
        product.setProductQuantity(100);

        when(productRepository.edit(any(Product.class))).thenReturn(product);

        Product editedProduct = productService.edit(product);

        assertNotNull(editedProduct);
        assertEquals(product.getProductId(), editedProduct.getProductId());
        verify(productRepository, times(1)).edit(product);
    }

    @Test
    void getProductById() {
        Product product1 = new Product();
        product1.setProductId("1234567890");
        product1.setProductName("Rakuyo");

        Product product2 = new Product();
        product2.setProductId("0987654321");
        product2.setProductName("Saw Cleaver");

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        when(productRepository.findAll()).thenReturn(productList.iterator());

        String productIdToGet = "0987654321";
        Product retrievedProduct = productService.get(productIdToGet);

        assertNotNull(retrievedProduct);
        assertEquals(product2.getProductId(), retrievedProduct.getProductId());
        assertEquals(product2.getProductName(), retrievedProduct.getProductName());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void deleteProductWithSufficientQuantity() {
        // Create a product with initial quantity 2
        Product product = new Product();
        product.setProductId("1234567890");
        product.setProductName("Moonlight Greatsword");
        product.setProductQuantity(2);

        // Mock the get() method to return the above product
        when(productRepository.findAll()).thenReturn(new ArrayList<Product>() {
            {
                add(product);
            }
        }.iterator());

        // Invoke the delete() method
        assertTrue(productService.delete("1234567890"));

        // Verify that product quantity is decremented and no deletion happens
        assertEquals(1, product.getProductQuantity());
        verify(productRepository, never()).delete(product);
    }

    @Test
    void deleteProductWithInsufficientQuantity() {
        // Create a product with initial quantity 0
        Product product = new Product();
        product.setProductId("1234567890");
        product.setProductName("Evelyn");
        product.setProductQuantity(0);

        // Mock the get() method to return the above product
        when(productRepository.findAll()).thenReturn(new ArrayList<Product>() {
            {
                add(product);
            }
        }.iterator());

        // Invoke the delete() method
        assertFalse(productService.delete("1234567890"));

        // Verify that product quantity is not changed and no deletion happens
        assertEquals(0, product.getProductQuantity());
        verify(productRepository, never()).delete(product);
    }

    @Test
    void deleteNonExistingProduct() {
        // Mock the get() method to return null
        when(productRepository.findAll()).thenReturn(new ArrayList<Product>().iterator());

        // Invoke the delete() method
        assertFalse(productService.delete("1234567890"));

        // Verify that no interactions with product repository occur
        verify(productRepository, never()).delete(any(Product.class));
    }

    @Test
    void deleteProductWithZeroQuantityShouldInvokeRepositoryDelete() {
        // Create a product with initial quantity 1
        Product product = new Product();
        product.setProductId("1234567890");
        product.setProductName("Ludwig, The Holy Blade");
        product.setProductQuantity(1);

        // Mock the get() method to return the above product
        when(productRepository.findAll()).thenReturn(new ArrayList<Product>() {
            {
                add(product);
            }
        }.iterator());

        // Invoke the delete() method
        assertTrue(productService.delete("1234567890"));

        // Verify that product quantity is decremented and productRepository.delete() is
        // invoked
        assertEquals(0, product.getProductQuantity());
        verify(productRepository, times(1)).delete(product);
    }
}
