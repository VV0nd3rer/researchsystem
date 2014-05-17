package controller;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class DrawGraph {
    private XYSeries createXYChart(/*float[][] points*/ FuzzyElement[] elements, String title) {
        XYSeries series = new XYSeries(title);
            for (int i = 0; i < elements.length; i++) {
                series.add(elements[i].getX(), elements[i].getY()); 
        }
        return series;
    }
    private XYSeriesCollection addSeries(XYSeries series) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }
    private void generateGraph(XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart("Chart", "x", "y", dataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setDomainZoomable(true);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(chartPanel, BorderLayout.NORTH);
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        try {
            ChartUtilities.saveChartAsJPEG(new File("C:\\chart.jpeg"), chart, 1000, 800);
        } catch (IOException ex) {
            System.out.println("Problem occurred creating chart " + ex);
        }
    }
    public void Draw(/*float[][] points*/ List<FuzzyNumber> points) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (FuzzyNumber number :points) 
            dataset.addSeries(createXYChart(number.getNumber(), number.getDlpTitle()));
        generateGraph(dataset);
    }
}
