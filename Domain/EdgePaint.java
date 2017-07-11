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
public class EdgePaint {
    public NodePaint from,to;
    public boolean mark;
    public int cost;

    public EdgePaint() {
        this.from = null;
        this.to = null;
        this.mark = false;
        this.cost = 0;
    }

    public EdgePaint(NodePaint from, NodePaint to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
        this.mark = false;
    }
    
    
}
