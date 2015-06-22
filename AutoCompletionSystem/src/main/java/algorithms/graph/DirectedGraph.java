package algorithms.graph;

import algorithms.heap.MaxHeap;
import system.Properties;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Catalin on 6/17/2015 .
 */
public class DirectedGraph {

    private Queue<HeapGraphNode> queue;
    private Map<String, WordNode> nodes;
    private MaxHeap<HeapGraphNode> heap;

    public DirectedGraph() {
        queue = new LinkedBlockingQueue<>();
        nodes = new HashMap<>();
        heap = new MaxHeap<>();
    }

    public void addNGram(List<String> nGram){
        for (String aNGram : nGram) {
            if (nodes.get(aNGram) == null) {
                nodes.put(aNGram, new WordNode(aNGram));
            }
        }

        for (int i = 0; i < (nGram.size() - 1); i++){
            nodes.get(nGram.get(i)).addConnection(nodes.get(nGram.get(i+1)));
        }
    }

    public List<String> getSuggestions(String word){
        if(nodes.get(word)==null){
            return Collections.emptyList();
        }
        queue.clear();
        heap.clearHeap();
        queue.offer(new HeapGraphNode(nodes.get(word),""));

        int step = 1;
        while(step < Properties.N_GRAM_DEPTH){
            int count = queue.size();
            while(count > 0) {
                HeapGraphNode heapNode = queue.poll();
                WordNode wordNode = heapNode.getWordNode();
                for (ConnectionNode conNode : wordNode.getAdjacencyList()) {
                    queue.offer(new HeapGraphNode(heapNode, conNode.getWordNode(), conNode.getWeight()));
                }
                count--;
            }
            step++;
        }

        int size = queue.size();
        for(int i = 0; i < size; i++){
            HeapGraphNode heapNode = queue.poll();
            heapNode.computeWeight();
            heap.insert(heapNode);
        }

        List<String> suggestions = new ArrayList<>();
        for( int i = 0; i < Properties.AUTOCOMPLETION_SUGGESTION_SIZE; i++){
            HeapGraphNode node = heap.delete();
            if(node == null){
                break;
            }
            suggestions.add(node.getNGram());
        }

        return suggestions;
    }

    public void clear() {
        nodes.clear();
    }

    public Map<String, WordNode> getNodes() {
        return nodes;
    }
}
