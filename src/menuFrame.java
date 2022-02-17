import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.*;

public class menuFrame extends JFrame implements ActionListener {

    GridBagConstraints c;
    JLabel label;


    JPanel arraySizePanel;

    JTextField arrLengthField;
    JLabel arraySizeLabel;

    JButton size10Button;
    JButton size50Button;
    JButton size100Button;

    JPanel normalRadioButtonPanel;
    JRadioButton bubbleSortButton;
    JRadioButton selectionSortButton;
    JRadioButton insertionSortButton;

    JButton startButton;

    public menuFrame() {

        c = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        label = new JLabel("Sort Visualizer");
        label.setFont(new Font("Arial", Font.BOLD, 40));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        add(label, c);

        arraySizePanel = new JPanel();
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1,2));
        arraySizePanel.setLayout(new GridLayout(2, 0));
        arraySizePanel.setBorder(new TitledBorder("Array"));

        
        arrLengthField = new JTextField();
        arrLengthField.setSize(100, 100);
        arraySizeLabel = new JLabel("Array Size");
        top.add(arrLengthField);
        top.add(arraySizeLabel);


        size10Button = new JButton("10");
        size50Button = new JButton("50");
        size100Button = new JButton("100");
        size10Button.addActionListener(this);
        size50Button.addActionListener(this);
        size100Button.addActionListener(this);
        
        arraySizePanel.add(top);
        arraySizePanel.add(size10Button);
        arraySizePanel.add(size50Button);
        arraySizePanel.add(size100Button);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        add(arraySizePanel, c);


       /*  arrLengthField.setText("10");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        add(arrLengthField, c);
        c.gridx = 2;
        c.gridwidth = 1;
        add(arraySizeLabel, c);
 */
       

        /* c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        add(size10Button, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        add(size50Button, c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        add(size100Button, c);
 */
        normalRadioButtonPanel = new JPanel();
        normalRadioButtonPanel.setLayout(new FlowLayout());
        normalRadioButtonPanel.setBorder(new TitledBorder("Standard"));
        bubbleSortButton = new JRadioButton("Bubble Sort");
        selectionSortButton = new JRadioButton("Selction Sort");
        insertionSortButton = new JRadioButton("Insertion Sort");
        bubbleSortButton.addActionListener(this);
        selectionSortButton.addActionListener(this);
        insertionSortButton.addActionListener(this);
        normalRadioButtonPanel.add(bubbleSortButton);
        normalRadioButtonPanel.add(selectionSortButton);
        normalRadioButtonPanel.add(insertionSortButton);


        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        add(normalRadioButtonPanel, c);

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        add(startButton, c);

        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == size10Button) {
            arrLengthField.setText("10");

        }

        if (e.getSource() == size50Button) {
            arrLengthField.setText("50");
        }

        if (e.getSource() == size100Button) {
            arrLengthField.setText("100");
        }

        if (e.getSource() == bubbleSortButton) {
            selectionSortButton.setSelected(false);

        }

        if (e.getSource() == selectionSortButton) {
            bubbleSortButton.setSelected(false);

        }

        if (e.getSource() == startButton && bubbleSortButton.isSelected()) {

            try {
                int size = Integer.parseInt(arrLengthField.getText());
                new SortFrame(size, "bubble");
            } catch (Exception eee) {
                eee.printStackTrace();
            }

        } else if (e.getSource() == startButton && selectionSortButton.isSelected()) {

            try {
                int size = Integer.parseInt(arrLengthField.getText());
                new SortFrame(size, "selection");
            } catch (Exception eee) {
                eee.printStackTrace();
            }

        }

    }

}
