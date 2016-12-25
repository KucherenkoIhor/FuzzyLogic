package udhtu.fuzzy;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.io.File;
import java.io.IOException;

public class LineChart_AWT extends ApplicationFrame {
   public LineChart_AWT( String applicationTitle , String chartTitle, LinguistikVar clusterVar ) {
      super(applicationTitle);
      linguistikVar = clusterVar;

      final JFreeChart chart = ChartFactory.createXYLineChart(
              "Переменная",
              "Значение",
              "Мю",
              new XYSeriesCollection(createSeries()),
              PlotOrientation.VERTICAL,
              true,
              true,
              false
      );

      final ChartPanel chartPanel = new ChartPanel(chart);
      chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
      setContentPane(chartPanel);


      int width = 640; /* Width of the image */
      int height = 480; /* Height of the image */
      File fileChart = new File( chartTitle+".jpeg" );
      try {
         ChartUtilities.saveChartAsJPEG(fileChart, chart, width ,height);
      } catch (IOException e) {
         e.printStackTrace();
      }


   }

   private LinguistikVar linguistikVar;

   public static void drawChart(String name, LinguistikVar clusterVar) {
      LineChart_AWT chart = new LineChart_AWT(
              name,
              name,
              clusterVar);


      chart.pack();
      RefineryUtilities.centerFrameOnScreen(chart);
      chart.setVisible(true);
   }

   private XYSeries createSeries() {
      final XYSeries series = new XYSeries("Переменная");
      for(int i = 0; i < linguistikVar.edgesOfIntervals.length; i ++) {
         series.add(
                 linguistikVar.edgesOfIntervals[i],
                 linguistikVar.normalizeFrequency[i]);
      }
      return series;
   }

}