package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository implements ProductRepositoryInterface {
    private final List<Product> productData = new ArrayList<>();

    @Override
    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    @Override
    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    @Override
    public boolean delete(Product product) {
        return productData.remove(product);
    }

    @Override
    public Product edit(Product product) {
        if (product.getProductQuantity() < 0) {
            throw new IllegalArgumentException("Negative quantities are not allowed.");
        }

        for (Product curProduct : productData) {
            if (curProduct.getProductId().equals(product.getProductId())) {
                int index = productData.indexOf(curProduct);
                if (index != -1) {
                    productData.set(index, product);
                    return product;
                }
            }
        }
        return null;
    }

    @Override
    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }
}
