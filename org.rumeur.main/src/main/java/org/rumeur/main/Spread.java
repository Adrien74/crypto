package org.rumeur.main;

import java.util.ArrayList;
import java.util.Iterator;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.Dijkstra.Element;
import org.graphstream.graph.Path;

public class Spread implements Runnable {
	private SocialNode startNode;
	private SocialNetwork graph;

	public Spread(SocialNode startNode, SocialNetwork graph) {
		super();
		this.startNode = startNode;
		this.graph = graph;
	}

	public void spread(SocialNode startNode) {
		boolean doSleep = false;

		Iterator<SocialNode> nodeIterator = startNode.getNeighborNodeIterator();
		ArrayList<SocialNode> contamined = new ArrayList<SocialNode>();

		while (nodeIterator.hasNext()) {
			SocialNode neighborNode = nodeIterator.next();
			if (neighborNode.getRumeur() == null 
					&& startNode.getRumeur().getTarget() != neighborNode
					&& startNode.getRumeur().getLauncher() != neighborNode) {
				System.out.println("Rumeur");
				doSleep = true;
				contamined.add(neighborNode);
				neighborNode.addAttribute("ui.class", startNode.getRumeur().getRumeurColor());
				neighborNode.setRumeur(startNode.getRumeur());
			} 
			
			else if (neighborNode.getRumeur() == null 
					&& startNode.getRumeur().getTarget() == neighborNode) {
				System.out.println("Contre rumeur");
				neighborNode.setRumeur(new Rumeur(neighborNode, startNode.getRumeur().getLauncher(), "truth"));
				doSleep = true;
				contamined.add(neighborNode);
				Spread antispread = new Spread(neighborNode, graph);
				Thread antiSpreadThread = new Thread(antispread);
				antiSpreadThread.run();
			}

			if (
					neighborNode.getRumeur().getTarget() != startNode.getRumeur().getTarget()&& 
					neighborNode.getRumeur().getTarget() != neighborNode && 
					startNode.getRumeur().getLauncher() != neighborNode && 
					startNode.getRumeur().getTarget() != neighborNode) {
				System.out.println("Dijkstra");
				if (closerToSelf(neighborNode, startNode.getRumeur().getLauncher(), startNode.getRumeur().getTarget()) == 1) {
					neighborNode.setRumeur(startNode.getRumeur());
					neighborNode.changeAttribute("ui.class", neighborNode.getRumeur().getRumeurColor());
					contamined.add(neighborNode);
				} else if (closerToSelf(neighborNode, startNode.getRumeur().getLauncher(), startNode.getRumeur().getTarget()) == 0){
					neighborNode.setRumeur(new Rumeur(startNode.getRumeur().getTarget(), neighborNode.getRumeur().getTarget(), "neutral"));

				}
				neighborNode.changeAttribute("ui.class", neighborNode.getRumeur().getRumeurColor());
				


			}
		}

		if (doSleep) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			doSleep = false;
		}

		for (SocialNode currentContamined : contamined) {
			spread(currentContamined);
		}
	}

	@Override
	public void run() {
		spread(this.startNode);

	}
	
	public int closerToSelf(SocialNode currentNode, SocialNode startNode, SocialNode targetNode) {
		Dijkstra dijkstra = new Dijkstra(Element.EDGE, null, null);
		double currentToStart;
		double currentToTarget;
		dijkstra.init(graph);
		dijkstra.setSource(graph.getNode(currentNode.getId()));
		dijkstra.compute();
		currentToStart = dijkstra.getPathLength(graph.getNode(startNode.getId()));
		currentToTarget = dijkstra.getPathLength(graph.getNode(targetNode.getId()));

		System.out.println(currentToStart < currentToTarget);
		System.out.println(currentToStart + " " + currentToTarget);
		if (currentToStart < currentToTarget) {
			return 1;
		} else if (currentToStart > currentToTarget ) {
			return -1;
		} else {
			return 0;
		}

	}

	public SocialNode getStartNode() {
		return startNode;
	}

	public void setStartNode(SocialNode startNode) {
		this.startNode = startNode;
	}

	public SocialNetwork getGraph() {
		return graph;
	}

	public void setGraph(SocialNetwork graph) {
		this.graph = graph;
	}

}
