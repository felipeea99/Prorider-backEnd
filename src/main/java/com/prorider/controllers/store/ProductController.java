package com.prorider.controllers.store;

import com.prorider.DTOs.request.store.ProductDetailsRequest;
import com.prorider.DTOs.response.store.ProductDetailsResponse;
import com.prorider.DTOs.response.store.ProductResponse;
import com.prorider.DTOs.update.store.ProductUpdate;
import com.prorider.services.store.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /// Adds a Product Object
    @PostMapping("/")
    public ProductDetailsResponse addProduct(@Valid  @RequestBody ProductDetailsRequest prodSizeRequest){
       return productService.addProduct(prodSizeRequest);
    }

    /// Edits a Product Object
    @PutMapping("/")
    public ProductResponse editProduct(@Valid @RequestBody ProductUpdate productUpdate){
        return productService.editProduct(productUpdate);
    }

    /// Deletes a Product Object
    @PostMapping("/{productId}")
    public Boolean deleteProduct(@PathVariable int productId){
        return productService.deleteProduct(productId);
    }

    /// This one is a call just for admin role
    @GetMapping("/adminProducts")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductResponse findProductById(@PathVariable int productId){
        return productService.findProductById(productId);
    }

    /// This is the EndPoint that calls the main view for each object
    @GetMapping("/{productId}/details")
    public ProductDetailsResponse findProductDetailsByProductId(int productId){
        return productService.findProductDetailsByProductId(productId);
    }

    @GetMapping("/excel")
    public ByteArrayOutputStream listProductsExcel(){
        return productService.listProductsExcel();
    }
}
