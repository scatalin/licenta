package controller.filter;

import dictionary.filters.CharacterFilter;
import dictionary.filters.RomanianCharacterFilter;
import system.Properties;

/**
 * Created by Catalin on 6/18/2015 .
 */
public class FrazeFilter {

    private CharacterFilter filter;

    public FrazeFilter() {
        filter = new RomanianCharacterFilter();
    }

    public Character filterCharacter(Character character) {
        String c = character + "";
        c = c.toLowerCase();
        return filter.filterCharacters(c, Properties.DIACRITICS).charAt(0);
    }
}
