package com.checkdoc.util;

import com.checkdoc.domain.Directory;
import com.checkdoc.domain.Document;
import com.checkdoc.domain.Role;
import com.checkdoc.domain.User;
import com.checkdoc.service.DirectoryService;
import com.checkdoc.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Date;
import java.util.HashSet;

public final class FileUtil {

    public static final String PATH_DOCUMENTS = "temp/documents/";

    public static Document process(MultipartFile mFile, DirectoryService directoryService, UserService userService) {
        // current user mock
        User user = userService.findUserById(1L);
        if (user == null) {
            throw new IllegalArgumentException("User with id 1 was not found. Create a user before uploading a file.");
        }

        String directoryUrl = new File(PATH_DOCUMENTS + user.getId()).getAbsolutePath();
        Directory directory = directoryService.findDirectoryByUrl(directoryUrl);

        if (directory == null) {
            directory = getDirectory(user);
            directoryService.add(directory);
            System.out.println("Adding new directory...");
        }

        String filePath = directory.getUrl() + "\\" + mFile.getOriginalFilename();
        File file = createFile(mFile, filePath);

        return new Document(filePath, getCreationTime(file), directory);
    }

    private static File createFile(MultipartFile mFile, String filePath) {
        File file = new File(filePath);

        String ext = getExtension(file.getName());
        if (!(ext.equalsIgnoreCase("docx") || ext.equalsIgnoreCase("doc"))) {
            throw new IllegalArgumentException("Only doc/docx files allowed.");
        }

        long fileSize = mFile.getSize();
        if (fileSize > (1024 * 1024)) {
            throw new IllegalArgumentException("File size should be less than 2048 kB. Provided: " + (fileSize / 1024) + "kB.");
        }
        System.out.println("size ==> " + fileSize);
        System.out.println("cannot exceed " + (1024 * 1024));

        try {
            mFile.transferTo(file);
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    private static Directory getDirectory(User user) {
        File file = new File(PATH_DOCUMENTS + user.getId() + "/");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                System.out.println("Cannot create folder ==> " + file.getAbsolutePath());
            }
        }

        return new Directory(file.getAbsolutePath(), getCreationTime(file), user);
    }

    private static Date getCreationTime(File file) {
        Path p = Paths.get(file.getAbsolutePath());
        BasicFileAttributes view = null;
        try {
            view = Files.getFileAttributeView(p, BasicFileAttributeView.class)
                    .readAttributes();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException while trying to get creation time.");
        }
        FileTime fileTime = view.creationTime(); // lastAccessTime, lastModifiedTime
        return new Date(fileTime.toMillis());
    }

    private static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
    }
}