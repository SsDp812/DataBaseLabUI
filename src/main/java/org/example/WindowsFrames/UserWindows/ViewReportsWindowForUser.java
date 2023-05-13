package org.example.WindowsFrames.UserWindows;

import org.example.Connections.ConnectionMaker;
import org.example.WindowsFrames.StandartWindow;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewReportsWindowForUser extends StandartWindow {
    private JTable reportTable;
    String[] colNames;
    String[][] info;
    public ViewReportsWindowForUser(String title, JFrame back) throws HeadlessException, SQLException {
        super(title);
        this.backFrame = back;
        initBackButton(backFrame,this);
        colNames = new String[]{"Name","Surname","Email","Fine"};
        Connection connection = ConnectionMaker.getConnect();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM debtor_view");
        ResultSet set = statement.executeQuery();
        connection.close();
        List<String[]> list = new ArrayList<>();
        while(set.next()){
            String[] temp = new String[4];
            temp[0] = set.getString(1);
            temp[1] = set.getString(2);
            temp[2] = set.getString(3);
            temp[3] = set.getString(4);
            list.add(temp);
        }
        String[][] data = new String[list.size()][4];
        for(int i = 0; i < list.size();i++){
            for(int j = 0; j < 4;j++){
                data[i][j] = list.get(i)[j];
            }
        }
        reportTable = new JTable(data,colNames);
        JTableHeader header = reportTable.getTableHeader();
        header.setBounds(80,80,700,40);
        container.add(header);
        reportTable.setBounds(80,120,700,640);
        container.add(reportTable);
        this.setVisible(true);
    }

    }

