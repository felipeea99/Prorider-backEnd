package com.prorider.interfaces.store;

import com.prorider.DTOs.request.store.PhotoRequest;
import com.prorider.DTOs.response.store.PhotoResponse;
import com.prorider.DTOs.update.store.PhotoUpdate;
import com.prorider.entities.store.Photo;

import java.util.List;
import java.util.UUID;

public interface IPhoto {
    Photo findByPhotoIdBaseForm(int photoId);
    PhotoResponse addPhoto(PhotoRequest photoRequest);
    List<PhotoResponse> addMultiplePhotos(List<PhotoRequest> photos);
    PhotoResponse editPhoto(PhotoUpdate photoUpdate);
    Boolean deletePhoto(int photoID);
    List<PhotoResponse> getAllPhotosByProductID(int productID);
    void photoOrder(int productID);
    List<PhotoResponse> getPhotosIndexZero(int productId);
    PhotoResponse ToPhotoResponseObj(Photo photo);
    Photo ToPhotoObj(PhotoRequest photoRequest);

    }
