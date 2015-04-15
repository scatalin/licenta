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
    private List<Integer> successfulPositions;
    private StatisticEntry currentEntry;
    private String currentWord;
    private long total;
    private long successful;
    private long outRange;
    private long notInTree;
    private long notFound;
    private double precision;
    private double recall;
    private long dictionarySize;

    Statistics(String filename, int currentRun, int dictionarySize) {
        this.fileName = filename;
        this.currentRun = currentRun;
        this.dictionarySize = dictionarySize;
        statistics = new ArrayList<StatisticEntry>();
        successfulPositions = new ArrayList<Integer>(Properties.AUTOCOMPLETION_K_SIZE+1);
        for (int i = 0; i < Properties.AUTOCOMPLETION_K_SIZE+1; i++) {
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
                } else {
                    outRange++;
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
        double sum = calculateSum();
        recall = sum / total;
    }

    private void calculatePrecision() {
        double sum = calculateSum();
        precision = sum / (successful+outRange);
    }

    private double calculateSum() {
        double sum = 0.0;
        for(int i = 1; i < successfulPositions.size(); i++){
            sum += (successfulPositions.get(i))*(1d/(double)(i));
        }
        return sum * 100 ;
    }

    public String printStatistics(boolean file) {
        StringBuilder stringBuilder = new StringBuilder();
        if(file) {
            stringBuilder.append("file ").append(fileName).append(" ");
            stringBuilder.append("generated the following statistics").append("\n");
        }
        stringBuilder.append("dictionary size ")
                .append(dictionarySize).append("\n");
        stringBuilder.append("characters typed ")
                .append(currentRun).append("\n");
        stringBuilder.append("number of test words: ").append(total).append("\n");
        stringBuilder.append("found within ").append(Properties.SUCCESS_THRESHOLD).append(" positions: ").append(successful)
                .append("=").append((int) ((double) successful / total * 100)).append("%").append("\n");
        stringBuilder.append("precision: ").append(precision).append("\n");
        stringBuilder.append("recall: ").append(recall).append("\n");
        stringBuilder.append("out of range: ").append(outRange).append("\n");
        stringBuilder.append("not in tree: ").append(notInTree).append("\n");
        stringBuilder.append("not found in suggestions: ").append(notFound).append("\n");

        if(file) {
            for (int i = 1; i < Properties.AUTOCOMPLETION_K_SIZE + 1; i++) {
                stringBuilder.append("position ").append(i).append(": ").append(successfulPositions.get(i)).append("\n");
            }
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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSuccessful() {
        return successful;
    }

    public void setSuccessful(long successful) {
        this.successful = successful;
    }

    public long getOutRange() {
        return outRange;
    }

    public void setOutRange(long outRange) {
        this.outRange = outRange;
    }

    public long getNotInTree() {
        return notInTree;
    }

    public void setNotInTree(long notInTree) {
        this.notInTree = notInTree;
    }

    public long getNotFound() {
        return notFound;
    }

    public void setNotFound(long notFound) {
        this.notFound = notFound;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getRecall() {
        return recall;
    }

    public void setRecall(double recall) {
        this.recall = recall;
    }

    public long getDictionarySize() {
        return dictionarySize;
    }

    public void setDictionarySize(long dictionarySize) {
        this.dictionarySize = dictionarySize;
    }
}
