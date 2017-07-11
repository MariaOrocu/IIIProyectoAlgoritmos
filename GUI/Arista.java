/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.EdgePaint;
import Domain.NodePaint;
import GUI.Window;
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
public class Arista extends JInternalFrame implements ActionListener {

    private JComboBox a;
    private JLabel jla;
    private JComboBox b;
    private JLabel jlb;
    private JTextField jtcosto;
    private JLabel jlcosto;
    private JButton ingresar;
    private Window window;

    public Arista(Window window) {
        super();
        this.window = window;
        this.setClosable(true);
        this.setSize(280, 220);
        this.setLayout(null);
        this.a = new JComboBox();
        this.a.setBounds(140, 20, 70, 30);
        this.add(this.a);
        this.jla = new JLabel("From: ");
        this.jla.setBounds(10, 20, 130, 30);
        this.add(this.jla);
        this.b = new JComboBox();
        this.b.setBounds(140, 60, 70, 30);
        this.add(this.b);
        this.jlb = new JLabel("To: ");
        this.jlb.setBounds(10, 60, 130, 30);
        this.add(this.jlb);
        this.jtcosto = new JTextField();
        this.jtcosto.setBounds(140, 100, 70, 30);
        this.jtcosto.setText("0");
        this.add(this.jtcosto);
        this.jlcosto = new JLabel("Cost: ");
        this.jlcosto.setBounds(10, 100, 130, 30);
        this.add(this.jlcosto);
        this.ingresar = new JButton("Add");
        this.ingresar.setBounds(140, 140, 70, 30);
        this.ingresar.addActionListener(this);
        this.add(this.ingresar);
        for (int i = 0; i < window.nodes.size(); i++) {
            a.addItem(window.nodes.get(i).id);
            b.addItem(window.nodes.get(i).id);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ingresar) {
            if (window.nodes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Insert a node before");
            } else {
                char from = a.getSelectedItem().toString().charAt(0);
                char to = b.getSelectedItem().toString().charAt(0);
                if (from == to) {
                    JOptionPane.showMessageDialog(this, "Can't be de same node.");
                    jtcosto.setText("0");
                } else {
                    boolean exist = false;
                    for (int i = 0; i < window.edges.size(); i++) {
                        EdgePaint d = window.edges.get(i);
                        if ((d.from.id == from && d.to.id == to) || d.from.id == to && d.to.id == from) {
                            exist = true;
                            break;
                        }
                    }
                    if (!exist) {
                        NodePaint fromNode = null, toNode = null;
                        for (int i = 0; i < window.nodes.size(); i++) {
                            if (window.nodes.get(i).id == from) {
                                fromNode = window.nodes.get(i);
                            } else if (window.nodes.get(i).id == to) {
                                toNode = window.nodes.get(i);
                            }
                        }
                        try {
                            int cost = Integer.parseInt(jtcosto.getText());
                            this.window.graph.insertNeighbor(from, to, cost);
                            this.window.graph.insertNeighbor(to, from, cost);
                            this.window.edges.add(new EdgePaint(fromNode, toNode, cost));
                            this.window.update();
                            jtcosto.setText("0");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Enter a valid cost.");
                            jtcosto.setText("0");
                        }
//                        this.dispose();
                    }else{
                        JOptionPane.showMessageDialog(this, "Edge already exits.");
                        jtcosto.setText("0");
                    }
                }

            }
        }

    }

}
