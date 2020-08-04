package com.terra.crud.entity.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntityFilter {

	private int size;

	private int page;

	private String phone;

	private String fullName;

	private Integer documentId;

}
