package org.example.ActionListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class backButtonListener implements ActionListener {
    JFrame backFrameFF;
    JFrame thisFrame;

    public backButtonListener(JFrame backFrameFF, JFrame thisFrame) {
        this.backFrameFF = backFrameFF;
        this.thisFrame = thisFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        backFrameFF.setVisible(true);
        this.thisFrame.dispose();

    }
}