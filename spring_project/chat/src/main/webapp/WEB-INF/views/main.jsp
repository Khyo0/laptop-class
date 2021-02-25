<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	function sendMessage(form) {
		form.writer.value = form.writer.value.trim(); 
		if (form.writer.value.length == 0) {
			alert('작성자를 입력하세요');
			form.writer.focus();
			return false;
		}
		
		form.body.value = form.body.value.trim();
		if (form.body.value.length == 0) {
			alert('내용을 입력하세요');
			form.body.focus();
			return false;
		}	
		$.post('./addMessage',{
			writer : form.writer.value,
			body : form.body.value
		}, function(data) {
		}, 'json');
		form.body.value = '';
		form.body.focus();
		
	}
	var Chat__lastReceivedMessageId = -1;
	function Chat__loadNewMessages() {
		$.get('./getMessages',{
			
			from : Chat__lastReceivedMessageId + 1
		
		}, function(data) {
			for ( var i = 0; i < data.length; i++ ) {
				var message = data[i];
				Chat__lastReceivedMessageId = message.id;
				Chat__drawMessages(message);
			}
			setTimeout(Chat__loadNewMessages,100);
			
		}, 'json');
		
	}
	function Chat__drawMessages(message) {
		var html = '[' +message.id + '] (' + message.writer + ') : ' + message.body;
		$('.chat-list').prepend('<div>' + html + '</div>')
	}
	$(function() {
		Chat__loadNewMessages();
		
	});
</script>

</head>
<body>

	<h1>채팅방</h1>
	
	<form onsubmit="sendMessage(this); return false;">
		<input type="text" name="writer" placeholder="작성자">
		<input type="text" name="body" placeholder="내용">
		<input type="submit" value="전송">
	</form>
	
	<div class="chat-list"></div>

</body>
</html>