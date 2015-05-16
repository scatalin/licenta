package dictionary;

import system.Properties;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class Word implements Comparable{

    private static final WordFrequencyComparator wordFrequencyComparator = new WordFrequencyComparator();

    private final String word;
    private int totalWeight;
    private int dictionaryFrequency;
    private int userFrequency;
    private int userActuality;
    private double dictionaryFrequencyPercentage;
    private double userFrequencyPercentage;
    private double userActualityPercentage;

    public Word(String word, int dictionaryFrequency, int userFrequency, int userActuality) {
        this.word = word;
        this.dictionaryFrequency = dictionaryFrequency;
        this.userFrequency = userFrequency;
        this.userActuality = userActuality;
    }

    public Word(String word, int dictionaryFrequency) {
        this(word, dictionaryFrequency, 0, 0);
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

    private void calculateTotalWeight() {
        initPercentages();
        if (userFrequency <= 0) {
            normalizeUserFrequency();
        }
        if (userActuality <= 0) {
            normalizeUserActuality();
        }
        totalWeight = (int) ((dictionaryFrequency * dictionaryFrequencyPercentage) + (userFrequency * userFrequencyPercentage) + (userActuality * userActualityPercentage));
    }

    private void initPercentages() {
        dictionaryFrequencyPercentage = Properties.WEIGHT_FREQUENCY;
        userFrequencyPercentage = Properties.WEIGHT_FREQUENCY_USER;
        userActualityPercentage = Properties.WEIGHT_ACTUALITY_USER;
    }

    private void normalizeUserFrequency() {
        double normalizeDivision = 1;
        dictionaryFrequencyPercentage += userFrequencyPercentage * (dictionaryFrequencyPercentage / normalizeDivision);
        userActualityPercentage += userFrequencyPercentage * (userActualityPercentage / normalizeDivision);
        userFrequencyPercentage = 0;
    }

    private void normalizeUserActuality() {
        double normalizeDivision = 1;
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

    @Override
    public int hashCode() {
        return (word != null) ? word.hashCode() : 0;
    }

    public void increaseFrequency(int increment) {
        dictionaryFrequency += increment;
    }

    @Override
    public String toString() {
        return word + "=" + dictionaryFrequency;
    }

    @Override
    public int compareTo(Object o) {
        return Word.wordFrequencyComparator.compare(this, ((Word) o));
    }

    public void setWeight(int dictionaryFrequency) {
        this.dictionaryFrequency = dictionaryFrequency;
    }
}
