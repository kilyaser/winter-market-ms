package market.core.controllers;


import lombok.RequiredArgsConstructor;
import market.api.ProductDto;
import market.api.ResourceNotFoundException;
import market.core.converters.ProductConverter;
import market.core.entities.Product;
import market.core.servicies.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductConverter productConverter;
    private final ProductService productService;
    @GetMapping
    public List<ProductDto> findAllProducts() {
       return productService.findAll().stream().map(productConverter::entityToDto).toList();
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Product id:%s not found", id)));
        return productConverter.entityToDto(p);
    }
    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {
        Product product = productService.createNewProduct(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
