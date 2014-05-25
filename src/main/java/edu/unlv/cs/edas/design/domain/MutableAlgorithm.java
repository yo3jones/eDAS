package edu.unlv.cs.edas.design.domain;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.unlv.cs.edas.design.dto.ObjectIdDeserializer;
import edu.unlv.cs.edas.design.dto.ObjectIdSerializer;

@Document(collection="Algorithm")
@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=NONE)
public class MutableAlgorithm implements Algorithm {

	@Id
	@JsonSerialize(using=ObjectIdSerializer.class)
	@JsonDeserialize(using=ObjectIdDeserializer.class)
	private ObjectId id;
	
	@JsonIgnore
	private ObjectId ownerId;
	
	private String name;
	
	private String algorithm;
	
	public MutableAlgorithm() {
		
	}
	
	public MutableAlgorithm(Algorithm algorithm) {
		this.id = algorithm.getId();
		this.ownerId = algorithm.getOwnerId();
		this.name = algorithm.getName();
		this.algorithm = algorithm.getAlgorithm();
	}
	
	@Override
	public ObjectId getId() {
		return id;
	}
	
	@Override
	public String getStringId() {
		return id.toHexString();
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	@Override
	public ObjectId getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(ObjectId ownerId) {
		this.ownerId = ownerId;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAlgorithm() {
		return algorithm;
	}
	
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

}
