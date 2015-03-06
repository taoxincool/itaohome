package com.itaohome.web;

import com.itaohome.dto.AutoReplyDto;
import com.itaohome.dto.AutoReplyNewsDto;
import com.itaohome.model.TbAutoReply;
import com.itaohome.model.TbAutoReplyNews;
import com.itaohome.model.TbMessage;
import com.itaohome.repository.AutoReplyNewsRepository;
import com.itaohome.repository.AutoReplyRepository;
import com.itaohome.repository.CommonRepository;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr tao on 2015/1/13.
 */
@Controller
@RequestMapping(value = "/reply")
public class AutoReplyController {

    @Resource
    private CommonRepository commonRepository;

    @Resource
    private AutoReplyRepository autoReplyRepository;

    @Resource
    private AutoReplyNewsRepository autoReplyNewsRepository;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu() {
        return "/reply/menu";
    }


    /*******************************************************************************************************
     **************************************************关注回复*********************************************
     **************************************************************************************************** */

    /**
     * "关注回复"列表
     *
     * @return
     */
    @RequestMapping(value = "/subscribe/list", method = RequestMethod.GET)
    public ModelAndView subscribelist() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/reply/subscribe/list");
        String hql = "FROM TbAutoReply t WHERE t.type = 2  AND t.isDelete = '0' order by t.id desc";
        List list = commonRepository.queryAll(null, hql, TbAutoReply.class);
        mv.addObject("list", list);
        return mv;
    }

    /**
     * "关注回复"删除
     *
     * @return
     */
    @RequestMapping(value = "/subscribe/delete", method = RequestMethod.POST)
    @ResponseBody
    public String subscribeDelete(String id) {
        try {
            //如果当前是启用状态不允许删除
            TbAutoReply tbAutoReply = autoReplyRepository.findOne(Integer.valueOf(id));
            if (tbAutoReply.getEnable().equals("1")) {
                return "2";
            }
            //提交
            tbAutoReply.setIsDelete("1");
            tbAutoReply.setUpdateDate(new Date());
            autoReplyRepository.save(tbAutoReply);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

    /**
     * "关注回复"新增
     *
     * @return
     */
    @RequestMapping(value = "/subscribe/new", method = RequestMethod.GET)
    public ModelAndView subscribeNew() {
        ModelAndView mv = new ModelAndView();
        TbAutoReply tbAutoReply = new TbAutoReply();
        mv.setViewName("/reply/subscribe/view");
        mv.addObject("returnObj", tbAutoReply);
        mv.addObject("optType", "subscribe");
        return mv;
    }

    /**
     * "关注回复"修改-查看
     *
     * @return
     */
    @RequestMapping(value = "/subscribe/view", method = RequestMethod.GET)
    public ModelAndView subscribeView(String id) {
        ModelAndView mv = new ModelAndView();
        TbAutoReply tbAutoReply = (TbAutoReply) commonRepository.findById(id, TbAutoReply.class);
        mv.setViewName("/reply/subscribe/view");
        mv.addObject("returnObj", tbAutoReply);
        mv.addObject("optType", "subscribe");
        return mv;
    }

    /**
     * "关注回复"提交
     *
     * @return
     */
    @RequestMapping(value = "/subscribe/submit", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String subscribeSubmit(AutoReplyDto dto) {
        try {
            //如果提交的数据为启用则需要将以前启用的数据置为禁用
            if (dto.getEnable().equals("1")) {
                List<TbAutoReply> list = autoReplyRepository.findByEnable("1");
                if (list != null && list.size() > 0) {
                    TbAutoReply autoReply = list.get(0);
                    autoReply.setEnable("");
                    autoReplyRepository.save(autoReply);
                }
            }
            //提交
            TbAutoReply tbAutoReply = new TbAutoReply();
            PropertyUtils.copyProperties(tbAutoReply, dto);
            autoReplyRepository.save(tbAutoReply);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

    /**
     * "关注回复"-关联的图文消息下拉框-查询
     *
     * @return
     */
    @RequestMapping(value = "/queryNews", method = RequestMethod.GET)
    @ResponseBody
    public List queryNews() {
        List<TbAutoReplyNews> tbAutoReplyNewses = autoReplyNewsRepository.findByParentIdAndIsDelete(null, "0");
        return tbAutoReplyNewses;
    }

    /*******************************************************************************************************
     ******************************************关键字回复***************************************************
     **************************************************************************************************** */

    /**
     * "关键字回复"列表
     *
     * @return
     */
    @RequestMapping(value = "/keyword/list", method = RequestMethod.GET)
    public ModelAndView keywordList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/reply/keyword/list");
        String hql = "FROM TbAutoReply t WHERE t.type = 1  AND t.isDelete = '0' order by t.id desc";
        List list = commonRepository.queryAll(null, hql, TbAutoReply.class);
        mv.addObject("list", list);
        return mv;
    }

    /**
     * "关注回复"删除
     *
     * @return
     */
    @RequestMapping(value = "/keyword/delete", method = RequestMethod.POST)
    @ResponseBody
    public String keywordDelete(String id) {
        try {
            TbAutoReply tbAutoReply = autoReplyRepository.findOne(Integer.valueOf(id));
            //提交
            tbAutoReply.setIsDelete("1");
            tbAutoReply.setUpdateDate(new Date());
            autoReplyRepository.save(tbAutoReply);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

    /**
     * "关键字回复"新增
     *
     * @return
     */
    @RequestMapping(value = "/keyword/new", method = RequestMethod.GET)
    public ModelAndView keywordNew() {
        ModelAndView mv = new ModelAndView();
        TbAutoReply tbAutoReply = new TbAutoReply();
        mv.setViewName("/reply/keyword/view");
        mv.addObject("returnObj", tbAutoReply);
        mv.addObject("optType", "keyword");
        return mv;
    }

    /**
     * "关键字回复"修改-查看
     *
     * @return
     */
    @RequestMapping(value = "/keyword/view", method = RequestMethod.GET)
    public ModelAndView keywordView(String id) {
        ModelAndView mv = new ModelAndView();
        TbAutoReply tbAutoReply = (TbAutoReply) commonRepository.findById(id, TbAutoReply.class);
        mv.setViewName("/reply/keyword/view");
        mv.addObject("returnObj", tbAutoReply);
        mv.addObject("optType", "keyword");
        return mv;
    }

    /**
     * "关键字回复"提交
     *
     * @return
     */
    @RequestMapping(value = "/keyword/submit", method = RequestMethod.POST)
    @ResponseBody
    public String keywordSubmit(AutoReplyDto dto) {
        try {
            //提交
            TbAutoReply tbAutoReply = new TbAutoReply();
            PropertyUtils.copyProperties(tbAutoReply, dto);
            autoReplyRepository.save(tbAutoReply);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }


    /*******************************************************************************************************
     ******************************************图文消息维护***************************************************
     **************************************************************************************************** */

    /**
     * "图文消息维护"列表
     *
     * @return
     */
    @RequestMapping(value = "/news/list", method = RequestMethod.GET)
    public ModelAndView newsList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/reply/news/list");
        String hql = "FROM TbAutoReplyNews t WHERE t.isDelete = '0' order by t.id desc";
        List list = commonRepository.queryAll(null, hql, TbAutoReplyNews.class);
        mv.addObject("list", list);
        return mv;
    }

    /**
     * "图文消息维护"删除
     *
     * @return
     */
    @RequestMapping(value = "/news/delete", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String newsDelete(String id) {
        try {
            //如果当前是启用状态不允许删除
            TbAutoReplyNews tbAutoReplyNews = autoReplyNewsRepository.findOne(Integer.valueOf(id));
            if (tbAutoReplyNews.getParentId() == null || tbAutoReplyNews.getParentId().toString().equals("")) {
                //表示当前是父节点，循环删除子节点
                List<TbAutoReplyNews> childList = autoReplyNewsRepository.findByParentIdAndIsDelete(tbAutoReplyNews.getId(), "0");
                for (TbAutoReplyNews autoReplyNews : childList) {
                    autoReplyNews.setIsDelete("1");
                    autoReplyNews.setUpdateDate(new Date());
                    autoReplyNewsRepository.save(autoReplyNews);
                }
            }
            //提交
            tbAutoReplyNews.setIsDelete("1");
            tbAutoReplyNews.setUpdateDate(new Date());
            autoReplyNewsRepository.save(tbAutoReplyNews);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

    /**
     * "图文消息维护"新增
     *
     * @return
     */
    @RequestMapping(value = "/news/new", method = RequestMethod.GET)
    public ModelAndView newsNew() {
        ModelAndView mv = new ModelAndView();
        TbAutoReplyNews tbAutoReplyNews = new TbAutoReplyNews();
        mv.setViewName("/reply/news/view");
        mv.addObject("returnObj", tbAutoReplyNews);
        mv.addObject("optType", "news");
        return mv;
    }

    /**
     * "图文消息维护"修改-查看
     *
     * @return
     */
    @RequestMapping(value = "/news/view", method = RequestMethod.GET)
    public ModelAndView newsView(String id) {
        ModelAndView mv = new ModelAndView();
        TbAutoReplyNews tbAutoReplyNews = (TbAutoReplyNews) commonRepository.findById(id, TbAutoReplyNews.class);
        mv.setViewName("/reply/news/view");
        mv.addObject("returnObj", tbAutoReplyNews);
        mv.addObject("optType", "news");
        return mv;
    }

    /**
     * "图文消息维护"提交
     *
     * @return
     */
    @RequestMapping(value = "/news/submit", method = RequestMethod.POST)
    @ResponseBody
    public String newsSubmit(AutoReplyNewsDto dto) {
        try {
            if (dto.getParentId() != null && !dto.getParentId().toString().equals("")) {
                //表示当前有选择父节点，如果父节点中的子节点超过10个则不允许再添加
                List<TbAutoReplyNews> childList = autoReplyNewsRepository.findByParentIdAndIsDelete(dto.getParentId(),"0");
                if (childList.size() >= 10) {
                    return "2";//表示不允许再添加子节点了
                }
            }
            //提交
            TbAutoReplyNews tbAutoReplyNews = new TbAutoReplyNews();
            PropertyUtils.copyProperties(tbAutoReplyNews, dto);
            autoReplyNewsRepository.save(tbAutoReplyNews);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }


    /**
     * ****************************************************************************************************
     * *************************************************其他*********************************************
     * ***************************************************************************************************
     */

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/m/list");
        List list = commonRepository.queryAll(TbMessage.class);
        mv.addObject("list", list);
        return mv;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view(String id) {
        ModelAndView mv = new ModelAndView();
        TbMessage tbMessage = (TbMessage) commonRepository.findById(id, TbMessage.class);
        mv.setViewName("/m/view");
        mv.addObject("returnObj", tbMessage);
        return mv;
    }
}
