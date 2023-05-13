package org.example.WindowsFrames.dialogWindows;

import org.example.Entities.Entity;
import org.example.Entities.switcher.entitySwitcher;
import org.example.WindowsFrames.StandartWindow;
import org.example.dao.Switcher.daoSwitcher;
import org.example.dao.dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class insertWindow extends StandartWindow {
    private JTextField[] fields;
    private int count;
    private String entityName;
    private JFrame thisFrame;
    public insertWindow(String title,String entityName,int countOfCols,String[] colNames){
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        thisFrame = this;
        this.entityName = entityName;
        this.setBounds(200,200,750,250);
        this.count = countOfCols;
        JButton submit = new JButton("insert");
        submit.addActionListener(new insertSub());
        submit.setActionCommand("insertS");
        submit.setBounds(20,140,120,40);
        container.add(submit);
        int x = 20;
        int wid = 90;
        int space = 20;
        fields = new JTextField[countOfCols];
        for(int i = 0; i < countOfCols;i++){
            JLabel label = new JLabel(colNames[i]);
            label.setBounds(x,20,wid,40);
            container.add(label);
            JTextField textField = new JTextField();
            textField.setBounds(x,80,wid,40);
            fields[i] = textField;
            container.add(textField);
            x += (wid+space);
        }
        this.setVisible(true);

    }

    class insertSub implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] info = new String[count];
            for(int i = 0; i < count;i++){
                info[i] = fields[i].getText();
            }
            try {
                dao DAO = daoSwitcher.getDao(entityName);
                Entity entity =  entitySwitcher.makeEntity(entityName, new ArrayList<>(Arrays.asList(info)));
                DAO.create(entity);
                thisFrame.dispose();

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
