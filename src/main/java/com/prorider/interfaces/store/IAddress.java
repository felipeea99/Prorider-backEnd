package com.prorider.interfaces.store;

import com.prorider.DTOs.request.store.AddressRequest;
import com.prorider.DTOs.response.store.AddressResponse;
import com.prorider.DTOs.update.store.AddressUpdate;
import com.prorider.entities.store.Address;

import java.util.List;
import java.util.UUID;

public interface IAddress {
    Address findAddressByIdBaseForm(int addressId);
    AddressResponse findAddressById(int addressId);
    List<AddressResponse> findAddressesByUserId(UUID userId);
    AddressResponse addAddress(AddressRequest addressRequest);
    Boolean deleteAddress(int addressId);
    AddressResponse editAddress(AddressUpdate addressUpdate);
    AddressResponse ToAddressResponse(Address address);
    Address ToAddressObj(AddressRequest addressRequest);
}
