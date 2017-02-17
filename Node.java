/**
 * A Node represents an element of a binary search tree
 * 
 * @author Teodor I. Mitrache
 * @version 1.0
 */
public class Node
{
    private int value;
    private String name;
    private Node leftChild, rightChild;
    
    Node(int value, String name){
        this.value = value;
        this.name = name;
    }
    
    public String toString(){
        return name + " has value: " + value;
        //+ "; \nLeft child: " + getLeftChild() + "; \nRight child: " + getRightChild() ;
    }
    
    public int getValue(){ return this.value; }
    public String getName(){ return this.name; }
    public Node getLeftChild(){ return this.leftChild; }
    public Node getRightChild(){ return this.rightChild; }
    
    public void setValue(int value){ this.value = value; }
    public void setName(String name){ this.name = name; }
    public void setLeftChild(Node leftChild){ this.leftChild = leftChild; }
    public void setRightChild(Node rightChild){ this.rightChild = rightChild; }
}