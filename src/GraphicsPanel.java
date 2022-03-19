import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;
import java.util.Arrays;
import java.awt.Graphics2D;

public class GraphicsPanel extends JPanel {

    int[] arrayl;
    int[] highlights;

    public GraphicsPanel(int[] array, int[] highlights) {

        this.arrayl = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayl[i] = array[i];
        }

        this.highlights = highlights.clone();
       

        // Debug
        // System.out.println("Array to be displayed: " + Arrays.toString(array));
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // System.out.println("repainting");


        //TODO: Possible problem with the width being to small for large Arrays
        Double widthBar = (double) (getWidth() /  (double) arrayl.length);
       

        int largestElement = 0;
        for (int i : arrayl) {
            if (i > largestElement) {
                largestElement = i;
            }
        }


        double heightBarPixel = (double) (this.getHeight() /  (double) largestElement);
        


        Rectangle2D.Double background = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.black);
        g2d.fill(background);

        System.out.println("drawing: " + Arrays.toString(arrayl));

        for (int i = 0; i < arrayl.length; i++) {
            /*
             * int barHeight = getHeight() - heightBarPixel*arrayl[i];
             * int topleft = heightBarPixel*arrayl[i];
             */

            Double barHeight = (double) (heightBarPixel * arrayl[i]);
            Double topleft = (double) (getHeight() - (heightBarPixel * arrayl[i]));
            if (i == highlights[0]) {
                Rectangle2D.Double rbar = new Rectangle2D.Double(i * widthBar, topleft, widthBar, barHeight);
                Rectangle2D.Double rborder = new Rectangle2D.Double(i * widthBar, topleft, widthBar, barHeight);
                g2d.setColor(Color.red);
                g2d.fill(rbar);
                g2d.setColor(Color.BLACK);

                if(widthBar > 1){
                    g2d.draw(rborder);
                }
                
            } else {
                Rectangle2D.Double rbar = new Rectangle2D.Double(i * widthBar, topleft, widthBar, barHeight);
                Rectangle2D.Double rborder = new Rectangle2D.Double(i * widthBar, topleft, widthBar, barHeight);
                g2d.setColor(Color.yellow);
                g2d.fill(rbar);
                g2d.setColor(Color.BLACK);

                if(widthBar > 1){
                    g2d.draw(rborder);
                }
            }

        }

    }

}
