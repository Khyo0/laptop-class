package com.gnjk.chat.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnjk.chat.dao.MessageDao;
import com.gnjk.chat.domain.ChatRoom;
import com.gnjk.chat.handler.ChattingHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j	//	log
@RequiredArgsConstructor	// @Autowired 없이 의존성 주입 가능
@Service
public class ChatService {

	private final ObjectMapper mapper;	// Json(String 형식) <-> 변환할 class
	private Map<String, ChatRoom> mapRoom;
	
	@PostConstruct	// 서비스 객체에서 사용 시, 호출할 메소드 지정
	private void init() {
		mapRoom = new LinkedHashMap<>();	// 순서 있는 HashMap
	}
	
	// 방 확인
	public List<ChatRoom> isRoom() {
		List rooms = new ArrayList<>(mapRoom.values());	// HashMap의 value값
		Collections.reverse(rooms);	// 채팅방 최근순으로 반환
		
		return rooms;
	}
	
	// 방 찾기
	public ChatRoom findRoom(String room_idx) {
		return mapRoom.get(room_idx);
	}
	
	// 방 만들기
	public ChatRoom createRoom(String name) {
		String randomId = UUID.randomUUID().toString();
		ChatRoom chatRoom = ChatRoom.builder()	// 빌더 패턴 적용. 널포인터 그만!!!!!
				.room_idx(randomId)	// 랜덤
				.name(name)	// 채팅방 이름
				.build();
		mapRoom.put(randomId, chatRoom);
		// 구별ID로 채팅방 객체 생성 후, 채팅방 map에 추가
		return chatRoom;
	}
	
	// 지정한 웹소켓 세션에 메세지 전송
	public <M> void sendMessage(WebSocketSession session, M mes) {
		try {
			session.sendMessage(new TextMessage(mapper.writeValueAsString(mes)));
												// class 값을 Json(String 형식)으로 반환
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}