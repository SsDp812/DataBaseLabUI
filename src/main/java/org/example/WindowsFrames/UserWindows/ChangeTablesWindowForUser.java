package org.example.WindowsFrames.UserWindows;

import org.example.Components.Panels.UserPanels.RadioPanelUserToChange;
import org.example.WindowsFrames.AdminWindows.ChangeTablesWindowForAdmin;
import org.example.WindowsFrames.StandartWindow;
import org.example.WindowsFrames.dialogWindows.deleteWindow;
import org.example.WindowsFrames.dialogWindows.insertWindow;
import org.example.WindowsFrames.dialogWindows.updateWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeTablesWindowForUser extends ChangeTablesWindowForAdmin {
    private JButton updateButton;
    private JButton insertButton;
    private JButton deleteButton;
    public ChangeTablesWindowForUser(String title, JFrame back) throws Exception {
        super(title,back);
        container.remove(leftPanel.getPanel());
        RadioPanelUserToChange panel = new RadioPanelUserToChange();
        JPanel panel1 = panel.getPanel();
        panel1.setBounds(20,80,250,530);
        container.add(panel1);
        this.repaint();

    }

}
