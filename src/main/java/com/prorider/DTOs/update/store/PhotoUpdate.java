package com.prorider.DTOs.update.store;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhotoUpdate {
    @NotNull(message = "photoID is mandatory")
    private int photoId;

    @NotBlank(message = "photoValue is mandatory")
    private String photoValue;

    private int index;

    @NotNull(message = "productId is mandatory")
    private int productId;
}
