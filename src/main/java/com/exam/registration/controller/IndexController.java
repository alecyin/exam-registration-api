package com.exam.registration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yhf
 * @classname IndexController
 * @description TODO
 * @date 2019/11/27
 **/
@Controller
public class IndexController {

    @RequestMapping(path = {"/", "index", "index.*"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

}
