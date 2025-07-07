package com.prorider.interfaces.store;

import com.prorider.DTOs.request.store.PriceBySizeRequest;
import com.prorider.DTOs.response.store.PriceBySizeResponse;
import com.prorider.DTOs.update.store.PriceBySizeUpdate;
import com.prorider.entities.store.PriceBySize;
import com.prorider.entities.store.Product;

import java.util.List;
import java.util.UUID;

public interface IPriceSize {
    List<PriceBySizeResponse> addProductSize(List<PriceBySizeRequest> request, Product product);
    PriceBySizeResponse editProductSize(PriceBySizeUpdate update);
    Boolean deleteProductSize(int priceSizeId);
    List<PriceBySizeResponse> findAllProductSizes();
    List<PriceBySize> findAllProductSizesBaseForm();
    PriceBySizeResponse findPriceSizeById(int priceSizeId);
    List<PriceBySizeResponse> findProductSizesByProductId(int productId);
    List<PriceBySizeResponse> findAllPriceBySizeByStoreId(UUID storeId);
    PriceBySizeResponse ToPriceBySizeResponse(PriceBySize priceBySize);
    PriceBySize ToPriceBySizeObj(PriceBySizeRequest request);


    }
