package ingredientList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class IngredientList1 {
    private JFrame jframe;
    private DefaultListModel<String> model;
    private JList<String> list;
    private JScrollPane scrollPane;

    public IngredientList1(){
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
        InputStream is = getClass().getResourceAsStream("/map/ListingredientCAKE.txt");
        BufferedReader br  = new BufferedReader(new InputStreamReader(is));
        try {
            String line;
            while ((line = br.readLine()) != null){
                String[] elements = line.split(" ");
                for (String element : elements){
                    if (!element.trim().isEmpty()){
                        model.addElement(element);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    model.remove(list.getSelectedIndex());
                }
            }
        });
    }
}
