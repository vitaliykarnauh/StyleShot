package com.checkdoc.service;

import com.checkdoc.domain.Mistake;

import java.util.List;

public interface MistakeService {


    void add(Mistake mistake);

    void update(Mistake mistake);

    void delete(Mistake mistake);

    Mistake findMistakeById(Long id);

    List<Mistake> getAllMistakes();
}
