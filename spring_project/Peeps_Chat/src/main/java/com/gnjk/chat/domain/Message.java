package com.gnjk.chat.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
	
	// 메세지 타입 : 입장, 채팅
	public enum MessageType {
		ENTER, TALK
	}

	private MessageType type;
	private int ch_idx;	// 채팅 번호
	private String rm_idx; 		// 받는 사람 
	private String ch_ms; // 채팅 내용
	private String room_idx;
	
}