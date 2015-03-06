package com.itaohome.web;

import com.itaohome.model.TbLog;
import com.itaohome.model.TbMessage;
import com.itaohome.repository.CommonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Mr tao on 2015/1/13.
 */
@Controller
@RequestMapping(value = "/m")
public class MessageController {

    @Resource
    private CommonRepository commonRepository;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/m/list");
        List list = commonRepository.queryAll(TbMessage.class);
        mv.addObject("list",list);
        return mv;
    }

    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public ModelAndView view(String  id){
        ModelAndView mv = new ModelAndView();
        TbMessage tbMessage = (TbMessage) commonRepository.findById(id,TbMessage.class);
        mv.setViewName("/m/view");
        mv.addObject("returnObj", tbMessage);
        return mv;
    }
}
