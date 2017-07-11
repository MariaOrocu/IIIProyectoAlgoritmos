/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author maria
 */
public class Node {
    public char id;
    public int isVisited;
    public Node next;
    public Node prev;
    public int distance;
    public NeighborNode headInnerList;

    public Node() {
    }

    public Node(char id) {
        this.id = id;
        this.isVisited = 0;
        this.distance = Integer.MAX_VALUE;
        this.next = null;
        this.prev = null;
        this.headInnerList = null;
    }
   
    
}
