package com.terra.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.terra.crud.entity.PersonEntity;
import com.terra.crud.entity.filter.PersonEntityFilter;
import com.terra.crud.entity.response.PersonResponsePaged;
import com.terra.crud.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping
	public ResponseEntity<PersonEntity> save(@RequestBody PersonEntity person) {
		PersonEntity response = personService.salvar(person);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PersonEntity> update(@PathVariable final String id, @RequestBody PersonEntity person) {
		PersonEntity response = personService.update(id, person);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<List<PersonEntity>> findAll() {
		List<PersonEntity> response = personService.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PersonEntity> findById(@PathVariable final String id) {
		PersonEntity response = personService.findById(id);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(value = "/fullName")
	public ResponseEntity<List<PersonEntity>> findByPartFullName(@RequestParam("fullName") final String fullName) {
		List<PersonEntity> response = personService.findByPartFullName(fullName);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable final String id) {
		personService.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/findByFilter")
	public ResponseEntity<PersonResponsePaged> findAllPagination(PersonEntityFilter filter) {
		PersonResponsePaged response = personService.findByFilter(filter);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
