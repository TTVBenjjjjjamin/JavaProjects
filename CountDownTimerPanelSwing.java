package project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Write a description  here.
 *
 * @author Ferguson
 * @version August 7, 2021
 */
public class CountDownTimerPanelSwing extends JPanel {

	private CountDownTimer watch;
    private Timer javaTimer;

    private JButton startButton, stopButton, saveButton, loadButton, addButton, stringInputButton, continueButton, subButton;;
    private JTextField hourField, minField, secondField, addSecondsField, newStringField, subSecondsField;

    private JLabel lblTime;

    public CountDownTimerPanelSwing() {

        // create the game object as well as the GUI1024 Frame
        watch = new CountDownTimer();
        javaTimer = new Timer(1000, new TimerListener());

        setLayout(new GridLayout(9, 2));
        setBackground(Color.lightGray);

       // Code goes on...
        startButton = new JButton("Start");
        add(startButton);

        stopButton = new JButton("Stop");
        add(stopButton);

        saveButton = new JButton("Save");
        add(saveButton);

        loadButton = new JButton("Load");
        add(loadButton);

        addButton = new JButton("Add");
        add(addButton);

        subButton = new JButton("Sub");
        add(subButton);

        continueButton = new JButton("Continue");
        add(continueButton);

        lblTime = new JLabel();
        lblTime.setText(watch.toString());
        add(lblTime);

        add(new JLabel("Time:"));

        startButton.addActionListener(new ButtonListener());
        // Code goes on...
        stopButton.addActionListener(new ButtonListener());

        addButton.addActionListener(new ButtonListener());

        subButton.addActionListener(new ButtonListener());

        continueButton.addActionListener(new ButtonListener());

        add(new JLabel("Hours"));
        hourField = new JTextField();
        add(hourField);

        add(new JLabel("Minutes"));
        minField = new JTextField();
        add(minField);

        add(new JLabel("Seconds"));
        secondField = new JTextField();
        add(secondField);

        add(new JLabel("Add"));
        addSecondsField = new JTextField();
        add(addSecondsField);

        add(new JLabel("Sub"));
        subSecondsField = new JTextField();
        add(subSecondsField);
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == continueButton){
               lblTime.getText();
            }

            if (event.getSource() == subButton){
                try {
                    watch.sub(Integer.parseInt(subSecondsField.getText()));
                    lblTime.setText(watch.toString());
                } catch (IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == addButton){
                try {
                    watch.add(Integer.parseInt(addSecondsField.getText()));
                    lblTime.setText(watch.toString());
                } catch (IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == stopButton) {
                javaTimer.stop();
            }

            if (event.getSource() == startButton) {
            	int mins, sec, milli;
                try {
                    mins = Integer.parseInt(hourField.getText());
                    sec = Integer.parseInt(minField.getText());
                    milli = Integer.parseInt(secondField.getText());
                    watch = new CountDownTimer(mins, sec, milli);
                    javaTimer.start();
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            lblTime.setText(watch.toString());
        }

    }

    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                watch.sub(1);
                lblTime.setText(watch.toString());
            }
            catch (Exception exception) {

			}
        }
    }
}
