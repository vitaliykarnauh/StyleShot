package com.styleshot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


@Controller
public class UploadController {


    private final static String BASE_URL = "images/";
    private final static String MESSAGE_ATTR = "message";

    @RequestMapping(value = "/file-upload", method = RequestMethod.POST)
    public String loadImage(@RequestParam(value = "file", required = false) MultipartFile file,
                            @RequestParam(value = "userId", required = false) String userId,
                            Model model) {


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


                model.addAttribute(MESSAGE_ATTR, "You successfully uploaded " + name + "!");
            } catch (Exception e) {
                model.addAttribute(MESSAGE_ATTR, "You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            model.addAttribute(MESSAGE_ATTR, "You failed to upload " + name + " because the file was empty.");
        }
        return "userpage";
    }
}
