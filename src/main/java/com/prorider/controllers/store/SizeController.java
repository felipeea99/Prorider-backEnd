package com.prorider.controllers.store;

import com.prorider.DTOs.request.store.SizeRequest;
import com.prorider.DTOs.response.store.SizeResponse;
import com.prorider.DTOs.update.store.SizeUpdate;
import com.ecommerce.prorider.services.store.SizeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/size")
public class SizeController {

    private final SizeService sizeService;

    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @PostMapping
    public SizeResponse addSize(@RequestBody SizeRequest sizeRequest){
        return this.sizeService.addSize(sizeRequest);
    }

    @PutMapping
    public SizeResponse editSize(@RequestBody SizeUpdate sizeUpdate){
        return this.sizeService.editSize(sizeUpdate);
    }

    @DeleteMapping("/{deleteId}")
    public Boolean deleteSize(@PathVariable int deleteId){
        return this.sizeService.deleteSize(deleteId);
    }

    @GetMapping
    public List<SizeResponse> findAllSizes(){
        return this.sizeService.findAllSizes();
    }

}
