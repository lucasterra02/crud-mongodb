package com.terra.crud.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.terra.crud.entity.PersonEntity;
import com.terra.crud.entity.filter.PersonEntityFilter;
import com.terra.crud.entity.response.PersonResponsePaged;

@Repository
public class PersonDynamicRepository {

	@Autowired
	private MongoOperations mongoOperations;

	public PersonResponsePaged findByFilterAndPagination(PersonEntityFilter filter) {

		Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize(), Sort.by("fullName").descending());

		Query patientsDynamicQuery = new Query().with(pageable);

		addRestrictions(filter, patientsDynamicQuery);

		List<PersonEntity> filteredPatients = mongoOperations.find(patientsDynamicQuery, PersonEntity.class);
		Page<PersonEntity> personEntityPage = PageableExecutionUtils.getPage(filteredPatients, pageable,
				() -> mongoOperations.count(patientsDynamicQuery.limit(-1).skip(-1), PersonEntity.class));

		return PersonResponsePaged.builder().numberOfElements(personEntityPage.getNumberOfElements())
				.sizeList(personEntityPage.getSize()).totalElements(personEntityPage.getTotalElements())
				.totalPages(personEntityPage.getTotalPages()).pageNumber(personEntityPage.getNumber())
				.last(personEntityPage.isLast()).persons(personEntityPage.getContent()).build();

	}

	private void addRestrictions(PersonEntityFilter filter, Query patientsDynamicQuery) {

		if (Objects.nonNull(filter.getFullName())) {
			Criteria criteria = Criteria.where("fullName").regex(".*" + filter.getFullName() + ".*", "i");
			patientsDynamicQuery.addCriteria(criteria);
		}

		if (Objects.nonNull(filter.getPhone())) {
			Criteria criteria = Criteria.where("phone").regex(filter.getPhone());
			patientsDynamicQuery.addCriteria(criteria);
		}

		if (Objects.nonNull(filter.getDocumentId())) {
			Criteria criteria = Criteria.where("documentId").regex(filter.getPhone());
			patientsDynamicQuery.addCriteria(criteria);
		}
	}
	
	public List<PersonEntity> findByPartFullName(String fullName) {

		Criteria regex = Criteria.where("fullName").regex(".*" + fullName + ".*", "i");
		return mongoOperations.find(new Query().addCriteria(regex), PersonEntity.class);

	}

}
