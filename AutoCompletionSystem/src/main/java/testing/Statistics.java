package testing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 3/30/2015 .
 */
public class Statistics {

    private String fileName;
    private List<StatisticEntry> statistics;
    private StatisticEntry currentEntry;
    private String currentWord;

    private class StatisticEntry {
        int charactersTyped;
        int positionFound;
        int charactersSaved;
    }

    Statistics(String filename) {
        this.fileName = filename;
        statistics = new ArrayList<StatisticEntry>();
    }

    public void beginWordStatistics(String word) {
        if (currentEntry != null) {
            statistics.add(currentEntry);
        }
        currentEntry = new StatisticEntry();
        currentWord = word;
    }

    public void interrogationStatistic(int prefixLength, int positionFound) {
        currentEntry.charactersTyped = prefixLength;
        currentEntry.positionFound = positionFound;
        currentEntry.charactersSaved = currentWord.length() - prefixLength;
        statistics.add(currentEntry);
        currentEntry = new StatisticEntry();
    }
}
