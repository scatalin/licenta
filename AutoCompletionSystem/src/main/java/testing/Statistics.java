package testing;

import system.Properties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 3/30/2015 .
 */
public class Statistics {

    private String fileName;
    private List<StatisticEntry> statistics;
    private List<Integer> successfulPositions;
    private StatisticEntry currentEntry;
    private String currentWord;
    private int total;
    private int successful;
    private int outRange;
    private int notInTree;

    Statistics(String filename) {
        this.fileName = filename;
        statistics = new ArrayList<StatisticEntry>();
        successfulPositions = new ArrayList<Integer>(Properties.SUCCESS_THRESHOLD + 1);
        for (int i = 0; i < Properties.SUCCESS_THRESHOLD + 1; i++) {
            successfulPositions.add(0);
        }
        currentEntry = new StatisticEntry();
    }

    public void makeAverages() {
        total = statistics.size();
        successful = 0;
        for (StatisticEntry entry : statistics) {
            if (entry.positionFound > 0) {
                if (entry.positionFound <= Properties.SUCCESS_THRESHOLD) {
                    successful++;
                    successfulPositions.set(entry.positionFound, successfulPositions.get(entry.positionFound) + 1);
                } else {
                    outRange++;
                }
            }
            else {
                notInTree++;
            }
        }
    }

    public String printStatistics() {
        StringBuilder stringBuilder = new StringBuilder("file ");
        stringBuilder.append(fileName).append(" ");
        stringBuilder.append("generated the following statistics\n");
        stringBuilder.append("number of words: ").append(total).append("\n");
        stringBuilder.append("successful: ").append(successful).append("=")
                .append((int) ((double) successful / total * 100)).append("% with threshold ")
                .append(Properties.SUCCESS_THRESHOLD).append("\n");
        stringBuilder.append("out of range: ").append(outRange).append("\n");
        stringBuilder.append("not in tree: ").append(notInTree).append("\n");
        for (int i = 1; i < Properties.SUCCESS_THRESHOLD + 1; i++) {
            stringBuilder.append("position ").append(i).append(": ").append(successfulPositions.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    public void beginWordStatistics(String word) {
        if (currentEntry != null && currentEntry.positionFound != 0) {
            statistics.add(currentEntry);
            currentEntry = new StatisticEntry();
        }
        currentWord = word;
    }

    public void interrogationStatistic(int prefixLength, int positionFound) {
        currentEntry.word = currentWord;
        currentEntry.charactersTyped = prefixLength;
        currentEntry.positionFound = positionFound;
        currentEntry.charactersSaved = currentWord.length() - prefixLength;
        statistics.add(currentEntry);
        currentEntry = new StatisticEntry();
    }

    @Override
    public String toString() {
        return statistics.toString();
    }

    private class StatisticEntry {
        String word;
        int charactersTyped;
        int positionFound;

        int charactersSaved;

        @Override
        public String toString() {
            return "StatisticEntry{" +
                    "word='" + word + '\'' +
                    ", charactersTyped=" + charactersTyped +
                    ", positionFound=" + positionFound +
                    ", charactersSaved=" + charactersSaved +
                    "}\n";
        }

    }
}
