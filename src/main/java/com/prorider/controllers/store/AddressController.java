package com.prorider.controllers.store;

import com.prorider.DTOs.request.store.AddressRequest;
import com.prorider.DTOs.response.store.AddressResponse;
import com.prorider.DTOs.update.store.AddressUpdate;
import com.prorider.services.store.AddressService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    /// Services
    private final AddressService addressService;

    /// Constructor
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /// This EndPoint find an Address object base on the userId that is retrieved from the ValidateStoreAccess using the userId
    @GetMapping("/{addressId}")
    public AddressResponse findAddressById(@PathVariable int addressId){
       return this.addressService.findAddressById(addressId);
    }
    /// This EndPoint search for a List of Address objects
    @GetMapping("/{userId}")
    public List<AddressResponse> findAddressesByUserId(@PathVariable UUID userId){
        return this.addressService.findAddressesByUserId(userId);
    }

    /// This EndPoint adds a AddressObj to the database
    @PostMapping("/")
    public AddressResponse addAddress(@Valid @RequestBody AddressRequest addressRequest){
        return this.addressService.addAddress(addressRequest);
    }

    /// This EndPoint is used to edit a AddressObj
    @PutMapping("/{userId}")
    public AddressResponse editAddress(@Valid @RequestBody AddressUpdate addressUpdate){
        return this.addressService.editAddress(addressUpdate);
    }
    /// This EndPoint is used to delete a AddressObj
    @DeleteMapping("/{addressId}")
    public Boolean deleteAddress(@PathVariable int addressId ){
        return this.addressService.deleteAddress(addressId);
    }

}
