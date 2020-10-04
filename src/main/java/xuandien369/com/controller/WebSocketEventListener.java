package xuandien369.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import xuandien369.com.model.ChatMessage;
import xuandien369.com.model.MessageType;

@Component
public class WebSocketEventListener {
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;
	@EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
       System.out.println("Received a new web socket connection");
    }
	
	@EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        Integer member = (Integer) headerAccessor.getSessionAttributes().get("member");
        if(username != null) {
            System.out.println("User Disconnected : " + username);
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(MessageType.LEAVE);
            chatMessage.setSender(username);
            --member;
            chatMessage.setMember(member);
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
	}
}
