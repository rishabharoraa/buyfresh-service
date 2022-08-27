package com.buyfresh.service.model;

import com.buyfresh.service.POJO.Address;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Document
@NoArgsConstructor
public class Vendor implements UserDetails {

    @Id
    private String id;

    private String name;
    private String email;
    private String password;

    private String mobileNumber;
    private Address address;

    private boolean isVendorNonExpired;
    private boolean isVendorNonLocked;
    private boolean isVendorCredentialsNonExpired;
    private boolean isVendorEnabled;

    @ReadOnlyProperty
    @DBRef
    private List<Item> items;

    public Vendor(String name,
                  String email,
                  String password,
                  String mobileNumber,
                  Address address
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.items = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
