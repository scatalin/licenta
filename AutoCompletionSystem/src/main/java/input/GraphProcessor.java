package input;

import algorithms.graph.ConnectionNode;
import algorithms.graph.DirectedGraph;
import algorithms.graph.WordNode;
import dictionary.filters.CharacterFilter;
import dictionary.filters.RomanianCharacterFilter;
import system.Properties;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 6/18/2015 .
 */
public class GraphProcessor {

    private File graphFile;
    private CharacterFilter filter;
    private DirectedGraph graph;

    public GraphProcessor(DirectedGraph graph) {
        this(Properties.GRAPH_FILE, Properties.DICTIONARY_DIRECTORY);
        this.graph = graph;
    }

    public GraphProcessor(String stopWordsFileName, String dictionaryDirectory) {

        File directory = new File(dictionaryDirectory);
        if (!directory.exists() && !directory.isDirectory()) {
            System.out.println("dictionary directory does not exist: " + directory + ";");
        }

        graphFile = new File(dictionaryDirectory + Properties.SYSTEM_PATH_SEPARATOR + stopWordsFileName);
        if (!graphFile.exists() && !graphFile.isFile()) {
            System.out.println("dictionary file does not exist " + graphFile + ";");
        }

        filter = new RomanianCharacterFilter();
    }


    public void readDictionary() {
        try (BufferedReader reader = new BufferedReader(new FileReader(graphFile))) {
            graph.clear();

            String line = reader.readLine();
            List<String> ngrams = new ArrayList<>();
            while (line != null) {
                String[] tokens = line.split("=");
                ngrams.add(filter.filterCharacters(tokens[0], Properties.DIACRITICS));
                ngrams.add(filter.filterCharacters(tokens[1], Properties.DIACRITICS));
                for(int i = 0; i<Integer.parseInt(tokens[2]); i++){
                    graph.addNGram(ngrams);
                }
                ngrams.clear();
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDictionary() {
        try (PrintWriter writer = new PrintWriter(graphFile)) {
            graphFile.delete();
            graphFile.createNewFile();
            int count = 0;
            for (WordNode wordNode : graph.getNodes().values()) {
                String word = wordNode.getWord();
                List<ConnectionNode> list = wordNode.getAdjacencyList();
                for (ConnectionNode wN : list) {
                    writer.println(word + "=" + wN.getWordNode().getWord()+"="+wN.getWeight());
                }
                count++;
                if (count > 100) {
                    writer.flush();
                    count = 0;
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
