package model.dictionary;

/**
 * Created by Catalin on 3/9/2015 .
 */
public class Word implements Comparable{
    private String word;
    private int frequency;


    public Word(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public Word(String word){
        this(word,1);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;

        Word word1 = (Word) o;

        if (word != null ? !word.equals(word1.word) : word1.word != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return word != null ? word.hashCode() : 0;
    }

    public void increaseFrequency() {
        frequency++;
    }

    @Override
    public int compareTo(Object o) {
        return word.compareTo(((Word)o).getWord());
    }

    @Override
    public String toString() {
        return word+"="+frequency;
    }
}
