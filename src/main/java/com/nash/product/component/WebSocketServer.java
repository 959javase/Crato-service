package com.nash.product.component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;


@ServerEndpoint("/agentServer/{agentIp}")
@Component
public class WebSocketServer {


    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;

    private String agentIp="";

    @OnOpen
    public void onOpen(Session session,@PathParam("agentIp") String agentIp) {
        this.session = session;
        this.agentIp=agentIp;
        if(webSocketMap.containsKey(agentIp)){
            webSocketMap.remove(agentIp);
            webSocketMap.put(agentIp,this);
            //加入set中
        }else{
            webSocketMap.put(agentIp,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        System.out.println("agent连接:"+agentIp+",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            System.out.println("agent:"+agentIp+",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(agentIp)){
            webSocketMap.remove(agentIp);
            //从set中删除
            subOnlineCount();
        }
        System.out.println("agentIp退出:"+agentIp+",当前在线agent为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("agent消息:"+agentIp+",报文:"+message);
        //可以群发消息
        //消息保存到数据库、redis
        if(StringUtils.isNotBlank(message)){
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("用户错误:"+this.agentIp+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     * */
    public static void sendInfo(String message,@PathParam("agentIp") String agentIp) throws IOException {
        System.out.println("发送消息到:"+agentIp+"，报文:"+message);
        if(StringUtils.isNotBlank(agentIp)&&webSocketMap.containsKey(agentIp)){
            webSocketMap.get(agentIp).sendMessage("{\"MsgType\":\"cmd\",\"MsgContent\":\""+message+"\"}");
        }else{
            System.out.println(""+agentIp+",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
