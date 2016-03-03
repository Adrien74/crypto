package org.rumeur.main;

import org.graphstream.graph.implementations.AbstractGraph;
import org.graphstream.graph.implementations.SingleNode;

public class SocialNode extends SingleNode {

	private Rumeur rumeur;
	
	protected SocialNode(AbstractGraph graph, String id) {
		super(graph, id);
	}

	public SocialNode(AbstractGraph graph, String id, Rumeur rumeur) {
		super(graph, id);
		this.rumeur = rumeur;
	}

	
	
	public Rumeur getRumeur() {
		return rumeur;
	}

	public void setRumeur(Rumeur rumeur) {
		this.rumeur = rumeur;
	}
	
}
