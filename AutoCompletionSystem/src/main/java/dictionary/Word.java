package dictionary;

import algorithms.heap.HeapNode;
import system.Properties;
import system.ServiceLocator;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class Word implements HeapNode<Word> {

    private final String word;
    private int heapLevel;
    private int totalWeight;
    private int dictionaryFrequency;
    private int userFrequency;
    private int userActuality;
    private long queryUpdated;
    private int initUserActuality;
    private int initUserFrequency;

    private Word(String word, int heapLevel, int totalWeight, int dictionaryFrequency, int userFrequency, int userActuality, long queryUpdated) {
        this.word = word;
        this.heapLevel = heapLevel;
        this.totalWeight = totalWeight;
        this.dictionaryFrequency = dictionaryFrequency;
        this.userFrequency = userFrequency;
        this.userActuality = userActuality;
        this.initUserActuality = userActuality;
        this.initUserFrequency = userFrequency;
        this.queryUpdated = queryUpdated;
    }

    public Word(String word, int dictionaryFrequency, int userFrequency, int userActuality, long queryUpdated) {
        this(word, 0, 0, dictionaryFrequency, userFrequency, userActuality, queryUpdated);
    }

    public Word(String word, int dictionaryFrequency) {
        this(word, dictionaryFrequency, 0, 0, 0);
    }

    public Word(String word, int dictionaryFrequency, int userFrequency) {
        this(word, dictionaryFrequency, userFrequency, 0, 0);
    }

    public Word(String word) {
        this(word, 1, 0, 0, 0);
    }

    public String getWord() {
        return word;
    }

    public int getWeight() {
        return calculateTotalWeight();
    }

    public void setWeight(int dictionaryFrequency) {
        this.dictionaryFrequency = dictionaryFrequency;
    }

    private int calculateTotalWeight() {
        double dictionaryFrequencyPercentage = Properties.WEIGHT_FREQUENCY / Properties.WEIGHT_FREQUENCY;
        double userFrequencyPercentage = Properties.WEIGHT_FREQUENCY_USER / Properties.WEIGHT_FREQUENCY;
        double userActualityPercentage = Properties.WEIGHT_ACTUALITY_USER / Properties.WEIGHT_FREQUENCY;
        totalWeight = (int) ((dictionaryFrequency * dictionaryFrequencyPercentage)
                + (userFrequency * userFrequencyPercentage)
                + (userActuality * userActualityPercentage));
        return totalWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Word)) {
            return false;
        }

        Word word1 = (Word) o;

        return !((word != null) ? !word.equals(word1.word) : (word1.word != null));
    }

    public Word clone() {
        return new Word(word, heapLevel, totalWeight, dictionaryFrequency, userFrequency, userActuality, queryUpdated);
    }


    @Override
    public int hashCode() {
        return (word != null) ? word.hashCode() : 0;
    }

    public void increaseFrequency(int increment) {
        dictionaryFrequency += increment;
    }

    @Override
    public String toString() {
        return word + "=" + dictionaryFrequency + "=" + userFrequency + "=" + userActuality;
    }

    public String toStringCustom() {
        return word + "=" + dictionaryFrequency + "=" + userFrequency + "=" + userActuality + "==" + getWeight();
    }

    @Override
    public int compareTo(Object o) {
        //sortare crescatoare
        return this.getWeight() - ((Word) o).getWeight();
    }

    @Override
    public int getLevel() {
        return heapLevel;
    }

    @Override
    public void setLevel(int level) {
        this.heapLevel = level;
    }

    public int getUserFrequency() {
        return userFrequency;
    }

    public void setUserFrequency(int userFrequency) {
        this.userFrequency = userFrequency;
    }

    public int getUserActuality() {
        return userActuality;
    }

    public void setUserActuality(int userActuality) {
        this.userActuality = userActuality;
    }

    public long getQueryUpdated() {
        return queryUpdated;
    }

    public void setQueryUpdated(long queryUpdated) {
        this.queryUpdated = queryUpdated;
        initUserFrequency = userFrequency;
        initUserActuality = userActuality;
    }

    public void degrade(long query) {
        userActuality = ServiceLocator.getDegrador().degradeActuality(initUserActuality, query, queryUpdated);
        userFrequency = ServiceLocator.getDegrador().degradeUser(initUserFrequency, query, queryUpdated);
        if (((initUserActuality - userActuality) > 0) || ((initUserActuality - userFrequency) > 0)) {
            System.out.println("degrading word:" + word + " by " + (initUserActuality - userActuality) + " and " + (initUserFrequency - userFrequency));
        }
    }
}

