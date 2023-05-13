package org.example.Entities;

import java.text.ParseException;
import java.util.List;

public class Entity {
    protected static String[] colNames;
    protected static int countOFCols;
    protected String[] data;

    public static int getCountOFCols() {
        return countOFCols;
    }


    public static Entity getEntityFromList(List<String> list) throws ParseException {
        return null;
    }
    public String[] getData(){
        return data;
    }
    public String[] getColNames() {return colNames;}

    public void LoadDataToArray(){
        String temp = this.toString();
        StringBuilder builder = new StringBuilder(temp);
        StringBuilder temp2 =new StringBuilder("");
        boolean flag = false;
        for(int i = 0; i < temp.length() - 1;i++){
            if(builder.charAt(i) == '{'){
                flag = true;
                continue;
            }
            if(flag){
                temp2.append(builder.charAt(i));
            }
        }
        String arr[] = temp2.toString().split(", ");
        String arr2[] = new String[arr.length];
        for(int i = 0; i < arr.length;i++){
            arr2[i] = arr[i].split("=")[1];
        }
        data = arr2;
    }
}
