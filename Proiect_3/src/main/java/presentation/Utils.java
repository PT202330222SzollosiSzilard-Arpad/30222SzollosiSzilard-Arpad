package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Utils {
public static DefaultTableModel getelements(List<?> objects){
    DefaultTableModel table = new DefaultTableModel();
    table.setRowCount(0);
    String[] header = new String[objects.get(0).getClass().getDeclaredFields().length];
    int i = 0;
    for(Field field : objects.get(0).getClass().getDeclaredFields()){
        field.setAccessible(true);
        header[i] = field.getName();
        i++;
    }

    Object[][] date =new Object[objects.size()][header.length];
    i = 0;
    for(Object object : objects){
        int j = 0;
        for(Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try{
                date[i][j] = field.get(object);
                j++;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        i++;
    }
    table = new DefaultTableModel(date,header);
    return table;
}
}
