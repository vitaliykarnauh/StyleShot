package com.checkdoc.service.impl;

import com.checkdoc.dao.MistakeTypeDao;
import com.checkdoc.domain.MistakeType;
import com.checkdoc.service.MistakeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MistakeTypeServiceImpl implements MistakeTypeService {

	@Autowired
	private MistakeTypeDao mistakeTypeDao;

	@Override
	public void add(MistakeType mistakeType) {
		mistakeTypeDao.save(mistakeType);
	}

	@Override
	public void update(MistakeType mistakeType) {
		mistakeTypeDao.save(mistakeType);
	}

	@Override
	public void delete(MistakeType mistakeType) {
		mistakeTypeDao.remove(mistakeType);
	}

	@Override
	public MistakeType findMistakeTypeById(Long id) {
		return mistakeTypeDao.find(id);
	}

	@Override
	public List<MistakeType> getAllMistakeTypes() {
		return mistakeTypeDao.findAll();
	}

	@Override
	public MistakeType findMistakeTypeByName(String name) {
		return mistakeTypeDao.findByMistakeTypeName(name);
	}
}
