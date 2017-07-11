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
public class NeighborNode {
    
    public char id;
    public int costo;
    public NeighborNode nextNeighbor;

    public NeighborNode() {
    }

    public NeighborNode(char id, int costo, NeighborNode nextNeighbor) {
        this.id = id;
        this.costo = costo;
        this.nextNeighbor = nextNeighbor;
    }
    
}
