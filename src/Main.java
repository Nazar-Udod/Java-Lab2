import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Зчитуємо текст з консолі
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть текст для сортування слів за кількістю голосних:");
        String text = scanner.nextLine();

        // Переводимо текст у StringBuffer
        StringBuffer bufferText = new StringBuffer(text);

        try {
            // Передаємо текст в сортувальний метод
            StringBuffer sortedText = sortByVowels(bufferText);
            // Виводимо результат
            System.out.println("Відсортований текст:\n" + sortedText);
        }
        catch (Exception e) {
            // Оброблення помилок
            System.err.println("Помилка: " + e.getMessage());
        }
    }

    /**
     * Метод, який сортує слова в тексті за зростанням кількості голосних літер.
     * @param text вхідний текст
     * @return відсортований текст
     * @throws IllegalArgumentException якщо текст порожній
     */
    public static StringBuffer sortByVowels(StringBuffer text) {
        // Якщо текст порожній, кидаємо виключення
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Порожній текст");
        }

        // Розділимо слова та розділові знаки та запишемо номери позицій слів, розділових знаків та пробілів
        List<StringBuffer> words = new ArrayList<>();
        List<Character> punctuation = new ArrayList<>();
        List<Integer> wordPositions = new ArrayList<>();
        List<Integer> punctuationPositions = new ArrayList<>();
        List<Integer> spacePositions = new ArrayList<>();
        int elementNumber = -1;
        StringBuffer currentWord = new StringBuffer();
        // У циклі проходимо по всім символам
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isWhitespace(ch)) {
                // Якщо символ є пробілом, записуємо нове слово
                if (!currentWord.isEmpty()) {
                    words.add(currentWord);
                    currentWord = new StringBuffer();
                }
                elementNumber++;
                spacePositions.add(elementNumber);
            } else if (Character.isLetterOrDigit(ch)) {
                // Якщо символ є літерою чи цифрою, записуємо його у слово
                if (currentWord.isEmpty()) {
                    elementNumber++;
                    wordPositions.add(elementNumber);
                }
                currentWord.append(ch);
            } else {
                // Якщо символ інший, записуємо його до розділових знаків та записуємо нове слово
                if (!currentWord.isEmpty()) {
                    words.add(currentWord);
                    currentWord = new StringBuffer();
                }
                punctuation.add(ch);
                elementNumber++;
                punctuationPositions.add(elementNumber);
            }
        }
        // За наявності додаємо останнє слово
        if (!currentWord.isEmpty()) {
            words.add(currentWord);
        }

        // Сортуємо слова
        words.sort(Comparator.comparingInt(Main::countVowels));

        // Знову об'єднуємо все в єдиний текст
        StringBuffer sortedText = new StringBuffer();
        int wordCounter = 0;
        int punctuationCounter = 0;
        for (int i = 0; i <= elementNumber; i++) {
            if (wordPositions.contains(i)) {
                sortedText.append(words.get(wordCounter));
                wordCounter++;
            }
            else if (punctuationPositions.contains(i)) {
                sortedText.append(punctuation.get(punctuationCounter));
                punctuationCounter++;
            }
            else if (spacePositions.contains(i)) {
                sortedText.append(" ");
            }
        }

        return sortedText;
    }

    /**
     * Метод, який рахує кількість голосних літер у слові.
     * @param word слово для обрахування
     * @return кількість голосних літер
     */
    public static int countVowels(StringBuffer word) {
        // Лічильник голосних літер
        int count = 0;
        // Рядок, що містить англійські та українські голосні літери
        String vowels = "aeiouAEIOUаеєиіоуюяАЕЄИІОУЮЯ";
        for (int i = 0; i < word.length(); i++) {
            // Якщо літера міститься у рядку голосних, збільшуємо лічильник
            if (vowels.indexOf(word.charAt(i)) != -1) {
                count++;
            }
        }
        return count;
    }
}