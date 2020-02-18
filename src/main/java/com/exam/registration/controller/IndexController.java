package com.exam.registration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yhf
 * @classname IndexController
 **/
@Controller
public class IndexController {

    @RequestMapping(path = {"/", "index", "index.*"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

}
