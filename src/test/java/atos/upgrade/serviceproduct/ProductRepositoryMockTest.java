package atos.upgrade.serviceproduct;

import atos.upgrade.serviceproduct.entities.Category;
import atos.upgrade.serviceproduct.entities.Product;
import atos.upgrade.serviceproduct.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    public void whenFindByCategory_thenReturnListProduct() {
        Product product01 = Product.builder()
                .name("Nike Air Max")
                .category(Category.builder().id(1).build())
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("100"))
                .status("Created")
                .createdAt(new Date()).build();
        productRepository.save(product01);

        List<Product> products = productRepository.findByCategory(product01.getCategory());

        Assertions.assertThat(products.size()).isEqualTo(3);
    }

}
