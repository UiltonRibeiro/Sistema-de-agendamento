package BDD;

import javax.swing.JOptionPane;

public class RowUp {
     static int rowUp;

    public RowUp() {
    }
    
    public static void setRow(int row){
        if(row != -1){
            rowUp = row;
        }else{
        }
        rowUp = row;
    }
    
    public static int getRow(){
        return rowUp;
    }
    
    
}
