package com.buyfresh.service.service;

import com.buyfresh.service.DTO.VendorDTO;
import com.buyfresh.service.model.Vendor;
import com.buyfresh.service.repository.VendorRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class VendorService implements UserDetailsService {

    private final VendorRepository vendorRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor getVendor(final String id) throws Exception {
        return vendorRepository
                .findById(id)
                .orElseThrow(
                        () -> new Exception("Vendor not found")
                );
    }

    @Transactional(rollbackFor = Exception.class)
    public String createVendor(@NotNull final VendorDTO vendorDTO) {
        Vendor vendor = vendorDTO.toVendor();
        vendor.setPassword(passwordEncoder.encode(vendor.getPassword()));
        return vendorRepository
                .save(vendor)
                .getEmail();
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        return vendorRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(email)
                );
    }
}
