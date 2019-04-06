package cn.seiei.service;

import cn.seiei.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import top.seiei.mall.common.ServerResponse;

@Service("forRedisService")
public class ForRedisService {

    @Resource
    private RedisUtil redisUtil;

    public ServerResponse setRedis() {
        if (redisUtil.set("name", "seiei")) {
            return ServerResponse.createdBySucessMessage("ok");
        }
        return ServerResponse.createdByErrorMessage("false");
    }

    public ServerResponse getRedis() {
        return ServerResponse.createdBySuccess(redisUtil.get("name").toString());
    }
}
