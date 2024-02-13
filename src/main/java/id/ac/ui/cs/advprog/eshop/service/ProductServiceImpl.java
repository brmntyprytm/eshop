package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    private int id = 0;

    @Override
    public Product create(Product product) {
        product.setProductId(Integer.toString(++id));
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    public Product get(String id) {
        Product product = null;
        Iterator<Product> iterator = productRepository.findAll();
        while (iterator.hasNext()) {
            Product currentIteration = iterator.next();
            if (currentIteration.getProductId().equals(id)) {
                product = currentIteration;
                break;
            }
        }
        return product;
    }

    @Override
    public Boolean delete(String id) {
        Product product = get(id);
        if (product != null) {
            int newQuantity = product.getProductQuantity() - 1;
            if (newQuantity >= 0) {
                product.setProductQuantity(newQuantity);
                if (newQuantity == 0) {
                    productRepository.delete(product);
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Product edit(Product product) {
        productRepository.edit(product);
        return product;
    }

}
