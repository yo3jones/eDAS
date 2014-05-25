package edu.unlv.cs.edas.design.domain;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.unlv.cs.edas.design.dto.ObjectIdDeserializer;
import edu.unlv.cs.edas.design.dto.ObjectIdSerializer;

@Document(collection="designGraphDetails")
@JsonInclude(NON_NULL)
public class DesignGraphDetails {
	
	@Id
	@JsonSerialize(using=ObjectIdSerializer.class)
	@JsonDeserialize(using=ObjectIdDeserializer.class)
	private ObjectId id;
	
	private String name;
	
	@JsonDeserialize(as=DesignHashGraph.class)
	@JsonUnwrapped
	private DesignGraph graph;
	
	@JsonIgnore
	private ObjectId owner;
	
	public ObjectId getId() {
		return id;
	}
	
	public String getStringId() {
		return id.toHexString();
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public DesignGraph getGraph() {
		return graph;
	}
	
	public void setGraph(DesignGraph graph) {
		this.graph = graph;
	}
	
	public ObjectId getOwner() {
		return owner;
	}
	
	public void setOwner(ObjectId owner) {
		this.owner = owner;
	}
	
}
