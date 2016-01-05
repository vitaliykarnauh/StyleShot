package com.styleshot.restcontroller;


import com.styleshot.domain.User;
import com.styleshot.domain.UserLinks;
import com.styleshot.service.UserLinksService;
import com.styleshot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping(value = "/rest/exchange")
public class ExchangeRestController {


    private final static String BASE_URL = "src/main/resources/META-INF/resources/images/";
    private final static String MESSAGE_ATTR = "message";

    @Autowired
    private UserService userService;

    @Autowired
    private UserLinksService userLinksService;

    @RequestMapping(value = "/fileupload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> loadImage(@RequestParam(value = "file", required = false) MultipartFile file,
                                          @RequestParam(value = "userId", required = false) String userId) {

        String name = "";
        if (!file.isEmpty()) {
            try {
                File folderForUser = new File(BASE_URL + userId);
                if (!folderForUser.exists()) {
                    folderForUser.mkdir();
                }
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(BASE_URL + userId + "/" + file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
                User fetchedUserById = userService.findUserById(Long.parseLong(userId));
                UserLinks userLink = new UserLinks(fetchedUserById, file.getOriginalFilename(), false);
                userLinksService.add(userLink);


            } catch (Exception e) {

            }
        } else {

        }

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }
}
