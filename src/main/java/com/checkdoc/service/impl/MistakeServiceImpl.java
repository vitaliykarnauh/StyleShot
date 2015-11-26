package com.checkdoc.service.impl;

import com.checkdoc.dao.MistakeDao;
import com.checkdoc.domain.Mistake;
import com.checkdoc.service.MistakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MistakeServiceImpl implements MistakeService{

    @Autowired
    private MistakeDao mistakeDao;


    @Override
    public void add(Mistake mistake) {
        mistakeDao.save(mistake);
    }

    @Override
    public void update(Mistake mistake) {
        mistakeDao.save(mistake);
    }

    @Override
    public void delete(Mistake mistake) {
        mistakeDao.save(mistake);
    }

    @Override
    public Mistake findMistakeById(Long id) {
        return mistakeDao.find(id);
    }

    @Override
    public List<Mistake> getAllMistakes() {
        return mistakeDao.findAll();
    }
}
