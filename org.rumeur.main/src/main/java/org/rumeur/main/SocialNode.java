package org.rumeur.main;

import java.util.Iterator;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.AbstractEdge;
import org.graphstream.graph.implementations.AbstractGraph;
import org.graphstream.graph.implementations.AbstractNode;

public class SocialNode extends AbstractNode {

	protected SocialNode(AbstractGraph graph, String id) {
		super(graph, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean addEdgeCallback(AbstractEdge arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void clearCallback() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getDegree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T extends Edge> T getEdge(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Edge> T getEdgeBetween(Node arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Edge> T getEdgeFrom(Node arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Edge> Iterator<T> getEdgeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Edge> T getEdgeToward(Node arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Edge> T getEnteringEdge(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Edge> Iterator<T> getEnteringEdgeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getInDegree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T extends Edge> T getLeavingEdge(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Edge> Iterator<T> getLeavingEdgeIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOutDegree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void removeEdgeCallback(AbstractEdge arg0) {
		// TODO Auto-generated method stub

	}

}
