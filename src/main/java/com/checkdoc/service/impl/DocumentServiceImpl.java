package com.checkdoc.service.impl;

import com.checkdoc.dao.DocumentDao;
import com.checkdoc.domain.Document;
import com.checkdoc.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDao documentDao;


    @Override
    public void add(Document document) {
        documentDao.save(document);
    }

    @Override
    public void update(Document document) {
        documentDao.save(document);
    }

    @Override
    public void delete(Document document) {
        documentDao.remove(document);
    }

    @Override
    public Document findDocumentById(Long id) {
        return documentDao.find(id);
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentDao.findAll();
    }

    @Override
    public Document findDocumentByUrl(String url) {
        return documentDao.findDocumentByUrl(url);
    }
}
