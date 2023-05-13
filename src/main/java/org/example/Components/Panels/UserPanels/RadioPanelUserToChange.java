package org.example.Components.Panels.UserPanels;

import org.example.Components.Panels.RadioButtonsPanel;

import javax.swing.*;
import java.awt.*;

public class RadioPanelUserToChange implements RadioButtonsPanel {

    private JPanel panel;
    private ButtonGroup buttonGroup;
    private JButton buttonToLoad;

    private String selectedTable;

    public RadioPanelUserToChange(){
        panel = new JPanel(new GridLayout(0,1));
        buttonGroup = new ButtonGroup();

        JRadioButton users = new JRadioButton("users",true);
        selectedTable = users.getText();

        panel.add(users);

        buttonToLoad = new JButton("Load");
        buttonToLoad.addActionListener(e -> {
            if(buttonGroup.getSelection() != null){
                selectedTable = buttonGroup.getSelection().getActionCommand();
                System.out.println(selectedTable);
            }
        });


        buttonGroup.add(users);
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public String getSelectedTable() {
        return selectedTable;
    }

    @Override
    public JButton getButtonToLoad() {
        return buttonToLoad;
    }
}
