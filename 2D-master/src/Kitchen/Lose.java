package Kitchen;

import javax.swing.*;
import java.awt.*;

public class Lose extends JPanel{
    private JFrame jframe;
    private Image background;
        public Lose(String imagename){
            background = new ImageIcon(getClass().getResource("/Images/"+ imagename)).getImage();
            jframe = new JFrame("Lose window");
            init();
        }
        public void init(){
            jframe.setSize(800,600);
            jframe.setLocationRelativeTo(null);
            jframe.setResizable(false);
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.setBounds(0,0,800,600);
            jframe.add(this);
            jframe.setVisible(true);
        }
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(background, 0,0, getWidth(),getHeight(),null);
        }
    }

