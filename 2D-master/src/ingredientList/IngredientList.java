package ingredientList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class IngredientList extends JPanel{
    private JFrame jframe;
    private DefaultListModel<String> model;
    private JList<String> list;
    private JScrollPane scrollPane;
    private JTextField textField;

    public IngredientList(){
        jframe = new JFrame("List of ingredients");
        model = new DefaultListModel<>();
        list = new JList<>();
        list.setModel(model);
        list.setModel(model);

        list.setFont(new Font("Arial", Font.BOLD, 18));
        list.setForeground(Color.PINK);
        list.setBackground(Color.WHITE);
        list.setLocation(200+200/2, 350+350/2);
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
    public void loadCake(){
        model.clear();
        loadFile("/map/ListingredientCAKE.txt");
    }
    public void loadSushi(){
        model.clear();
        loadFile("/map/ListingredientSUSHI.txt");
    }
    private void loadFile(String path) {
        InputStream is = getClass().getResourceAsStream(path);

        if (is == null) return;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;

            while ((line = br.readLine()) != null) {
                for (String element : line.split(" ")) {
                    if (!element.trim().isEmpty()) {
                        model.addElement(element);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(248, 233, 235));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
