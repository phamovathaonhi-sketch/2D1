package ingredientList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IngredientList2 {
    private JFrame jframe;
    private DefaultListModel<String> model;
    private JList<String> list;
    private JScrollPane scrollPane;
    private JTextField textField;

    public IngredientList2(){
        jframe = new JFrame("List of ingredients");
        model = new DefaultListModel<>();
        list = new JList<>();
        list.setModel(model);
        scrollPane = new JScrollPane(list);
        textField = new JTextField();
        init();
    }
    public void init(){
        jframe.setSize(200,350);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(new BorderLayout());
        jframe.add(scrollPane, BorderLayout.CENTER);
        InputStream is = getClass().getResourceAsStream("/map/ListingredientSUSHI.txt");
        BufferedReader br  = new BufferedReader(new InputStreamReader(is));
        try {
            String line;
            while ((line = br.readLine()) != null){
                String[] elements = line.split(" ");
                for (String element : elements){
                    if (!element.trim().isEmpty()){
                        model.addElement(element);
                    }
                    textField.setText("");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    int index = list.getSelectedIndex();

                    if (index != -1) {
                        model.remove(index);
                    }
                }
            }
        });
    }
    public boolean getCurrentLocation2(){
        return true;
    }

}
