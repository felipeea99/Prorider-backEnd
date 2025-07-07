package com.prorider.DTOs.request.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdSizeRequest {
    private ProductRequest productRequest;
    private PriceBySizeRequest priceBySizeRequest;
}
