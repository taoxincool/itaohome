package com.itaohome.web;

import com.itaohome.repository.AutoReplyRepository;
import com.itaohome.repository.LogRepository;
import com.itaohome.service.WeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
 * Created by Mr tao on 2015/1/13.
 */
@Controller
public class HomeController {

    @Resource
    private LogRepository logRepository;

    @Resource
    private WeService weService;

    @Resource
    private AutoReplyRepository autoReplyRepository;

    @RequestMapping(value = "/")
    public String welcome() {
        return "home";
    }
}
