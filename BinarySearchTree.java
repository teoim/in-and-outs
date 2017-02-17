
/**
 * Implementation of a Binary Search Tree
 * 
 * @author Teodor I. Mitrache 
 * @version 1.0
 */
import java.util.*;
public class BinarySearchTree
{
    private Node root;
    
    public void addNode(int value, String name){
        
        Node newNode = new Node(value, name);
        
        if ( root == null ) // the first created node will be the root
            root = newNode;
        else  // root contains a value
        {
            Node currentNode = root;
            
            while(true)
            {
                if( value < currentNode.getValue() )  //insert new node in left sub-tree
                {
                    if(currentNode.getLeftChild() != null)
                        currentNode = currentNode.getLeftChild();
                    else
                    {
                        currentNode.setLeftChild(newNode);
                        break;
                    }
                }
                else  //insert new node in right sub-tree
                {
                    if(currentNode.getRightChild() != null)
                        currentNode = currentNode.getRightChild();
                    else
                    {
                        currentNode.setRightChild(newNode);
                        break;
                    }
                }
            }//end while
        }
    }
    
    
    public boolean removeNode(int value)
    {
        //if ( value == root.getValue() ) return false; //prevents the removal of the root ( temporary bug fix )
        Node parentNode = this.root;
        Node currentNode = this.root;
        boolean isLeftSubtree = true;
        
        //first we find the node and store it as the currentNode
        while( currentNode != null && currentNode.getValue() != value )
        {
            parentNode = currentNode;  //parentNode advances
            
            if( value < currentNode.getValue() )
            {
                currentNode = currentNode.getLeftChild();  //currentNode advances on left subtree
                isLeftSubtree = true;
            }
            else 
            {
                currentNode  = currentNode.getRightChild();  //currentNode advances on right subtree
                isLeftSubtree = false;
            }
        }
        
        if ( currentNode == null ) return false; // node not found
        
        if ( currentNode.getLeftChild() == null && currentNode.getRightChild() == null)  // ==> the node has no subtrees ( it's a leaf )
        {
            if ( isLeftSubtree ) 
                parentNode.setLeftChild(null);
            else 
                parentNode.setRightChild(null);
                
            return true;
        }
        else if ( currentNode.getLeftChild() == null )  // ==> the node has a right subtree
             {
                 if ( isLeftSubtree )
                     parentNode.setLeftChild(currentNode.getRightChild());
                 else
                     parentNode.setRightChild(currentNode.getRightChild());
                 
                 //if the node to be deleted is the root: 
                 if( value == this.root.getValue() ) { this.root = parentNode.getRightChild(); }
                 return true;
             }
             else if ( currentNode.getRightChild() == null )  // ==> the node has a left subtree
                  {
                      if ( isLeftSubtree )
                          parentNode.setLeftChild(currentNode.getLeftChild());
                      else 
                          parentNode.setRightChild(currentNode.getLeftChild());
                      
                      //if the node to be deleted is the root: 
                      if( value == this.root.getValue() ) { this.root = parentNode.getLeftChild(); }
                      return true;
                  }
                  else  // ==> the node has both subtrees
                  {
                      Node replacement = getReplacementNode(currentNode);
                      
                      if ( isLeftSubtree )
                          parentNode.setLeftChild(replacement);
                      else
                          parentNode.setRightChild(replacement);
                          
                      replacement.setLeftChild(currentNode.getLeftChild());
                      //replacement.setRightChild(currentNode.getRightChild()); /* ? */
                      
                      //if the node to be deleted is the root: 
                      if( value == this.root.getValue() ) { this.root = replacement; }
                      return true;
                  }
    }
    
    private Node getReplacementNode(Node toBeReplaced)
    {
        // Initializations: 
        Node parentNode = toBeReplaced;
        Node currentNode = toBeReplaced.getRightChild();
        Node replacement = currentNode;
        
        while(currentNode != null)
        {
            parentNode = replacement;
            replacement = currentNode;
            currentNode = currentNode.getLeftChild();
        }
        
        if ( replacement != toBeReplaced.getRightChild() ) 
        {
            parentNode.setLeftChild( replacement.getRightChild() );
            replacement.setRightChild( toBeReplaced.getRightChild() );
        }
        return replacement;
    }
    
    
    public Node findNode(int val)
    {
        Node currentNode = this.getRoot();
        
        while( true )
        {
            if ( currentNode == null ) return null;
            
            if ( val == currentNode.getValue() )
            {
                return currentNode; 
            }
            
            if ( val < currentNode.getValue() )
                currentNode = currentNode.getLeftChild();
                else
                currentNode = currentNode.getRightChild();
        }
    }
    
    
    public ArrayList<Node> preorder(Node currentNode, ArrayList<Node> list)
    {
        if(currentNode != null)
        {
            list.add(currentNode);//System.out.println(currentNode);
            preorder(currentNode.getLeftChild(), list);
            preorder(currentNode.getRightChild(), list);
        }
        return list;
    }
    
    public ArrayList<Node> inorder(Node currentNode, ArrayList<Node> list)
    {
        if(currentNode != null)
        {
            inorder(currentNode.getLeftChild(), list);
            list.add(currentNode);//System.out.println(currentNode);
            inorder(currentNode.getRightChild(), list);
        }
        return list;
    }
    
    public ArrayList<Node> postorder(Node currentNode, ArrayList<Node> list)
    {
        if(currentNode != null)
        {
            postorder(currentNode.getLeftChild(), list);
            postorder(currentNode.getRightChild(), list);
            list.add(currentNode);//System.out.println(currentNode);
        }
        return list;
    }
    
    
    public Node getRoot(){ return this.root; }
}

