/**
 * Клас `Punctuation` представляє набір пунктуаційних знаків та пробілів.
 */
public class Punctuation {
    private final StringBuffer value;

    /**
     * Конструктор створює пунктуацію.
     * @param value текст пунктуації.
     */
    public Punctuation(StringBuffer value) {
        this.value = value;
    }

    /**
     * Перетворює пунктуацію у формат `StringBuffer`.
     * @return текст пунктуації у вигляді `StringBuffer`.
     */
    public StringBuffer toStringBuffer() {
        return value;
    }
}
