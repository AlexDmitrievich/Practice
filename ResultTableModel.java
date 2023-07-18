import javax.swing.table.AbstractTableModel;
import java.util.*;
public class ResultTableModel extends AbstractTableModel{
    List <Result> results;
    public ResultTableModel(List <Result> results){
        super();
        this.results = results;}
    @Override
    public int getRowCount(){
        return results.size();}
    @Override
    public int getColumnCount(){
        return 4;}
    @Override
    public Object getValueAt(int r, int c){
        switch (c){
            case 0:return results.get(r).getNumber();
            case 1:return results.get(r).getFirm ();
            case 2:return results.get(r).getAddress();
            case 3:return results.get(r).getSquare();
            default:return "";}}
    @Override
    public String getColumnName(int c){
        String name = "";
        switch (c){
            case 0:name = "Номер преподавателя";break;
            case 1:name = "Дисциплина";break;
            case 2:name = "Вид занятий";break;
            case 3:name = "Часы";break;}
        return name;}}