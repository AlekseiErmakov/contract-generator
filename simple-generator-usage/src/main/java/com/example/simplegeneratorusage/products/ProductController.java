package com.example.simplegeneratorusage.products;

import com.example.contractgenerator.products.api.ProductApi;
import com.example.contractgenerator.products.model.ProductCreateRequest;
import com.example.contractgenerator.products.model.ProductDto;
import com.example.contractgenerator.products.model.ProductUpdateRequest;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController implements ProductApi {

    private final Map<Long, ProductDto> products = new HashMap<>();
    private Long nextId = 0L;

    @Override
    public ResponseEntity<Long> productServiceCreateProduct(@Valid ProductCreateRequest productCreateRequest) {
        nextId++;
        ProductDto productDto = new ProductDto()
                .id(nextId)
                .name(productCreateRequest.getName())
                .description(productCreateRequest.getDescription())
                .price(productCreateRequest.getPrice());
        products.put(nextId, productDto);
        return ResponseEntity.ok(nextId);
    }

    @Override
    public ResponseEntity<Void> productServiceDeleteProduct(Long productId) {
        if (!products.containsKey(productId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ProductDto> productServiceGetProduct(Long productId) {
        ProductDto product = products.get(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<List<ProductDto>> productServiceListProducts() {
        return ResponseEntity.ok(List.copyOf(products.values()));
    }

    @Override
    public ResponseEntity<Void> productServiceUpdateProduct(Long aLong, @Valid ProductUpdateRequest productUpdateRequest) {
        ProductDto existingProduct = products.get(aLong);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }
        ProductDto updatedProduct = new ProductDto()
                .id(existingProduct.getId())
                .name(productUpdateRequest.getName())
                .description(productUpdateRequest.getDescription())
                .price(productUpdateRequest.getPrice())
                .available(productUpdateRequest.isAvailable());
        products.put(aLong, updatedProduct);
        return ResponseEntity.ok().build();
    }
}
