package com.prorider.interfaces.store;

import com.prorider.DTOs.request.store.SizeRequest;
import com.prorider.DTOs.response.store.SizeResponse;
import com.prorider.DTOs.update.store.SizeUpdate;
import com.prorider.entities.store.Size;

import java.util.List;

public interface ISize {
    SizeResponse addSize(SizeRequest sizeRequest);
    SizeResponse editSize(SizeUpdate sizeUpdate);
    Boolean deleteSize(int sizeId);
    List<SizeResponse> findAllSizes();
    Size findSizeByIdBaseForm(int sizeId);
    SizeResponse ToSizeResponse(Size size);
    Size ToSizeObject(SizeResponse sizeResponse);

    }
