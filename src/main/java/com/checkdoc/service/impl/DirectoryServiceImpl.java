package com.checkdoc.service.impl;

import com.checkdoc.dao.DirectoryDao;
import com.checkdoc.domain.Directory;
import com.checkdoc.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DirectoryServiceImpl implements DirectoryService {

    @Autowired
    private DirectoryDao directoryDao;

    @Override
    public void add(Directory directory) {
        directoryDao.save(directory);
    }

    @Override
    public void update(Directory directory) {
        directoryDao.save(directory);
    }

    @Override
    public void delete(Directory directory) {
      directoryDao.remove(directory);
    }

    @Override
    public Directory findDirectoryById(Long id) {
        return directoryDao.find(id);
    }

    @Override
    public List<Directory> getAllDirectories() {
        return directoryDao.findAll();
    }
}
