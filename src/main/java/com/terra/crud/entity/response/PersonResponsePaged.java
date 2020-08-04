package com.terra.crud.entity.response;

import java.util.List;

import com.terra.crud.entity.PersonEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponsePaged {

	private long totalElements;
	private int totalPages;
	private int sizeList;
	private int numberOfElements;
	private int pageNumber;
	private boolean last;

	private List<PersonEntity> persons;

}