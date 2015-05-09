package testing;

import function.EulerDecay;
import printing.Window;
import system.Properties;

import java.awt.*;

/**
 * Created by Catalin on 5/9/2015 .
 */
public class PrintAlphas {

    Window w;
    EulerDecay decay;

    public PrintAlphas() {
        w = new Window();
    }

    public void printAlphas() {
        for (double i = Properties.TEST_DECAY_ALPHA_BEGIN; i <= Properties.TEST_DECAY_ALPHA_END; i += 0.01d) {
            decay = new EulerDecay(i);
            w.clear();
            for (double t = 0d; t <= Properties.TEST_DECAY_T_MAXIMUM; t += 0.1d) {
                decay.setT(t);
                double value = decay.getValue();
                value *= 330d;
                w.addPoint(new Point((int) (t * 80) + 10, (int) ((340 - (value + 10)) + 10)));
            }
            w.drawPoints();
            String s = ((int) (i * 100)) + "";
            while (s.length() < 3) {
                s = "0" + s;
            }
            w.setFileName(Properties.RESULT_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR
                    + Properties.DECAY_SUBDIRECTORY_OUTPUT_NAME + Properties.SYSTEM_PATH_SEPARATOR
                    + Properties.DECAY_FILE_OUTPUT_NAME + "-" + s);
            w.save();
        }
    }

}
