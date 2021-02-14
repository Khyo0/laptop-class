package com.gnjk.chat;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gnjk.chat.domain.ChatRoom;
import com.gnjk.chat.service.ChatService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor	// @Autowired 없이 의존성 주입 가능
@RestController				// @controller에 @ResponseBody추가 =>> Json으로 객체 반환
@RequestMapping("/chat")
public class HomeController { 
	
	private final ChatService service;
	
	@PostMapping	// RequestMethod.POST
	public ChatRoom createRoom(@RequestParam String name) {
		return service.createRoom(name);
	}
	
	@GetMapping	// HTTP RequestMethod.GET
	public List<ChatRoom> isRoom() {
		return service.isRoom();
	}

}