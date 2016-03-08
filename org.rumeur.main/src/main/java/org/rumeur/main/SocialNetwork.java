package org.rumeur.main;

import java.util.ArrayList;
import java.util.Iterator;

import org.graphstream.graph.Graph;
import org.graphstream.graph.NodeFactory;
import org.graphstream.graph.implementations.AbstractGraph;
import org.graphstream.graph.implementations.AdjacencyListGraph;

public class SocialNetwork extends AdjacencyListGraph {
	public SocialNetwork(String id, boolean strictChecking, boolean autoCreate, int initialNodeCapacity,
			int initialEdgeCapacity) {
		super(id, strictChecking, autoCreate, initialNodeCapacity, initialEdgeCapacity);
		// All we need to do is to change the node factory
		setNodeFactory(new NodeFactory<SocialNode>() {
			public SocialNode newInstance(String id, Graph graph) {
				return new SocialNode((AbstractGraph) graph, id);
			}
		});
	}

	public SocialNetwork(String id, boolean strictChecking, boolean autoCreate) {
		this(id, strictChecking, autoCreate, DEFAULT_NODE_CAPACITY, DEFAULT_EDGE_CAPACITY);
	}

	public SocialNetwork(String id) {
		this(id, true, false);
	}
}
