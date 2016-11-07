package biocomputation;

import java.awt.Color;
import java.awt.BasicStroke;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

public class XYLineChart_AWT extends ApplicationFrame {

    public XYLineChart_AWT(String applicationTitle, String chartTitle) throws IOException {
        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle,
                "Category",
                "Score",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(566, 380));
        final XYPlot plot = xylineChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);

        renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        renderer.setSeriesStroke(1, new BasicStroke(1.0f));

        plot.setRenderer(renderer);
        setContentPane(chartPanel);
    }

    private XYDataset createDataset() throws FileNotFoundException, IOException {
        final XYSeries mean = new XYSeries("Mean");
        final XYSeries best = new XYSeries("Best");
        String sCurrentLine;
        int counter = 0;
        BufferedReader br = new BufferedReader(new FileReader("mean.txt"));
        while ((sCurrentLine = br.readLine()) != null) {

            mean.add(counter, Integer.parseInt(sCurrentLine));
            counter++;
        }
        counter = 0;
        br.close();
        br = new BufferedReader(new FileReader("best.txt"));

        while ((sCurrentLine = br.readLine()) != null) {
            best.add(counter, Integer.parseInt(sCurrentLine));
            counter++;

        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(mean);
        dataset.addSeries(best);
        return dataset;
    }

    public XYLineChart_AWT(String title) throws IOException {
        super(title);
        XYLineChart_AWT chart = new XYLineChart_AWT( "Test","");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

}
