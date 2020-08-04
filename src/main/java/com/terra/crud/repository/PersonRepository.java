package com.terra.crud.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.terra.crud.entity.PersonEntity;

@Repository
public interface PersonRepository extends MongoRepository<PersonEntity, String> {

	@Query("{ $and: [ { 'age': { $gte: ?0 } }, { 'age': {$lte: ?1 } } ] }")
	public List<PersonEntity> findByRangeAge(Integer start, Integer end);

}
