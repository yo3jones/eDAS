package edu.unlv.cs.edas.execute.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;

import edu.unlv.cs.edas.design.dom.DesignGraphDomAdapter;
import edu.unlv.cs.edas.execute.domain.Execution;
import edu.unlv.cs.edas.execute.manager.ExecutionManager;

@Controller
@RequestMapping("/executions")
public class ExecutionsController {

	@Autowired ExecutionManager manager;
	
	@Autowired DesignGraphDomAdapter adapter;
	
	@RequestMapping(value="/{id}", method=GET)
	public ModelAndView getExecutionRound(@PathVariable String id) throws Exception{
		Execution execution = manager.get(id);
		
		return new ModelAndView("execution/execution")
			.addObject("model", execution)
			.addObject("svg", getSvg(execution));
	}
	
	private String getSvg(Execution execution) throws TransformerException, IOException {
		Document document = adapter.getDomFromGraph(execution.getDesignGraphDetails().getGraph());
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StreamResult result = new StreamResult(out);
		transformer.transform(source, result);
		
		out.flush();
		
		return out.toString();
	}
	
}
