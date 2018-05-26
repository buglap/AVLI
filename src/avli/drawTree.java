/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avli;

import java.awt.Color;
import java.awt.Graphics;

/**
/*permite graficar un árbol binario en el que se dibuja el un rectangulo seguido del dato del nodo, basado en el recorrido inorden
 */
public class drawTree {
    public void drawTree(Graphics g, NodeAVL sheet, int x, int y,int xangle,int yangle,int level){
     if (sheet == null) return; //arbo vacio
     
     if(sheet.getLeftChild()!= null){
         g.setColor(Color.black);
         g.drawLine(x, y, x - xangle+(sheet.height*4), y + yangle ); 
         //dibuja la linea que une al nodo izquiedo
     }
     if(sheet.getRightChild()!= null){
         g.setColor(Color.black);
         g.drawLine(x, y, x + xangle-(sheet.height*4), y + yangle);
         //dibuja la linea que une al nodo derecho
     }
     g.setColor(Color.pink);
     //pinta el rectangulo
     g.fill3DRect(x-10, y-20, 35-level*2, 35, true);
     
     g.setColor(Color.black);
     //pinta el dato
     g.drawString(sheet.data+"", x , y );
     if(sheet.getLeftChild()!=null){
        drawTree(g, sheet.getLeftChild(), (int)(x - xangle), (y + yangle),xangle+level*2,yangle,level+1); 
        //usa recursión para seguir dibbujando el sub árbol izquierdo
     }
     if((sheet.getRightChild()!=null) && (sheet.rightChild.data != sheet.data)){
     drawTree(g, sheet.getRightChild(), (int)(x + xangle), (y + yangle),xangle-level*2,yangle,level+1);
    //usa recursión para seguir dibbujando el sub árbol derecho
        }
     }

}
