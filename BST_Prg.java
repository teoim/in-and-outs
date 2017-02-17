
/**
 * Search/Insert/Delete data stored inside a binary search tree
 * 
 * @author Teodor Ionut Mitrache 
 * @version 1.0
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class BST_Prg extends Frame
{
    private Label taLbl, srcLbl, insLblName, insLblVal, delLblName, delLblValue;
    private TextField tfSrc, tfInsName, tfInsValue, tfDelName, tfDelValue;
    private TextArea taCont;
    private Button btnSrc, btnIns, btnDel, btnPRE, btnIN, btnPOST;
    
    private BinarySearchTree myBST;
    private ArrayList<Node> nodeList; //used @ preorder/inorder/postorder
    
    
    BST_Prg(String titlu){
        setTitle(titlu);
        setLayout(new FlowLayout());
        
      /**
       * CONTENT TEXT AREA
       */  
        taLbl = new Label("Continut arbore: ");
        add(taLbl);
        taCont = new TextArea("", 25, 35);
        taCont.setEditable(false);
        add(taCont);
        //ta.addActionListener(this);
        
      /**
       * SEARCH 
       */  
        srcLbl = new Label("Search (value): ");
        add(srcLbl);
        
        tfSrc = new TextField(30);
        add(tfSrc);
        
        btnSrc = new Button("Search");
        btnSrc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int value = -1;
                try{
                    value = Integer.parseInt(tfSrc.getText());
                }
                catch(NumberFormatException exc){
                    System.out.println("Please insert an integer value! ");
                    throw new RuntimeException(exc);
                }
                System.out.println("\nSearch: " + value);
                
                Node myNode = myBST.findNode(value);
                if( myNode == null ) System.out.println("Not found. . ."); 
                else System.out.println(myNode);
            }
        });
        add(btnSrc);
        
      /** 
       * INSERT 
       */
        insLblName = new Label("Insert (name):");
        add(insLblName);
        tfInsName = new TextField(30);
        add(tfInsName);
        
        insLblVal = new Label("Insert (value): ");
        add(insLblVal);
        tfInsValue = new TextField(30);
        add(tfInsValue);
        
        btnIns = new Button("Insert");
        btnIns.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String name = tfInsName.getText();
                int value = -1;
                
                try{
                    value = Integer.parseInt(tfInsValue.getText());
                }
                catch(NumberFormatException exc){
                    System.out.println("Please insert an integer value! ");
                    throw new RuntimeException(exc);
                }
                
                System.out.println("\nInsert value: " + value + " , name: " + name);
                myBST.addNode(value, name);
                System.out.println("OK! Please refresh text area.");
            }
        });
        add(btnIns);
        
      /** 
       * DELETE 
       */
        delLblName = new Label("Delete (name):");
        add(delLblName);
        tfDelName = new TextField(30);
        tfDelName.setText("NOT required.");
        tfDelName.setEditable(false);
        add(tfDelName);
        
        delLblValue = new Label("Delete (value):");
        add(delLblValue);
        tfDelValue = new TextField(30);
        add(tfDelValue);
        
        btnDel = new Button("Delete");
        btnDel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("\'Delete\' button clicked.");
                int value = -1;
                
                try{
                    value = Integer.parseInt(tfDelValue.getText());
                }
                catch(NumberFormatException exc){
                    System.out.println("Please insert an integer value!");
                    throw new RuntimeException(exc);
                }
                
                System.out.println("\nDelete node: " + value);
                if(myBST.removeNode(value)) 
                {
                    System.out.println("OK! Please refresh text area");
                }
                else
                {
                    System.out.println("Node not found! \nPlease check input value.");
                }
            }
        });
        add(btnDel);
        
      /** 
       * PREORDER button
       */
        btnPRE = new Button("Preorder");
        btnPRE.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                nodeList.clear();
                System.out.print("\nPreorder:");
                myBST.preorder( myBST.getRoot() , nodeList );
                //for( Node node : nodeList ){ System.out.println(node); }
                update( nodeList );
                System.out.println(" OK");
            }
        });
        add(btnPRE);
      
      /**
       * INORDER button
       */
        btnIN = new Button("Inorder");
        btnIN.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                nodeList.clear();
                System.out.print("\nInorder:");
                myBST.inorder( myBST.getRoot() , nodeList );
                //for( Node node : nodeList ) { System.out.println(node); }
                update( nodeList );
                System.out.println(" OK");
            }
        });
        add(btnIN);
        
      /**
       * POSTORDER button
       */  
        btnPOST = new Button("Postorder");
        btnPOST.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                nodeList.clear();
                System.out.print("\nPostorder:");
                myBST.postorder( myBST.getRoot() , nodeList );
                //for( Node node : nodeList ) { System.out.println(node); }
                update( nodeList );
                System.out.println(" OK");
            }
        });
        add(btnPOST);
        
      /** 
       * Close window  
       */
        addWindowListener(
            new WindowAdapter()
            {
                public void windowClosing(WindowEvent e){
                    System.exit(0);
                }
            }
        );
        
        
        setSize(800, 530);
        setResizable(false);
        init();
        setVisible(true);
        
        System.out.println("Ready . . . ");
    } //end frame constructor F()
    
    
    /**
     * Initializes the BST 
     */
    private void init(){
        int[]    elemInt = { 50,  40,  51,  30,  45,  60,  20,  35,  48,  57,  65,  10,  34,  46,  49,  52,  58,  62,  70,  32,  59,  33,  31  };
        String[] elemStr = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W" };
        
        myBST = new BinarySearchTree();
        for ( int i = 0 ; i < elemInt.length ; i++ )
        {
            myBST.addNode( elemInt[i] , elemStr[i] );
        }
        
        nodeList = new ArrayList<Node>();
    }
    
    
    /**
     * Updates the BST-content TextArea 
     */
    private void update(ArrayList<Node> list){
        taCont.setText("");
        for ( Node n : list ) {
            taCont.append(n.toString() + "\n");
        }
    }
    
    
    /**
     * Main entry point
     */
    public static void main(String[] args){
        
        new BST_Prg("BST Test 01");
        
    }
}
