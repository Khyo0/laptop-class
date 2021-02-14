package com.gnjk.chat.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnjk.chat.dao.MessageDao;
import com.gnjk.chat.domain.ChatRoom;
import com.gnjk.chat.domain.Message;
import com.gnjk.chat.service.ChatService;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // @Autowired 없이 의존성 주입 가능
@Component // bean 생성
public class ChattingHandler extends TextWebSocketHandler {

	private final ObjectMapper mapper; // Json(String 형식) <-> 변환할 class
	private final ChatService service;

	/*
	 * //private Set<WebSocketSession> set = new HashSet<>();
	 * 
	 * 
	 * // 클라이언트가 서버로 연결 시
	 * 
	 * @Override public void afterConnectionEstablished(WebSocketSession session)
	 * throws Exception {
	 * 
	 * log.info("연결되었습니다. session.getId() {}", session.getId());
	 * 
	 * setSession.put();
	 * 
	 * }
	 */

	  @Override
	    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	        System.out.println("연결 "+ session);
	    }
	
	// 클라이언트가 Data 전송 시
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		String msg = message.getPayload();

		log.info("{} 를 전달 받았습니다.", msg);

		Message mes = mapper.readValue(msg, Message.class); // 자바 클래스 => Json
		ChatRoom room = service.findRoom(mes.getRoom_idx()); // 메세지의 room_idx로 채팅방 정보 조회
		room.handleActions(session, mes, service);
		// 채팅방에 입장한 웹소켓 세션의 타입에 따른 메세지 전송
	}

	 @Override
	    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	        System.out.println("연결끊김 "+session);
	        session.close();
	    }
	
	/*
	 * // 연결 해제 시
	 * 
	 * @Override public void afterConnectionClosed(WebSocketSession session,
	 * CloseStatus status) throws Exception {
	 * 
	 * log.info("연결이 끊김", session.getId());
	 * 
	 * session.close();
	 * 
	 * }
	 */
	// 에러 발생 시
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

		log.info(session.getId() + "exception 발생 : " + exception.getMessage());

	}

}