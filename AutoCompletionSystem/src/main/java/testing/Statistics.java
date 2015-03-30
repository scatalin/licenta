package testing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 3/30/2015 .
 */
public class Statistics {

    private String fileName;
    private List<StatisticEntry> statistics;
    private StatisticEntry currentEntry = new StatisticEntry();
    private String currentWord;

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

    Statistics(String filename) {
        this.fileName = filename;
        statistics = new ArrayList<StatisticEntry>();
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
}
