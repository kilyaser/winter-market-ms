package market.core.servicies;

import lombok.RequiredArgsConstructor;
import market.api.ProductDto;
import market.core.converters.ProductConverter;
import market.core.repositories.ProductRepository;
import market.core.entities.Product;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductConverter productConverter;
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
    public Product createNewProduct(ProductDto productDto) {
        Product p = productConverter.dtoToEntity(productDto);
        productRepository.save(p);
        return p;
    }
}
