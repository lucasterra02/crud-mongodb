package com.terra.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terra.crud.entity.AddressEntity;
import com.terra.crud.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	public AddressEntity salvar(AddressEntity address) {
		return addressRepository.save(address);
	}

}
