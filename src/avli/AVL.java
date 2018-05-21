/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avli;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 Este programa está basado en el libro de algoritmia 3 de Roberto Florez y
 algunos algoritmos realizados por Mayank Jaiswal posteados en la página
 https://www.geeksforgeeks.org/
 **/
public class AVL {
    /* la raiz del árbol, se ultilizará en la mayoría de algorimos para 
    iniciar los recorridos
    */
    private NodeAVL root; 
    //variable para controlar el número de datos del árbol
    private int totalNodes = 0;
    /*variables para controlar lo que se envía a la interfas en los 
    recorridos inorden, preorden y posorden en sus algorimos recursivos 
    que devuelven una cadena*/
    public String inorden, preorden, posorden;

    public void setTotalNodes(int totalNodes) {
        this.totalNodes = totalNodes; //asigna el número de datos
    }
    
    public AVL(){
        root = null; //inicializa la raiz de un árbol como nula
        inorden = preorden= posorden = "";
    }
    
    public NodeAVL getRoot() {
		return this.root; //retorna la raiz del árbol
	}


    public int getTotalNodes() {
        return totalNodes; //retorna el número de datos
    }
    
    //funcionalidad de busqueda de un dato
      boolean findData(int d){
      /*utiliza otro metodo para el recorrido del árbol balanceado AVLSearch que
         el nodo en el que encontró el dato, o null si no lo encontró*/
            root = AVLSearch( d, root);
           
            if(root != null){
                return true; //lo encontró
            }
            return false; // no lo encontró
    }

    //Busqueda del dato en un AVL
    public NodeAVL AVLSearch(int data, NodeAVL root){
        if (root == null){
       //caso en el que no hay  elementos 
            return null; 
        }else if(root.data == data){
     // caso en el dato de la raiz corresponde al buscado
            return root; 
        }else if(root.data < data){
     /*como es un AVL si el dato de la raiz es menor que el buscado, se 
     utiliza la recusión para buscarlo en la rama izquierda de la raiz acual */
            return AVLSearch(data,root.leftChild);
        }else if(root.data > data) {
     /*como es un AVL si el dato de la raiz es menor que el buscado, se 
     utiliza la recusión para buscarlo en la rama derecha de la raiz acual */
            return AVLSearch(data,root.rightChild);
        }   else{
      //caso en el que no hay  elementos que correspondan con el dato buscado
            return null;
        }
}
    /*Se tiliza la definiciín de factor de balanceo 
    y otro metodo para obtener la altura*/
   public int getBalanceFactor(NodeAVL root) {
        if (root == null) return 0; //no hay elementos
        return height(root.leftChild) - height(root.rightChild);
    } 
    int height(NodeAVL root) {
        if (root == null) return 0; // no hay elementos
        return root.height; //altura asociada al nodo
        
    }
    /*Se utilaza una defición recursiva para obtener la altura del árbol, 
    comparando el maximo de alturas  de la rama derecha y la izquierda*/
    public int getheight(NodeAVL root) {
		if (root == null)return 0;
		return Math.max(getheight(root.leftChild), getheight(root.rightChild)) + 1;
	}
    //detemina el mayor entre dos datos
   int maxData(int data1, int data2) {
        return (data1 > data2) ? data1 : data2; //retorna el dato mayor
    }
   
   //rotacion derecha simple
   public NodeAVL simpleRightRotation(NodeAVL current){
    //newRootNode --nueva raiz, corresponde a hijo izquierdo de la raiz  actual
        NodeAVL newRootNode = current.leftChild; 
       // se usa un nodo temporal para guardar la rama derecha de la nueva raiz
        NodeAVL temporal = newRootNode.rightChild;
        // se asina al lado derecho de la  nueva raiz el nodo actual
        newRootNode.rightChild = current;
        /* se conecta al lado izquierdo del actual lo que tenia la nueva raiz y
        que fue guardado en temporal*/
        current.leftChild = temporal;
 
        // Se acualiza la altura de ambos nodos
        current.height = maxData(height(current.leftChild), height(current.rightChild)) + 1;
        newRootNode.height= maxData(height(newRootNode.leftChild), height(newRootNode.rightChild)) + 1;
 
        // Retorna la nueva raiz
        return newRootNode;
   }
        
    //Rotación izquierda simple
   public NodeAVL simpleLeftRotation(NodeAVL current){
    //newRootNode --nueva raiz, corresponde a hijo derecho de la raiz actual
        NodeAVL newRootNode = current.rightChild;
      // se usa un nodo temporal para guardar la rama izquierda de la nueva raiz  
        NodeAVL temporal = newRootNode.leftChild;
 
      // se asina al lado izquierdo de la raiz el nodo actual
        newRootNode.leftChild = current;
       /* se conecta al lado derecho del actual lo que tenia la nueva raiz y
        que fue guardado en temporal*/
        current.rightChild = temporal;
 
        //  Se actualizan las alturas de ambos nodos
        current.height = maxData(height(current.leftChild), height(current.rightChild)) + 1;
        newRootNode.height = maxData(height(newRootNode.leftChild), height(newRootNode.rightChild)) + 1;
 
        // retorna la nueva raiz
        return newRootNode;
   }

  /*devuelve una cadena de texto con el recorrido en preorden utilizando recursión
    para guardar la raiz en el texto mientras recorre el por hijo izquierdo y luego el
    por hijo derecho*/
    public String preOrder(NodeAVL current) {
        if (current != null) { //para guardar solo los datos que no sean nulos
            preorden = preorden + current.getData() + " ";
            preOrder(current.getLeftChild());
            preOrder(current.getRightChild());
		}
        return preorden;
	}
  /*devuelve una cadena de texto con el recorrido en inorden utilizando recursión
   para recorrer el por hijo izquierdo guardar la raiz y luego recorrer por hijo
   derecho además de ir contando cuantos nodos tiene el árbol mientras realiza
    el recorrido*/
    public String inOrder(NodeAVL current) {
        if (current != null) {          
            inOrder(current.getLeftChild());
            inorden = inorden + current.getData() + " ";
            inOrder(current.getRightChild());
            totalNodes = totalNodes + 1;
		}
        return inorden;
	}
/*devuelve una cadena de texto con el recorrido en preorden utilizando recursión
    para  recorrer el por hijo izquierdo, luego el por hijo derecho y posteriormente
    ir guandando laz raices*/
    public String postOrder(NodeAVL current) {
	if (current != null) {
            postOrder(current.getLeftChild());
            postOrder(current.getRightChild());
            posorden = posorden + current.getData() + " ";
		}
        return posorden;
        }
    
    /*Algorimo para insertar un dato, utilizando otro para insertarlo en un arbol
    de busqueda balanceado*/
   public void Insert(int data){
        NodeAVL n = new NodeAVL(data);
        if(root == null){
            /*crea un nuevo nodo si el arbol no tiene ningun elemento, y pasa a 
            convertirse en la raiz del árbol*/
            root = n; 
        }else{
            //inserta de manera balanceada con ayuda del otro metodo
            root = AVLInsertBalanceForm(root, data);
            
        }
    }
   /*Algoritmo basado en el algoritmo de inserción de Mayank Jaiswal
    de la página https://www.geeksforgeeks.org/avl-tree-set-2-deletion/
   */
   
    NodeAVL AVLInsertBalanceForm(NodeAVL root, int data) {
        //crea nuevo noto para el dato
        if (root == null)
            return (new NodeAVL(data));
        /* si el dato a insertar es menor al de la raiz actual, utiliza recución
        para insertarlo buscar la posición adecuada*/
        
        if (data < root.data)
            root.leftChild = AVLInsertBalanceForm(root.leftChild, data);
        /* si el dato a insertar es mayor al de la raiz actual, utiliza recución
        para insertarlo buscar la posición adecuada*/
        else if (data > root.data)
            root.rightChild = AVLInsertBalanceForm(root.rightChild, data);
        else // No permite datos duplicados
            return root;
 
        /* Actualiza la altura de su ancestro */
        root.height = 1 + maxData(height(root.leftChild),
                              height(root.rightChild));
 
        /* Obtiene el factor de balanceo del ancestro, para verificar si 
        el árbol está desbalanceado */
        int balance = getBalanceFactor(root);
 
        // Existen 4 casos de desbalanceo a considerar
        //Cuando requiere una totación a la derecha
        if (balance > 1 && data < root.leftChild.data)
            return simpleRightRotation(root);
 
        // Cuando requiere una rotación a la izquierda
        if (balance < -1 && data > root.rightChild.data)
            return simpleLeftRotation(root);
 
        // cuando requiere una doble rotación a la derecha
        if (balance > 1 && data > root.leftChild.data) {
            root.leftChild = simpleLeftRotation(root.leftChild);
            return simpleRightRotation(root);
        }
 
        // cuando requiere una doble rotación a la izquierda
        if (balance < -1 && data < root.rightChild.data) {
           root.rightChild = simpleRightRotation(root.rightChild);
           return simpleLeftRotation(root);
        }
        
        /* retorna el nodo acualizado */
        return root;
    }
   /*Algorimo para insertar un dato, utilizando otro para eliminarlo en un arbol
    de busqueda balanceado*/
    public void delete(int data){
        if(root != null){
            root = deleteNode(root,data);
        }
    }
    
   /*Algoritmo basado en el algoritmo de e de Mayank Jaiswal
    de la página https://www.geeksforgeeks.org/avl-tree-set-2-deletion/
   */
    NodeAVL deleteNode(NodeAVL root, int data)
    {
      
        /*Si el dato a eliminar es menor que el dato de la raiz se busca
        en el subarbol de la izquierda, para ser eliminado 
                utilizando recursion*/
        if (data < root.data)
            root.leftChild = deleteNode(root.leftChild, data);
 
        /*Si el dato a eliminar es mayor que el dato de la raiz se busca
        en el subarbol de la derecha, para ser eliminado 
                utilizando recursion*/
        else if (data> root.data)
            root.rightChild = deleteNode(root.rightChild, data);
 
        /*Cuando el dato a eliminar es igual al de la raiz 
        este es el nodo a eliminar*/
        else
        {
            //para eliminar un dato en un arbol balanceado hay tres casos:
    // cuando el nodo a eliminar no tiene hijos, solo tiene uno, o tiene 2 hijos
            if ((root.leftChild == null) || (root.rightChild == null)){
                NodeAVL temp = null;
                if (temp == root.leftChild)
                    temp = root.rightChild;
                else
                    temp = root.leftChild;
 
                // cuando no tiene hijos
                if (temp == null){
                    temp = root;
                    root = null;
                }
                else   // cuando tiene un hijo
                    root = temp; //copia el contenido del hijo
            }
            else{
            /*Cuando tiene 2 hijos: Obtiene el sucesor más pequeño en 
                el subárbol derecho)*/
                NodeAVL temp = minValueNode(root.rightChild);
                // copia el dato del succesor
                root.data = temp.data;
                //Elimina al sucesor
                root.rightChild = deleteNode(root.rightChild, temp.data);
            }
        }
        // cuando el árbol tiene un solo nodo
        if (root == null)
            return root;
 
        // Actualixa la altura del nodo
        root.height = maxData(height(root.leftChild), height(root.rightChild)) + 1;
        /* Obtiene el factor de balanceo del ancestro, para verificar si 
        el árbol está desbalanceado */
        int balance = getBalanceFactor(root);
        // Existen 4 casos de desbalanceo a considerar
        //Cuando requiere una rotación a la derecha
        if (balance > 1 && getBalanceFactor(root.leftChild) >= 0)
            return simpleRightRotation(root);
         //Cuando requiere una doble rotación a la deracha
        if (balance > 1 && getBalanceFactor(root.leftChild) < 0){
            root.leftChild = simpleLeftRotation(root.leftChild);
            return simpleRightRotation(root);
        }
        //Cuando requiere una rotación a la izquierda
        if (balance < -1 && getBalanceFactor(root.rightChild) <= 0)
            return simpleLeftRotation(root);
 
         //Cuando requiere una doble rotación a la izquierda
        if (balance < -1 && getBalanceFactor(root.rightChild) > 0){
            root.rightChild = simpleRightRotation(root.rightChild);
            return simpleLeftRotation(root);
        }
        return root;
    }
    
    //permite encontrar el minimo valor del árbol
     NodeAVL minValueNode(NodeAVL node)
    {
        NodeAVL current = node;
        /*ciclo para buscar la hoja izquierda de más bajo nivel */
        while (current.leftChild != null)
           current = current.leftChild;
        return current;
    }
     
     /*permite hacer el recorrido Breadth first search sobre el árbol retornando
     una cadena de texto con esta*/
    public String orderBFS(NodeAVL root){
        //utiliza una cola para guardar los elementos del arbol
        Queue<NodeAVL> queue = new LinkedList<NodeAVL>();
        queue.add(root);
        String result = "Recorrido BFS: ";
        while(!queue.isEmpty()){
            NodeAVL n = queue.remove();
        //guarda el la variable result el valor del nodo desencolado
            result = result + String.valueOf(n.data)+ " ";
            if(n.leftChild != null){
                queue.add(n.leftChild); //agrega al hijo izquierdo a la cola 
            }
            if(n.rightChild != null){
                queue.add(n.rightChild); // agrega al hijo derecho a la cola
            }
        }
        return result;
    }
    /*permite hacer el recorrido Deapth first search sobre el árbol retornando
   una cadena de texto con esta. Está basado en el algoritmo realizado por 
   Paulina Jonušaitė publicado en la página 
    https://www.quora.com/How-do-I-implement-a-BFS-and-DFS-on-a-binary-tree-in-Java*/
    
    
    public String orderDFS(final NodeAVL root) {
        String result =" Recorrido DFS: ";
    //utiliza una cola que permite almacenar los nodos del árbol
    //inicializa la cola con la raiz
    final Deque<NodeAVL> stack = new LinkedList<>(Collections.singleton(root));
    while (!stack.isEmpty()) {
        final NodeAVL current = stack.removeFirst(); 
        //remueve el mimero nodo de la lista
        //guarda el valor del dato actual en el recorrido
        result = result +String.valueOf(current.data)+ " ";
        //si tiene hijo izquierdo pasa a ser el primero en la cola
        if (current.leftChild != null) stack.addFirst(current.leftChild);
        //si tiene hijo izquierdo pasa a ser el primero en la cola
        if (current.rightChild != null) stack.addFirst(current.rightChild); 
    }
    //retorna una cadena de texto con el orden del recorrido
    return result;
}
    
  /*utiliza la definición de arbol  binario está lleno para conocer si lo está
    que es numero total de nodos = 2^nivel -1 para que lo esté*/
 public String fullTree(NodeAVL root, int total){
     String a = "No";
     int hight = getheight(root);
     inOrder(root);
     if(total == Math.pow(2, hight)-1){
          a = "Si"; //está lleno
     }
     return a; //si llega hasta aquí es porque no lo está
 }
 
 /*Algorimo para reconocer si un árbol ninario está completo, basado en el algorimo de*/
 public String isComplete(NodeAVL root){
     //Creamos una variable para determinar cuando el árbol está completo
        String complete = "No";
        // caso en el que el árbol está vacio
        if(root == null){
            complete ="Si"; // completo
            return complete;
        }
        // Creamos una lista Vacia para guardar los nodos
        Queue<NodeAVL> queue =new LinkedList<>();
        // Do level order traversal using queue.
        queue.add(root);
        while(!queue.isEmpty()){
            NodeAVL temporal = queue.remove();
            /* Check if left child is present*/
            if(temporal.leftChild != null){
                 // If we have seen a non full node, and we see a node
                 // with non-empty left child, then the given tree is not
                 // a complete Binary Tree
                if(complete.contains("Si")){
                    complete = "No";
                    return complete;
                }
                queue.add(temporal.leftChild); // encolamos el hijo izquierdo
            }
            // If this a non-full node, set the flag as true
            else
                complete = "Si";
             
            /* Check if right child is present*/
            if(temporal.rightChild != null)
            {
                // If we have seen a non full node, and we see a node
                // with non-empty right child, then the given tree is not
                // a complete Binary Tree
                 if(complete.contains("Si")){
                    complete = "No";
                    return complete;
                }
                 
                // Enqueue Right Child
                queue.add(temporal.rightChild);
                 
            }
            // If this a non-full node, set the flag as true
            else
                complete = "Si";
        }
         // If we reach here, then the tree is complete Bianry Tree
      complete= "Si";
      return complete;
    }
 /*Algorimo para retornar una lista de ancestro de un data, basado en el algoritmo de  Chandra Prakash
 pubicado en https://www.geeksforgeeks.org/print-ancestors-of-a-given-binary-tree-node-without-recursion/*/
 public String printAncestors(NodeAVL root,int key){
     String result = "Los ancestros de "+ String.valueOf(key) + " son: ";
        if(root == null)
            return null;
         //Se crea una pila para guardar los ancestros
        Stack<NodeAVL> stack = new Stack<>();
        // recorrido posorden para encontrar el dato
        while(true){
            /* atraviesa el lado izquierdo. mientras desapila los nodos 
            para poder atravesar el dado derecho*/
            while(root != null && root.data != key){
                stack.push(root);   // apila el nodo
                root = root.leftChild;   // pasa al siguiente nodo
            }
             //el ciclo termina cuando el dato es encontrado
            if(root != null && root.data == key)
                break;
            /*si el hijo derecho del elemento que está en la cima de la pila 
            es nulo, se desapila */
            if(stack.peek().rightChild == null){
                root =stack.peek();
                stack.pop();
                //se desapila el de la cima también, para procesar el hijo izquierdo
                while( stack.empty() == false && stack.peek().rightChild == root){
                    root = stack.peek();
                    stack.pop();
                }
            }
            // Si la pila no está vacia se toma el hijo derecho como raiz
            root = stack.empty() ? null : stack.peek().rightChild;
        }
    //Si la pila no esta vacia, se guarda su contenido en la variable resultado
        while( !stack.empty() ){
            result = result+ String.valueOf(stack.peek().data)+" ";
            stack.pop();
        }
        return result;
    }
}

