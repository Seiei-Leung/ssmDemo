package cn.seiei.controller;

import cn.seiei.service.ForRedisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.seiei.mall.common.ServerResponse;

import javax.annotation.Resource;

@Controller
public class ForRedisController {

    @Resource
    private ForRedisService forRedisService;

    @RequestMapping("/setRedis")
    @ResponseBody
    public ServerResponse setReids() {
        return forRedisService.setRedis();
    }

    @RequestMapping("/getRedis")
    @ResponseBody
    public ServerResponse getReids() {
        return forRedisService.getRedis();
    }
}
