package graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class XYLineChart extends ApplicationFrame {

	private static final long serialVersionUID = 1L;

	private XYLineChart(String applicationTitle , String chartTitle, String seriesName, double[][] dataSet) {
		super(applicationTitle);
		JFreeChart lineChart = ChartFactory.createXYLineChart(chartTitle,"Epoch","Loss", createDataset(seriesName,dataSet));
		ChartPanel chartPanel = new ChartPanel( lineChart );
		chartPanel.setPreferredSize( new java.awt.Dimension( 1000 , 500 ) );
		setContentPane( chartPanel );
	}

	private static DefaultXYDataset createDataset(String seriesName, double[][] dataSet) {
		DefaultXYDataset ds = new DefaultXYDataset();
		ds.addSeries(seriesName,dataSet);
		return ds;
	}

	public static void plot(String applicationTitle , String chartTitle, String seriesName, double[][] dataSet) {
		XYLineChart chart = new XYLineChart(applicationTitle,chartTitle, seriesName, dataSet);
		chart.pack( );
		RefineryUtilities.centerFrameOnScreen( chart );
		chart.setVisible( true );
	}
}
