package cn.seiei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 前端使用 POST 方法，传递密码与名称
 *
 * @author Seiei
 *
 */
@Controller
public class ForPostSigninController {

    @RequestMapping(value = "/testForJsonData", method = RequestMethod.POST)
    @ResponseBody
    public int testForJsonData(@RequestBody String name, @RequestBody String pw) {
        System.out.println(name);
        System.out.println(pw);
        return 1;
    }

    @RequestMapping(value = "/testForObj", method = RequestMethod.POST)
    @ResponseBody
    public int testForObj(@RequestBody Map<String, String> params) {
        System.out.println(params.get("name"));
        System.out.println(params.get("pw"));
        return 1;
    }

}
