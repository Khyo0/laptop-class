package com.gnjk.chat.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import com.gnjk.chat.service.ChatService;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom {
	
	private String room_idx;
	private String name;
	private Set<WebSocketSession> setSession = new HashSet<>();
	// 웹소켓 세션 정보 리스트
	// set 인터페이스 구현 => 중복 제거
	
	@Builder	// 빌더 패턴 적용 => nullPointerExeption 오류 이제 그만...
	public ChatRoom(String room_idx, String name) {
		this.room_idx = room_idx;
		this.name = name;
	}
	
	public void handleActions(WebSocketSession session, Message mes, ChatService service) {
		if(mes.getType().equals(Message.MessageType.ENTER)) {	// 메세지 타입이 enter이면		
			setSession.add(session);
			mes.setCh_ms(mes.getRm_idx() + " online");
		}
		sendMessage(mes, service);
	}
	
	public <M> void sendMessage(M mes, ChatService service) {
		setSession.parallelStream().forEach(session -> service.sendMessage(session, mes));
		// 채팅 룸의 세션에게 메세지 보내기 (여러명도,,, 가능,,,,,,,,,,,?)
		// parallelStream() => 병렬 스트림 바로 리턴
		// forEach => 배열 순회 (return 값 없음)
	}
	
}