package market.core.controllers;

import lombok.RequiredArgsConstructor;
import market.core.converters.ProductConverter;
import market.core.soap.products.GetAllProductsRequest;
import market.core.soap.products.GetAllProductsResponse;
import market.core.servicies.ProductService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.winter-market.com/spring/products";
    private final ProductService productService;
    private final ProductConverter productConverter;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.findAll().stream().map(productConverter::entityToSoapDto).forEach(response.getProducts()::add);
        return response;
    }
}
