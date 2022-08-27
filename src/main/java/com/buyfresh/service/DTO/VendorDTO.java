package com.buyfresh.service.DTO;

import com.buyfresh.service.POJO.Address;
import com.buyfresh.service.model.Item;
import com.buyfresh.service.model.Vendor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
@NoArgsConstructor
public class VendorDTO {

    String name;
    private String email;
    private String password;

    private String mobileNumber;
    private Address address;

    private List<Item> items;

    public VendorDTO(String name,
                     String email,
                     String password,
                     String mobileNumber,
                     Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

    public Vendor toVendor() {
        return new Vendor(
                this.name,
                this.email,
                this.password,
                this.mobileNumber,
                this.address
        );
    }

    public static @NotNull VendorDTO fromVendor(@NotNull Vendor vendor) {
        return new VendorDTO(
                vendor.getName(),
                vendor.getEmail(),
                vendor.getPassword(),
                vendor.getMobileNumber(),
                vendor.getAddress()
        );
    }
}
