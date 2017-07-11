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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author maria
 */
public class Node extends JInternalFrame implements ActionListener {

    private JTextField node;
    private JLabel jlnode;
    private JButton ingresar;
    private Window window;

    public Node(Window window) {
        super();
        this.window = window;
        this.setClosable(true);
        this.setSize(250, 140);
        this.setLayout(null);
        this.node = new JTextField();
        this.node.setBounds(140, 20, 50, 30);
        this.add(this.node);
        this.jlnode = new JLabel("Node: ");
        this.jlnode.setBounds(10, 20, 130, 30);
        this.add(this.jlnode);
        this.ingresar = new JButton("Add");
        this.ingresar.setBounds(140, 60, 70, 30);
        this.ingresar.addActionListener(this);
        this.add(this.ingresar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ingresar) {
            boolean exists = false;
            if (this.node.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Enter an id for the node.");
            } else {
                char id = this.node.getText().toUpperCase().charAt(0);
                for (int i = 0; i < window.nodes.size(); i++) {
                    if (window.nodes.get(i).id == id) {
                        exists = true;
                    }
                }
                if (!exists) {
                    this.window.graph.insertGraphNode(id);
                    this.window.update();
                    this.window.nodes.add(new NodePaint(id));
                    this.node.setText("");
//                this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "This node already exists");
                    this.node.setText("");
                }
            }

        }
    }

}
