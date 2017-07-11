/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author mary_
 */
public class MyQueue {
    public Node nodeHead,nodeTail;

    public MyQueue() {
    }
   
    public void enqueue(char id, int distance){
        Node node = new Node(id);
        node.distance = distance;
        
        // si esta vacia
        if(this.nodeHead == null || this.nodeTail == null){
            this.nodeTail = node;
            this.nodeHead = node;
        }else{
            // insertar en orden de distancia
            Node temp = this.nodeHead;

            while(temp != null && temp.distance > node.distance){
                temp = (Node) temp.next;
            }

            // hay que insertar al final
            if(temp == null){
                this.nodeTail.next = node;
                node.prev = this.nodeTail;
                this.nodeTail = node; // actualizo
            }
            // hay que insertar al principio
            else if(temp.prev == null){
                this.nodeHead.prev = node;
                node.next = this.nodeHead;
                this.nodeHead = node; // actualizo
            }
            // hay que insertar en el medio
            else{
                ((Node) temp.prev).next = node;
                node.prev = temp.prev;
                temp.prev = node;
                node.next = temp;
            }
        }
        
        
        
    }
    
    public boolean isEmpty(){
        return this.nodeHead == null || this.nodeTail == null;
    }
    
    public Node dequeue(){
       Node node = this.nodeTail;
       
       // se elimina la cola del queue
       if(node != null){
           this.nodeTail = (Node)this.nodeTail.prev;
           if(this.nodeTail != null){
               this.nodeTail.next = null;
           }
       }
       return node;
    }
    
    public int size(){
        int size =0;
        Node temp = nodeHead;
        while(temp != null){
            size++;
            temp = temp.next;
        }
        
        return size;
    }
}
