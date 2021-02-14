package com.gnjk.chat.controller;

import com.gnjk.chat.domain.ChatRoom;
import com.gnjk.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor	// @Autowired 없이 의존성 주입 가능
@Controller
@RequestMapping("/chatting")
public class RoomController {

	private final ChatService service;

	// 왼쪽 채팅 리스트 화면
	@GetMapping("chatting")	
	public String rooms(Model model) {
		return "/chat/room";
	}

	// 채팅 목록
	@GetMapping("chatting")	
	@ResponseBody
	public List<ChatRoom> room() {
		return service.isRoom();
	}

	// 채팅방 생성
	@PostMapping("chatting")
	@ResponseBody
	public ChatRoom createRoom(@RequestParam String name) {
		return service.createRoom(name);
	}

	// 채팅 입장 화면
	@GetMapping("chatting")
	public String roomDetail(Model model, @PathVariable String room_idx) {
		model.addAttribute("roomId", room_idx);
		return "/chat/roomdetail";
	}

	// 채팅방 조회
	@GetMapping("chatting")
	@ResponseBody
	public ChatRoom roomInfo(@PathVariable String room_idx) {
		return service.findRoom(room_idx);
	}
}