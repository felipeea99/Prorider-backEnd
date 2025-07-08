package com.prorider.DTOs.request.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsRequest {
    private ProductRequest productRequest;
    private List<PriceBySizeRequest> priceBySizeRequest;
    private List<PhotoRequest> photosRequests;

}
