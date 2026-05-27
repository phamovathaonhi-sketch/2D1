package ingredient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BagPopUp {

    public static void show(JFrame parent, Bag bag) {
        JFrame frame = new JFrame("bag");
        frame.setSize(430, 250);
        frame.setLocationRelativeTo(parent);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                g2.setColor(new Color(35, 25, 15));
                g2.fillRoundRect(10, 10, 400, 190, 20, 20);
                g2.setColor(new Color(160, 120, 70));
                g2.drawRoundRect(10, 10, 400, 190, 20, 20);

                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Arial", Font.BOLD, 16));
                g2.drawString("Bag", 20, 35);

                int slotSize = 50;
                int gap = 8;
                int startX = 20;
                int startY = 45;
                int perRow = 6;

                for (int i = 0; i < 12; i++) {
                    int col = i % perRow;
                    int row = i / perRow;
                    int x = startX + col * (slotSize + gap);
                    int y = startY + row * (slotSize + gap);

                    g2.setColor(new Color(0, 0, 0, 90));
                    g2.fillRoundRect(x, y, slotSize, slotSize, 8, 8);
                    g2.setColor(new Color(210, 180, 120));
                    g2.drawRoundRect(x, y, slotSize, slotSize, 8, 8);

                    if (i < bag.size()) {
                        Ingredient ing = bag.get(i);
                        g2.drawImage(ing.getImg(), x + 4, y + 4, slotSize - 8, slotSize - 8, null);

                        g2.setColor(Color.WHITE);
                        g2.setFont(new Font("Arial", Font.PLAIN, 8));
                        g2.drawString(ing.getName(), x + 3, y + slotSize - 3);
                    }
                }

                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Arial", Font.PLAIN, 12));
                g2.drawString("Double-click an item to remove it", 20, 210);
            }
        };

        // Double-click to remove
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int slot = getSlotAt(e.getX(), e.getY());
                    if (slot >= 0 && slot < bag.size()) {
                        bag.remove(slot);
                        panel.repaint();
                    }
                }
            }
        });

        panel.setBackground(new Color(70, 70, 70));
        frame.add(panel);
        frame.setVisible(true);
    }

    private static int getSlotAt(int clickX, int clickY) {
        int slotSize = 50;
        int gap = 8;
        int startX = 20;
        int startY = 45;
        int perRow = 6;

        for (int i = 0; i < 12; i++) {
            int col = i % perRow;
            int row = i / perRow;
            int x = startX + col * (slotSize + gap);
            int y = startY + row * (slotSize + gap);

            if (new Rectangle(x, y, slotSize, slotSize).contains(clickX, clickY)) {
                return i;
            }
        }
        return -1;
    }
}