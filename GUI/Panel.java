/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.EdgePaint;
import Domain.NodePaint;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.QuadCurve2D;
import javax.swing.JDesktopPane;

/**
 *
 * @author maria
 */
public class Panel extends JDesktopPane{
    
    private static final int SIZE = 10;
    private int a = SIZE / 2;
    private int b = a;
    private int r = 4 * SIZE / 5;
    private Window window;
    
    public Panel(Window w) {
        this.window = w;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int n = window.nodes.size();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);
        a = (getWidth() / 2);
        b = (getHeight() / 2) - 40;
        int m = Math.min(a, b);
        r = 4 * m / 5;
        int r2 = Math.abs(m - r) / 2;
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        for (int i = 0; i < n; i++) {
            NodePaint node = window.nodes.get(i);
            double t = 2 * Math.PI * i / n;
            int x = (int) Math.round(a + r * Math.cos(t));
            int y = (int) Math.round(b + r * Math.sin(t)); 
            node.x = x - r2;
            node.y = y - r2;
        }
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < this.window.edges.size(); i++) {
            EdgePaint edge = this.window.edges.get(i);
            g2d.setColor(Color.BLACK);
            int x1 = edge.from.x;
            int y1 = edge.from.y;
            int x2 = edge.to.x;
            int y2 = edge.to.y;
            int contx = Math.abs(x1 - x2);
            int conty = Math.abs(y1 - y2);
            if (window.mark) {
                if (edge.mark) {
                    QuadCurve2D.Double curve = new QuadCurve2D.Double(x1 + 19, y1 + 30, (contx == 0) ? x1 - 10 : (contx / 2) + Math.min(x1, x2) + 50,
                            (conty == 0) ? y1 - 10 : (conty / 2) + Math.min(y1, y2) + 50, x2 + 19, y2 + 30);
                    ((Graphics2D) g).draw(curve);
                    g2d.setColor(Color.RED);
                    g2d.drawString(edge.cost + "", (contx == 0) ? x1 - 10 : (contx / 2) + Math.min(x1, x2) + 50, (conty == 0) ? y1 - 10 : (conty / 2) + Math.min(y1, y2) + 50);
                }
            } else {
                QuadCurve2D.Double curve = new QuadCurve2D.Double(x1 + 19, y1 + 30, (contx == 0) ? x1 - 10 : (contx / 2) + Math.min(x1, x2) + 50,
                        (conty == 0) ? y1 - 10 : (conty / 2) + Math.min(y1, y2) + 50, x2 + 19, y2 + 30);
                ((Graphics2D) g).draw(curve);
                g2d.setColor(Color.RED);
                g2d.drawString(edge.cost + "", (contx == 0) ? x1 - 10 : (contx / 2) + Math.min(x1, x2) + 50, (conty == 0) ? y1 - 10 : (conty / 2) + Math.min(y1, y2) + 50);
            }
        }
        
        for (int i = 0; i < window.nodes.size(); i++) {
            g2d.setColor(Color.blue);
            NodePaint node = window.nodes.get(i);
            if (window.mark) {
                if (node.mark) {
                    g2d.fillOval(node.x, node.y, 50, 50);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(node.id + "", (float) node.x + 19, (float) node.y + 30);
                }
            } else {
                g2d.fillOval(node.x, node.y, 50, 50);
                g2d.setColor(Color.WHITE);
                g2d.drawString(node.id + "", (float) node.x + 19, (float) node.y + 30);
            }

        }
        if(window.mark){
            g2d.setColor(Color.RED);
            g2d.drawString("Cost: "+window.cost,15,30);
        }

    }
    
    public void updatePanel(){
        this.repaint();
    }
}
    
