package com.yc.C76S3PlySpringBoot;

import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

//webSCOket服务器注解
@ServerEndpoint(value = "/websocket/{id}")
@Component
public class MyWebSocket {
	private static Hashtable<String, Session> webHashtable = new Hashtable<String, Session>();
	/*
	 * //静态变量记录在线链接数 private static int onlineCount = 0; private static
	 * CopyOnWriteArraySet<Session> webArraySet = new
	 * CopyOnWriteArraySet<Session>(); private Session session;
	 */
	
	//链接建立成功调用
	@OnOpen
	public void onOpen(@PathParam("id")String id,Session session) {
		System.out.println(session);
		webHashtable.put(id,session);
	}
	//链接关闭调用
	@OnClose
	public void onClose(Session session) {
		//webArraySet.remove(session);
	}
	//收到客户端消息后调用方法
	@OnMessage
	public void onMessage(String message ,Session session) throws IOException {
		String[] strings = message.split(":");
		String id = strings[0];
		message = strings[1];
		Session targetSession = webHashtable.get(id);
		if(targetSession != null) {
			targetSession.getBasicRemote().sendText(message);
		}else {
			System.out.println(id + "不在线！");
		}
	}
}

