package edu.unlv.cs.graph.dom.domain;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import edu.unlv.cs.graph.EdgeKey;
import edu.unlv.cs.graph.Graph;

public interface GraphDomBuilder<K, V, E, C> {

	Document createDocument();
	
	C createContext(Document document, Graph<K, V, E> graph);
	
	Node createVertex(C context, K key, V vertex);
	
	Node createEdge(C context, EdgeKey<K> key, E edge);
	
}
