package com.terra.crud.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "person")
public class PersonEntity {

	@Id
	private String id;

	@Indexed(unique = true)
	private String phone;

	private String fullName;

	private Integer documentId;

	private Integer age;

	@DBRef
	@Field("address")
	private List<AddressEntity> addresses = new ArrayList<>();

}
