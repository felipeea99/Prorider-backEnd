package com.prorider.services.store;

import com.prorider.DTOs.request.store.ProductDetailsRequest;
import com.prorider.DTOs.request.store.ProductRequest;
import com.prorider.DTOs.response.store.*;
import com.prorider.DTOs.update.store.ProductUpdate;
import com.prorider.entities.store.Category;
import com.prorider.entities.store.PriceBySize;
import com.prorider.entities.store.Product;
import com.prorider.exceptions.BadRequestException;
import com.prorider.interfaces.store.IProduct;
import com.prorider.repository.store.ProductRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProduct {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final PriceBySizeService priceBySizeService;
    private final PhotoService photoService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService, PriceBySizeService priceBySizeService, PhotoService photoService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.priceBySizeService = priceBySizeService;
        this.photoService = photoService;
    }

    ///Add Product Object
    @Override
    public ProductDetailsResponse addProduct(ProductDetailsRequest prodSizeRequest) {
        /// To get the productRequest object more clear
        ProductRequest productRequest = prodSizeRequest.getProductRequest();
        /// Get the "category" object
        Category category = categoryService.findCategoryByIdBaseForm(productRequest.getCategoryId());
        /// Create the Product object
        Product product = new Product();
        product.setActive(productRequest.isActive());
        product.setCategory(category);
        product.setProductName(productRequest.getProductName());
        product.setHasSizes(productRequest.isHasSizes());
        /// Price & Quantity Configuration
        if (!productRequest.isHasSizes()){
            product.setPrice(productRequest.getPrice());
            product.setQuantity(productRequest.getQuantity());
        }else{
            /// product.quantity(0) due to is going priceBySize object will have quantity and price = null
            product.setPrice(null);
            product.setQuantity(0);
        }
        /// Save the product on the database and store it on "productSaved"
        Product productSaved = productRepository.save(product);
        /// Photos, save all the photos once the product products has been saved
        List<PhotoResponse> photoResponse = photoService.addMultiplePhotos(prodSizeRequest.getPhotosRequests());
        /// Price By Size List, this method receives a list of priceBySizeRequest and save it into the database
        List<PriceBySizeResponse> priceBySizeResponse =  priceBySizeService.addProductSize(prodSizeRequest.getPriceBySizeRequest(),productSaved);
        ///ProductDetailsResponse initialization and transformations
        ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();
        productDetailsResponse.setProductResponse(this.ToProductResponse(productSaved));
        productDetailsResponse.setPhotoList(photoResponse);
        productDetailsResponse.setPriceBySizeResponseList(priceBySizeResponse);
        return productDetailsResponse ;
    }

    ///Edit ProductSingle Objects
    @Override
    public ProductResponse editProduct(ProductUpdate productUpdate) {
        //productId validation
        Product product = productRepository.findById(productUpdate.getProductId())
                .orElseThrow(() ->new BadRequestException("productId was not found on the database"));

        //categoryId validation
        Category category = categoryService.findCategoryByIdBaseForm(productUpdate.getCategoryId());

        //Edit Changes
        product.setCategory(category);
        product.setProductName(productUpdate.getProductName());
        product.setActive(productUpdate.isActive());
        product.setQuantity(product.getQuantity());
        product.setHasSizes(productUpdate.isHasSizes());
        product.setDescription(productUpdate.getDescription());
        //if it is true
        if (productUpdate.isHasSizes()){
            product.setPrice(null);
        //if not
        }else{
            product.setPrice(productUpdate.getPrice());
        }
        // Save changes on the database and stored it on "productSaved"
        Product productSaved = productRepository.save(product);
        //transform "productSaved" into ProductResponse type
        return this.ToProductResponse(productSaved);
    }
    ///Delete ProductSingle Objects
    @Override
    public Boolean deleteProduct(int productId) {
        //productId validation
        productRepository.findById(productId)
                .orElseThrow(() -> new BadRequestException("productId was not found on the database"));
        //remove the product object from the database
        productRepository.deleteById(productId);
        return true;
    }

    /// Used when an item is bought, reduce the quantity value
    @Override
    public ProductResponse decreaseProductByOne(Product product) {
       Product productObj = this.findProductByIdBaseForm(product.getProductId());
       if(product.getQuantity() == 0){
           return null;
       }
       productObj.setQuantity(product.getQuantity()-1);
       productRepository.save(productObj);
       return ToProductResponse(productObj);
    }

    ///Retrieve All ProductSingle Objects for Admin Calls
    @Override
    public List<ProductResponse> getAllProducts() {
        //Retrieve all the product from the database (for Admin)
        List<Product> productList = productRepository.findAll();
        //Convert the "productList" into ProductResponse type
         return productList.stream().map(this::ToProductResponse).toList();
    }


    /// Retrieve a ProductSingle object by productId
    @Override
    public ProductResponse findProductById(int productId) {
        //productId validation and search
          Product product =  productRepository.findById(productId)
                .orElseThrow(()->new BadRequestException("productId not found on the database"));
          //Transform it into productResponse type
          return this.ToProductResponse(product);
    }
    /// This is like the other findProductById, but this one return the base Product form
    @Override
    public Product findProductByIdBaseForm(int productId) {
        return productRepository.findById(productId).orElseThrow(()-> new BadRequestException("productId was not found on the database"));
    }

    /// Retrieve all products objects base on the categoryId and the storeId
    @Override
    public List<ProductResponse> getProductsByCategoryId(int categoryId, UUID storeId) {
        return productRepository.findAll()
                        .stream()
                        .filter(product -> product.getCategory().getCategoryId() == categoryId)
                        .map(this::ToProductResponse)
                        .toList();
    }

    /// Retrieve the product objects base on the categoryId and the storeId and Mix them to have random order
    @Override
    public List<ProductResponse> getProductsRandomByCategory(int categoryId, UUID storeId) {
    List<ProductResponse>productResponse = productRepository.findAll()
                    .stream()
                    .filter(product -> product.getCategory().getCategoryId() == categoryId)
                    .map(this::ToProductResponse)
                    .collect(Collectors.toList()); // mutable list
                    Collections.shuffle(productResponse); //mixes the objects

        return productResponse;
    }

    /// This method retrieves a "product" object and a list of photos, priceBySize by using a "productId", useful to show useful for show the data to the customer on the product information interface
    @Override
    public ProductDetailsResponse findProductDetailsByProductId(int productId) {
        /// Find the "Product" by productId
         ProductResponse productResponse =  this.findProductById(productId);
         /// Find the Photos by  productId
        List<PhotoResponse> photoResponseList = photoService.getAllPhotosByProductID(productId);
        /// Find the PriceBySize by productId
        List<PriceBySizeResponse> priceBySizeResponseList = priceBySizeService.findProductSizesByProductId(productId);
        /// ProductDetailsResponse Initialization
        ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();
        productDetailsResponse.setProductResponse(productResponse);
        productDetailsResponse.setPriceBySizeResponseList(priceBySizeResponseList);
        productDetailsResponse.setPhotoList(photoResponseList);
        return productDetailsResponse;
    }

    @Override
    public ByteArrayOutputStream listProductsExcel() {
            try (Workbook workbook = new HSSFWorkbook()) {
                /// Products with Single Price
                Sheet productSheet = workbook.createSheet("Productos");

                // Headers
                Row header = productSheet.createRow(0);
                header.createCell(0).setCellValue("Nombre del Producto");
                header.createCell(1).setCellValue("Activo/Inactivo");
                header.createCell(2).setCellValue("Precio");
                header.createCell(3).setCellValue("Categoría");

                // Get the products that its price is no 0
                List<Product> productList = productRepository.findAll()
                        .stream().filter(product -> product.getPrice() != null)
                                .toList();

                /// Data
                int rowCount = 1;
                for (Product product : productList) {
                    Row dataRow = productSheet.createRow(rowCount++);
                    dataRow.createCell(0).setCellValue(product.getProductName());
                    dataRow.createCell(1).setCellValue(product.isActive() ? "Activo" : "Inactivo");
                    dataRow.createCell(2).setCellValue(product.getPrice().doubleValue());
                    dataRow.createCell(3).setCellValue(product.getCategory().getCategoryName());
                }

                //Auto-just
                for (int i = 0; i <= 3; i++) {
                    productSheet.autoSizeColumn(i);
                }
                /// File Name
                String fileName = "Productos"+ ".xls";

                /// ProductSize

                //retrieves all the priceBySize objects
                List<PriceBySize> priceBySizeList = priceBySizeService.findAllProductSizesBaseForm();

                Sheet productSizeSheet = workbook.createSheet("Productos y Tallas");

                // Headers
                Row header2 = productSizeSheet.createRow(0);
                header2.createCell(0).setCellValue("Nombre del Producto");
                header2.createCell(1).setCellValue("Activo/Inactivo");
                header2.createCell(2).setCellValue("Categoría");
                header2.createCell(3).setCellValue("Talla y Precio");


                /// Data PriceBySize
                int rowCountPs = 1;
                for (PriceBySize ps : priceBySizeList) {
                    Row dataRow = productSizeSheet.createRow(rowCountPs++);
                    dataRow.createCell(0).setCellValue(ps.getProduct().getProductName());
                    dataRow.createCell(1).setCellValue(ps.getProduct().isActive() ? "Activo" : "Inactivo");
                    dataRow.createCell(2).setCellValue(ps.getProduct().getCategory().getCategoryName());
                    dataRow.createCell(3).setCellValue(ps.getSize().getSize().concat(" : ").concat(String.valueOf(ps.getPrice())));
                }

                //Auto-just
                for (int i = 0; i <= 3; i++) {
                    productSheet.autoSizeColumn(i);
                }

                //Save document
                try (FileOutputStream fos = new FileOutputStream(fileName)) {
                    workbook.write(fos);
                }
                // Create ByteArrayOutputStream to save the Excel on the memory
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);

                return outputStream;

            } catch (Exception e) {
                throw new RuntimeException("Error generating  Excel", e);
            }
    }

    @Override
    public ProductResponse ToProductResponse(Product product) {
        /// "CategoryResponse" Initialization
        CategoryResponse categoryResponse = categoryService.ToCategoryResponse(product.getCategory());
        /// PriceBySize - List
       List<PriceBySizeResponse> priceBySize = priceBySizeService.findProductSizesByProductId(product.getProductId());
        /// "ProductResponse" Initialization
        ProductResponse response = new ProductResponse();
        response.setProductId(product.getProductId());
        response.setProductName(product.getProductName());
        response.setActive(product.isActive());
        response.setQuantity(product.getQuantity());
        response.setCategory(categoryResponse);
        response.setHasSizes(product.isHasSizes());
        response.setPrice(product.getPrice());
        response.setPricesBySize(priceBySize);

        return response;
    }

}
