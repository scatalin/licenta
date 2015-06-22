package controller;

import algorithms.graph.DirectedGraph;
import controller.filter.FrazeFilter;
import system.ServiceLocator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 6/18/2015 .
 */
public class FrazeController {
    private FrazeFilter filter;
    private List<String> sequence;
    private String currentWord;
    private DirectedGraph graph;

    public FrazeController() {
        filter = new FrazeFilter();
        sequence = new ArrayList<>();
        graph = ServiceLocator.getGraph();
    }

    public void handleCharacter(Character character) {
        character = filter.filterCharacter(character);
        if (character.equals(' ')) {
            if (wordNotNull(currentWord)) {
                sequence.add(currentWord);
                if (sequence.size() >= 2) {
                    graph.addNGram(sequence);
                }
                String lastWord = (!sequence.isEmpty() ? "" : sequence.get(sequence.size() - 1));
                sequence.clear();
                if (!lastWord.equals("")) {
                    sequence.add(lastWord);
                }
                sequence.add(currentWord);
                currentWord = "";
            }
        } else if ((character < 'a') || (character > 'z')) {
            sequence.clear();
            currentWord = "";
        } else {
            currentWord += character;
        }
    }

    private boolean wordNotNull(String currentWord) {
        return !currentWord.equals("");
    }
}
