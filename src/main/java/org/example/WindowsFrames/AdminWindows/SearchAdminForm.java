package org.example.WindowsFrames.AdminWindows;


import org.example.Connections.ConnectionMaker;
import org.example.Entities.Impls.Author;
import org.example.Entities.Impls.User;
import org.example.WindowsFrames.StandartWindow;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchAdminForm extends StandartWindow {
    JLabel label;
    JTextField searchField;

    private JTable table;
    JButton bt;
    JFrame thisFrame;

    JTableHeader header;

    public SearchAdminForm(String title,JFrame back) throws HeadlessException {
        super(title);
        this.backFrame = back;
        initBackButton(backFrame,this);
        thisFrame = this;
        label = new JLabel("Search text: ");
        label.setBounds(20,100,120,40);
        searchField = new JTextField("");
        searchField.setBounds(20,160,120,50);
        bt = new JButton("Search!");
        bt.setBounds(20,220,120,40);
        bt.addActionListener(new listener());
        table = new JTable();
        container.add(table);
        container.add(label);
        container.add(searchField);
        container.add(bt);
        header = new JTableHeader();
        container.add(header);
        this.setVisible(true);
    }

    private class listener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            container.remove(table);
            container.remove(header);
            List<User> list = new ArrayList<>();

            try (Connection con = ConnectionMaker.getConnect();
                 CallableStatement cs = con.prepareCall("{call search_user2(?)}")) {

                // установка параметра для хранимой процедуры
                cs.setString(1, searchField.getText());

                // выполнение хранимой процедуры
                ResultSet rs = cs.executeQuery();

                // читаем результат поиска
                while (rs.next()) {
                    Long userId = rs.getLong("user_id");
                    String userName = rs.getString("user_name");
                    String userSurname = rs.getString("user_surname");
                    String userEmail = rs.getString("user_email");
                    String userRole = rs.getString("user_role");
                    list.add(new User(userId,userName,userSurname,userEmail,userRole));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String[][] arr = new String[list.size()][5];
            for(int i = 0; i < list.size();i++){
                list.get(i).LoadDataToArray();
                arr[i] = list.get(i).getData();
            }

            table = new JTable(arr,list.get(0).getColNames());
            table.setBounds(200,80,400,560);
            header = table.getTableHeader();
            header.setBounds(200,20,400,40);
            container.add(header);
            container.add(table);
            thisFrame.repaint();
            // установка параметра для хранимой процедуры

        }
    }
}
