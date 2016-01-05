package com.styleshot.upload;

import com.styleshot.domain.User;
import com.styleshot.domain.UserLinks;
import com.styleshot.service.UserLinksService;
import com.styleshot.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class UploadUtils {


    private final static String BASE_URL = "src/main/resources/META-INF/resources/images/";

    public static boolean uploadImage(MultipartFile file, Long userId,
                                      UserService userService, UserLinksService userLinksService) throws Exception {


        if (file.isEmpty()) {
            return false;
        }

        File folderForUser = new File(BASE_URL + userId);
        if (!folderForUser.exists()) {
            folderForUser.mkdir();
        }
        byte[] bytes = file.getBytes();
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(new File(BASE_URL + userId + "/" + file.getOriginalFilename())));
        stream.write(bytes);
        stream.close();
        User fetchedUserById = userService.findUserById(userId);
        UserLinks userLink = new UserLinks(fetchedUserById, file.getOriginalFilename(), false);
        userLinksService.add(userLink);

        return true;
    }
}
