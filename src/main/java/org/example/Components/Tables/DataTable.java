package org.example.Components.Tables;

import org.example.Entities.Entity;
import org.example.dao.Switcher.daoSwitcher;
import org.example.dao.dao;

import javax.swing.*;
import java.util.List;

public class DataTable {
    private String[] colNames;
    private String[][] data;
    private JTable table;
    private JScrollPane scrollPane;
    private int countOfCols;
    private String tableName;

    public DataTable(String tableName) throws Exception {
        this.tableName = tableName;
        dao entityDao = daoSwitcher.getDao(tableName);
        List<Entity> list = entityDao.getAll();
        if(list!=null && list.size() != 0){
           colNames = list.get(0).getColNames();
           countOfCols = list.get(0).getCountOFCols();
           data = new String[list.size()][countOfCols];

           for(int i = 0; i < list.size();i++){
               Entity temp = list.get(i);
               temp.LoadDataToArray();
               String[] info = temp.getData();
               for(int j = 0; j < info.length;j++){
                   data[i][j] = info[j];
               }
           }

           table = new JTable(data,colNames);
           scrollPane = new JScrollPane(table);
           entityDao.closeConnection();
        }
    }

    public JTable getTable() {
        return table;
    }

    public int getCountOfCols() {
        return countOfCols;
    }

    public String[] getColNames() {
        return colNames;
    }

    public String getTableName(){
        return tableName;
    }
}
