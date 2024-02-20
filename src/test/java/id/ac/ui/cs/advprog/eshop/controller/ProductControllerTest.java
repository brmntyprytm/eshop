package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("CreateProduct", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = productController.createProductPost(product, model);
        assertEquals("redirect:list", viewName);
        verify(productService).create(product);
    }

    @Test
    void testProductListPage() {
        List<Product> products = new ArrayList<>();
        when(productService.findAll()).thenReturn(products);

        String viewName = productController.productListPage(model);
        assertEquals("ProductList", viewName);
        verify(model).addAttribute("products", products);
    }

    @Test
    void testDeleteProduct() {
        String productId = "123";
        String viewName = productController.deleteProduct(productId);
        assertEquals("redirect:/product/list", viewName);
        verify(productService).delete(productId);
    }

    @Test
    void testEditProductPage() {
        String productId = "123";
        Product product = new Product();
        when(productService.get(productId)).thenReturn(product);

        String viewName = productController.editProductPage(model, productId);
        assertEquals("EditProduct", viewName);
        verify(model).addAttribute("product", product);
    }

    @Test
    public void testEditProductPost() {
        Product product = new Product();
        String viewName = productController.editProductPost(product);
        assertEquals("redirect:list", viewName);
        verify(productService).edit(product);
    }
}
