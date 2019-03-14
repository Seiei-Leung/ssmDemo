package cn.seiei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public int testForJsonData(String name, String pw) {
        System.out.println("testForJsonData");
        System.out.println(name);
        System.out.println(pw);
        return 1;
    }

    @RequestMapping(value = "/testForObj", method = RequestMethod.POST)
    @ResponseBody
    public int testForObj(@RequestBody Map<String, String> params) {
        System.out.println("testForObj");
        System.out.println("name:" + params.get("name"));
        System.out.println("pw:" + params.get("pw"));
        return 2;
    }


    @RequestMapping(value = "/testForObj2", method = RequestMethod.POST)
    @ResponseBody
    public int testForObj2(@RequestBody String params) {
        System.out.println("testForObj2");
        System.out.println(params);
        return 3;
    }
}
