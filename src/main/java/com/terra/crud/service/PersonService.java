package com.terra.crud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terra.crud.entity.AddressEntity;
import com.terra.crud.entity.PersonEntity;
import com.terra.crud.entity.filter.PersonEntityFilter;
import com.terra.crud.entity.response.PersonResponsePaged;
import com.terra.crud.repository.PersonDynamicRepository;
import com.terra.crud.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AddressService addressService;

	@Autowired
	private PersonDynamicRepository personDynamicRepository;

	public PersonEntity salvar(PersonEntity person) {

		List<AddressEntity> addresses = new ArrayList<>();

		person.getAddresses().forEach(address -> {
			addresses.add(addressService.findById(address.getId()));
		});

		person.setAddresses(addresses);

		return personRepository.save(person);

	}

	public List<PersonEntity> findAll() {
		return personRepository.findAll();
	}

	public PersonEntity findById(String id) {

		return personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pessoa não existe."));
	}

	public PersonEntity update(String id, PersonEntity person) {

		PersonEntity personEntity = findById(id);

		personEntity.setFullName(Optional.ofNullable(person.getFullName()).orElse(personEntity.getFullName()));
		personEntity.setDocumentId(Optional.ofNullable(person.getDocumentId()).orElse(personEntity.getDocumentId()));
		personEntity.setPhone(Optional.ofNullable(person.getPhone()).orElse(personEntity.getPhone()));

		if (!person.getAddresses().isEmpty()) {
			personEntity.setAddresses(person.getAddresses());
		}

		return salvar(personEntity);
	}

	public void delete(String id) {
		personRepository.deleteById(id);
	}

	public List<PersonEntity> findByPartFullName(String fullName) {
		return personDynamicRepository.findByPartFullName(fullName);
	}

	public PersonResponsePaged findByFilter(PersonEntityFilter filter) {
		return personDynamicRepository.findByFilterAndPagination(filter);
	}

	public List<PersonEntity> findByRangeAge(Integer start, Integer end) {
		return personRepository.findByRangeAge(start, end);//
	}

}
