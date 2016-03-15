package org.rumeur.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Edge;

/**
 * Hello world!
 *
 */
public class App {

	public final static int SIZE_GRAPH = 100;
	public static final int NB_EDGES_MAX_PER_NODE = 2;

	public static void main(String[] args) {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		File cssFile = new File("ressources/style.css");
		SocialNetwork graph = new SocialNetwork("Barabàsi-Albert");
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");

		// Between 1 and 3 new links per node added.
		Generator gen = new BarabasiAlbertGenerator(NB_EDGES_MAX_PER_NODE);
		// Generate 100 nodes:
		gen.addSink(graph);
		gen.begin();

		while (gen.nextEvents()) {
			if (graph.getNodeCount() >= SIZE_GRAPH) {
				break;
			}
		}

		gen.end();

		Random rand = new Random();

		Iterator<Edge> edgeIterator = graph.getEdgeIterator();
		 do {
			Edge edge = edgeIterator.next();
			//int randNumber = rand.nextInt(1 - 1) + 1;
//			edge.addAttribute("label", randNumber);
			//edge.addAttribute("weight", randNumber);

		}while (edgeIterator.hasNext());

		Iterator<SocialNode> nodeIterator = graph.getNodeIterator();
		do {
			SocialNode node = nodeIterator.next();
			double degree = node.getDegree();
			degree = degree / 10;
			degree = 30 * degree;
			node.addAttribute("ui.style", "size :" + degree + ";");
//			node.addAttribute("label", node.getId());
		}while (nodeIterator.hasNext()) ;

		graph.display();

		System.out.println(cssFile.toURI().toString());
		graph.addAttribute("ui.stylesheet", "url(" + cssFile.toURI().toString() + ")");

		int first = rand.nextInt(SIZE_GRAPH - 1) + 1;
		int target = first;
		while (target == first) {
			target = rand.nextInt(SIZE_GRAPH - 1) + 1;
		}

		SocialNode startNode = graph.getNode(first);
		SocialNode targetNode = graph.getNode(target);

		targetNode.addAttribute("ui.class", "target");
		startNode.setRumeur(new Rumeur(startNode, targetNode, "infect"));
		startNode.addAttribute("ui.class", "start");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Spread spreadO = new Spread(startNode, graph);
		Thread spreadThread = new Thread(spreadO);
		spreadThread.start();

		ArrayList<ProgressNode> progressNode = new ArrayList<ProgressNode>();
		while (Thread.activeCount() > 3) {
			double rumeur = 0, contre = 0, neutre = 0;
			Iterator<SocialNode> nodeIt = graph.getNodeIterator();
			do {
				SocialNode node = nodeIt.next();
				if (node.getRumeur() != null && node.getRumeur().equals(startNode.getRumeur())) {
					rumeur++;
				} else if (node.getRumeur() != null && node.getRumeur().equals(targetNode.getRumeur())) {
					contre++;
				} else {
					neutre++;
				}
			}while (nodeIt.hasNext());
			ProgressNode currentProgressNode = new ProgressNode(rumeur, contre, neutre, new Date());
			progressNode.add(currentProgressNode);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		XYLineChart_AWT_Progress chart = new XYLineChart_AWT_Progress("Progression des rumeurs dans le temps",
				"Progression des rumeurs dans le temps", progressNode);

		UtilStat.calculStat(graph, startNode, targetNode);
		chart.init();

	}

}
