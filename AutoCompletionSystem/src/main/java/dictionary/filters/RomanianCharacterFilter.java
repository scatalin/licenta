package dictionary.filters;

/**
 * Created by Catalin on 5/24/2015 .
 */
public class RomanianCharacterFilter implements CharacterFilter {

    @Override
    public String filterCharacters(String word, boolean change) {
        if(change){
            return word;
        }
        String s = word;
        //todo: memorise this in a map, iterate word characters and replace if found
        s = replaceCharacter(s,RomanianSpecialCharacters.aVarianta1,'a');
        s = replaceCharacter(s,RomanianSpecialCharacters.aVarianta1Mare, 'A');
        s = replaceCharacter(s,RomanianSpecialCharacters.aVarianta2, 'a');
        s = replaceCharacter(s,RomanianSpecialCharacters.aVarianta2Mare, 'A');
        s = replaceCharacter(s,RomanianSpecialCharacters.i,'i');
        s = replaceCharacter(s,RomanianSpecialCharacters.iMare,'I');
        s = replaceCharacter(s,RomanianSpecialCharacters.t,'t');
        s = replaceCharacter(s,RomanianSpecialCharacters.tMare,'T');
        s = replaceCharacter(s,RomanianSpecialCharacters.s,'s');
        s = replaceCharacter(s,RomanianSpecialCharacters.sMare,'S');

        return s;
    }

    private String replaceCharacter(String word, RomanianSpecialCharacters character, Character replaceCharacter) {
        return word.replaceAll((char) character.getValue().intValue() + "", replaceCharacter + "");
    }

    public enum RomanianSpecialCharacters {

        aVarianta1(259),
        aVarianta1Mare(258),
        aVarianta2(226),
        aVarianta2Mare(194),
        i(238),
        iMare(206),
        s(537),
        sMare(536),
        t(539),
        tMare(538);



        private final Integer ascii;

        RomanianSpecialCharacters(Integer ascii) {
            this.ascii = ascii;
        }

        public Integer getValue() {
            return ascii;
        }
    }

}
