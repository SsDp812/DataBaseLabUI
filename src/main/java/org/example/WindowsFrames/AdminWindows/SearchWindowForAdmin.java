package org.example.WindowsFrames.AdminWindows;

import org.example.Connections.ConnectionMaker;
import org.example.WindowsFrames.StandartWindow;
import org.example.WindowsFrames.UserWindows.SearchWindowForUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchWindowForAdmin extends StandartWindow {
    private JTextField idField;
    private JTextField answer;
    private JFrame thisFrame;
    public SearchWindowForAdmin(String title, JFrame back) throws HeadlessException {
        super(title);
        thisFrame = this;
        this.backFrame = back;
        initBackButton(backFrame,this);
        JLabel label = new JLabel("Supply id:");
        label.setBounds(200,200,120,40);
        container.add(label);
        JButton button = new JButton("Check!");
        button.addActionListener(new checkFineListener());
        button.setBounds(360,200,120,40);
        container.add(button);
        idField = new JTextField();
        idField.setBounds(520,200,100,40);
        container.add(idField);
        answer = new JTextField();
        answer.setBounds(620,200,150,40);
        container.add(answer);
        this.setVisible(true);
    }

    private class checkFineListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection connection = ConnectionMaker.getConnect();
                int id = Integer.parseInt(idField.getText());
                CallableStatement stmt = connection.prepareCall("{ ? = call get_supplies_data(?) }");
                stmt.registerOutParameter(1, Types.DATE);
                stmt.setInt(2, id);
                stmt.execute();
                Date dateFromServer = stmt.getDate(1);
                answer.setText(dateFromServer.toString());
                thisFrame.repaint();


            } catch (SQLException ex) {
                answer.setText("wrong id");
                thisFrame.repaint();
                throw new RuntimeException(ex);
            }
        }
    }
}
