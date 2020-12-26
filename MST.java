import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MST{

    public static HashMap<String, ArrayList<Edge>> adjMap = new HashMap<String, ArrayList<Edge>>();

    public static void addEdge(String vertex1, String vertex2, int weight){

        Edge newEdge = new Edge(vertex1, vertex2, weight);
        Edge newEdge2 = new Edge(vertex2, vertex1, weight);

        if(adjMap.containsKey(vertex1) && adjMap.containsKey(vertex2)){
            ArrayList<Edge> nestedList = adjMap.get(vertex1);
            ArrayList<Edge> nestedList2 = adjMap.get(vertex2);
            nestedList.add(newEdge);
            nestedList2.add(newEdge2);
            adjMap.put(vertex1, nestedList);
            adjMap.put(vertex2, nestedList2);
        }else if(!adjMap.containsKey(vertex1) && !adjMap.containsKey(vertex2)) {
            ArrayList<Edge> nestedList = new ArrayList<Edge>();
            ArrayList<Edge> nestedList2 = new ArrayList<Edge>();
            nestedList.add(newEdge);
            nestedList2.add(newEdge2);
            adjMap.put(vertex1, nestedList);
            adjMap.put(vertex2, nestedList2);
        }else if(adjMap.containsKey(vertex1) && !adjMap.containsKey(vertex2)){
            ArrayList<Edge> nestedList = adjMap.get(vertex1);
            ArrayList<Edge> nestedList2 = new ArrayList<Edge>();
            nestedList.add(newEdge);
            nestedList2.add(newEdge2);
            adjMap.put(vertex1, nestedList);
            adjMap.put(vertex2, nestedList2);
        }else{
            ArrayList<Edge> nestedList2 = adjMap.get(vertex1);
            ArrayList<Edge> nestedList = new ArrayList<Edge>();
            nestedList.add(newEdge);
            nestedList2.add(newEdge2);
            adjMap.put(vertex1, nestedList);
            adjMap.put(vertex2, nestedList2);
        }
    }

    // https://www.youtube.com/watch?v=xq3ABa-px_g&t=7s
    public static void eagerPrimMST(String startVertex){
        int totalCost =0;

        HashMap<String, Edge> needToVisit = new HashMap<String, Edge>();

        ArrayList<Edge> mst = new ArrayList<Edge>();

        ArrayList<String> visitedNodes =  new ArrayList<String>();

        eagerPrimMSTPrivate(startVertex, needToVisit, mst, visitedNodes);

        if(mst.size() != adjMap.size()-1){
            throw new Error("Cannot find the MST!");
        }

        System.out.println("Eager Prim's MST is (From -> To, weight): ");
        
        for (Edge edge : mst) {
            System.out.println(edge.start + "-> " + edge.end + ", " + edge.weight);
            totalCost += edge.weight;
        }
        System.out.println("Total cost is: " + totalCost);
    }

    private static void eagerPrimMSTPrivate(String startVertex, HashMap<String, Edge> needToVisit, ArrayList<Edge> mst, ArrayList<String> visitedNodes){

        if(!adjMap.containsKey(startVertex))
            throw new Error("Cannot find the MST!");

        visitedNodes.add(startVertex);

        addVertexToVisit(startVertex, visitedNodes, needToVisit);

        // transform needToVisit hash map to priority queue
        PriorityQueue<Map.Entry<String, Edge>> needToVisitPQ = convertMapToPQByWeight(needToVisit);
        
        while(!needToVisitPQ.isEmpty()){
            Map.Entry<String, Edge> visitedNodeEdgePair = needToVisitPQ.poll();

            mst.add(visitedNodeEdgePair.getValue());

            if(needToVisitPQ.isEmpty()) break;

            startVertex = visitedNodeEdgePair.getKey();
            visitedNodes.add(startVertex);
            needToVisit.remove(startVertex);
            
            // update all 4 parameters
            addVertexToVisit(startVertex, visitedNodes, needToVisit);
            needToVisitPQ = convertMapToPQByWeight(needToVisit);
        
        }
    }

    private static PriorityQueue<Map.Entry<String, Edge>> convertMapToPQByWeight(HashMap<String, Edge> hm){
        
        Comparator<Map.Entry<String, Edge>> weightComparator = new Comparator<Map.Entry<String, Edge>>(){
            public int compare(Map.Entry<String, Edge> a, Map.Entry<String, Edge> b){
                if(a.getValue().weight > b.getValue().weight) return 1;
                if(a.getValue().weight < b.getValue().weight) return -1;
                return 0;
            }
        };
        
        PriorityQueue<Map.Entry<String, Edge>> pq = new PriorityQueue<Map.Entry<String, Edge>>(weightComparator);

        for(Map.Entry<String,Edge> entry : hm.entrySet()) {
            pq.add(entry);
        }
        return pq;
    }

    private static void addVertexToVisit(String startVertex, ArrayList<String> visitedNodes, HashMap<String, Edge> needToVisit){

        ArrayList<Edge> edges = adjMap.get(startVertex);
        for (Edge edge : edges) {
            if(visitedNodes.contains(edge.end)){
                continue;
            }
            if(!needToVisit.containsKey(edge.end) || edge.weight < needToVisit.get(edge.end).weight){
                needToVisit.put(edge.end, edge);
            }
        }
    }



    // https://www.youtube.com/watch?v=jsmMtJpPnhU&t=603s&pbjreload=101
    public static void lazyPrimMST(String startVertex){
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<Edge> mst = new ArrayList<Edge>();
        int totalCost =0;

        lazyPrimMSTPrivate(startVertex, visited, mst);

        if(mst.size() != adjMap.size()-1){
            throw new Error("Cannot find the MST!");
        }

        System.out.println("Lazy Prim's MST is (From -> To, weight): ");
        
        for (Edge edge : mst) {
            System.out.println(edge.start + "-> " + edge.end + ", " + edge.weight);
            totalCost += edge.weight;
        }
        System.out.println("Total cost is: " + totalCost);
    }

    private static void lazyPrimMSTPrivate(String startVertex, ArrayList<String> visited, ArrayList<Edge> mst){
        if(!adjMap.containsKey(startVertex)){
            throw new Error("No MSt exist!");
        }
        
        visited.add(startVertex);

        PriorityQueue<Edge> edgePQ = new PriorityQueue<Edge>((a,b)->{
            return a.weight - b.weight;
        });

        addEdgeToPQ(startVertex, edgePQ, visited);

        while(!edgePQ.isEmpty()){
            Edge minEdge = edgePQ.poll();
            if(visited.contains(minEdge.start) && visited.contains(minEdge.end)){
                continue;
            }
            
            mst.add(minEdge);
            visited.add(minEdge.end);

            startVertex = minEdge.end;
            addEdgeToPQ(startVertex, edgePQ, visited);
        }
    }

    private static void addEdgeToPQ(String startVertex, PriorityQueue<Edge> edgePQ, ArrayList<String> visited) {
        ArrayList<Edge> nestedList = adjMap.get(startVertex);
        for(Edge edge : nestedList){
            if(!visited.contains(edge.end)){
                edgePQ.add(edge);
            }
        }
    }

    
}