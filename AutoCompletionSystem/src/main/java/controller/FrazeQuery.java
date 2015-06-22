package controller;

import algorithms.GraphFactory;
import algorithms.graph.DirectedGraph;

import java.util.List;

/**
 * Created by Catalin on 6/18/2015 .
 */
public class FrazeQuery {

    private DirectedGraph graph;

    public FrazeQuery() {
        graph = GraphFactory.getGraph();
    }

    public List<String> getSuggestions(String prefix){
        return graph.getSuggestions(prefix);
    }
}
