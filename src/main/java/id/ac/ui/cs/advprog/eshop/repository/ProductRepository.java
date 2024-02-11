package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public boolean delete(Product product) {
        return productData.remove(product);
    }

    public Product edit(Product product) {
        // Validate if the edited quantity is negative
        if (product.getProductQuantity() < 0) {
            // Return an error or throw an exception indicating that negative quantities are not allowed
            throw new IllegalArgumentException("Negative quantities are not allowed.");
        }

        // Find the product in the list and update it
        for (Product curProduct : productData) {
            if (curProduct.getProductId().equals(product.getProductId())) {
                int index = productData.indexOf(curProduct);
                if (index != -1) {
                    productData.set(index, product);
                    return product;
                }
            }
        }
        // If the product is not found, return null
        return null;
    }
}
