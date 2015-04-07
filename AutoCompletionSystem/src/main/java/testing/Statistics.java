package testing;

import system.Properties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 3/30/2015 .
 */
public class Statistics {

    private String fileName;
    private int currentRun;
    private List<StatisticEntry> statistics;
//    private List<String> outOfRange;
    private List<Integer> successfulPositions;
    private StatisticEntry currentEntry;
    private String currentWord;
    private int total;
    private int successful;
    private int outRange;
    private int notInTree;
    private int notFound;
    private double precision;
    private double recall;

    Statistics(String filename, int currentRun) {
        this.fileName = filename;
        this.currentRun = currentRun;
        statistics = new ArrayList<StatisticEntry>();
        successfulPositions = new ArrayList<Integer>(Properties.TEST_WORD_DEPTH+1);
        for (int i = 0; i < Properties.TEST_WORD_DEPTH+1; i++) {
            successfulPositions.add(0);
        }
        currentEntry = new StatisticEntry();
//        outOfRange = new ArrayList<String>();
    }

    public void makeAverages() {
        total = statistics.size();
        successful = 0;
        for (StatisticEntry entry : statistics) {
            if (entry.positionFound > 0) {
                if (entry.positionFound <= Properties.SUCCESS_THRESHOLD) {
                    successful++;
                } else {
                    outRange++;
//                    outOfRange.add(entry.word);
                }
                successfulPositions.set(entry.positionFound, successfulPositions.get(entry.positionFound) + 1);
            }
            else {
                if(entry.positionFound==-2){
                    notInTree++;
                }
                if(entry.positionFound==-1){
                    notFound++;
                }
            }
        }
        calculatePrecision();
        calculateRecall();
    }

    private void calculateRecall() {
        double sum = 0.0;
        sum = calculateSum();
        recall = sum / total;
    }

    private void calculatePrecision() {
        double sum = 0.0;
        sum = calculateSum();
        precision = sum / (successful+outRange);
    }

    private double calculateSum() {
        double sum = 0.0;
        for(int i = 1; i < successfulPositions.size(); i++){
            sum += (successfulPositions.get(i))*(1d/(double)(i));
        }
        return sum * 100 ;
    }

    public String printStatistics() {
        StringBuilder stringBuilder = new StringBuilder("file ");
        stringBuilder.append(fileName).append(" ");
        stringBuilder.append("with characters typed ")
                .append(currentRun).append("\n");
        stringBuilder.append("generated the following statistics\n");
        stringBuilder.append("number of words: ").append(total).append("\n");
        stringBuilder.append("found within ").append(Properties.SUCCESS_THRESHOLD).append(" positions: ").append(successful).append("=")
                .append((int) ((double) successful / total * 100)).append("%").append("\n");
        stringBuilder.append("precision: ").append(precision).append("\n");
        stringBuilder.append("recall: ").append(recall).append("\n");
        stringBuilder.append("out of range: ").append(outRange).append("\n");
        stringBuilder.append("not in tree: ").append(notInTree).append("\n");
        stringBuilder.append("not found in suggestions: ").append(notFound).append("\n");

//        stringBuilder.append(outOfRange).append("\n");
        for (int i = Properties.TEST_WORD_START; i < Properties.TEST_WORD_DEPTH + 1; i++) {
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
