import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ViewPanel extends JPanel implements ActionListener{
    JTextField num1;
    JTextField num2;
    JTextField tf;
    public ViewPanel(){
        setLayout(new GridLayout(7,2,2,2));
        JButton but1=new JButton ("Среднее кол-во часов");
        JButton but2=new JButton ("Кол-во часов выше среднего");
        JButton but3=new JButton ("Кол-во часов в диапазоне");
        JButton but4=new JButton ("Запрос 1:  суммарное кол-во часов по дисциплине");
        JButton but5=new JButton ("Запрос 2:  максимальное кол-во часов");
        JButton but6=new JButton ("Применить фильтр");
        JButton but7=new JButton ("Сортировать по кол-ву часов");
        JButton but8=new JButton ("Сортировать по дисциплине и кол-ву часов");
        JButton but9=new JButton ("Вывести все");
        tf=new JTextField("");
        JLabel l1 = new JLabel("Введите фильтр для дисциплины",JLabel.CENTER);
        JLabel l2 = new JLabel("");
        JLabel l3 = new JLabel("Укажите границы диапазона (часы)",JLabel.CENTER);
        but1.setActionCommand("Avg");
        but2.setActionCommand("AboveAvg");
        but3.setActionCommand("Between");
        but4.setActionCommand("Total1");
        but5.setActionCommand("Total2");
        but6.setActionCommand("Filter");
        but7.setActionCommand("Sort1");
        but8.setActionCommand("Sort2");
        but9.setActionCommand("All");
        num1 = new JTextField("",10);
        num2 = new JTextField("",10);
        JPanel p1 = new JPanel();
        p1.add(num1); p1.add(num2);
        add(l1); add(l2);
        add(tf); add(but6);
        add(but1); add(but9);
        add(l3); add(but2);
        add(p1); add(but3);
        add(but7); add(but8);
        add(but4); add(but5);
        but1.addActionListener(this);
        but2.addActionListener(this);
        but3.addActionListener(this);
        but4.addActionListener(this);
        but5.addActionListener(this);
        but6.addActionListener(this);
        but7.addActionListener(this);
        but8.addActionListener(this);
        but9.addActionListener(this);}
    private void showAvg(){
        MainFrame.MSG.setText("   Запрос на выборку: выдать среднее кол-во часов");
        JOptionPane.showMessageDialog(MainFrame.frame,
                String.format("Среднее кол-во часов: %6.2f", Global.table.avgTime()));}
    private void showAboveAvg(){
        MainFrame.MSG.setText("   Запрос на выборку: выдать записи с кол-вом часов выше заданного");
        Global.updateJTable(Global.table.aboveAvgTime().getSchedules());};
    private void showBetween(){
        int n, m;
        try{
            n=Integer.parseInt(num1.getText());
            m=Integer.parseInt(num2.getText());}
        catch (NumberFormatException  e){
            MainFrame.MSG.setText("   Задайте правильно границы диапазона");
            return;}
        MainFrame.MSG.setText(String.format(
                "   Запрос на выборку: выдать записи с кол-вом часов в диапазоне [%4d ,%4d]", n,m));
        Global.updateJTable(Global.table.betweenTime(n,m).getSchedules());};
    private void showTotal1(){
        TotalDialog td = new TotalDialog (MainFrame.frame,"Суммарное кол-во часов по дисциплине",Global.table.totalSumFirm());
        td.setVisible(true);};
    private void showTotal2(){
        MainFrame.MSG.setText("   Итоговый запрос на выборку");
        JOptionPane.showMessageDialog(MainFrame.frame,
                String.format("Максимальное кол-во часов: %5d", Global.table.generalMaxSquare()));};
    private void showFilter(){
        String filter = tf.getText();
        if (filter.equals("")) {
            MainFrame.MSG.setText("     Введите фильтр");
            return;}
        MainFrame.MSG.setText(String.format(
                "   Запрос на выборку: выдать записи с шифра, начинающегося с \"%s\"", filter));
        Global.updateJTable(Global.table.filterForCipher(filter).getSchedules());};
    private void showSort1(){
        MainFrame.MSG.setText(
                "   Запрос на выборку: выдать все записи таблицы с сортировкой по кол-ву часов");
        Global.updateJTable(Global.table.sort(new CompCommandAscBallDesc()).getSchedules());};
    private void showSort2(){
        MainFrame.MSG.setText(
                "   Запрос на выборку: выдать все записи таблицы с сортировкой по дисциплине и кол-ву часов");
        Global.updateJTable(Global.table.sort(new CompChampAscBallDesc()).getSchedules());};
    private void showAll(){
        MainFrame.MSG.setText("   Запрос на выборку: выдать все записи таблицы без сортировки");
        Global.updateJTable(Global.table.getSchedules());};
    public void actionPerformed (ActionEvent e){
        if ("Avg".equals(e.getActionCommand())) showAvg();
        else if("AboveAvg".equals(e.getActionCommand())) showAboveAvg();
        else if("Between".equals(e.getActionCommand())) showBetween();
        else if("Total1".equals(e.getActionCommand())) showTotal1();
        else if("Total2".equals(e.getActionCommand())) showTotal2();
        else if("Filter".equals(e.getActionCommand())) showFilter();
        else if("Sort1".equals(e.getActionCommand())) showSort1();
        else if("Sort2".equals(e.getActionCommand())) showSort2();
        else showAll();}}