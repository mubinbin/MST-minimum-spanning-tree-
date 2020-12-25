public class MSTtest{
    public static void main(String[] args) {
        
        MST.addEdge("0", "2", 0);
        MST.addEdge("0", "5", 7);
        MST.addEdge("0", "3", 5);
        MST.addEdge("0", "1", 9);

        MST.addEdge("1", "3", -2);
        MST.addEdge("1", "6", 4);
        MST.addEdge("1", "4", 3);

        MST.addEdge("4", "6", 6);

        MST.addEdge("6", "3", 3);
        MST.addEdge("6", "5", 1);

        MST.addEdge("5", "3", 2);
        MST.addEdge("5", "2", 6);

        // System.out.println(MST.adjMap);
        MST.eagerPrimMST("0");
        
    }
}