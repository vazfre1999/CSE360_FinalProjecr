import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ScatterPlot extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;

    public ScatterPlot(String title) {
        super(title);

        // Create dataset
        XYDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Attendance Report",
                "Percentage of Lecture Attended (min attended / total min of lecture)", "Number of Students", dataset);


        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,228,196));


        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        //Boys (Age,weight) series
        XYSeries serieguessString = new XYSeries("Boys");
        serieguessString.add(1, 72.9);
        serieguessString.add(2, 81.6);
        serieguessString.add(3, 88.9);
        serieguessString.add(4, 96);
        serieguessString.add(5, 102.1);
        serieguessString.add(6, 108.5);
        serieguessString.add(7, 113.9);
        serieguessString.add(8, 119.3);
        serieguessString.add(9, 123.8);
        serieguessString.add(10, 124.4);

        dataset.addSeries(serieguessString);

        //Girls (Age,weight) series
        XYSeries series2 = new XYSeries("Girls");
        series2.add(1, 72.5);
        series2.add(2, 80.1);
        series2.add(3, 87.2);
        series2.add(4, 94.5);
        series2.add(5, 101.4);
        series2.add(6, 107.4);
        series2.add(7, 112.8);
        series2.add(8, 118.2);
        series2.add(9, 122.9);
        series2.add(10, 123.4);

        dataset.addSeries(series2);

        return dataset;
    }

}
