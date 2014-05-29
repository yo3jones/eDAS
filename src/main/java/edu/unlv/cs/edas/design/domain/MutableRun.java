package edu.unlv.cs.edas.design.domain;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.unlv.cs.edas.design.dto.ObjectIdDeserializer;
import edu.unlv.cs.edas.design.dto.ObjectIdSerializer;

@Document(collection="Run")
@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=NONE)
public class MutableRun implements Run {

	private static final long serialVersionUID = -553312792859259663L;

	@Id
	@JsonSerialize(using=ObjectIdSerializer.class)
	@JsonDeserialize(using=ObjectIdDeserializer.class)
	private ObjectId id;
	
	@JsonIgnore
	@Indexed
	private ObjectId ownerId;
	
	private String name;
	
	@JsonSerialize(using=ObjectIdSerializer.class)
	@JsonDeserialize(using=ObjectIdDeserializer.class)
	private ObjectId graphId;
	
	@JsonSerialize(using=ObjectIdSerializer.class)
	@JsonDeserialize(using=ObjectIdDeserializer.class)
	private ObjectId algorithmId;
	
	public MutableRun() {
		
	}
	
	public MutableRun(Run run) {
		this.id = run.getId();
		this.ownerId = run.getOwnerId();
		this.name = run.getName();
		this.graphId = run.getGraphId();
		this.algorithmId = run.getAlgorithmId();
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
	public ObjectId getGraphId() {
		return graphId;
	}
	
	@Override
	public String getStringGraphId() {
		return graphId.toHexString();
	}

	public void setGraphId(ObjectId graphId) {
		this.graphId = graphId;
	}
	
	@Override
	public ObjectId getAlgorithmId() {
		return algorithmId;
	}

	@Override
	public String getStringAlgorithmId() {
		return algorithmId.toHexString();
	}

	public void setAlgorithmId(ObjectId algorithmId) {
		this.algorithmId = algorithmId;
	}
	
}
