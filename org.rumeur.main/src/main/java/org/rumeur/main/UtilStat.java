package org.rumeur.main;

import java.util.ArrayList;
import java.util.Iterator;

import org.graphstream.graph.Edge;

public class UtilStat {
	public static void calculStat (SocialNetwork graph, SocialNode startNode, SocialNode targetNode) {
		
		ArrayList<SocialNode> listNodeGossip = new ArrayList<SocialNode>();
		ArrayList<SocialNode> listNodeCounterGossip = new ArrayList<SocialNode>();
		ArrayList<SocialNode> listNodeNeutral = new ArrayList<SocialNode>();
		
		Iterator<SocialNode> nodeIterator = graph.getNodeIterator();
		do{
			SocialNode node=nodeIterator.next();
			if (node.getRumeur() != null && node.getRumeur().equals(startNode.getRumeur())) {
				listNodeGossip.add(node);
			} else if (node.getRumeur() != null && node.getRumeur().equals(targetNode.getRumeur())) {
				listNodeCounterGossip.add(node);
			} else {
				listNodeNeutral.add(node);
			}
		}while(nodeIterator.hasNext());
		
		double[] nbNodeResult = {listNodeGossip.size(), listNodeCounterGossip.size(),  listNodeNeutral.size(), App.SIZE_GRAPH};
		PieChart_AWT_Node PCA = new PieChart_AWT_Node("Nombre de noeuds par rumeur", nbNodeResult) ;
		PCA.init();
		
		int totDegRumeur =0, totDegContreRumeur=0, totDegNeutral = 0, totDeg=0;
		for (SocialNode inode : listNodeGossip) {
			Iterator<Edge> edgeIt = inode.getEdgeIterator();
			while (edgeIt.hasNext()) {
				Edge edge = edgeIt.next();
				totDegRumeur += 1;
				//totDegRumeur += (int) edge.getAttribute("weight");	
			}
		}
		for (SocialNode inode : listNodeCounterGossip) {
			Iterator<Edge> edgeIt = inode.getEdgeIterator();
			do {
				Edge edge = edgeIt.next();
				
				totDegContreRumeur += 1;
				//totDegContreRumeur += (int) edge.getAttribute("weight");	
			}while (edgeIt.hasNext());
			
			totDegContreRumeur += inode.getDegree();
		}
		for (SocialNode inode : listNodeNeutral) {
			Iterator<Edge> edgeIt = inode.getEdgeIterator();
			do {
				Edge edge = edgeIt.next();
				totDegNeutral += 1;
				//totDegNeutral += (int) edge.getAttribute("weight");	
			}while (edgeIt.hasNext());
		}
		totDeg = totDegRumeur + totDegContreRumeur + totDegNeutral;
		
		double[] nbInfluence = {totDegRumeur, totDegContreRumeur,  totDegNeutral, totDeg};
		PieChart_AWT_Weight PCA2 = new PieChart_AWT_Weight("Poids par rumeur", nbInfluence) ;
		PCA2.init();
		
		System.out.println("===== Statistiques en influence =====");
		System.out.println("Poids total d'influence pour rumeur : " + totDegRumeur);
		System.out.println("Poids total d'influence pour contre rumeur  " + totDegContreRumeur);
		System.out.println("Poids total des noeuds neutres : " + totDegNeutral);
		System.out.println("Pour une influence générale de " + totDeg);
		
		
		
		
		//TODO récupérer les 10 noeuds les plus influents
		//ArrayList<SocialNode> listNodeNeutral = new ArrayList<SocialNode>();
		
		
		
		
	}
}
