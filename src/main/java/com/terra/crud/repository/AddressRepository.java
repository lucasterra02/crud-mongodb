package com.terra.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.terra.crud.entity.AddressEntity;

@Repository
public interface AddressRepository extends MongoRepository<AddressEntity, String> {

}
