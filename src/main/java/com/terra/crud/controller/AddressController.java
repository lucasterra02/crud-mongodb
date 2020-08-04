package com.terra.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.terra.crud.entity.AddressEntity;
import com.terra.crud.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService service;

	@PostMapping
	public ResponseEntity<AddressEntity> save(@RequestBody AddressEntity address) {
		AddressEntity response = service.salvar(address);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
