package com.buyfresh.service.controller;

import com.buyfresh.service.DTO.VendorDTO;
import com.buyfresh.service.model.Vendor;
import com.buyfresh.service.service.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/vendor")
public class VendorController {

    private final VendorService vendorService;

//    @GetMapping
//    public List<VendorDTO> getAllVendors() {
//        return vendorService
//                .getAllVendors()
//                .stream()
//                .map(VendorDTO::fromVendor)
//                .toList();
//    }

    @GetMapping("/{vendorId}")
    public VendorDTO getVendor(final String email) throws Exception {
        Vendor vendor = (Vendor) vendorService.loadUserByUsername(email);
        return VendorDTO.fromVendor(vendor);
    }


    @PostMapping
    public String createVendor(@RequestBody final VendorDTO vendorDTO) {
        return vendorService.createVendor(vendorDTO);
    }
}
