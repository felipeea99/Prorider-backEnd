package com.prorider.DTOs.request.store;

import com.prorider.entities.store.Size;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SizeRequest {
    @NotNull(message = "El campo 'Talla' esta vacía")
    private String size;

    public Size ToSizeObject() {
        Size sizeObj = new Size();
        sizeObj.setSize(size);
        return sizeObj;
    }

}
