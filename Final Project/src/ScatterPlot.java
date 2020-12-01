import java.awt.Color;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ScatterPlot extends JFrame {
    //private static final long serialVersionUID = 6294689542092367723L;

    private Information info;

    public void setRoster(Information info) {
        this.info = info;
    }
    public Information getRoster() {
        return info;
    }


    public ScatterPlot(String title, Information info) {
        super(title);
        this.info = info;
        // Create dataset
        XYDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Attendance Report",
                "Percentage of Lecture Attended (min attended / total min of lecture)", "Students", dataset);


        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,228,196));


        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for ( int dateColIndex = 0; dateColIndex < info.getDateColCount(); dateColIndex++) {
            XYSeries series = new XYSeries(info.getHeaderRow()[ 6 + dateColIndex]);
            int percentage = 0;
            int roundedPercentage = 0;
            int[] tallyArray = new int[11]; // each index represents a percentage tally. 0 = 0%, 1 = 10% ... 10 = 100%
            int tallyIndex = 0;

            for (int rowIndex = 0; rowIndex < info.getRosterRowCount(); rowIndex++) {

                percentage = (100*(info.getAttendancePoint(rowIndex, dateColIndex)))/75;
                roundedPercentage = ((percentage+5)/10)*10;
                if (roundedPercentage > 100) {
                    tallyIndex = 10;
                } else {
                    tallyIndex = roundedPercentage / 10;
                }

                tallyArray[tallyIndex]++;
            }

            for (int i = 0; i < 11; i++) {
                series.add(i*10, tallyArray[i]);
            }
            dataset.addSeries(series);
        }

        return dataset;
    }

}
