package xuandien369.com.model;

public class ChatMessage {
	private MessageType type;
    private String content;
    private String sender;
    private Integer member;

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

	public Integer getMember() {
		return member;
	}

	public void setMember(Integer member) {
		this.member = member;
	}
    
}
