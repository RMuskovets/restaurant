package org.roman.restaurant.ui;

import org.roman.restaurant.Table;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {
    public TableChangedListener onTableChanged;

    public TablePanel(Table[] t) {
        double sqr = Math.sqrt(t.length);
        int rows = 0, columns = 0;
        if (sqr != (int) sqr) {
            int[] dividers = {3, 4, 5};
            for (int i = 0; i < dividers.length; i++) {
                double x = (double) t.length / dividers[i];
                if (x == (int) x) {
                    columns = dividers[i];
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
                        // TODO: foods list
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
}
