public class MSTtest{
    public static void main(String[] args) {
        
        // MST.addEdge("0", "2", 0);
        // MST.addEdge("0", "5", 7);
        // MST.addEdge("0", "3", 5);
        // MST.addEdge("0", "1", 9);

        // MST.addEdge("1", "3", -2);
        // MST.addEdge("1", "6", 4);
        // MST.addEdge("1", "4", 3);

        // MST.addEdge("4", "6", 6);

        // MST.addEdge("6", "3", 3);
        // MST.addEdge("6", "5", 1);

        // MST.addEdge("5", "3", 2);
        // MST.addEdge("5", "2", 6);

        // https://www.google.com/imgres?imgurl=https%3A%2F%2Fmiro.medium.com%2Fmax%2F1924%2F1*RC0NtEiAvMigfjDHSZBa3g.png&imgrefurl=https%3A%2F%2Ftowardsdatascience.com%2Fkruskals-minimum-spanning-tree-implementation-8179e6916cd8&tbnid=uNpX7Nd4KL4R8M&vet=12ahUKEwiYx7qt2-ntAhWSSKwKHaQBD7EQMygLegUIARDhAQ..i&docid=s9lMzMJB2zPnZM&w=962&h=659&q=minimum%20spanning%20tree&ved=2ahUKEwiYx7qt2-ntAhWSSKwKHaQBD7EQMygLegUIARDhAQ
        // MST.addEdge("A", "B", 3);
        // MST.addEdge("A", "E", 9);
        // MST.addEdge("A", "D", 6);
        // MST.addEdge("B", "D", 4);
        // MST.addEdge("B", "C", 2);
        // MST.addEdge("B", "F", 9);
        // MST.addEdge("B", "E", 9);
        // MST.addEdge("C", "D", 2);
        // MST.addEdge("C", "G", 9);
        // MST.addEdge("C", "F", 8);
        // MST.addEdge("D", "G", 9);
        // MST.addEdge("E", "F", 8);
        // MST.addEdge("E", "J", 18);
        // MST.addEdge("F", "G", 7);
        // MST.addEdge("F", "I", 9);
        // MST.addEdge("F", "J", 10);
        // MST.addEdge("G", "H", 4);
        // MST.addEdge("G", "I", 5);
        // MST.addEdge("H", "I", 1);
        // MST.addEdge("H", "J", 4);
        // MST.addEdge("I", "J", 3);

        //https://www.geneseo.edu/~baldwin/csci242/spring2013/0404mst.html
        MST.addEdge("A", "B", 6);
        MST.addEdge("A", "F", 3);
        MST.addEdge("A", "G", 6);
        MST.addEdge("A", "C", 10);
        MST.addEdge("B", "F", 2);
        MST.addEdge("F", "G", 1);
        MST.addEdge("G", "C", 1);
        MST.addEdge("G", "H", 9);
        MST.addEdge("G", "D", 5);
        MST.addEdge("C", "D", 7);
        MST.addEdge("D", "H", 4);
        MST.addEdge("D", "E", 3);
        MST.addEdge("H", "E", 4);

        // System.out.println(MST.adjMap);
        MST.eagerPrimMST("A");
        System.out.println("\n");
        MST.lazyPrimMST("A");
        
    }
}