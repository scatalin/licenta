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
        //todo distributed percentages
        totalWeight = (int) (frequency * Properties.WEIGHT_FREQUENCY + userFrequency * Properties.WEIGHT_FREQUENCY_USER + userActuality * Properties.WEIGHT_ACTUALITY_USER);
        return totalWeight;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
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
