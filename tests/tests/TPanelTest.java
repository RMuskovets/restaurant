package tests;

import org.junit.Test;
import org.roman.restaurant.Table;
import org.roman.restaurant.ui.TablePanel;

import javax.swing.*;

public class TPanelTest {

    public static final Table[] tables = gen(16);

    public static Table[] gen(int n) {
        Table[] t = new Table[n];
        for (int i = 0; i < n; i++) t[i] = new Table(i+1);
        return t;
    }

    @Test
    public void testPanel() {
        JFrame jfr = new JFrame("tests.TPanelTest");
        TablePanel tp = new TablePanel(tables);
        tp.onTableChanged = (t) -> System.out.println(t.getIndex() + "/" + t.isUsed());
        jfr.add(tp);
        jfr.pack();
        jfr.setVisible(true);
        jfr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TPanelTest().testPanel();
    }
}
