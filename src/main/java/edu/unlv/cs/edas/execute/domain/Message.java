package edu.unlv.cs.edas.execute.domain;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.collections4.map.UnmodifiableMap;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=NONE)
public final class Message implements Serializable {

	private static final long serialVersionUID = 2434445345142184993L;

	private Map<String, Object> message;
	
	private String messageDisplay;
	
	public Message(Map<String, Object> message, String messageDisplay) {
		this.message = UnmodifiableMap.unmodifiableMap(message);
		this.messageDisplay = messageDisplay;
	}
	
	public final Map<String, Object> getMessage() {
		return message;
	}
	
	public final String getMessageDisplay() {
		return messageDisplay;
	}
	
}
