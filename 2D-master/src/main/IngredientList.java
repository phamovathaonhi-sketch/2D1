package main;

import javax.swing.*;
import java.awt.*;

public class IngredientList {
    private JFrame jframe;
    private DefaultListModel<String> model;
    private JList<String> list;
    private JScrollPane scrollPane;

    public IngredientList(){
        jframe = new JFrame("List of igredients");
        model = new DefaultListModel<>();
        list = new JList<>();
        scrollPane = new JScrollPane(list);
        init();
    }
    public void init(){
        jframe.setSize(200,600);
        jframe.setResizable(false);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(new BorderLayout());
        jframe.add(scrollPane, BorderLayout.CENTER);
    }
}
