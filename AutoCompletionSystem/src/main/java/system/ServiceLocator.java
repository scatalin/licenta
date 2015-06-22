package system;

import algorithms.GraphFactory;
import algorithms.SearchTree;
import algorithms.SearchTreeFactory;
import algorithms.graph.DirectedGraph;
import algorithms.heap.MaxHeap;
import dictionary.Dictionary;
import dictionary.ModelChangeChecker;
import dictionary.ModelConstructor;
import dictionary.Word;

import java.util.List;

/**
 * Created by Catalin on 6/22/2015 .
 */
public class ServiceLocator {

    private static Dictionary dictionary;
    private static ModelConstructor modelConstructor;


    public static Dictionary getDictionary(){
        if (dictionary == null){
            dictionary = new Dictionary();
        }
        return dictionary;
    }

    public static Dictionary getNewInstanceDictionary(){
        return new Dictionary();
    }

    public static void createDictionary(String filename){
        dictionary = new Dictionary(filename);
    }

    public static DirectedGraph getGraph(){
        return GraphFactory.getGraph();
    }

    public static Dictionary getNewInstanceDictionary(String name) {
        return new Dictionary(name);
    }

    public static SearchTree getCompletionTree() {
        return SearchTreeFactory.getCompletionTree();
    }

    public static ModelConstructor getModelConstructor() {
        if(modelConstructor == null){
            modelConstructor = new ModelConstructor();
        }
        return modelConstructor;
    }

    public static void constructModelChangeChecker(){
        List<MaxHeap<Word>> heaps = getDictionary().getData();
        for(MaxHeap<Word> heap : heaps){
            new ModelChangeChecker(heap);
        }
    }

    public static void setDictionary(Dictionary dict) {
        dictionary = dict;
    }
}
