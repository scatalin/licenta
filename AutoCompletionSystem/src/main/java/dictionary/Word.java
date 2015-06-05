package dictionary;

import algorithms.heap.HeapNode;
import system.Properties;

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
    private double dictionaryFrequencyPercentage;
    private double userFrequencyPercentage;
    private double userActualityPercentage;

    private Word(String word, int heapLevel, int totalWeight, int dictionaryFrequency, int userFrequency, int userActuality) {
        this.word = word;
        this.heapLevel = heapLevel;
        this.totalWeight = totalWeight;
        this.dictionaryFrequency = dictionaryFrequency;
        this.userFrequency = userFrequency;
        this.userActuality = userActuality;
    }

    public Word(String word, int dictionaryFrequency, int userFrequency, int userActuality) {
        this.word = word;
        this.dictionaryFrequency = dictionaryFrequency;
        this.userFrequency = userFrequency;
        this.userActuality = userActuality;
        heapLevel = 0;
    }

    public Word(String word, int dictionaryFrequency) {
        this(word, dictionaryFrequency, 0, 0);
    }

    public Word(String word, int dictionaryFrequency, int userFrequency) {
        this(word, dictionaryFrequency, userFrequency, 0);
    }

    public Word(String word) {
        this(word, 1, 0, 0);
    }

    public String getWord() {
        return word;
    }

    public int getWeight() {
        calculateTotalWeight();
        return totalWeight;
    }

    public void setWeight(int dictionaryFrequency) {
        this.dictionaryFrequency = dictionaryFrequency;
    }

    private void calculateTotalWeight() {
        initPercentages();
//        if (userFrequency <= 0) {
//            normalizeUserFrequency();
//        }
//        if (userActuality <= 0) {
//            normalizeUserActuality();
//        }
        totalWeight = (int) ((dictionaryFrequency * dictionaryFrequencyPercentage) + (userFrequency * userFrequencyPercentage) + (userActuality * userActualityPercentage));
    }

    private void initPercentages() {
        dictionaryFrequencyPercentage = Properties.WEIGHT_FREQUENCY / Properties.WEIGHT_FREQUENCY;
        userFrequencyPercentage = Properties.WEIGHT_FREQUENCY_USER / Properties.WEIGHT_FREQUENCY;
        userActualityPercentage = Properties.WEIGHT_ACTUALITY_USER / Properties.WEIGHT_FREQUENCY;
    }

    private void normalizeUserFrequency() {
        double normalizeDivision = dictionaryFrequencyPercentage + userActualityPercentage;
        dictionaryFrequencyPercentage += userFrequencyPercentage * (dictionaryFrequencyPercentage / normalizeDivision);
        userActualityPercentage += userFrequencyPercentage * (userActualityPercentage / normalizeDivision);
        userFrequencyPercentage = 0;
    }

    private void normalizeUserActuality() {
        double normalizeDivision = dictionaryFrequencyPercentage + userFrequencyPercentage;
        dictionaryFrequencyPercentage += userActualityPercentage * (dictionaryFrequencyPercentage / normalizeDivision);
        userFrequencyPercentage += userActualityPercentage * (userFrequencyPercentage / normalizeDivision);
        userActualityPercentage = 0;
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
        return new Word(word, heapLevel, totalWeight, dictionaryFrequency, userFrequency, userActuality);
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
}
