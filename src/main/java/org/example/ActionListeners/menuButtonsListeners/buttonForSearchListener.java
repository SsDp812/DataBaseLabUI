package org.example.ActionListeners.menuButtonsListeners;

import org.example.Role;
import org.example.WindowsFrames.AdminWindows.SearchAdminForm;
import org.example.WindowsFrames.AdminWindows.SearchWindowForAdmin;
import org.example.WindowsFrames.UserWindows.SearchWindowForUser;
import org.example.WindowsFrames.UserWindows.SerchForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class buttonForSearchListener implements ActionListener {
    private Role role;
    private JFrame thisWindow;

    public buttonForSearchListener(Role role, JFrame thisWindow) {
        this.role = role;
        this.thisWindow = thisWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame searchWindow = switch (role) {
            case Admin -> new SearchAdminForm("Searh author",thisWindow);
            case User ->  new SerchForm("Searh author",thisWindow);
        };
        thisWindow.setVisible(false);
    }
}
