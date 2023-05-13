package org.example.Components.Panels.AdminPanels;

import org.example.Components.Panels.RadioButtonsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadoiButtunsPanelForTablesAdmin implements RadioButtonsPanel {

    private JPanel panel;
    private ButtonGroup buttonGroup;
    private JButton buttonToLoad;

    private String selectedTable;

    public RadoiButtunsPanelForTablesAdmin(){

        panel = new JPanel(new GridLayout(0,1));
        buttonGroup = new ButtonGroup();
        JRadioButton author = new JRadioButton("author",true);
        selectedTable = author.getText();
        author.setActionCommand("author");

        JRadioButton book = new JRadioButton("book",true);
        book.setActionCommand("book");

        JRadioButton bookItem = new JRadioButton("bookitem");
        bookItem.setActionCommand("bookitem");

        JRadioButton booksandauthors = new JRadioButton("booksandauthors");
        booksandauthors.setActionCommand("booksandauthors");

        JRadioButton debtors = new JRadioButton("debtors");
        debtors.setActionCommand("debtors");

        JRadioButton documents = new JRadioButton("documents");
        documents.setActionCommand("documents");

        JRadioButton genre = new JRadioButton("genre");
        genre.setActionCommand("genre");

        JRadioButton givenbook = new JRadioButton("givenbook");
        givenbook.setActionCommand("givenbook");

        JRadioButton producer = new JRadioButton("producer");
        producer.setActionCommand("producer");

        JRadioButton publisher = new JRadioButton("publisher");
        publisher.setActionCommand("publisher");

        JRadioButton supplies = new JRadioButton("supplies");
        supplies.setActionCommand("supplies");

        JRadioButton supplogs = new JRadioButton("supplogs");
        supplogs.setActionCommand("supplogs");

        JRadioButton users = new JRadioButton("users");
        users.setActionCommand("users");

        SelectListener selectListener = new SelectListener();


        author.addActionListener(selectListener);
        book.addActionListener(selectListener);
        bookItem.addActionListener(selectListener);
        booksandauthors.addActionListener(selectListener);
        debtors.addActionListener(selectListener);
        documents.addActionListener(selectListener);
        genre.addActionListener(selectListener);
        givenbook.addActionListener(selectListener);
        producer.addActionListener(selectListener);
        publisher.addActionListener(selectListener);
        supplies.addActionListener(selectListener);
        supplogs.addActionListener(selectListener);
        users.addActionListener(selectListener);

        panel.add(author);
        panel.add(book);
        panel.add(bookItem);
        panel.add(booksandauthors);
        panel.add(debtors);
        panel.add(documents);
        panel.add(genre);
        panel.add(givenbook);
        panel.add(producer);
        panel.add(publisher);
        panel.add(supplies);
        panel.add(supplogs);
        panel.add(users);

        buttonToLoad = new JButton("Load");
        buttonToLoad.addActionListener(e -> {
            if(buttonGroup.getSelection() != null){
                selectedTable = buttonGroup.getSelection().getActionCommand();
                System.out.println(selectedTable);
            }
        });

        buttonGroup.add(author);
        buttonGroup.add(book);
        buttonGroup.add(bookItem);
        buttonGroup.add(booksandauthors);
        buttonGroup.add(debtors);
        buttonGroup.add(documents);
        buttonGroup.add(genre);
        buttonGroup.add(givenbook);
        buttonGroup.add(producer);
        buttonGroup.add(publisher);
        buttonGroup.add(supplies);
        buttonGroup.add(supplogs);
        buttonGroup.add(users);
    }

    public JPanel getPanel(){
        return panel;
    }

    public String getSelectedTable(){
        return selectedTable;
    }

    public JButton getButtonToLoad(){
        return buttonToLoad;
    }

    class SelectListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedTable = e.getActionCommand();
        }
    }
}
