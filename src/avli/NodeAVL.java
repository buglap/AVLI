/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avli;

/**
 *
 * @author 
 */
public class NodeAVL {

    int data;
    int balanceFactor;
    int height;
    NodeAVL rightChild;
    NodeAVL leftChild;
 
    public NodeAVL(int dato) {
        this.data = dato;
        this.height = 1;
        this.balanceFactor = 0;
        this.rightChild = null;
        this.leftChild = null;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setRightChild(NodeAVL rightChild) {
        this.rightChild = rightChild;
    }

    public void setLeftChild(NodeAVL leftChild) {
        this.leftChild = leftChild;
    }

    public int getData() {
        return data;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public int getHeight() {
        return height;
    }

    public NodeAVL getRightChild() {
        return rightChild;
    }

    public NodeAVL getLeftChild() {
        return leftChild;
    }

 

    
}
