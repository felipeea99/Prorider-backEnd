package com.prorider.services.store;

import com.prorider.DTOs.response.store.PriceBySizeResponse;
import com.prorider.DTOs.response.store.ProductResponse;
import com.prorider.DTOs.response.store.SizeResponse;
import com.prorider.DTOs.update.store.PriceBySizeUpdate;
import com.prorider.entities.store.PriceBySize;
import com.prorider.entities.store.Product;
import com.prorider.entities.store.Size;
import com.prorider.exceptions.BadRequestException;
import com.prorider.interfaces.store.IPriceSize;
import com.prorider.repository.store.PriceSizeRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PriceBySizeService implements IPriceSize {

    private final PriceSizeRepository priceSizeRepository;
    private final ProductService productService;
    private final SizeService sizeService;

    public PriceBySizeService (PriceSizeRepository priceSizeRepository, @Lazy ProductService productService, SizeService sizeService){
        this.priceSizeRepository = priceSizeRepository;
        this.productService = productService;
        this.sizeService = sizeService;
    }

    /// Add a list of productSizes to the database, it is used by "productService.addProduct"
    @Override
    public List<PriceBySizeResponse> addProductSize(List<PriceBySizeRequest> request, Product product) {
        List<PriceBySize> priceBySizeList = request.stream()
                .map(req -> {
                    PriceBySize pbs = ToPriceBySizeObj(req);
                    pbs.setProduct(product);
                    return pbs;
                }).toList();

        List<PriceBySize> priceSavedList = priceSizeRepository.saveAll(priceBySizeList);
        return priceSavedList.stream()
                .map(this::ToPriceBySizeResponse)
                .toList();
    }


    @Override
    public PriceBySizeResponse editProductSize(PriceBySizeUpdate update) {
        // priceBySizeId validation
        PriceBySize editObj = priceSizeRepository.findById(update.getPriceBySizeId())
                .orElseThrow(() -> new BadRequestException("priceBySizeId was not found on the database"));

        // Product Validation
       Product product = productService.findProductByIdBaseForm(update.getProductId());

        //make changes
         editObj.setProduct(product);
         editObj.setSize(update.getSize());
         editObj.setPrice(update.getPrice());
         editObj.setQuantity(update.getQuantity());

         PriceBySize priceBySizeSaved = priceSizeRepository.save(editObj);
         return this.ToPriceBySizeResponse(priceBySizeSaved);

    }

    @Override
    public Boolean deleteProductSize(int priceSizeId) {
        // priceBySizeId validation
        PriceBySize editObj = priceSizeRepository.findById(priceSizeId)
                .orElseThrow(() -> new BadRequestException("priceBySizeId was not found on the database"));
        //remove object from the database
        priceSizeRepository.deleteById(priceSizeId);
        return true;
    }

    /// This is general, more for the Admin
    @Override
    public List<PriceBySizeResponse> findAllProductSizes() {
        //Retrieves all the PriceBySize For Admin
        List<PriceBySize> priceBySizeList = priceSizeRepository.findAll();
        //Transform tha "priceBySizeList" type into PriceBySizeResponse
       return  priceBySizeList.stream().map(this::ToPriceBySizeResponse).toList();
    }

    @Override
    public List<PriceBySize> findAllProductSizesBaseForm() {
        //Retrieves all the PriceBySize For Admin
        return priceSizeRepository.findAll();
    }
    /// This is used for retrieve the priceSize individually
    @Override
    public PriceBySizeResponse findPriceSizeById(int priceSizeId) {
        //Retrieves a specific PriceBySize Object base on a "priceSizeId"
        PriceBySize priceBySize = priceSizeRepository.findById(priceSizeId)
                .orElseThrow(() -> new BadRequestException("priceSizeId was not found on the database"));
        //Transform tha "priceBySize" type into PriceBySizeResponse
        return this.ToPriceBySizeResponse(priceBySize);
    }

    /// Used for retrieve al the productBySize objects from the database of a specific productId
    @Override
    public List<PriceBySizeResponse> findProductSizesByProductId(int productId) {
        //Retrieves all the PriceBySize by productId
         List<PriceBySize> priceBySizeList = priceSizeRepository.findAllByProductId(productId);
        //Transform tha "priceBySizeList" type into PriceBySizeResponse
        return priceBySizeList.stream().map(this::ToPriceBySizeResponse).toList();
    }

    @Override
    public List<PriceBySizeResponse> findAllPriceBySizeByStoreId(UUID storeId) {
         List<PriceBySize> priceBySizeList = priceSizeRepository.findAllPriceBySizeByStoreId(storeId);
        return priceBySizeList.stream().map(this::ToPriceBySizeResponse).toList();
    }

    @Override
    public PriceBySizeResponse ToPriceBySizeResponse(PriceBySize priceBySize) {
        /// ProductResponse and SizeResponse Transformation
        ProductResponse productResponse =  productService.ToProductResponse(priceBySize.getProduct());
        SizeResponse sizeResponse = sizeService.ToSizeResponse(priceBySize.getSize());

        /// PriceBySizeResponse Initialization
        PriceBySizeResponse priceSize = new PriceBySizeResponse();
        priceSize.setPriceBySizeId(priceBySize.getPriceBySizeId());
        priceSize.setProduct(productResponse);
        priceSize.setSize(sizeResponse);
        priceSize.setPrice(priceBySize.getPrice());

        return priceSize;
    }

    @Override
    public PriceBySize ToPriceBySizeObj(PriceBySizeRequest request) {
        Product product = productService.findProductByIdBaseForm(request.getProductId());
        /// Size Initialization
        Size size = sizeService.findSizeByIdBaseForm(request.getSizeId());

        PriceBySize priceSize = new PriceBySize();
        priceSize.setSize(size);
        priceSize.setPrice(request.getPrice());
        priceSize.setProduct(product);
        priceSize.setQuantity(request.getQuantity());
        return priceSize;
    }
}
