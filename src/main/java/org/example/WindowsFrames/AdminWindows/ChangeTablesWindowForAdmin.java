package org.example.WindowsFrames.AdminWindows;

import org.example.Components.Panels.AdminPanels.RadoiButtunsPanelForTablesAdmin;
import org.example.WindowsFrames.StandartWindow;
import org.example.WindowsFrames.dialogWindows.deleteWindow;
import org.example.WindowsFrames.dialogWindows.insertWindow;
import org.example.WindowsFrames.dialogWindows.updateWindow;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeTablesWindowForAdmin extends ViewTablesWindowForAdmin {
    private JButton updateButton;
    private JButton insertButton;
    private JButton deleteButton;
    public ChangeTablesWindowForAdmin(String title, JFrame back) throws Exception {
        super(title,back);
        listenerToChangeButtons ChangeButtons = new listenerToChangeButtons();
        updateButton = new JButton("Update");
        updateButton.setActionCommand("update");
        updateButton.addActionListener(ChangeButtons);
        updateButton.setBounds(20,620,100,40);
        container.add(updateButton);
        insertButton = new JButton("Insert");
        insertButton.setActionCommand("insert");
        insertButton.addActionListener(ChangeButtons);
        insertButton.setBounds(160,620,100,40);
        container.add(insertButton);


        deleteButton = new JButton("Delete");
        deleteButton.setActionCommand("delete");
        deleteButton.addActionListener(ChangeButtons);
        deleteButton.setBounds(300,620,100,40);
        container.add(deleteButton);
    }

    protected void insert(){
        insertWindow window = new insertWindow("insert",dataTable.getTableName(),
                dataTable.getCountOfCols(), dataTable.getColNames());
    }

    protected void delete(){
        deleteWindow window = new deleteWindow("delte",dataTable.getTableName());
    }

    protected void update(){
        updateWindow window = new updateWindow("update", dataTable.getTableName(),
                dataTable.getCountOfCols(),dataTable.getColNames());
    }
    class listenerToChangeButtons implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "update" -> update();
                case "delete" -> delete();
                case "insert" -> insert();
            }
        }
    }
}
