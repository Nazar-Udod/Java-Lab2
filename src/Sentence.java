import java.util.List;
import java.util.ArrayList;

/**
 * Клас `Sentence` представляє речення, яке складається зі слів та пунктуації.
 */
public class Sentence {
    private final Object[] components;

    /**
     * Конструктор створює речення з тексту.
     * @param sentence текст речення у вигляді `StringBuffer`.
     */
    public Sentence(StringBuffer sentence) {
        List<StringBuffer> parts = new ArrayList<StringBuffer>();
        StringBuffer currentPart = new StringBuffer();
        boolean isWord = Character.isLetterOrDigit(sentence.charAt(0));
        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            if (Character.isLetterOrDigit(ch) != isWord) {
                parts.add(new StringBuffer(currentPart));
                isWord = !isWord;
                currentPart = new StringBuffer();
            }
            currentPart.append(ch);
        }
        if (!currentPart.isEmpty()) {
            parts.add(new StringBuffer(currentPart));
        }
        components = new Object[parts.size()];
        for (int i = 0; i < parts.size(); i++) {
            if (Character.isLetterOrDigit(parts.get(i).charAt(0))) {
                components[i] = new Word(new StringBuffer(parts.get(i)));
            } else {
                components[i] = new Punctuation(new StringBuffer(parts.get(i)));
            }
        }
    }

    /**
     * Отримує всі компоненти речення.
     * @return масив компонентів (слова або пунктуація).
     */
    public Object[] getComponents() {
        return components;
    }

    /**
     * Перетворює речення у формат `StringBuffer`.
     * @return текст речення у вигляді `StringBuffer`.
     */
    public StringBuffer toStringBuffer() {
        StringBuffer buffer = new StringBuffer();
        for (Object component : components) {
            if (component instanceof Word) {
                buffer.append(((Word)component).toStringBuffer());
            }
            else {
                buffer.append(((Punctuation)component).toStringBuffer());
            }
        }
        return buffer;
    }
}
