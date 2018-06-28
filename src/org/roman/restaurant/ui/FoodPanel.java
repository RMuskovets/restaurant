/*
 * Created by JFormDesigner on Wed Jun 27 21:05:01 EEST 2018
 */

package org.roman.restaurant.ui;

import org.roman.restaurant.Food;
import org.roman.restaurant.Table;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class FoodPanel extends JPanel {
    public FoodPanel(Table f) {
        table = f;
        initComponents();
        refresh();
    }

    private void plus1Press() {
        table.addFood(current.getType(), 1.0);
        refresh();
    }

    private void plushalfPress() {
        table.addFood(current.getType(), .5);
        refresh();
    }

    private void plus5Press() {
        table.addFood(current.getType(), 5);
        refresh();
    }

    private void plus10Press() {
        table.addFood(current.getType(), 10);
        refresh();
    }

    private void foodsValueChanged() {
        this.current = foods.getSelectedValue();
        refresh();
    }

    private void addPress() {
        String allTypes = String.join(", ", Food.FoodType.getAllTypes());
        String typeName = JOptionPane.showInputDialog("Enter food type, valid are: " + allTypes);
        Food.FoodType type = Food.FoodType.getType(typeName);
        assert type != null;
        Food f = new Food(type);
        table.addNewFood(f);
        refresh();
    }

    private void initComponents() {
        allprice = new JLabel();
        scrollPane1 = new JScrollPane();
        foods = new JList<>();
        add = new JButton();
        panel2 = new JPanel();
        name = new JLabel();
        nameField = new JTextField();
        price = new JLabel();
        priceField = new JTextField();
        quantity = new JLabel();
        textField1 = new JTextField();
        panel3 = new JPanel();
        plus1 = new JButton();
        plushalf = new JButton();
        panel4 = new JPanel();
        plus5 = new JButton();
        plus10 = new JButton();

        setMinimumSize(new Dimension(500, 300));
        setLayout(new BorderLayout());

        allprice.setText("Price: ");
        allprice.setFont(new Font("Vijaya", Font.BOLD, 18));
        add(allprice, BorderLayout.NORTH);

        {

            foods.setMaximumSize(new Dimension(100, 54));
            foods.setMinimumSize(new Dimension(100, 54));
            foods.setPreferredSize(new Dimension(100, 54));
            //foods.addListSelectionListener(e -> foodsValueChanged());
            foods.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    foodsValueChanged();
                }
            });
            scrollPane1.setViewportView(foods);
        }
        add(scrollPane1, BorderLayout.WEST);

        add.setIcon(new ImageIcon(getClass().getResource("/plus.png")));
        add.addActionListener(e -> addPress());
        add(add, BorderLayout.SOUTH);

        {
            panel2.setLayout(new GridLayout(4, 2));

            name.setText("Name: ");
            name.setLabelFor(nameField);
            panel2.add(name);

            nameField.setMaximumSize(new Dimension(2147483647, 28));
            panel2.add(nameField);

            price.setText("Price: ");
            price.setLabelFor(priceField);
            panel2.add(price);
            panel2.add(priceField);

            quantity.setText("Quantity: ");
            panel2.add(quantity);
            panel2.add(textField1);

            {
                panel3.setLayout(new FlowLayout());

                plus1.setText("+1");
                plus1.addActionListener(e -> plus1Press());
                panel3.add(plus1);

                plushalf.setText("+0.5");
                plushalf.addActionListener(e -> plushalfPress());
                panel3.add(plushalf);
            }
            panel2.add(panel3);

            {
                panel4.setLayout(new FlowLayout());

                plus5.setText("+5");
                plus5.addActionListener(e -> plus5Press());
                panel4.add(plus5);

                plus10.setText("+10");
                plus10.addActionListener(e -> plus10Press());
                panel4.add(plus10);
            }
            panel2.add(panel4);
        }
        add(panel2, BorderLayout.CENTER);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel allprice;
    private JScrollPane scrollPane1;
    private JList<Food> foods;
    private JButton add;
    private JPanel panel2;
    private JLabel name;
    private JTextField nameField;
    private JLabel price;
    private JTextField priceField;
    private JLabel quantity;
    private JTextField textField1;
    private JPanel panel3;
    private JButton plus1;
    private JButton plushalf;
    private JPanel panel4;
    private JButton plus5;
    private JButton plus10;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private Table table;
    private Food current = null;

    public void refresh() {
//        nameField.setText(food.getName());
//        priceField.setText(String.valueOf(food.myPrice()));
//        textField1.setText(String.valueOf(food.quantity()));
        DefaultListModel<Food> food = new DefaultListModel<>();
        Arrays.stream(table.getAllFoods()).forEach(food::addElement);
        double sum = Arrays.stream(table.getAllFoods()).mapToDouble(Food::myPrice).sum();
        foods.setModel(food);
        refreshFood();
        allprice.setText("Price: " + sum);
    }

    public void refreshFood() {
        if (current != null) {
            nameField.setText(current.getName());
            priceField.setText(String.valueOf(current.myPrice()));
            textField1.setText(String.valueOf(current.quantity()));
        }
    }


    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        JFrame jfr = new JFrame("org.roman.restaurant.FoodPanel");
        Table t = new Table(2);
        t.addNewFood(new Food(Food.FoodType.FISH_SUSHI));
        t.addNewFood(new Food(Food.FoodType.SWEETS));
        t.addNewFood(new Food(Food.FoodType.JUICE));
        //t.addFood(Food.FoodType.FISH_SUSHI, 7.5);
        FoodPanel x = new FoodPanel(t);
        jfr.setContentPane(x);
        jfr.setSize(500, 300);
        jfr.setVisible(true);
        jfr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
