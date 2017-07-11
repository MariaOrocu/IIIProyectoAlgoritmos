/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iiiproyectoalgoritmos;

import Domain.Graph;
import javax.swing.JFrame;
import GUI.Window;

/**
 *
 * @author maria
 */
public class IIIProyectoAlgoritmos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph graph = new Graph(new Window());
        
        graph.insertGraphNode('G');
        graph.insertGraphNode('P');
        graph.insertGraphNode('A');
        graph.insertGraphNode('Z');
        
        
        graph.insertNeighbor('A', 'P', 10);
        graph.insertNeighbor('P', 'A', 10);
        graph.insertNeighbor('G', 'P', 4);
        graph.insertNeighbor('P', 'G', 4);
        graph.insertNeighbor('G', 'A', 6);
        graph.insertNeighbor('A', 'G', 6);
        graph.insertNeighbor('Z', 'G', 3);
        graph.insertNeighbor('G', 'Z', 3);
        graph.insertNeighbor('A', 'Z', 2);
        graph.insertNeighbor('Z', 'A', 2);
        graph.insertNeighbor('Z', 'P', 1);
        graph.insertNeighbor('P', 'Z', 1);

//        System.out.println("---------------------------------------");
//        graph.dijkstra('G');
//        graph.getPath('G','A');
//        graph.prim();
//        grafos.insertGraphNode('B');
//        grafos.insertNeighbor('B', 'E', 0);
//
//        grafos.insertGraphNode('A');
//        grafos.insertNeighbor('A', 'D', 0);
//
//        grafos.insertGraphNode('C');
//        grafos.insertNeighbor('C', 'D', 0);
//        grafos.insertNeighbor('C', 'F', 0);
//
//        grafos.insertGraphNode('D');
//        grafos.insertNeighbor('D', 'A', 0);
//        grafos.insertNeighbor('D', 'F', 0);
//        grafos.insertNeighbor('D', 'C', 0);
//
//        grafos.insertGraphNode('F');
//        grafos.insertNeighbor('F', 'E', 0);
//        grafos.insertNeighbor('F', 'D', 0);
//        grafos.insertNeighbor('F', 'C', 0);
//
//        grafos.insertGraphNode('E');
//        grafos.insertNeighbor('E', 'B', 0);
//        grafos.insertNeighbor('E', 'F', 0);
//
//        grafos.printGraph();
//        grafos.recorridoProfundidad();
//        //iniciar busqueda por un nodo diferente
//        GraphNode node = grafos.getNode('A');
//        grafos.rp(node);
//        
        Window window = new Window();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

}
