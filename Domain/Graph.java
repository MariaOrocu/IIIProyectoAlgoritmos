/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import GUI.Window;

/**
 *
 * @author maria
 */
public class Graph {

    public Node headGraphNode;
    private final Window window;

    public Graph(Window window) {
        this.window = window;
    }

    public void insertGraphNode(char id) {
        Node newNode = new Node(id);
        Node temp = headGraphNode;
        newNode.next = temp;
        headGraphNode = newNode;

    }

    public void printGraph() {
        Node temp = headGraphNode;
        while (temp != null) {
            System.out.print(temp.id + " -> ");
            temp = temp.next;
        }
        System.out.println("End");
    }

    public Node getNode(char id) {
        Node current = headGraphNode;
        while (current != null && current.id != id) {
            current = current.next;
        }
        return current;
    }

    public void insertNeighbor(char idOrigin, char idDestiny, int cost) {
        Node node = getNode(idOrigin);

        //crear un nuevo vecino
        NeighborNode newNeighbor = new NeighborNode();
        newNeighbor.id = idDestiny;
        newNeighbor.costo = cost;
        //
        NeighborNode neighborTemp = node.headInnerList;
        newNeighbor.nextNeighbor = neighborTemp;
        node.headInnerList = newNeighbor;
    }

    public void printGraphWithNeighbors() {
        //variables
        Node node = headGraphNode;
        NeighborNode neighbor;
        while (node != null) {
            System.out.println("Node " + node.id);
            //obtener el nombre vecino de ese nodo
            neighbor = node.headInnerList;
            while (neighbor != null) {
                System.out.print("\t" + neighbor.id);
                neighbor = neighbor.nextNeighbor;
            }
            System.out.println("\tEnd\n\n");
            node = node.next;
        }
    }

    public void recorridoProfundidad() {
        //marcar todos los nodos como no-visitados
        Node temp = headGraphNode;
        while (temp != null) {

            temp.isVisited = 0;
            temp = temp.next;

        }
        System.out.print("Visited nodes order: \n\t");

        //recorrer todos los nodos del grafo
        temp = headGraphNode;
        while (temp != null) {
            //preguntar s ya fue visitado
            if (temp.isVisited != 1) {
                //metodo para profundizar
                rp(temp);

            }
            temp = temp.next;

        }
        System.out.println("NULL\n\n");

    }

    public void rp(Node node) {
        //marcar para indicar que ya fue visitado
        node.isVisited = 1;
        //impresion de control
        //System.out.print(node.id + "-->");//preorden

        //recorrer la lista interna del nodo
        NeighborNode neighbor = node.headInnerList;
        while (neighbor != null) {

            Node nodeFromNeighbor = getNode(neighbor.id);

            //preguntar por su marca(si ya fue visitado)
            if (nodeFromNeighbor.isVisited != 1) {
                rp(nodeFromNeighbor);

            }
            neighbor = neighbor.nextNeighbor;
        }
        System.out.print(node.id + "-->");//posOrden
    }

    
    
    public void dijkstra (char origin){
        cleanGraph();
        
        MyQueue queue = new MyQueue();
        Node originNode = getNode(origin);
        
        originNode.distance = 0;
        queue.enqueue(originNode.id, originNode.distance);
        
        while(!queue.isEmpty()){
            Node currentNode = queue.dequeue();
              
            while(currentNode.isVisited == 1){
                currentNode = queue.dequeue();
            }
            
            currentNode = getNode(currentNode.id);
            currentNode.isVisited = 1;
            
            NeighborNode currentNeighbor = currentNode.headInnerList;
            
            while(currentNeighbor != null){
                
                Node neighborNode = getNode(currentNeighbor.id);
                
                if(neighborNode.isVisited == 0){
                    relax(currentNode, neighborNode, currentNeighbor.costo);
                    queue.enqueue(currentNeighbor.id, neighborNode.distance);
                }
                
                currentNeighbor = currentNeighbor.nextNeighbor;
                
            }
        }
    }
    

    public int getPath(char origin, char destiny){

        
        Node currentNode = getNode(destiny);
        int cost = 0;
        char lastId = ' ';
        System.out.print(currentNode.id+"-> ");
        
        window.markNode(currentNode.id);
        while(currentNode.distance != 0){
            
            NeighborNode possNeighbor = currentNode.headInnerList;
            while(possNeighbor != null){
                Node neighborNode = getNode(possNeighbor.id);
                
                if(neighborNode.distance == currentNode.distance - possNeighbor.costo){
                    System.out.print(possNeighbor.id+"-> ");
                    window.markNode(possNeighbor.id);
                    window.markEdge(currentNode.id, possNeighbor.id);
                    lastId = possNeighbor.id;
                    cost += possNeighbor.costo;
                    // insertar nodo en nuevo grafo
                    
                    
                    // insertar aristas en nuevo grafo
                    
                    
                    // se traslada a ese nuevo nodo
                    currentNode = neighborNode;
                    break;
                }
                
                possNeighbor = possNeighbor.nextNeighbor;
            }
            
        }
        
        System.out.print(origin);
        System.out.print("\n");
        window.markNode(origin);
        window.markEdge(lastId, origin);
        
        return cost;
        
    }
    
    public void cleanGraph(){
        
        Node temp = headGraphNode;
        
        while(temp != null){
            temp.isVisited = 0;
            temp.distance = Integer.MAX_VALUE;
            temp = temp.next;
        }
    }
    
    public void relax(Node current, Node adjacent, int cost){
        if(current.distance + cost < adjacent.distance){
            adjacent.distance = current.distance + cost;
            
        }
    }
    
    public int prim() {
        cleanGraph();

        MyQueue queue = new MyQueue();
        Node originNode = headGraphNode;
        int pesoTotal = 0;
        originNode.distance = 0;
        queue.enqueue(originNode.id, originNode.distance);

        while (size() != queue.size()) {
            Node currentN = queue.nodeHead;
            Node minNode = null;
            Node tempNode = null;
            int costo = Integer.MAX_VALUE;
            while (currentN != null) {

                Node currentNode = getNode(currentN.id);

                NeighborNode currentNeighbor = currentNode.headInnerList;
                while (currentNeighbor != null) {

                    Node neighborNode = getNode(currentNeighbor.id);

                    if (neighborNode.isVisited == 0) {
                        if (neighborNode.distance > currentNeighbor.costo) {
                            //neighborNode.distance = currentNeighbor.costo;
                            if (currentNeighbor.costo < costo) {
                                minNode = neighborNode;
                                tempNode = currentNode;
                                costo = currentNeighbor.costo;
                            }
                        }

                    }

                    currentNeighbor = currentNeighbor.nextNeighbor;

                }

                currentN = currentN.next;
            }
            if(minNode != null && tempNode != null){
                minNode.isVisited = 1;
                minNode.distance = costo;
                queue.enqueue(minNode.id, minNode.distance);
                System.out.println(tempNode.id + " --> " + minNode.id);
                window.markNode(tempNode.id);
                window.markNode(minNode.id);
                window.markEdge(tempNode.id, minNode.id);
                pesoTotal += costo;
            }
        }
        System.out.println("\nCosto total: "+pesoTotal);
        return pesoTotal;
    }
    
    public int size(){
        int size =0;
        Node temp = headGraphNode;
        while(temp != null){
            size++;
            temp = temp.next;
        }
        return size;
    }

}
