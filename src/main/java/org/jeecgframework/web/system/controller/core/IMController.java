package org.jeecgframework.web.system.controller.core;

import org.jeecgframework.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mr.Yangxiufeng on 2016/11/21.
 * Time:18:25
 * ProjectName:jeecg
 */
@Controller
@RequestMapping("/imController")
public class IMController extends BaseController{
    @RequestMapping(params = "index")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("extend/im/imPage");
        return modelAndView;
    }

}
