package xuandien369.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import xuandien369.com.model.ChatMessage;
import xuandien369.com.model.MessageType;
import xuandien369.com.model.UserStorage;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, ChatMessage message) {
        System.out.println("handling send message: " + message.getContent() + " to: " + to);
        int count2 = ++UserStorage.getInstance().countMember;
        message.setMember(count2);
        message.setUsers(UserStorage.getInstance().getUsers());
        message.setRecipient(to);
//        boolean isExists = UserStorage.getInstance().getUsers().contains(to);
//        if (isExists) {
//        	
//        }
        	if(to.equals("Everybody")) {
        		simpMessagingTemplate.convertAndSend("/topic/public" , message);
        	}
        	else {
        		  simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
                  simpMessagingTemplate.convertAndSend("/topic/messages/" + message.getSender(), message);
        	}      
    }    
	@MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, 
                               SimpMessageHeaderAccessor headerAccessor) {
				int count2 = ++UserStorage.getInstance().countMember;
		        try {
		            UserStorage.getInstance().setUser(chatMessage.getSender());
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		        chatMessage.setMember(count2);
		        chatMessage.setUsers(UserStorage.getInstance().getUsers());
		        System.out.println(chatMessage.getUsers());
		        return chatMessage;

    }
}
