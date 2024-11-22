import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Клас `Main` містить головний метод програми для сортування слів у тексті за кількістю голосних.
 */
public class Main {
    /**
     * Головний метод програми.
     * @param args аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        // Отримуємо текст від користувача
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть текст для сортування слів за кількістю голосних:");
        StringBuffer input = new StringBuffer(scanner.nextLine());

        // Замінюємо набори пробілів та табуляцій одним пробілом
        for (int i = 0; i < input.length(); i++) {
            if ((input.charAt(i) == ' ' || input.charAt(i) == '\t')) {
                input.replace(i, i + 1, " ");
                if (input.charAt(i - 1) == ' ') {
                    input.deleteCharAt(i);
                    i--;
                }
            }
        }

        // Створюємо екземпляр тексту
        Text text = new Text(input);

        // Сортуємо слова за кількістю голосних
        Word[] words = text.getAllWords();
        Arrays.sort(words, Comparator.comparingInt(Word::countVowels));
        text.replaceWords(words);

        // Виводимо результат
        System.out.println("Відсортований текст:\n" + text);
    }
}