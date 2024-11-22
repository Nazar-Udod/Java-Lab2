/**
 * Клас `Letter` представляє одну літеру.
 */
public class Letter {
    private final char value;

    /**
     * Конструктор створює літеру.
     * @param value символ літери.
     */
    public Letter(char value) {
        this.value = value;
    }

    /**
     * Повертає значення літери.
     * @return символ літери.
     */
    public char getValue() {
        return value;
    }

    /**
     * Перевіряє, чи є літера голосною.
     * @return true, якщо літера є голосною, інакше false.
     */
    public boolean isVowel() {
        String vowels = "aeiouAEIOUаеєиіоуюяАЕЄИІОУЮЯ";
        return vowels.indexOf(value) != -1;
    }
}
