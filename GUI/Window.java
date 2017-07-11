/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.EdgePaint;
import Domain.Graph;
import Domain.NodePaint;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author maria
 */
public class Window extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem jminode;
    private JMenuItem jmiarista;
    private Panel desktopPane;
    private JMenuItem dijkstra;
    private JMenuItem prim;
    private JMenuItem jmClean;
    ArrayList<NodePaint> nodes;
    ArrayList<EdgePaint> edges;
    Graph graph;
    boolean mark;
    int cost;

    public Window() {
        super();
        this.graph = new Graph(this);
        this.setSize(new Dimension(800, 700));
        this.setLocation(300, 10);
        this.setLayout(null);
        this.desktopPane = new Panel(this);
        this.desktopPane.setBounds(0, 0, 800, 700);
        this.getContentPane().add(desktopPane);
        this.menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        this.menu = new JMenu("Menu ");
        this.menuBar.add(this.menu);
        this.jmClean = new JMenuItem("Clean Graph");
        this.jmClean.addActionListener(this);
        this.menu.add(jmClean);
        this.jminode = new JMenuItem("Add Nodes");
        this.menu.add(this.jminode);
        this.jminode.addActionListener(this);
        this.jmiarista = new JMenuItem("Add Edges");
        this.menu.add(this.jmiarista);
        this.jmiarista.addActionListener(this);
        this.dijkstra = new JMenuItem("Dijkstra");
        this.menu.add(dijkstra);
        this.dijkstra.addActionListener(this);
        this.prim = new JMenuItem("Prim");
        this.menu.add(prim);
        this.prim.addActionListener(this); 
        
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.mark = false;
        this.cost =0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jminode) {
            Node node = new Node(this);
            node.setVisible(true);
            this.desktopPane.add(node);
        } else if (e.getSource() == this.jmiarista) {
            Arista arista = new Arista(this);
            arista.setVisible(true);
            this.desktopPane.add(arista);
        }else if(e.getSource() == this.jmClean){
            clean();
            update();
            JOptionPane.showMessageDialog(this, "Graph is clean!");
        }else if(e.getSource() == this.prim){
            if(nodes.isEmpty()){
                JOptionPane.showMessageDialog(this, "Insert a node before");
            }else{
                clean();
                mark = true;
                cost = graph.prim();
                update();
            }
        }else if(e.getSource() == this.dijkstra){
             Dijkstra dijkstraV =  new Dijkstra(this);
             dijkstraV.setVisible(true);
             this.desktopPane.add(dijkstraV);
             
        }

    }
    
    public void update(){
        desktopPane.updatePanel();
    };
    
    public void clean(){
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).mark = false;
        }
        for (int i = 0; i < edges.size(); i++) {
            edges.get(i).mark = false;
        }
        mark = false;
        graph.cleanGraph();
        
    }
    
    public void markNode(char id){
        for (int i = 0; i < nodes.size(); i++) {
            NodePaint node = nodes.get(i);
            if(node.id == id){
                node.mark = true;
                break;
            }
        }
    }

    public void markEdge(char from, char to) {
        for (int i = 0; i < edges.size(); i++) {
            EdgePaint d = edges.get(i);
            if ((d.from.id == from && d.to.id == to) || d.from.id == to && d.to.id == from) {
                d.mark =true;
                break;
            }
        }
    }

}
