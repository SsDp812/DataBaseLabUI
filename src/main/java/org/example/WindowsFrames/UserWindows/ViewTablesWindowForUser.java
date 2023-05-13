package org.example.WindowsFrames.UserWindows;

import org.example.Components.Panels.AdminPanels.RadoiButtunsPanelForTablesAdmin;
import org.example.Components.Panels.UserPanels.RadioPanelUserToView;
import org.example.Components.Tables.DataTable;
import org.example.WindowsFrames.AdminWindows.ViewTablesWindowForAdmin;
import org.example.WindowsFrames.StandartWindow;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewTablesWindowForUser extends StandartWindow {
    private JFrame thisFrame;
    private DataTable dataTable;
    private JTableHeader header;
    public ViewTablesWindowForUser(String title, JFrame back) throws Exception {
        super(title);
        this.backFrame = back;
        thisFrame = this;
        initBackButton(backFrame,this);
        leftPanel = new RadioPanelUserToView();
        JPanel panel = leftPanel.getPanel();
        panel.setBounds(20,80,250,530);
        container.add(panel);
        JButton buttonToLoad = leftPanel.getButtonToLoad();
        buttonToLoad.setBounds(120,20,100,40);

        buttonToLoad.addActionListener(new newLoadTable());

        container.add(buttonToLoad);
        dataTable = new DataTable(leftPanel.getSelectedTable());
        JTable table = dataTable.getTable();
        header = table.getTableHeader();
        header.setBounds(300,20,500,40);
        table.setBounds(300,60,500,540);
        container.add(header);
        container.add(dataTable.getTable());
        this.setVisible(true);
    }

    class newLoadTable implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("!" + leftPanel.getSelectedTable() + "!");
                container.remove(dataTable.getTable());
                container.remove(header);
                dataTable = new DataTable(leftPanel.getSelectedTable());
                header = dataTable.getTable().getTableHeader();
                header.setBounds(300,20,500,40);
                System.out.println(dataTable.getTable());
                JTable temp = dataTable.getTable();
                temp.setBounds(300,60,500,540);
                container.add(temp);
                container.add(header);
                thisFrame.repaint();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
