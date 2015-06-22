package algorithms;

import algorithms.graph.DirectedGraph;
import input.GraphProcessor;

/**
 * Created by Catalin on 5/16/2015 .
 */
public class GraphFactory {

    private static DirectedGraph globalGraph;

    public static DirectedGraph getGraph() {
        if(globalGraph == null){
            globalGraph = new DirectedGraph();
            GraphProcessor processor = new GraphProcessor(globalGraph);
            processor.readDictionary();
        }
        return globalGraph;
    }

}
