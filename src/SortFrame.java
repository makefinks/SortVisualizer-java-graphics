import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

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

    //Frame stats
    int maxFrames;
    int currFrame;

    int[] array;

    ArrayList<int[]> steps;
    ArrayList<int[]> highlights;

    public SortFrame(int arraySize, String algo) {


        drawPanel = new JPanel();

        setVisible(true);
        setLayout(new BorderLayout());

        array = generateArray(arraySize);

        /*
         * GraphicsPanel graphicsPanel = new GraphicsPanel(array);
         * add(graphicsPanel, BorderLayout.CENTER);
         */

        int start[] = { 0, 0 };
        drawPanel = new GraphicsPanel(array, start);
        add(drawPanel, BorderLayout.CENTER);
        setVisible(true);

        if (algo.equals("bubble")) {
            bubblesort(array);
        }

        if (algo.equals("selection")) {
            selectionsort(array);
        }

        if (algo.equals("insertion")){
            insertionSort(array);
        }

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

    int counter = 0;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == endButton) {

            if (timer != null) {
                timer.cancel();
            }
            dispose();
        }

        if (e.getSource() == nextFrameButton) {

            displayNewFrame();

        }

        if (e.getSource() == playButton) {

            System.out.println("playButton Pressed");

            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    displayNewFrame();
                }

            }, 1000, Integer.parseInt(delayField.getText()) * 10);

        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {

        if (e.getSource() == delaySlider) {
            delayField.setText(Integer.toString(delaySlider.getValue()));
        }

    }

    private void selectionsort(int[] arr) {

        steps = new ArrayList<int[]>();
        highlights = new ArrayList<int[]>();

        int n = arr.length;

        for (int i = 0; i < n-1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++){
                if (arr[j] < arr[min_idx]){
                   min_idx = j;
                  // highlights.add(new int[]{j});
                    }
                }
                   

            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
            int[] stepArray = arr.clone();
            highlights.add(new int[]{i});
            steps.add(stepArray);
            

        }

    }

    private void bubblesort(int[] arrayto) {

        int[] tempArray = new int[arrayto.length];

        for (int c = 0; c < tempArray.length; c++) {
            tempArray[c] = arrayto[c];
        }

        steps = new ArrayList<>();
        highlights = new ArrayList<>();

      

        int n = tempArray.length;
        int temp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (tempArray[j - 1] > tempArray[j]) {
                    // swap elements
                    temp = tempArray[j - 1];
                    tempArray[j - 1] = tempArray[j];
                    tempArray[j] = temp;

                    int[] stepArray = new int[tempArray.length];

                    for (int b = 0; b < tempArray.length; b++) {
                        stepArray[b] = tempArray[b];
                    }
                    highlights.add(new int[]{j});
                    steps.add(stepArray);

                    /*
                     * System.out.println(Arrays.toString(tempArray));
                     * steps.add(tempArray);
                     * System.out.println(steps.toString());
                     */

                }

            }
        }

       
    }

    public void insertionSort(int[] arr){

        System.out.println(Arrays.toString(arr));

        steps = new ArrayList<>();
        highlights = new ArrayList<>();
        
       int n = arr.length;
       for(int i = 1; i < n; ++i){
           int key = arr[i];
           int j = i - 1;

           while(j >= 0 && arr[j] > key){
               arr[j + 1] = arr[j];
               j = j-1;
               
           }
           arr[j+1] = key;
           highlights.add(new int[]{j});
           steps.add(arr.clone());
           
       }
       

    }


    public void displayNewFrame() {
        if (counter < steps.size()) {
            int[] frame = steps.get(counter);
            int[] highlight = highlights.get(counter);

            frameLabel.setText("Frame " + (counter+1) + " / " + steps.size());

            // playSwitchSound();

            // System.out.println("nextFrame pressed");

            remove(drawPanel);
            drawPanel = new GraphicsPanel(frame, highlight);

            this.add(drawPanel, BorderLayout.CENTER);
            setVisible(true);
            repaint();
            counter++;

            if (counter == steps.size()) {
                if (timer != null) {
                    timer.cancel();
                }
            }
        }
    }

    public void playSwitchSound() {

        File sound = new File("C:/Users/Leon/eclipse-workspace/SortVisualizer/src/switch.wav");

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(sound);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();

        } catch (UnsupportedAudioFileException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (LineUnavailableException e) {

            e.printStackTrace();
        }

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
