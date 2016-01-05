package com.styleshot.controller;

import com.styleshot.service.UserLinksService;
import com.styleshot.service.UserService;
import com.styleshot.upload.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UploadController {

    private final static String MESSAGE_ATTR = "message";

    @Autowired
    public UserLinksService userLinksService;

    @Autowired
    public UserService userService;


    @RequestMapping(value = "/file-upload", method = RequestMethod.POST)
    public String loadImage(@RequestParam(value = "file", required = false) MultipartFile file,
                            @RequestParam(value = "userId", required = false) Long userId,
                            Model model) {


        try {
            boolean isUploaded = UploadUtils.uploadImage(file, userId, userService, userLinksService);

            if (isUploaded) {
                model.addAttribute(MESSAGE_ATTR, "You successfully uploaded " + file.getOriginalFilename() + " !");
            } else {
                model.addAttribute(MESSAGE_ATTR, "You failed to upload, because the file was empty.");
            }
        } catch (Exception e) {
            model.addAttribute(MESSAGE_ATTR, "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage());
        }
        model.addAttribute("userId", userId);
        return "userpage";
    }
}
