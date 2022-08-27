package com.buyfresh.service.repository;

import com.buyfresh.service.model.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VendorRepository extends MongoRepository<Vendor, String> {
    Optional<Vendor> findByEmail(String emailId);
}
