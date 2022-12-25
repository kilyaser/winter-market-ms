package market.core.converters;

import lombok.RequiredArgsConstructor;
import market.api.ProductDto;
import market.api.ResourceNotFoundException;
import market.core.soap.products.ProductSoapDto;
import market.core.entities.Category;
import market.core.entities.Product;
import market.core.servicies.CategoryService;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;
    public ProductDto entityToDto(Product product) {
        ProductDto p = new ProductDto();
        p.setId(product.getId());
        p.setTitle(product.getTitle());
        p.setPrice(product.getPrice());
        p.setCategoryTitle(product.getCategory().getTitle());
        p.setHeight(product.getHeight());
        p.setWeight(product.getWeight());
        p.setDescription(product.getDescription());
        p.setImage(product.getImage());
        return p;
    }
    public ProductSoapDto entityToSoapDto(Product product) {
        ProductSoapDto soapDto = new ProductSoapDto();
        soapDto.setId(product.getId());
        soapDto.setCategoryTitle(product.getTitle());
        soapDto.setPrice(product.getPrice());
        soapDto.setTitle(product.getTitle());
        soapDto.setHeight(product.getHeight());
        soapDto.setWeight(product.getWeight());
        soapDto.setDescription(product.getDescription());
        soapDto.setImage(product.getImage());
        return soapDto;
    }

    public Product dtoToEntity(ProductDto productDto) {
        Product p = new Product();
        p.setId(productDto.getId());
        p.setTitle(productDto.getTitle());
        p.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        p.setCategory(category);
        return p;
    }
}
