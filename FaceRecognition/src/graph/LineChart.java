package graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class LineChart extends ApplicationFrame {

	private static final long serialVersionUID = 1L;

	private LineChart(String applicationTitle , String chartTitle, Double[] xDataset, Double[] yDataSet) {
		super(applicationTitle);
		JFreeChart lineChart = ChartFactory.createLineChart(
				chartTitle,
				"Target","Input",
				createDataset(xDataset,yDataSet),
				PlotOrientation.VERTICAL,
				true,true,false);

		ChartPanel chartPanel = new ChartPanel( lineChart );
		chartPanel.setPreferredSize( new java.awt.Dimension( 1000 , 500 ) );
		setContentPane( chartPanel );
	}
	
	private static DefaultCategoryDataset createDataset(Double[] xDataSet, Double[] yDataSet) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		for (int i=0;i<xDataSet.length;i++) {
			dataset.addValue(  yDataSet[i] , "" , String.format("%.2f", xDataSet[i]) );
		}
		return dataset;
	}
	
	public static void plot(String applicationTitle , String chartTitle, Double[] xDataset, Double[] yDataSet) {
		LineChart chart = new LineChart(applicationTitle,chartTitle, xDataset, yDataSet);
		chart.pack( );
		RefineryUtilities.centerFrameOnScreen( chart );
		chart.setVisible( true );
	}

}
