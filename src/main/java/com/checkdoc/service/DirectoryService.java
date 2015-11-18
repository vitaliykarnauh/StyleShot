package com.checkdoc.service;

import com.checkdoc.domain.Directory;
import com.checkdoc.domain.Role;

import java.util.List;

public interface DirectoryService {

    void add(Directory directory);

    void update(Directory directory);

    void delete(Directory directory);

    Directory findDirectoryById(Long id);

    List<Directory> getAllDirectories();

    Directory findDirectoryByUrl(String url);

}
