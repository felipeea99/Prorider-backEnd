package com.prorider.DTOs.request.store;

import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "productRequest is mandatory")
    private ProductRequest productRequest;
    @NotNull(message = "priceBySizeRequest is mandatory")
    private List<PriceBySizeRequest> priceBySizeRequest;
    @NotNull(message = "photosRequests is mandatory")
    private List<PhotoRequest> photosRequests;

}
