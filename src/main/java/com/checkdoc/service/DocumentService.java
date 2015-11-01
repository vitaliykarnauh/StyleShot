package com.checkdoc.service;

import com.checkdoc.domain.Document;

import java.util.List;

public interface DocumentService {

    void add(Document document);

    void update(Document document);

    void delete(Document document);

    Document findDocumentById(Long id);

    List<Document> getAllDocuments();
}
