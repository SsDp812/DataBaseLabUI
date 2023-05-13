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

public class updateWindow extends StandartWindow {
    private JTextField[] fields;
    private int count;
    private String entityName;
    private JFrame thisFrame;
    private JTextField idField;
    public updateWindow(String title,String entityName,int countOfCols,String[] colNames) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel idLabel = new JLabel("id");
        idLabel.setBounds(20,20,100,40);
        container.add(idLabel);
        idField = new JTextField();
        idField.setBounds(140,20,100,40);
        container.add(idField);
        JButton loadBT = new JButton("Load");
        loadBT.addActionListener(new loadEntityToUpdate());
        loadBT.setBounds(260,20,100,40);
        container.add(loadBT);
        thisFrame = this;
        this.entityName = entityName;
        this.setBounds(200,200,750,320);
        this.count = countOfCols;
        JButton submit = new JButton("update");
        submit.addActionListener(new updateSub());
        submit.setActionCommand("updateS");
        submit.setBounds(20,220,120,40);
        container.add(submit);
        int x = 20;
        int wid = 90;
        int space = 20;
        fields = new JTextField[countOfCols];
        for(int i = 0; i < countOfCols;i++){
            JLabel label = new JLabel(colNames[i]);
            label.setBounds(x,80,wid,40);
            container.add(label);
            JTextField textField = new JTextField();
            textField.setBounds(x,160,wid,40);
            fields[i] = textField;
            container.add(textField);
            x += (wid+space);
        }
        this.setVisible(true);
    }
    class updateSub implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] info = new String[count];
            for(int i = 0; i < count;i++){
                info[i] = fields[i].getText();
            }
            try {
                dao DAO = daoSwitcher.getDao(entityName);
                Entity entity =  entitySwitcher.makeEntity(entityName, new ArrayList<>(Arrays.asList(info)));
                DAO.update(entity);
                thisFrame.dispose();

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    class loadEntityToUpdate implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                dao DAO = daoSwitcher.getDao(entityName);
                Entity entity = DAO.getById(Long.parseLong(idField.getText()));
                entity.LoadDataToArray();
                String data[] = entity.getData();
                for(int i = 0; i < count;i++){
                    fields[i].setText(data[i]);
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
