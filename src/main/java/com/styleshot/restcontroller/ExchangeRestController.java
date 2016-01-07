package com.styleshot.restcontroller;


import com.styleshot.service.UserLinksService;
import com.styleshot.service.UserService;
import com.styleshot.upload.UploadUtils;
import net.arnx.wmf2svg.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.websocket.server.PathParam;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

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
    public
    @ResponseBody
    ResponseEntity<Void> loadImage(@RequestParam(value = "file", required = false) MultipartFile file,
                                   @RequestParam(value = "userId", required = false) Long userId) {


        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            boolean isUploaded = UploadUtils.uploadImage(file, userId, userService, userLinksService);

            if (isUploaded) {
                httpHeaders.add("isUploaded", "true");
                return new ResponseEntity<Void>(httpHeaders, HttpStatus.ACCEPTED);
            } else {
                httpHeaders.add("isUploaded", "false");
                return new ResponseEntity<Void>(httpHeaders, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            httpHeaders.add("isUploaded", "false");
            return new ResponseEntity<Void>(httpHeaders, HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/images/{userId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getImagesForUser(@PathVariable Long userId, @PathParam("imageName") String imageName) throws Exception {
        return Base64.encode(getImagebyName(imageName, userId));
    }


    private byte[] getImagebyName(String imageName, Long userId) throws IOException {

        File imgPath = new File(BASE_URL + userId + "/" + imageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        WritableRaster raster = bufferedImage.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

        return data.getData();

    }
}
