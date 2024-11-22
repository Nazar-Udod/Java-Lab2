import java.util.List;
import java.util.ArrayList;

/**
 * Клас `Text` представляє текст, що складається з речень.
 */
public class Text {
    private final Sentence[] sentences;

    /**
     * Конструктор створює текст з переданого тексту.
     * @param text текст у вигляді `StringBuffer`.
     */
    public Text(StringBuffer text) {
        List<StringBuffer> sentencesList = new ArrayList<StringBuffer>();
        StringBuffer currentSentence = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            currentSentence.append(ch);
            if (ch == '.' || ch == '!' || ch == '?') {
                if (currentSentence.charAt(currentSentence.length() - 1) == ' ') {
                    currentSentence.deleteCharAt(currentSentence.length() - 1);
                }
                sentencesList.add(currentSentence);
                currentSentence = new StringBuffer();
            }
        }
        if (!currentSentence.isEmpty()) {
            sentencesList.add(currentSentence);
        }
        sentences = new Sentence[sentencesList.size()];
        for (int i = 0; i < sentencesList.size(); i++) {
            sentences[i] = new Sentence(sentencesList.get(i));
        }
    }

    /**
     * Отримує всі слова з тексту.
     * @return масив слів.
     */
    public Word[] getAllWords() {
        int wordCount = 0;
        for (Sentence sentence : sentences) {
            for (Object component : sentence.getComponents()) {
                if (component instanceof Word) {
                    wordCount++;
                }
            }
        }
        Word[] words = new Word[wordCount];
        int index = 0;

        for (Sentence sentence : sentences) {
            for (Object component : sentence.getComponents()) {
                if (component instanceof Word) {
                    words[index++] = (Word) component;
                }
            }
        }

        return words;
    }

    /**
     * Замінює всі слова у тексті переданим масивом слів.
     * @param words масив нових слів.
     */
    public void replaceWords(Word[] words) {
        int index = 0;
        for (Sentence sentence : sentences) {
            Object[] components = sentence.getComponents();
            for (int i = 0; i < components.length; i++) {
                if (components[i] instanceof Word) {
                    components[i] = words[index++];
                }
            }
        }
    }

    /**
     * Повертає текст у вигляді рядка.
     * @return текст у форматі `String`.
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (Sentence sentence : sentences) {
            buffer.append(sentence.toStringBuffer()).append(" ");
        }
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }
}
