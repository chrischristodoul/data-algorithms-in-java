import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class RootedTree {

    int N;     // number of tree nodes; nodes are numbered 0,1,...,N-1
    int root;  // root of the tree
    String[] Label;  // stores the label of each node

     ArrayList<Integer>[] children;

    // construct children lists from parent array
    RootedTree(int N, int[] parent) {
        this.N = N;
        Label = new String[N];
        /* enter your code */
        children = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            children[i] = new ArrayList<>();   

        for (int v = 0; v < N; v++) {
            if (parent[v] != v) {  
                children[parent[v]].add(v);  
            } else {
                root = v;   
            }
        }
    }


    }

    void printLabels() {
        System.out.println("node labels");
        for (int v = 0; v < N; v++) {
            System.out.println("label of " + v + " : " + Label[v]);
        }
    }
     
    // traverse tree compute node labels and the tree label
    private String LabelNode (int v) {
         if (children[v].isEmpty()) { 
            Label[v] = "10";
        } else {
            ArrayList<String> childLabels = new ArrayList<>();
            for (int w : children[v]) {  
                childLabels.add(LabelNode(w));
            }
            childLabels.sort((a, b) -> b.compareTo(a));  

            StringBuilder labelBuilder = new StringBuilder("1");
            for (String lbl : childLabels) {
                labelBuilder.append(lbl);
            }
            labelBuilder.append("0");
            Label[v] = labelBuilder.toString();
        }
        return Label[v];
    }
        
    // return the label of the root
    public String LabelTree() {
        return this.LabelNode(this.root);
    }

    public static void main(String[] args) {
        In.init();
        int n = In.getInt();

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            int v = In.getInt();
            parent[i] = v;
        }

        RootedTree T = (RootedTree) new RootedTree(n, parent);
        String LabelT = T.LabelTree();

        System.out.println("label of tree = " + LabelT);
        T.printLabels();
    }
}
