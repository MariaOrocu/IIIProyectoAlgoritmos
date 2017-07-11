/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.NodePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author maria
 */
public class Dijkstra  extends JInternalFrame implements ActionListener{
    private JComboBox from;
    private JLabel jlFrom;
    private JComboBox to;
    private JLabel jlTo;
    private JButton search;
    private Window window;

    public Dijkstra(Window w) {
        this.window = w;
        this.setClosable(true);
        this.setSize(280, 220);
        this.setLayout(null);
        this.from = new JComboBox();
        this.from.setBounds(140, 20, 70, 30);
        this.add(this.from);
        this.jlFrom = new JLabel("From: ");
        this.jlFrom.setBounds(10, 20, 130, 30);
        this.add(this.jlFrom);
        this.to = new JComboBox();
        this.to.setBounds(140, 60, 70, 30);
        this.add(this.to);
        this.jlTo = new JLabel("To: ");
        this.jlTo.setBounds(10, 60, 130, 30);
        this.add(this.jlTo);
        this.search = new JButton("Search");
        this.search.setBounds(140, 140, 90, 30);
        this.search.addActionListener(this);
        this.add(this.search);
        for (int i = 0; i < window.nodes.size(); i++) {
            from.addItem(window.nodes.get(i).id);
            to.addItem(window.nodes.get(i).id);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.search) {
            if (window.nodes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Insert a node before");
            } else {
                char fromC = this.from.getSelectedItem().toString().toUpperCase().charAt(0);
                char toC = this.to.getSelectedItem().toString().toUpperCase().charAt(0);
                if (fromC == toC) {
                    JOptionPane.showMessageDialog(this, "Can't be de same node.");
                } else {
                    window.clean();
                    window.graph.dijkstra(fromC);
                    window.cost = window.graph.getPath(fromC, toC);
                    window.mark = true;
                    window.update();
                    this.dispose();
                }
            }

        }
    }

}
