package org.example.ActionListeners.menuButtonsListeners;

import org.example.Role;
import org.example.WindowsFrames.AdminWindows.ViewReportsWindowForAdmin;
import org.example.WindowsFrames.UserWindows.ViewReportsWindowForUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class buttonForViewReportsListener implements ActionListener {
    private Role role;
    private JFrame thisWindow;

    public buttonForViewReportsListener(Role role, JFrame thisWindow) {
        this.role = role;
        this.thisWindow = thisWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame viewReportsWindow = switch (role) {
            case Admin -> {
                try {
                    yield new ViewReportsWindowForAdmin("View reports", thisWindow);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case User -> {
                try {
                    yield new ViewReportsWindowForUser("View reports", thisWindow);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        thisWindow.setVisible(false);
    }
}
