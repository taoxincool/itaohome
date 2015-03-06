package com.itaohome.web;

import com.itaohome.model.TbFans;
import com.itaohome.model.TbLog;
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
@RequestMapping(value = "/l")
public class LogController {

    @Resource
    private CommonRepository commonRepository;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/l/list");
        List list = commonRepository.queryAll(TbLog.class);
        mv.addObject("list",list);
        return mv;
    }

    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public ModelAndView view(String  id){
        ModelAndView mv = new ModelAndView();
        TbLog tbLog = (TbLog) commonRepository.findById(id,TbLog.class);
        mv.addObject("title", "日志详情");
        mv.addObject("message", tbLog.getMemo());
        mv.setViewName("/common/message-box");
        return mv;
    }
}
