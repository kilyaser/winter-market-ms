package market.core.servicies;

import lombok.RequiredArgsConstructor;
import market.api.ProductDto;
import market.core.converters.ProductConverter;
import market.core.repositories.ProductRepository;
import market.core.entities.Product;
import market.core.repositories.specifications.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public Page<Product> findAll(BigDecimal minPrice, BigDecimal maxPrice, String partTitle, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecification.priceGranderOrEqualsThen(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecification.priceLessOrEqualsThen(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductSpecification.titleLike(partTitle));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 6));
    }
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
