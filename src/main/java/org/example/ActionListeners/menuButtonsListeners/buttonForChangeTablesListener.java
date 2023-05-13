package org.example.ActionListeners.menuButtonsListeners;

import org.example.Role;
import org.example.WindowsFrames.AdminWindows.ChangeTablesWindowForAdmin;
import org.example.WindowsFrames.UserWindows.ChangeTablesWindowForUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class buttonForChangeTablesListener implements ActionListener {
    private Role role;
    private JFrame thisWindow;

    public buttonForChangeTablesListener(Role role, JFrame thisWindow) {
        this.role = role;
        this.thisWindow = thisWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame changeTablesListener = switch (role) {
            case Admin -> {
                try {
                    yield new ChangeTablesWindowForAdmin("Change tables", thisWindow);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            case User -> {
                try {
                    yield new ChangeTablesWindowForUser("Change tables", thisWindow);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        thisWindow.setVisible(false);
    }
}
