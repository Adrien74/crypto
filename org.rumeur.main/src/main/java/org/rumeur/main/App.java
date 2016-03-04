package org.rumeur.main;
import java.io.File;


import org.graphstream.algorithm.generator.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.util.Iterator;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
    	File cssFile = new File("ressources/style.css");
    	SocialNetwork graph = new SocialNetwork("Barabàsi-Albert");
    	// Between 1 and 3 new links per node added.
    	Generator gen = new BarabasiAlbertGenerator(1);
    	// Generate 100 nodes:
    	gen.addSink(graph); 
    	gen.begin();

    	for(int i=0; i<999; i++) {
    		gen.nextEvents();
    	}

    	gen.end();
    	graph.display();
    	
    	
    	
    	System.out.println(cssFile.toURI().toString());
    	graph.addAttribute("ui.stylesheet", "url(" +cssFile.toURI().toString()+")");
    	
    	SocialNode startNode = graph.getNode(1);
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	graph.spread(startNode);
    	
    		// TODO Dijkstra 
    }
    
    
}
