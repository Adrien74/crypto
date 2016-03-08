package org.rumeur.main;
import java.io.File;

import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import java.util.Random;
/**
 * Hello world!
 *
 */
public class App 
{
	public final static int SIZE_GRAPH = 30; 
	
    public static void main( String[] args )
    {
    	System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
    	File cssFile = new File("ressources/style.css");
    	SocialNetwork graph = new SocialNetwork("Barabàsi-Albert");
    	graph.addAttribute("ui.quality");
    	graph.addAttribute("ui.antialias");


    	// Between 1 and 3 new links per node added.
    	Generator gen = new BarabasiAlbertGenerator(2);
    	// Generate 100 nodes:
    	gen.addSink(graph); 
    	gen.begin();
    	
    	
    	while(gen.nextEvents())
    	{
    		if(graph.getNodeCount() >= SIZE_GRAPH){
    			break;
    		}
    	}

    	gen.end();
    	graph.display();
    	
    	
    	
    	System.out.println(cssFile.toURI().toString());
    	graph.addAttribute("ui.stylesheet", "url(" +cssFile.toURI().toString()+")");
    	
    	Random rand = new Random();
    	int first = rand.nextInt(SIZE_GRAPH);
    	int target  = first;
    	while(target == first)
    	{
    		target  = rand.nextInt(SIZE_GRAPH);
    	}
    	
    	SocialNode startNode = graph.getNode(first);
    	SocialNode targetNode = graph.getNode(target);
    	
    	targetNode.addAttribute("ui.class", "target");
    	
    	startNode.setRumeur(new Rumeur(startNode,targetNode, "infect"));
		startNode.addAttribute("ui.class", "start"); 	
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	Spread spreadO = new Spread(startNode, graph);
    	Thread spreadThread = new Thread (spreadO);
    	spreadThread.run();

    }
    
    
}
