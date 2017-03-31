package com.keveon.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Keveon on 2017/3/20.
 * .
 */
@Slf4j
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController {
    @GetMapping(value = "login.html", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/authentication-failure")
    public ResponseEntity<String> apiAuthenticationFailure() {
        return new ResponseEntity<>("{\"success\" : false, \"message\" : \"authentication-failure\"}", HttpStatus.OK);
    }

    @GetMapping(value = "/authentication-success")
    public ResponseEntity<String> apiDefaultTarget() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userJson = JSON.toJSONString(
                authentication,
                (PropertyFilter) (object, name, value) -> !(object instanceof Class || name.endsWith(".password"))
        );
        return new ResponseEntity<>(userJson, HttpStatus.OK);
    }
}