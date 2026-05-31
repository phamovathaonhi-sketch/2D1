package ingredient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

public class IngredientList extends JPanel {

    private JFrame jframe;
    private DefaultListModel<String> model;
    private JList<String> list;
    private JScrollPane scrollPane;
    private JTextField textField;
    private Ingredient ing;
    private ArrayList<Ingredient> mapIngredients = new ArrayList<>();
    private ArrayList<String> originalOrder = new ArrayList<>();

    public IngredientList() {
        jframe = new JFrame("List of ingredients");
        model = new DefaultListModel<>();
        list = new JList<>();
        list.setModel(model);

        list.setFont(new Font("Arial", Font.BOLD, 18));
        list.setForeground(Color.PINK);
        list.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(list);
        textField = new JTextField();
    }

    private void init() {
        jframe.setSize(200, 350);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframe.setLayout(new BorderLayout());
        jframe.add(scrollPane, BorderLayout.CENTER);

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = list.getSelectedIndex();
                    if (index != -1) {
                        model.remove(index);
                    }
                }
            }
        });
    }
    public void showWindow() {
        init();
        jframe.setVisible(true);
    }
    public boolean isEmpty() {
        return model.getSize() == 0;  // returns true if no ingredients left
    }
    public ArrayList<String> getIngredients() {
       return originalOrder;
    }
    public void removeIng(String name) {
        for (int i = 0; i < model.getSize(); i++) {
            if (model.get(i).equalsIgnoreCase(name)) {
                model.remove(i);
                return;
            }
        }
    }

    public void loadCake() {
        model.clear();
        originalOrder.clear();
        loadFile("/map/ListingredientCAKE.txt");
        for (int i = 0; i< model.getSize(); i++){
            originalOrder.add(model.get(i));
        }
    }

    public void loadSushi() {
        model.clear();
        originalOrder.clear();
        loadFile("/map/ListingredientSUSHI.txt");
        for (int i = 0; i<model.getSize(); i++){
            originalOrder.add(model.get(i));
        }
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