package org.example.WindowsFrames;

import org.example.ActionListeners.menuButtonsListeners.buttonForChangeTablesListener;
import org.example.ActionListeners.menuButtonsListeners.buttonForSearchListener;
import org.example.ActionListeners.menuButtonsListeners.buttonForViewReportsListener;
import org.example.ActionListeners.menuButtonsListeners.buttonForViewTablesListener;
import org.example.Role;

import javax.swing.*;
import java.awt.*;

public class MenuWindow extends StandartWindow {
    private Role role;
    private JButton buttonForViewTables;
    private JButton buttonForChangeTable;
    private JButton buttonForViewReports;
    private JButton buttonForSearch;

    public MenuWindow(Role role, JFrame backFrame) throws HeadlessException {
        super("Menu");
        this.role = role;
        this.backFrame = backFrame;
        backFrame.setVisible(true);
        initButtons();
        setListenersToButtons();
        initBackButton(backFrame,this);
        setLookAndFeel();
        this.setVisible(true);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initButtons(){
        String report = "";
        String search = "";
        if(role == Role.User){
            report = "Report of user fines";
            search = "Search authors";
        }else{
            report = "Report of supplies";
            search = "Search user(admin func)";
        }
        buttonForViewTables = new JButton("View tables");
        buttonForChangeTable = new JButton("Change tables");
        buttonForViewReports = new JButton(report);
        buttonForSearch = new JButton(search);

        // Set button layout and font
        Font buttonFont = new Font("Arial", Font.PLAIN, 20);
        Color buttonBGColor = Color.WHITE; // Gray color for buttons background
        Color buttonTextColor = Color.BLACK; // White text color
        buttonForViewTables.setBounds(275,80,300,80);
        buttonForViewTables.setBackground(buttonBGColor);
        buttonForViewTables.setForeground(buttonTextColor);
        buttonForViewTables.setFont(buttonFont);
        container.add(buttonForViewTables);

        buttonForChangeTable.setBounds(275,200,300,80);
        buttonForChangeTable.setBackground(buttonBGColor);
        buttonForChangeTable.setForeground(buttonTextColor);
        buttonForChangeTable.setFont(buttonFont);
        container.add(buttonForChangeTable);

        buttonForViewReports.setBounds(275,300,300,80);
        buttonForViewReports.setBackground(buttonBGColor);
        buttonForViewReports.setForeground(buttonTextColor);
        buttonForViewReports.setFont(buttonFont);
        container.add(buttonForViewReports);

        buttonForSearch.setBounds(275,400,300,80);
        buttonForSearch.setBackground(buttonBGColor);
        buttonForSearch.setForeground(buttonTextColor);
        buttonForSearch.setFont(buttonFont);
        container.add(buttonForSearch);


    }

    public void setListenersToButtons(){
        buttonForViewTables.addActionListener(new buttonForViewTablesListener(role,this));
        buttonForChangeTable.addActionListener(new buttonForChangeTablesListener(role,this));
        buttonForViewReports.addActionListener(new buttonForViewReportsListener(role,this));
        buttonForSearch.addActionListener(new buttonForSearchListener(role,this));
    }
}