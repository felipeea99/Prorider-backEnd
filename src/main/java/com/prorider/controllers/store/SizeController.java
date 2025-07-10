package com.prorider.controllers.store;

import com.prorider.DTOs.request.store.SizeRequest;
import com.prorider.DTOs.response.store.SizeResponse;
import com.prorider.DTOs.update.store.SizeUpdate;
import com.prorider.services.store.SizeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/size")
public class SizeController {

    private final com.prorider.services.store.SizeService sizeService;

    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @PostMapping("/")
    public SizeResponse addSize(@Valid  @RequestBody SizeRequest sizeRequest){
        return this.sizeService.addSize(sizeRequest);
    }

    @PutMapping("/")
    public SizeResponse editSize(@Valid @RequestBody SizeUpdate sizeUpdate){
        return this.sizeService.editSize(sizeUpdate);
    }

    @DeleteMapping("/{deleteId}")
    public Boolean deleteSize(@PathVariable int deleteId){
        return this.sizeService.deleteSize(deleteId);
    }

    @GetMapping("/allSizes")
    public List<SizeResponse> findAllSizes(){
        return this.sizeService.findAllSizes();
    }

}
