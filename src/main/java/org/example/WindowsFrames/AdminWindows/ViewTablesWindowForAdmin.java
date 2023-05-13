package org.example.WindowsFrames.AdminWindows;

import org.example.Components.Panels.AdminPanels.RadoiButtunsPanelForTablesAdmin;
import org.example.Components.Tables.DataTable;
import org.example.WindowsFrames.StandartWindow;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ViewTablesWindowForAdmin extends StandartWindow {

    protected DataTable dataTable;
    private JFrame thisFrame;
    private  JTableHeader header;
    public ViewTablesWindowForAdmin(String title, JFrame back) throws Exception {
        super(title);
        this.backFrame = back;
        thisFrame = this;
        initBackButton(backFrame,this);
        leftPanel = new RadoiButtunsPanelForTablesAdmin();
        JPanel panel = leftPanel.getPanel();
        panel.setBounds(20,80,250,530);
        container.add(panel);
        JButton buttonToLoad = leftPanel.getButtonToLoad();
        buttonToLoad.setBounds(120,20,100,40);

        buttonToLoad.addActionListener(new newLoadTable());

        container.add(buttonToLoad);
        dataTable = new DataTable(leftPanel.getSelectedTable());
        JTable table = dataTable.getTable();
        header = dataTable.getTable().getTableHeader();
        header.setBounds(300,20,500,40);
        container.add(header);
        table.setBounds(300,60,500,590);
        for(int i = 0; i < dataTable.getCountOfCols();i++){
            dataTable.getTable().getColumnModel().getColumn(i).setHeaderValue(dataTable.getColNames()[i]);
        }
        dataTable.getTable().getTableHeader().resizeAndRepaint();
        container.add(dataTable.getTable());
        this.setVisible(true);

    }
    class newLoadTable implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("!" + leftPanel.getSelectedTable() + "!");
                container.remove(dataTable.getTable());
                container.remove(header);
                dataTable = new DataTable(leftPanel.getSelectedTable());
                System.out.println("---");
                System.out.println(leftPanel.getSelectedTable());
                System.out.println(dataTable.getTable());
                JTable temp = dataTable.getTable();
                header = temp.getTableHeader();
                header.setBounds(300,20,500,40);
                container.add(header);
                temp.setBounds(300,60,500,590);
                container.add(temp);
                thisFrame.repaint();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}



