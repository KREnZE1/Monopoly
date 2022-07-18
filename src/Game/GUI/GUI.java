package Game.GUI;


import javax.swing.*;

public class GUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Monopoly");
        JLabel label = new JLabel("");
        frame.add(label);
//        frame.setPreferredSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        frame.setPreferredSize(new java.awt.Dimension(1000, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        label.setText("Hello World");
    }
}
