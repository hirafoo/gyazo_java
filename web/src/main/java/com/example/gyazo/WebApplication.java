package com.example.gyazo;

import com.example.gyazo.service.ImageService;
import com.example.gyazo.entity.Image;
import com.example.gyazo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.MessageDigest;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;

@RestController
@SpringBootApplication
public class WebApplication {

    @Autowired
    ImageService imageService;

    @Value("${hoge}")
    private String hoge;

    private static Logger log = LoggerFactory.getLogger(WebApplication.class);

    @RequestMapping("/")
    String index() {
        Date date = new Date();
        String now = date.toString();
        log.info("get index " + hoge);
        return "web " + now;
    }

    @RequestMapping("/r")
    void handleFoo(HttpServletResponse response) throws IOException {
        log.info("redirect to /");
        response.sendRedirect("/");
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    String upload(
            @RequestParam("imagedata") MultipartFile multipartFile
    ) {
        log.info("original file is " + multipartFile.getOriginalFilename());
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");

            byte[] bytes = multipartFile.getBytes();
            byte[] hash = digest.digest(bytes);
            String returnUrl = imageService.createByBytes(bytes, hash);
            return returnUrl;
        } catch (Exception e) {
            // 残念でした
        } catch (Throwable t) {
            // 残念でした
        }
        return "/fail";
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
