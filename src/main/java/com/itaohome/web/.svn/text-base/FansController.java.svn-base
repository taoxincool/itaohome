package com.itaohome.web;

import com.itaohome.model.TbFans;
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
@RequestMapping(value = "/f")
public class FansController {

    @Resource
    private CommonRepository commonRepository;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/f/list");
        List list = commonRepository.queryAll(TbFans.class);
        mv.addObject("list",list);
        return mv;
    }

    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public ModelAndView view(String  id){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/f/view");
        TbFans tbFans = (TbFans) commonRepository.findById(id,TbFans.class);
        mv.addObject("returnObj",tbFans);
        return mv;
    }
}
