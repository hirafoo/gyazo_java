package com.example.gyazo.controller;

import com.example.gyazo.AdminApplication;
import com.example.gyazo.entity.Image;
import com.example.gyazo.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ImageController {

    @Autowired
    ImageService imageService;


    private static Logger log = LoggerFactory.getLogger(AdminApplication.class);

    @RequestMapping("/image/")
    public String index(Model model) {
        final List<Image> imageList = imageService.selectAll();
        model.addAttribute("images", imageList);
        log.info(imageList.toString());
        return "image/index";
    }

    //    @RequestMapping(value="/image/detail", method = RequestMethod.GET)
    //    public String detail(@RequestParam(value="id") Integer id, Model model) {
    @RequestMapping("/image/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        final Image image = imageService.selectOne(id);
        model.addAttribute("image", image);
        return "image/detail";
    }
}
