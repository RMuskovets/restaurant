package org.roman.restaurant.ui;

import org.roman.restaurant.Table;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class TablePanel extends JPanel {
    public TableChangedListener onTableChanged;

    public TablePanel(Table[] t) {
        double sqr = Math.sqrt(t.length);
        int rows = 0, columns = 0;
        if (sqr != (int) sqr) {
            int[] dividers = {3, 4, 5};
            for (int divider : dividers) {
                double x = (double) t.length / divider;
                if (x == (int) x) {
                    columns = divider;
                    rows = (int) x;
                }
            }
        } else rows = columns = (int) sqr;
        if (rows == 0 && columns == 0) {
            rows = 1; columns = t.length;
        }
        GridLayout layout = new GridLayout(rows, columns);
        setLayout(layout);
        JButton[] buttons = new JButton[t.length];
        for (int i = 0; i < t.length; i++) {
            buttons[i] = new JButton("Table " + t[i].getIndex());
            int finalI = i;
            buttons[i].addActionListener(e -> {
                if (t[finalI].isUsed()) {
                    int x = JOptionPane.showConfirmDialog(null, "Would you like to un-use this table?");
                    if (x == JOptionPane.YES_OPTION) {
                        t[finalI].unuse();
                    } else {
                        new Thread(() -> {
                            Table table = t[finalI];
                            JFrame n = new JFrame("Foods: Table " + t[finalI].getIndex());
                            FoodPanel panel = new FoodPanel(table);
                            n.setContentPane(panel);
                            n.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                            n.setSize(panel.getMinimumSize());
                            n.setVisible(true);
                        }).start();
                    }
                }
                else {
                    t[finalI].use();
                    if (onTableChanged != null) onTableChanged.tableChanged(t[finalI]);
                }
            });
        }
        for (JButton b : buttons) add(b);
    }

    public static final Table[] tables = gen(10);

    public static Table[] gen(int n) {
        Table[] t = new Table[n];
        for (int i = 0; i < n; i++) t[i] = new Table(i+1);
        return t;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        JFrame main = new JFrame("Restaurant 1.0");
        main.setContentPane(new TablePanel(tables));
        main.pack();
        main.setVisible(true);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
