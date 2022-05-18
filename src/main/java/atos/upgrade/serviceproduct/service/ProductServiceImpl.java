package atos.upgrade.serviceproduct.service;

import atos.upgrade.serviceproduct.entities.Category;
import atos.upgrade.serviceproduct.entities.Product;
import atos.upgrade.serviceproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("CREATED");
        product.setCreatedAt(new java.util.Date());
        return productRepository.save(product);

    }

    @Override
    public Product updateProduct(Product product) {
        Product productDB = getProduct(product.getId());
        if (null == productDB) {
            return null;
        }
        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setPrice(product.getPrice());
        productDB.setCategory(product.getCategory());
        return productRepository.save(productDB);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB = getProduct(id);
        if (null == productDB) {
            return null;
        }
        productDB.setStatus("DELETED");
        return productRepository.save(productDB);

    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product productDB = getProduct(id);
        if (null == productDB) {
            return null;
        }
        Double stock = productDB.getStock() + quantity; // stock = stock + quantity
        productDB.setStock(stock);
        return productRepository.save(productDB);
    }
}
