package org.rumeur.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.Dijkstra.Element;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Path;

public class Spread implements Runnable {
	private SocialNode startNode;
	private SocialNetwork graph;

	public Spread(SocialNode startNode, SocialNetwork graph) {
		super();
		this.startNode = startNode;
		this.graph = graph;
	}

	public void spread(SocialNode startNode) throws InterruptedException {
		boolean doSleep = false;

		Iterator<SocialNode> nodeIterator = startNode.getNeighborNodeIterator();
		ArrayList<SocialNode> contamined = new ArrayList<SocialNode>();

		do {
			SocialNode neighborNode = nodeIterator.next();
			if (neighborNode.getRumeur() == null && startNode.getRumeur().getTarget() != neighborNode
					&& startNode.getRumeur().getLauncher() != neighborNode) {
				doSleep = true;
				contamined.add(neighborNode);
				neighborNode.addAttribute("ui.class", startNode.getRumeur().getRumeurColor());
				neighborNode.setRumeur(startNode.getRumeur());
			}

			else if (neighborNode.getRumeur() == null && startNode.getRumeur().getTarget() == neighborNode) {
				neighborNode.setRumeur(new Rumeur(neighborNode, startNode.getRumeur().getLauncher(), "truth"));
				doSleep = true;
				contamined.add(neighborNode);
				Spread antispread = new Spread(neighborNode, graph);
				Thread antiSpreadThread = new Thread(antispread);
				antiSpreadThread.start();
			}

			if (neighborNode.getRumeur().getTarget() != startNode.getRumeur().getTarget()
					&& neighborNode.getRumeur().getTarget() != neighborNode
					&& startNode.getRumeur().getLauncher() != neighborNode
					&& startNode.getRumeur().getTarget() != neighborNode) {
				if (closerToSelf(neighborNode, startNode.getRumeur().getLauncher(),
						startNode.getRumeur().getTarget()) == 1) {
					neighborNode.setRumeur(startNode.getRumeur());
					neighborNode.changeAttribute("ui.class", neighborNode.getRumeur().getRumeurColor());
					contamined.add(neighborNode);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else if (closerToSelf(neighborNode, startNode.getRumeur().getLauncher(),
						startNode.getRumeur().getTarget()) == 0) {
					neighborNode.setRumeur(new Rumeur(startNode.getRumeur().getTarget(),
							neighborNode.getRumeur().getTarget(), "neutral"));

				}
				neighborNode.changeAttribute("ui.class", neighborNode.getRumeur().getRumeurColor());
			}
//			if (neighborNode.getRumeur().getTarget() != neighborNode
//					&& neighborNode.getRumeur().getLauncher() != neighborNode) {
//				Edge edge = startNode.getEdgeBetween(neighborNode);
//				if (edge != null) {
//					edge.setAttribute("ui.class", startNode.getRumeur().getRumeurColor());
//					edge.addAttribute("ui.color", 0);
//					for (double i = 0.1; i <= 1; i += 0.1) {
//						edge.changeAttribute("ui.color", i);
//						Thread.sleep(10);
//					}
//				}
//			}
		}while (nodeIterator.hasNext());

		if (doSleep) {
			try {
				Thread.sleep(200);
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
		try {
			spread(this.startNode);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int closerToSelf(SocialNode currentNode, SocialNode startNode, SocialNode targetNode) {
		//Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "weight");
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, null);
		double currentToStart;
		double currentToTarget;

		dijkstra.init(graph);
		dijkstra.setSource(graph.getNode(currentNode.getId()));
		dijkstra.compute();
		currentToStart = dijkstra.getPathLength(graph.getNode(startNode.getId()));
		currentToTarget = dijkstra.getPathLength(graph.getNode(targetNode.getId()));
		if (currentToStart < currentToTarget) {
			return 1;
		} else if (currentToStart > currentToTarget) {
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
