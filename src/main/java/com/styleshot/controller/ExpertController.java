package com.styleshot.controller;


import com.google.common.collect.Lists;
import com.styleshot.constant.RoleEnum;
import com.styleshot.domain.User;
import com.styleshot.domain.UserLinks;
import com.styleshot.service.UserLinksService;
import com.styleshot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/expert")
public class ExpertController {


    private static Queue<UserLinks> userLinksQueue = Lists.newLinkedList();

    private static int cursor;

    private final static String BASE_URL = "src/main/resources/META-INF/resources/images/";
    private final static String EXPERT_PAGE = "expertpage";
    private final static String IMAGES = "/images/";
    private static final String REDIRECT_EXPERT_PAGE = "redirect:/expert/mainpage";

    @Autowired
    public UserLinksService userLinksService;

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/mainpage")
    public String showExpertPage(@ModelAttribute("user") User user, Model model) {

        if (CollectionUtils.isEmpty(userLinksQueue)) {
            List<UserLinks> userLinks = userService.getAllUsers().stream()
                    .filter(a -> a.getRole().getName().equalsIgnoreCase(RoleEnum.USER.getRoleName()))
                    .map(User::getUserLinks).flatMap(l -> l.stream()).collect(Collectors.toList());
            userLinks = userLinks.stream().filter(a -> !a.isViewed()).collect(Collectors.toList());
            userLinksQueue = new LinkedList<>(userLinks);
        }


        UserLinks currentUserLink = userLinksQueue.peek();

        model.addAttribute("userId", currentUserLink.getUser().getId());
        model.addAttribute("imageName", currentUserLink.getFileName());
        model.addAttribute("imagePath", getImagePath(currentUserLink));
        return EXPERT_PAGE;
    }


    @RequestMapping(value = "/result/{userId}")
    public String gettingDescriptionFromExpert(@RequestParam("imageName") String imageName,
                                               @RequestParam("description") String description,
                                               @RequestParam("links") String links,
                                               @PathVariable String userId,
                                               @RequestParam("expertId") Long expertId,
                                               RedirectAttributes redirectAttributes
    ) {

        List<UserLinks> userLinks = userService.findUserById(Long.valueOf(userId)).getUserLinks();
        Optional<UserLinks> optionalUserLink = userLinks.stream()
                .filter(a -> a.getFileName().equalsIgnoreCase(imageName))
                .findFirst();

        if (optionalUserLink.isPresent()) {
            UserLinks userLink = optionalUserLink.get();
            userLink.setViewed(true);
        }
        redirectAttributes.addFlashAttribute("user", userService.findUserById(expertId));
        return REDIRECT_EXPERT_PAGE;
    }

    private synchronized String getImagePath(UserLinks userLink) {

        String pathToImage = IMAGES + userLink.getUser().getId() + "/" + userLink.getFileName();
        return pathToImage;
    }


}
