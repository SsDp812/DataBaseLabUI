package org.example.WindowsFrames.dialogWindows;

import org.example.WindowsFrames.StandartWindow;
import org.example.dao.Switcher.daoSwitcher;
import org.example.dao.dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class deleteWindow extends StandartWindow {
    private JTextField field;
    private int count;
    private String entityName;
    private JFrame thisFrame;
    public deleteWindow(String title,String entityName) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        thisFrame = this;
        this.entityName = entityName;
        this.setBounds(200,200,200,250);
        JButton submit = new JButton("delete");
        submit.addActionListener(new deleteListener());
        submit.setActionCommand("deleteS");
        submit.setBounds(20,140,120,40);
        container.add(submit);
        JTextField textField = new JTextField();
        JLabel label = new JLabel("Id");
        label.setBounds(20,20,120,40);
        container.add(label);
        textField.setBounds(20,80,120,40);
        container.add(textField);
        this.field = textField;
        this.setVisible(true);

    }

    class deleteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                dao DAO = daoSwitcher.getDao(entityName);
                DAO.delete(Long.parseLong(field.getText()));
                thisFrame.dispose();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
