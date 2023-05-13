package org.example.WindowsFrames.UserWindows;

import org.example.Connections.ConnectionMaker;
import org.example.WindowsFrames.StandartWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchWindowForUser extends StandartWindow {
    private JTextField idField;
    private JTextField answer;
    private JFrame thisFrame;
    public SearchWindowForUser(String title, JFrame back) throws HeadlessException {
        super(title);
        thisFrame = this;
        this.backFrame = back;
        initBackButton(backFrame,this);
        JLabel label = new JLabel("Your id:");
        label.setBounds(200,200,120,40);
        container.add(label);
        JButton button = new JButton("Check!");
        button.addActionListener(new checkFineListener());
        button.setBounds(360,200,120,40);
        container.add(button);
        idField = new JTextField();
        idField.setBounds(520,200,80,40);
        container.add(idField);
        answer = new JTextField();
        answer.setBounds(620,200,80,40);
        container.add(answer);
        this.setVisible(true);
    }

    private class checkFineListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection connection = ConnectionMaker.getConnect();
                Long id = Long.parseLong(idField.getText());
                CallableStatement stmt = connection.prepareCall("{ ? = call find_fine(?) }");
                stmt.registerOutParameter(1, Types.INTEGER);
                stmt.setLong(2, id);
                stmt.execute();

                Integer result = stmt.getInt(1);
                connection.close();
                System.out.println(result);
                answer.setText(result.toString());
                thisFrame.repaint();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
