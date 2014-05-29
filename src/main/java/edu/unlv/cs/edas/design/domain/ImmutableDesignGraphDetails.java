package edu.unlv.cs.edas.design.domain;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=NONE)
public class ImmutableDesignGraphDetails implements DesignGraphDetails {

	private static final long serialVersionUID = 1780499828238696135L;
	
	@JsonUnwrapped
	private MutableDesignGraphDetails graphDetails;
	
	public ImmutableDesignGraphDetails(MutableDesignGraphDetails graphDetails) {
		this.graphDetails = graphDetails;
	}
	
	@Override
	public ObjectId getId() {
		return graphDetails.getId();
	}

	@Override
	public String getStringId() {
		return graphDetails.getStringId();
	}

	@Override
	public String getName() {
		return graphDetails.getName();
	}

	@Override
	public DesignGraph getGraph() {
		DesignGraph graph = graphDetails.getGraph();
		return graph == null ? null : new DesignUnmodifiableGraph(graph);
	}

	@Override
	public ObjectId getOwner() {
		return graphDetails.getOwner();
	}

}
