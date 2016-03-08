package org.rumeur.main;

import org.graphstream.graph.implementations.AbstractGraph;
import org.graphstream.graph.implementations.SingleNode;

public class SocialNode extends SingleNode {

	private Rumeur rumeur;
	private Rumeur contreRumeur;
	
	protected SocialNode(AbstractGraph graph, String id) {
		super(graph, id);
	}

	public SocialNode(AbstractGraph graph, String id, Rumeur rumeur, Rumeur contreRumeur) {
		super(graph, id);
		this.rumeur = rumeur;
		this.contreRumeur = contreRumeur;
	}

	
	
	public Rumeur getContreRumeur() {
		return contreRumeur;
	}

	public void setContreRumeur(Rumeur contreRumeur) {
		this.contreRumeur = contreRumeur;
	}

	public Rumeur getRumeur() {
		return rumeur;
	}

	public void setRumeur(Rumeur rumeur) {
		this.rumeur = rumeur;
	}
	
}
