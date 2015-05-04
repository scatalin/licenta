package model.dictionary;

import system.Properties;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class Word {
    private String word;
    private int totalWeight;
    private int frequency;
    private int userFrequency;
    private int userActuality;
    private double frequencyPercentage;
    private double userFrequencyPercentage;
    private double userActualityPercentage;

    public Word(String word, int frequency, int userFrequency, int userActuality) {
        this.word = word;
        this.frequency = frequency;
        this.userFrequency = userFrequency;
        this.userActuality = userActuality;
    }

    public Word(String word, int frequency) {
        this(word, frequency, 0, 0);
    }

    public Word(String word) {
        this(word, 1);
    }

    public String getWord() {
        return word;
    }

    public int getFrequency() {
        calculateTotalWeight();
        return totalWeight;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    private void calculateTotalWeight() {
        initPercentages();
        if (userFrequency <= 0) {
            normalizeUserFrequency();
        }
        if (userActuality <= 0) {
            normalizeUserActuality();
        }
        totalWeight = (int) (frequency * frequencyPercentage + userFrequency * userFrequencyPercentage + userActuality * userActualityPercentage);
    }

    private void initPercentages() {
        frequencyPercentage = Properties.WEIGHT_FREQUENCY;
        userFrequencyPercentage = Properties.WEIGHT_FREQUENCY_USER;
        userActualityPercentage = Properties.WEIGHT_ACTUALITY_USER;
    }

    private void normalizeUserFrequency() {
        double normalizeDivision = 1;
        frequencyPercentage += userFrequencyPercentage * (frequencyPercentage / normalizeDivision);
        userActualityPercentage += userFrequencyPercentage * (userActualityPercentage / normalizeDivision);
        userFrequencyPercentage = 0;
    }

    private void normalizeUserActuality() {
        double normalizeDivision = 1;
        frequencyPercentage += userActualityPercentage * (frequencyPercentage / normalizeDivision);
        userFrequencyPercentage += userActualityPercentage * (userFrequencyPercentage / normalizeDivision);
        userActualityPercentage = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;

        Word word1 = (Word) o;

        return !(word != null ? !word.equals(word1.word) : word1.word != null);
    }

    @Override
    public int hashCode() {
        return word != null ? word.hashCode() : 0;
    }

    public void increaseFrequency() {
        frequency++;
    }

    public void increaseFrequency(int increment) {
        frequency += increment;
    }

    @Override
    public String toString() {
        return word + "=" + frequency;
    }
}
