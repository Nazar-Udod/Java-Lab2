/**
 * Клас `Word` представляє слово як набір літер.
 */
public class Word {
    private final Letter[] letters;

    /**
     * Конструктор створює об'єкт слова з переданого тексту.
     * @param word текст слова у вигляді `StringBuffer`.
     */
    public Word(StringBuffer word) {
        letters = new Letter[word.length()];
        for (int i = 0; i < word.length(); i++) {
            letters[i] = new Letter(word.charAt(i));
        }
    }

    /**
     * Підраховує кількість голосних літер у слові.
     * @return кількість голосних.
     */
    public int countVowels() {
        int count = 0;
        for (Letter letter : letters) {
            if (letter.isVowel()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Перетворює слово у формат `StringBuffer`.
     * @return слово у вигляді `StringBuffer`.
     */
    public StringBuffer toStringBuffer() {
        StringBuffer buffer = new StringBuffer();
        for (Letter letter : letters) {
            buffer.append(letter.getValue());
        }
        return buffer;
    }
}
