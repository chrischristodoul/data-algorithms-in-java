import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RootedTreeIso {

    static int[] ComputeMap(RootedTree T1, RootedTree T2) {
        int n = T1.N;
        int[] mapNode = new int[n];
        int r1 = T1.root;
        int r2 = T2.root;

        /* enter your code */
        mapNodesRecursively(T1, T2, T1.root, T2.root, mapNode);
        return mapNode;
    }

    private static void mapNodesRecursively(RootedTree T1, RootedTree T2, int v1, int v2, int[] mapNode) {
    mapNode[v1] = v2;  

    
    List<Integer> children1 = new ArrayList<>(T1.children[v1]);
    List<Integer> children2 = new ArrayList<>(T2.children[v2]);

   
    children1.sort((a, b) -> T1.Label[a].compareTo(T1.Label[b]));
    children2.sort((a, b) -> T2.Label[a].compareTo(T2.Label[b]));

    
    for (int i = 0; i < children1.size(); i++) {
        mapNodesRecursively(T1, T2, children1.get(i), children2.get(i), mapNode);
    }
}

    public static void main(String[] args) {
        In.init();
        int n = In.getInt();

        int[] parent1 = new int[n];
        int[] parent2 = new int[n];
        for (int i = 0; i < n; i++) {
            int v = In.getInt();
            parent1[i] = v;
        }
        for (int i = 0; i < n; i++) {
            int v = In.getInt();
            parent2[i] = v;
        }

        RootedTree T1 = (RootedTree) new RootedTree(n, parent1);
        String LabelT1 = T1.LabelTree();
        System.out.println("label of tree T1 = " + LabelT1);

        RootedTree T2 = (RootedTree) new RootedTree(n, parent2);
        String LabelT2 = T2.LabelTree();
        System.out.println("label of tree T2 = " + LabelT2);

        if (LabelT1.equals(LabelT2)) {
            System.out.println("Rooted trees T1 and T2 are isomporphic!");
            int[] mapNode = ComputeMap(T1, T2);
            System.out.println(" node of tree T1 -> node of tree T2 ");
            for (int i = 0; i < n; i++) {
                System.out.println("\t" + i + " \t -> \t " + mapNode[i]);
            }
        } else {
            System.out.println("Rooted trees T1 and T2 are NOT isomporphic!");
        }
    }
}
