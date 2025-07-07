package com.prorider.controllers.store;

import com.prorider.DTOs.request.store.PhotoRequest;
import com.prorider.DTOs.response.store.PhotoResponse;
import com.prorider.DTOs.update.store.PhotoUpdate;
import com.prorider.services.store.PhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {
///Services
private final PhotoService photoService;
/// Constructor
public PhotoController(PhotoService photoService) {
    this.photoService = photoService;
}

/// Add a single photo
@PostMapping("/")
public PhotoResponse addPhoto(@RequestBody PhotoRequest photoRequest) {
    return photoService.addPhoto(photoRequest);
}

/// Edit a photo
@PutMapping("/")
public PhotoResponse editPhoto(@RequestBody PhotoUpdate photoUpdate) {
    return photoService.editPhoto(photoUpdate);
}

/// Delete a photo by photoID
@DeleteMapping("/{photoID}")
public ResponseEntity<Boolean> deletePhoto(@PathVariable int photoID) {
    boolean deleted = photoService.deletePhoto(photoID);
    return ResponseEntity.ok(deleted);
}

/// Get all photos by product ID
@GetMapping("/{productID}")
public List<PhotoResponse> getAllPhotosByProductID(@PathVariable int productID) {
    return photoService.getAllPhotosByProductID(productID);
}

/// Get all photos with index 0 by product ID
@GetMapping("/index-zero/{productID}")
public List<PhotoResponse> getPhotosIndexZero(@PathVariable int productID) {
   return photoService.getPhotosIndexZero(productID);
}


}