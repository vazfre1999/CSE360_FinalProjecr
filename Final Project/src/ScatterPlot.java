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

        // for all date columns (after roster info)
        for ( int dateColIndex = 0; dateColIndex < info.getDateColCount(); dateColIndex++) {
            XYSeries series = new XYSeries(info.getHeaderRow()[6 + dateColIndex]); // gets date from header string array
            int percentage;
            int roundedPercentage;
            int[] tallyArray = new int[11]; // each index represents a percentage tally. 0 = 0%, 1 = 10% ... 10 = 100%
            int tallyIndex;

            for (int rowIndex = 0; rowIndex < info.getRosterRowCount(); rowIndex++) {

                percentage = (100*(info.getAttendancePoint(rowIndex, dateColIndex)))/75; // based off 75 minute class session
                roundedPercentage = ((percentage+5)/10)*10; // rounds to nearest 10's place
                if (roundedPercentage > 100) { // student was there for longer than the session then set to 100% index (10)
                    tallyIndex = 10;
                } else {
                    tallyIndex = roundedPercentage / 10;
                }

                tallyArray[tallyIndex]++; // increment the tally at the representative percentage index
            }

            // for all percentage possibilities
            for (int i = 0; i < 11; i++) {
                series.add(i*10, tallyArray[i]); // add tally value at its percentage value
            }
            dataset.addSeries(series);
        }

        return dataset;
    }

}
