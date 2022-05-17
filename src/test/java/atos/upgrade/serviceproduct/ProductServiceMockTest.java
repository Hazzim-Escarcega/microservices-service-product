package atos.upgrade.serviceproduct;

import atos.upgrade.serviceproduct.entities.Category;
import atos.upgrade.serviceproduct.entities.Product;
import atos.upgrade.serviceproduct.repository.ProductRepository;
import atos.upgrade.serviceproduct.service.ProductService;
import atos.upgrade.serviceproduct.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceMockTest {
    @Mock
    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);

        Product computer = Product.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1).build())
                .price(Double.parseDouble("199.90"))
                .stock(Double.parseDouble("10"))
                .build();

        Mockito.when(productRepository.findById(1L))
                .thenReturn(java.util.Optional.of(computer));
        Mockito.when(productRepository.save(computer)).thenReturn(computer);
    }
    @Test
    public void whenValidGetID_thenReturnProduct() {
        Product found = productService.getProduct(1L);

        assert found != null;
        assert found.getId() == 1L;
        assert found.getName().equals("computer");
        assert found.getCategory().getId() == 1;
        assert found.getPrice() == 199.90;
        assert found.getStock() == 10;
    }
    public void whenValidUpdateStock_thenReturnNewStock() {
        Product newStock = productService.updateStock(1L, Double.parseDouble("8"));

        assert newStock != null;
        assert newStock.getId() == 1L;
        assert newStock.getName().equals("computer");
        assert newStock.getCategory().getId() == 1;
        assert newStock.getPrice() == 199.90;
        assert newStock.getStock() == 18;
    }
}

