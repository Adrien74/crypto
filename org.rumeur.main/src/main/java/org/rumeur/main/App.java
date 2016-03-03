package org.rumeur.main;
import java.io.File;


import org.graphstream.algorithm.generator.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
    	Graph graph = new SingleGraph("Barabàsi-Albert");
    	// Between 1 and 3 new links per node added.
    	Generator gen = new BarabasiAlbertGenerator(6);
    	// Generate 100 nodes:
    	gen.addSink(graph); 
    	gen.begin();

    	for(int i=0; i<20; i++) {
    		gen.nextEvents();
    	}

    	gen.end();
    	
    	File cssFile = new File("ressources/style.css");
    	System.out.println(cssFile.toURI().toString());
    	graph.addAttribute("ui.stylesheet", "url(" +cssFile.toURI().toString()+")");

    	graph.display();

    }
}
