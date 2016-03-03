package org.rumeur.org.rumeur.main;
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
    	graph.addAttribute("ui.stylehseet", "node { size: 15px; fill-color: #D22; stroke-mode: plain; stroke-color: #999; shadow-mode: plain; shadow-width: 0px; shadow-color: #999; shadow-offset: 3px, -3px; }");

    	graph.display();

    }
}
