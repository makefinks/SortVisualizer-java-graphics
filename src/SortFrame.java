import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;

import Algorithms.BubbleSort;
import Algorithms.InsertionSort;
import Algorithms.QuickSort;
import Algorithms.SelectionSort;
import Algorithms.SortCallBack;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;


import javax.sound.sampled.*;

import javax.swing.*;

public class SortFrame extends JFrame implements ActionListener, ChangeListener {

    JPanel drawPanel;
    JPanel buttonPanelLeft;
    JPanel statsPanelTop;
    GridBagConstraints c;

    JTextField delayField;

    JButton nextFrameButton;
    JButton lastFrameButton;
    JButton endButton;
    JButton playButton;
    JLabel playLabel;
    JLabel frameLabel;
    JSlider delaySlider;

    Timer timer;

    int[] array;

    ArrayList<int[]> steps =  new ArrayList<>();
    ArrayList<int[]> highlights = new ArrayList<>();

    public SortFrame(int arraySize, String algo) {

        drawPanel = new JPanel();

        setVisible(true);
        setLayout(new BorderLayout());

        array = generateArray(arraySize);

        int start[] = { 0, 0 };
        drawPanel = new GraphicsPanel(array, start);
        add(drawPanel, BorderLayout.CENTER);
        setVisible(true);

        if (algo.equals("bubble")) {

           BubbleSort bubble = new BubbleSort();

            bubble.sort(array, new SortCallBack() {
                @Override
                public void update(int[] steps_, int[] highlights_) {
                    steps.add(steps_);
                    highlights.add(highlights_);
                }
            });

        }

        if (algo.equals("selection")) {
            SelectionSort select = new SelectionSort();
            select.sort(array, new SortCallBack(){
            @Override
            public void update(int[] steps_, int[] highlights_) {
                steps.add(steps_);
                highlights.add(highlights_);
            }
        });

        }

        if (algo.equals("insertion")){
            InsertionSort insert = new InsertionSort();
            insert.sort(array, new SortCallBack() {
            @Override
            public void update(int[] steps_, int[] highlights_) {
                steps.add(steps_);
                highlights.add(highlights_);
            }
            });

        }

        if(algo.equals("quick")){
            QuickSort quick = new QuickSort();
            quick.sort(array, new SortCallBack(){
            @Override
            public void update(int[] steps_, int[] highlights_) {
                steps.add(steps_);
                highlights.add(highlights_);
            }
        });
        }

        //Button Panel
        buttonPanelLeft = new JPanel();
        this.add(buttonPanelLeft, BorderLayout.WEST);

        buttonPanelLeft.setLayout(new GridBagLayout());
        c = new GridBagConstraints();

        nextFrameButton = new JButton("next Frame");
        lastFrameButton = new JButton("prev Frame");
        endButton = new JButton("end");
        playButton = new JButton("play");

        playLabel = new JLabel("delay in seconds");
        frameLabel = new JLabel();

        delayField = new JTextField(5);
        delayField.setText("2");

        delaySlider = new JSlider(0, 5, 2);
        delaySlider.setPaintTicks(true);
        delaySlider.setMajorTickSpacing(1);
        delaySlider.setPaintLabels(true);
        delaySlider.addChangeListener(this);

        endButton.addActionListener(this);
        nextFrameButton.addActionListener(this);
        playButton.addActionListener(this);
        lastFrameButton.addActionListener(this);

        // c.ipadx = 2;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        buttonPanelLeft.add(nextFrameButton, c);

        c.gridy = 1;
        buttonPanelLeft.add(lastFrameButton, c);

        c.gridy = 2;
        buttonPanelLeft.add(endButton, c);

        c.gridy = 3;
        buttonPanelLeft.add(playLabel, c);

        c.gridy = 4;
        buttonPanelLeft.add(delaySlider, c);

        c.gridy = 5;
        buttonPanelLeft.add(delayField, c);

        c.gridx = 0;
        c.gridy = 6;
        buttonPanelLeft.add(playButton, c);

        c.gridx = 0;
        c.gridy = 7;
        buttonPanelLeft.add(frameLabel, c);

        statsPanelTop = new JPanel();
        add(statsPanelTop, BorderLayout.NORTH);
        
        setSize(1000, 700);

    }

    //iterates over list 'steps'
    int counter = 0;
    @Override
    public void actionPerformed(ActionEvent e) {

        //Stop the timer when the end button is pressed
        if (e.getSource() == endButton) {
            if (timer != null) {
                timer.cancel();
            }
            dispose();
        }

        //show next Frame
        if (e.getSource() == nextFrameButton) {
            counter++;
            displayNewFrame();
        }

        //show last Frame
        if(e.getSource() == lastFrameButton){
            if(counter > 0){
                counter--;
                displayNewFrame();
            }
        }


        //play all frames
        if (e.getSource() == playButton) {
            System.out.println("playButton Pressed");
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    //increase counter while there are still frames
                    if(counter < steps.size()){
                        counter++;
                    }
                    //display new Frame
                    displayNewFrame();
                    //if there are no frames left, cancel the timer
                    if(counter == steps.size()){
                        timer.cancel();
                    }
                }
                //delay Settings
            }, 1000, Integer.parseInt(delayField.getText()) * 10);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == delaySlider) {
            delayField.setText(Integer.toString(delaySlider.getValue()));
        }
    }

    public void displayNewFrame() {
        if (counter < steps.size()) {

            int[] frame = steps.get(counter);
            int[] highlight = highlights.get(counter);

            frameLabel.setText("Frame " + (counter+1) + " / " + steps.size());

          //  playsound(highlights.get(counter)[0], array.length);

            //delete the current panel with the current frame and add a new one displaying the next Frame
            remove(drawPanel);
            drawPanel = new GraphicsPanel(frame, highlight);
            this.add(drawPanel, BorderLayout.CENTER);
            setVisible(true);
            repaint();
            //counter++

            if (counter == steps.size()) {
                if (timer != null) {
                    timer.cancel();
                }
            }
        }
    }

    public static void playsound(double depth, int size) {
		
        //TODO: Modify depth value to my liking
        depth = (depth / 10)+1;

		byte[] buf = new byte[1];;
	    AudioFormat af = new AudioFormat( (float )44100, 8, 1, true, false );
	    SourceDataLine sdl = null;
		try {
			sdl = AudioSystem.getSourceDataLine(af);
			sdl.open();
		} catch (Exception e) {
			
		}
	  
	    sdl.start();
	    for( int i = 0; i < 50 * (float )44100 / 1000; i++ ) {
	        double angle = i / ( (float )44100 / 440 ) * depth * Math.PI;
	        buf[ 0 ] = (byte )( Math.sin( angle ) * 100 );
	        sdl.write( buf, 0, 1 );
	    }
	    sdl.drain();
	    sdl.stop();
		
		
		
	}
	

    public int[] generateArray(int arraySize) {
        int[] temp = new int[arraySize];

        // fill with numbers
        for (int i = 1; i <= arraySize; i++) {
           temp[i - 1] = i;
        }
        
        // mix numbers
        for (int x = 0; x < arraySize; x++) {

            Random rand = new Random();
            int new_pos = rand.nextInt(arraySize);
            int n = temp[new_pos];
            temp[new_pos] = temp[x];
            temp[x] = n;
        }
        System.out.println(Arrays.toString(temp));
        return temp;
    }
}
