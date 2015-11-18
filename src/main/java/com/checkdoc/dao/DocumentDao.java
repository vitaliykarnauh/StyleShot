package com.checkdoc.dao;

import com.checkdoc.domain.Document;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface DocumentDao extends GenericDAO<Document, Long> {

    Document findDocumentByUrl(String url);
}
