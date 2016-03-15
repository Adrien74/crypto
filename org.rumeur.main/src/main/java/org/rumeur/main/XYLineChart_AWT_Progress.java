package org.rumeur.main;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.awt.BasicStroke; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class XYLineChart_AWT_Progress extends ApplicationFrame 
{
	ArrayList<ProgressNode> progressNode;
   public XYLineChart_AWT_Progress( String applicationTitle, String chartTitle, ArrayList<ProgressNode> progressNode)
   {
      super(applicationTitle);
      
      this.progressNode = progressNode;
      
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Temps en milliseconde" ,
         "Nombre de noeuds en pourcentage" ,
         createDataset() ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      XYPlot plot = xylineChart.getXYPlot( );
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      Shape shape = new Ellipse2D.Double(0, 0, 0, 0);
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.BLUE );
      renderer.setSeriesPaint( 2 , Color.GRAY );
      renderer.setSeriesShape( 0 , shape );
      renderer.setSeriesShape( 1 , shape );
      renderer.setSeriesShape( 2 , shape );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
   }
   
   private XYDataset createDataset( )
   {
	   final XYSeries rumeur = new XYSeries( "Rumeur" );
	   final XYSeries contre = new XYSeries( "Contre rumeur" );
	   final XYSeries neutre = new XYSeries( "Neutre" );
	   for(ProgressNode current : this.progressNode) {
               
		   rumeur.add(  current.date.getTime() , current.rumeurNode );                    
                
		   contre.add(  current.date.getTime() , current.counterRumeurNode );                    
                
		   neutre.add(  current.date.getTime() , current.neutralNode );          

	   }
      
      
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries( rumeur );          
      dataset.addSeries( contre );          
      dataset.addSeries( neutre );
      return dataset;
   }

   public void init() 
   {
      this.pack( );          
      RefineryUtilities.centerFrameOnScreen( this );          
      this.setVisible( true ); 
   }
}