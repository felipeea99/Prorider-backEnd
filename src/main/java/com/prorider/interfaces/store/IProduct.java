package com.prorider.interfaces.store;

import com.prorider.DTOs.request.store.PriceBySizeRequest;
import com.prorider.DTOs.request.store.ProductRequest;
import com.prorider.DTOs.response.store.ProductDetailsResponse;
import com.prorider.DTOs.response.store.ProductResponse;
import com.prorider.DTOs.update.store.ProductUpdate;
import com.prorider.entities.store.Product;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;

public interface IProduct {
    ProductResponse addProduct(ProductRequest productRequest,List<PriceBySizeRequest>priceBySizeList);
    ProductResponse editProduct(ProductUpdate productUpdate);
    Boolean deleteProduct(int productId);
    ProductResponse decreaseProductByOne (Product product);
    List<ProductResponse> getAllProducts();
    ProductResponse findProductById(int productId);
    Product findProductByIdBaseForm(int productId);
    List<ProductResponse> getProductsByCategoryId(int categoryId, UUID storeId);
    List<ProductResponse> getProductsRandomByCategory(int categoryId, UUID storeId);
    ProductDetailsResponse findProductDetailsByProductId(int productId);
    ByteArrayOutputStream listProductsExcel();
    ProductResponse ToProductResponse(Product product);
}
