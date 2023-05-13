package org.example.WindowsFrames;

import org.example.Role;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends StandartWindow {
    private JLabel label;
    private JButton buttonForUser;
    private JButton buttonForAdmin;

    public MainWindow(int size, String title,int x, int y) throws HeadlessException {
        super("AppV1");
        backButton = null;
        buttonForAdmin = new JButton("Admin");
        buttonForUser = new JButton("User");
        buttonForAdmin.setBounds(300,180,200,80);
        buttonForAdmin.addActionListener(new GoToMenu(Role.Admin,this));
        buttonForUser.addActionListener(new GoToMenu(Role.User,this));
        buttonForUser.setBounds(300,310,200,80);
        container.add(buttonForUser);
        container.add(buttonForAdmin);
        label = new JLabel("Choose your role:");
        label.setBounds(310,120,200,40);
        label.setFont(new Font("Verdana", Font.PLAIN, 20));
        container.add(label);
        this.setVisible(true);
    }



    private class GoToMenu implements ActionListener {

        public GoToMenu(Role role, JFrame frame) {
            this.role = role;
            this.frame = frame;
        }
        Role role;
        JFrame frame;
        @Override
        public void actionPerformed(ActionEvent e) {
            MenuWindow menu = new MenuWindow(role,frame);
            frame.setVisible(false);
        }
    }
}
