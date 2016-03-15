package org.rumeur.main;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
 
public class PieChart_AWT_Weight extends ApplicationFrame 
{
	String title;
	double[] donnees;
   public PieChart_AWT_Weight( String title, double[] nbNodeResult) 
   {
      super( title ); 
      this.title = title;
      this.donnees = nbNodeResult;
      setContentPane(createDemoPanel( ));
   }
   
   private PieDataset createDatasetForNbNode() 
   {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      double result0 = this.donnees[0]/this.donnees[3]*100;
      double result1 = this.donnees[1]/this.donnees[3]*100;
      double result2 = this.donnees[2]/this.donnees[3]*100;
      dataset.setValue("Poids pour rumeur", result0);
      dataset.setValue("Poids pour contre rumeur", result1);
      dataset.setValue("Poids neutre", result2);         
      return dataset;         
   }
   
   private JFreeChart createChart( PieDataset dataset )
   {
      JFreeChart chart = ChartFactory.createPieChart(      
         this.title + ", total poids = " + this.donnees[3],  // chart title 
         dataset,        // data    
         true,           // include legend   
         true, 
         false);

      return chart;
   }
   
   public JPanel createDemoPanel( )
   {
      JFreeChart chart = createChart(createDatasetForNbNode( ) );  
      return new ChartPanel( chart ); 
   }
   
   public void init()
   {
      this.setSize( 560 , 367 );    
      RefineryUtilities.centerFrameOnScreen( this );    
      this.setVisible( true ); 
   }
}
