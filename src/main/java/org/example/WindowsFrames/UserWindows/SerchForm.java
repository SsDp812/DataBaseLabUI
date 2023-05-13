package org.example.WindowsFrames.UserWindows;

import org.example.Connections.ConnectionMaker;
import org.example.Entities.Impls.Author;
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

public class SerchForm extends StandartWindow {
    JLabel label;
    JTextField searchField;

    private JTable table;
    JButton bt;
    JFrame thisFrame;
    JTableHeader header;

    public SerchForm(String title,JFrame back) throws HeadlessException {
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
            Connection con = null;
            List<Author> list = new ArrayList<>();
            try {
                con = ConnectionMaker.getConnect();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            CallableStatement cs = null;
            try {
                cs = con.prepareCall("{call search_authors(?)}");
                cs.setString(1, searchField.getText());

                // выполнение хранимой процедуры
                ResultSet rs = cs.executeQuery();

                // читаем результат поиска
                while (rs.next()) {
                    Long authorId = rs.getLong("author_id");
                    String authorName = rs.getString("author_name");
                    String authorSurname = rs.getString("author_surname");
                    String authorDadname = rs.getString("author_dadname");
                    int authorYear = rs.getInt("author_year");
                  list.add(new Author(authorId,authorName,authorSurname,authorDadname,authorYear));
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            String[][] arr = new String[list.size()][5];
            for(int i = 0; i < list.size();i++){
                list.get(i).LoadDataToArray();
                arr[i] = list.get(i).getData();
            }
            table = new JTable(arr,list.get(0).getColNames());
            table.setBounds(200,80,400,560);
            header = table.getTableHeader();
            header.setBounds(200,40,400,40);
            container.add(header);
            container.add(table);
            thisFrame.repaint();
            // установка параметра для хранимой процедуры

        }
    }
}
