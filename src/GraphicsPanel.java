import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;
import java.util.Arrays;
import java.awt.Graphics2D;

public class GraphicsPanel extends JPanel{
    
    int[] arrayl;
    int[] highlights;
    
    public GraphicsPanel(int[] array, int[] highlights){
        

        this.arrayl = new int[array.length];
        for(int i = 0; i<array.length; i++){
            arrayl[i] = array[i];
        }

        this.highlights = new int[highlights.length];
        this.highlights[0] = highlights[0];
        this.highlights[1] = highlights[1];

        //Debug
        //System.out.println("Array to be displayed: " + Arrays.toString(array));
    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //System.out.println("repainting");


        int widthBar = this.getWidth() / arrayl.length;
        int largestElement = 0;
        for(int i : arrayl){
            if(i > largestElement){
                largestElement = i;
            }
        }
        int heightBarPixel = this.getHeight() / largestElement;

    
        Rectangle2D.Double background = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.black);
        g2d.fill(background);

        for(int i = 0; i<arrayl.length; i++){
/* 
            int barHeight = getHeight() - heightBarPixel*arrayl[i];
            int topleft = heightBarPixel*arrayl[i]; */


            int barHeight = heightBarPixel*arrayl[i];
            int topleft = getHeight() - (heightBarPixel*arrayl[i]);
            if(i == highlights[0] || i== highlights[1]){
                Rectangle2D.Double r = new Rectangle2D.Double(i*widthBar, topleft, widthBar, barHeight);
                g2d.setColor(Color.red);
                g2d.fill(r);
            }else{
                Rectangle2D.Double r = new Rectangle2D.Double(i*widthBar, topleft, widthBar, barHeight);
                g2d.setColor(Color.yellow);
                g2d.fill(r);
            }
            
            
        }
     

      //System.out.println("width: " + getWidth() + " height: " + getHeight());
    }

}
