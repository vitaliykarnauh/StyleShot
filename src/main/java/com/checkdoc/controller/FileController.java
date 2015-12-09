package com.checkdoc.controller;

import com.checkdoc.check.Rule;
import com.checkdoc.domain.Document;
import com.checkdoc.domain.Mistake;
import com.checkdoc.service.*;
import com.checkdoc.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FileController {

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

    private static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

    @RequestMapping(value = "/file-upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam(value = "file", required = false) MultipartFile file, Model model) {
        if (file != null && !file.isEmpty()) {
            try {
                Document document = FileUtil.process(file, directoryService, userService);
                Document dbDocument = documentService.findDocumentByUrl(document.getUrl());
                if (dbDocument == null) {
                    System.out.println("New document is added to db.");
                    documentService.add(document);
                } else {
                    System.out.println("Document is taken from db.");
                    document = dbDocument;
                }
                session().setAttribute("lastDocument", document);
                System.out.println("You successfully uploaded " + file.getOriginalFilename() + "!");
                model.addAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(
                        "You failed to upload " + file.getOriginalFilename() + " ==> " + (e.getMessage() != null ? e.getMessage() : ""));
                model.addAttribute("message",
                        "You failed to upload " + file.getOriginalFilename() + " ==> " + (e.getMessage() != null ? e.getMessage() : ""));
            }
        } else {
            System.out.println("File is empty.");
            model.addAttribute("message", "File is empty.");
        }
        return "index";
    }

    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public String processFile(@RequestParam("font") String font,
                              @RequestParam("fontSize") String fontSizeStr,
                              @RequestParam(value = "indentSize", required = false) String indentSizeStr,
                              @RequestParam(value = "lineSpacing", required = false) String lineSpacingStr,
                              Model model) {
        Document document = (Document) session().getAttribute("lastDocument");
        if (document == null) {
            System.out.println("You haven't uploaded any documents yet.");
            model.addAttribute("message", "You haven't uploaded any documents yet.");
            return "index";
        }
        int fontSize;
        long indentSize;
        float lineSpacing = 0;
        try {
            fontSize = Integer.valueOf(fontSizeStr);
            indentSize = Integer.valueOf(indentSizeStr);
            lineSpacing = Float.valueOf(lineSpacingStr);
        } catch (NumberFormatException e) {
            System.out.println("String cannot be converted to number: " + e.getMessage());
            model.addAttribute("message", "String cannot be converted to number: " + e.getMessage());

            return "index";
        }

        // NO LINE SPACING FIELD
        Rule rule = new Rule(font, fontSize, indentSize);
        List<Mistake> mistakes = FileUtil.findMistakes(document, rule, mistakeService, mistakeTypeService);

        System.out.println("Mistakes found ==> " + mistakes);
        model.addAttribute("mistakes", mistakes);

        return "result";
    }

    @RequestMapping(value = "/pragiarism", method=RequestMethod.POST)
    public String checkPragiarism(){
        return "piracyresult";
    }
}
