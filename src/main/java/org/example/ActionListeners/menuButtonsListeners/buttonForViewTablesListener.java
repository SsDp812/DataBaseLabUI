package org.example.ActionListeners.menuButtonsListeners;

import org.example.Role;
import org.example.WindowsFrames.AdminWindows.ViewTablesWindowForAdmin;
import org.example.WindowsFrames.UserWindows.ViewTablesWindowForUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class buttonForViewTablesListener implements ActionListener {
    private Role role;
    private JFrame thisWindow;

    public buttonForViewTablesListener(Role role, JFrame thisWindow) {
        this.role = role;
        this.thisWindow = thisWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame viewTablesWindow = switch (role) {
            case Admin -> {
                try {
                    yield new ViewTablesWindowForAdmin("View tables", thisWindow);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            case User -> {
                try {
                    yield new ViewTablesWindowForUser("View tables", thisWindow);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        thisWindow.setVisible(false);
    }
}
