package org.example.WindowsFrames;

import org.example.ActionListeners.backButtonListener;
import org.example.Components.Panels.RadioButtonsPanel;

import javax.swing.*;
import java.awt.*;

public class StandartWindow extends JFrame {
    private int widSize;
    private int heigSize;
    private int x;
    private int y;
    protected RadioButtonsPanel leftPanel;
    protected Container container;
    protected JFrame backFrame;
    protected JButton backButton;

    public StandartWindow(String title) throws HeadlessException {
        super(title);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.widSize = 850;
        this.heigSize = 700;
        this.x = 200;
        this.y = 200;
        this.setBounds(x,y,widSize,heigSize);
        backFrame = null;
        this.setResizable(false);
        this.container = this.getContentPane();
        Color windowBGColor = Color.LIGHT_GRAY;
        container.setBackground(windowBGColor);
    }

    protected void initBackButton(JFrame prevWindow,JFrame thisWindow){
        backButton = new JButton("|Back>");
        backButton.setBounds(15,20,80,40);
        backButton.addActionListener(new backButtonListener(prevWindow,thisWindow));
        container.add(backButton);
    }
}
