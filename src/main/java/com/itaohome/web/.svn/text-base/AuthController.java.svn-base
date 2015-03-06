package com.itaohome.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

/**
 * Created by Mr tao on 2015/1/27.
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(String message) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/auth/login");
        return mv;
    }

}
