package com.checkdoc.controller;

import com.checkdoc.check.Rule;
import com.checkdoc.domain.Document;
import com.checkdoc.service.*;
import com.checkdoc.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @Autowired
    private DocumentService documentService;
    @Autowired
    private DirectoryService directoryService;
    @Autowired
    private MistakeService mistakeService;
    @Autowired
    private MistakeTypeService mistakeTypeService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam(value = "file", required = false) MultipartFile file, Model model) {
        if (file != null && !file.isEmpty()) {
            try {
                Document document = FileUtil.process(file, directoryService, userService);
                if (documentService.findDocumentByUrl(document.getUrl()) == null) {
                    documentService.add(document);
                }
                //rule mock
                Rule rule = new Rule("Times New Roman", 14, 2L);
                System.out.println("Mistakes found ==> " + FileUtil.findMistakes(document, rule, mistakeService));
                System.out.println("You successfully uploaded " + file.getOriginalFilename() + "!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(
                        "You failed to upload " + file.getOriginalFilename() + " ==> " + (e.getMessage() != null ? e.getMessage() : ""));
            }
        } else {
            System.out.println("File is empty.");
        }
        return "index";
    }
}
