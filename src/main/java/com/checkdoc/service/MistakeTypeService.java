package com.checkdoc.service;

import com.checkdoc.domain.MistakeType;

import java.util.List;

public interface MistakeTypeService {

	void add(MistakeType mistakeType);

	void update(MistakeType mistakeType);

	void delete(MistakeType mistakeType);

	MistakeType findMistakeTypeByName(String name);

	MistakeType findMistakeTypeById(Long id);

	List<MistakeType> getAllMistakeTypes();
}
