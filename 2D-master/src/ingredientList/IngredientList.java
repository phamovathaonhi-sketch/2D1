package ingredientList;

import javax.swing.*;
import java.awt.*;
import java.io.*;

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
        InputStream is = getClass().getResourceAsStream("/map/ListingredientCAKE.txt");
        BufferedReader br  = new BufferedReader(new InputStreamReader(is));
        try {
            String line = br.readLine();
            String[] num = line.split(" ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
