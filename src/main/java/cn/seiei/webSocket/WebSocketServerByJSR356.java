package cn.seiei.webSocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import org.springframework.web.socket.server.standard.SpringConfigurator;

@ServerEndpoint(value="/websocket/commodity/{fromUserId}/{toUserId}", configurator = SpringConfigurator.class)
public class WebSocketServerByJSR356 {

    // 已经建立链接的对象缓存起来（线性安全）
    private static ConcurrentMap<Integer, WebSocketServerByJSR356> serverMap = new ConcurrentHashMap<Integer, WebSocketServerByJSR356>();

    // 记录当前 WebSocket 的 session 对象
    // 当中 isOpen 方法可以判断该用户是否在线
    // 调用 getBasicRemote().sendText(content) 可以发送消息到客户端
    private Session currentSession;

    /**
     * 用户开始连接 webSocket 事件
     * @PathParam 解释：https://blog.csdn.net/u011410529/article/details/66974974
     * @param session webSocket session 对象
     * @param fromUserId url 传入的用户 ID
     * @param toUserId url 传入的目标用户 ID
     * @throws IOException
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("fromUserId") int fromUserId, @PathParam("toUserId") int toUserId) throws IOException {
        this.currentSession = session;
        serverMap.put(fromUserId, this);//建立链接时，缓存对象，这个 this 就是 WebSocketServer 对象
        System.out.println("UserId:" + fromUserId + " 连接服务器成功。。。");
        System.out.println("session.getRequestURI:" + session.getRequestURI());
        System.out.println("session.getQueryString:" + session.getQueryString());
        System.out.println("session.getRequestParameterMap:" + session.getRequestParameterMap());
    }

    /**
     * 用户关闭 webSocket 连接事件，清除缓存
     * @param session webSocket session 对象
     * @param reason 连接关闭原因
     */
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("用户关闭：" + reason.toString());
        // 如果缓存中有当前用户的缓存（这里的 this 就是 WebSocketServer 对象）
        if (serverMap.containsValue(this)) {
            Iterator<Integer> keys = serverMap.keySet().iterator();
            int userId = 0;
            while(keys.hasNext()) {
                userId = keys.next();
                if (serverMap.get(userId) == this) {
                    serverMap.remove(userId, this);//关闭链接时，删除缓存对象
                }
            }
        }
        this.currentSession = null;
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接过后，发送信息
     * @param json 信息 JSON 对象，当中包含发送者ID，接受者ID 以及发送信息
     */
    @OnMessage
    @SuppressWarnings("unchecked")
    public void onMessage(String json) {
        HashMap<String, String> map =  JSON.parseObject(json, HashMap.class);
        int fromUserId = Integer.parseInt(map.get("fromUserId"));
        int toUserId = Integer.parseInt(map.get("toUserId"));
        String content = map.get("content").toString();
        WebSocketServerByJSR356 server = serverMap.get(toUserId);
        //若存在则用户在线，否在用户不在线
        if (server != null && server.currentSession.isOpen()) {
            if (fromUserId != toUserId) {
                try {
                    // 发送信息
                    server.currentSession.getBasicRemote().sendText(content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 连接发生错误事件
     * @param t 错误对象
     */
    @OnError
    public void onError(Throwable t) {
        System.out.println("发生错误事件！！");
        t.printStackTrace();
    }
}
