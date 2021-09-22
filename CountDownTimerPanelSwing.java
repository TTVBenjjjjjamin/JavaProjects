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

    private JButton startButton, stopButton, saveButton, loadButton, addButton,
            stringInputButton, continueButton, subButton, incButton, decButton;;
    private JTextField hourField, minField, secondField, addSecondsField, newStringField,
            subSecondsField;

    private JLabel lblTime;

    public CountDownTimerPanelSwing() {

        // create the game object as well as the GUI1024 Frame
        watch = new CountDownTimer();
        javaTimer = new Timer(1000, new TimerListener());


        setLayout(new GridLayout(12, 2));
        setBackground(Color.lightGray);

        add(new JLabel("Hours:"));
        hourField = new JTextField();
        hourField.setText("0");
        add(hourField);

        add(new JLabel("Minutes:"));
        minField = new JTextField();
        minField.setText("0");
        add(minField);

        add(new JLabel("Seconds:"));
        secondField = new JTextField();
        secondField.setText("0");
        add(secondField);

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

        addSecondsField = new JTextField();
        addSecondsField.setText("0");
        add(addSecondsField);

        subButton = new JButton("Sub");
        add(subButton);

        subSecondsField = new JTextField();
        subSecondsField.setText("0");
        add(subSecondsField);

        stringInputButton = new JButton("New");
        add(stringInputButton);

        newStringField = new JTextField();
        newStringField.setText("0:0:0");
        add(newStringField);

        incButton = new JButton("Inc");
        add(incButton);

        decButton = new JButton("Dec");
        add(decButton);

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

        incButton.addActionListener(new ButtonListener());

        decButton.addActionListener(new ButtonListener());

        loadButton.addActionListener(new ButtonListener());

        stringInputButton.addActionListener(new ButtonListener());

    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == stringInputButton){
                CountDownTimer watch = new CountDownTimer(newStringField.getText());
                secondField.setText(String.format("%d", watch.getSeconds()));
                minField.setText(String.format("%d", watch.getMinutes()));
                hourField.setText(String.format("%d", watch.getHours()));
            }

            if (event.getSource() == decButton){
                watch.dec();
            }

            if (event.getSource() == incButton){
                watch.inc();
            }

            if (event.getSource() == continueButton){
                javaTimer.start();
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
                    //secondField.setText(String.format("%d", watch.getSeconds()));
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
                if (watch.seconds == 0 && watch.minutes == 0 && watch.hours == 0){
                    JOptionPane.showMessageDialog(null, "Happy New Year!!");
                }
            }
            catch (Exception exception) {

            }
        }
    }
}
