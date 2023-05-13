package org.example.Components.Panels.UserPanels;

import org.example.Components.Panels.RadioButtonsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioPanelUserToView implements RadioButtonsPanel {
    private JPanel panel;
    private ButtonGroup buttonGroup;
    private JButton buttonToLoad;

    private String selectedTable;

    public RadioPanelUserToView(){
        panel = new JPanel(new GridLayout(0,1));
        buttonGroup = new ButtonGroup();
        SelectListener selectListener = new SelectListener();

        JRadioButton author = new JRadioButton("author",true);
        selectedTable = author.getText();
        author.addActionListener(selectListener);
        author.setActionCommand("author");

        JRadioButton book = new JRadioButton("book", true);
        book.addActionListener(selectListener);
        book.setActionCommand("book");

        JRadioButton bookItem = new JRadioButton("bookItem");
        bookItem.addActionListener(selectListener);
        bookItem.setActionCommand("bookItem");

        JRadioButton booksandauthors = new JRadioButton("booksandauthors");
        booksandauthors.addActionListener(selectListener);
        booksandauthors.setActionCommand("booksandauthors");

        JRadioButton debtors = new JRadioButton("debtors");
        debtors.addActionListener(selectListener);
        debtors.setActionCommand("debtors");

        JRadioButton documents = new JRadioButton("documents");
        documents.addActionListener(selectListener);
        documents.setActionCommand("documents");

        JRadioButton genre = new JRadioButton("genre");
        genre.addActionListener(selectListener);
        genre.setActionCommand("genre");

        JRadioButton givenbook = new JRadioButton("givenbook");
        givenbook.addActionListener(selectListener);
        givenbook.setActionCommand("givenbook");

        JRadioButton producer = new JRadioButton("producer");
        producer.addActionListener(selectListener);
        producer.setActionCommand("producer");

        JRadioButton publisher = new JRadioButton("publisher");
        publisher.addActionListener(selectListener);
        publisher.setActionCommand("publisher");

        JRadioButton supplies = new JRadioButton("supplies");
        supplies.addActionListener(selectListener);
        supplies.setActionCommand("supplies");

        JRadioButton supplogs = new JRadioButton("supplogs");
        supplogs.addActionListener(selectListener);
        supplogs.setActionCommand("supplogs");

        JRadioButton users = new JRadioButton("users");
        users.addActionListener(selectListener);
        users.setActionCommand("users");

        panel.add(author);
        panel.add(book);
        panel.add(bookItem);
        panel.add(booksandauthors);
        panel.add(genre);
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
        buttonGroup.add(genre);
        buttonGroup.add(users);
    }
    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public String getSelectedTable() {
        return selectedTable;
    }

    @Override
    public JButton getButtonToLoad() {
        return buttonToLoad;
    }

    class SelectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedTable = e.getActionCommand();
        }
    }
}
