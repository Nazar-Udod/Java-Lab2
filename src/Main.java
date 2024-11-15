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

        // Розділимо текст на елементи (слова та послідовності інших символів)
        List<StringBuffer> words = new ArrayList<>();
        List<StringBuffer> delimitations = new ArrayList<>();
        StringBuffer currentElement = new StringBuffer();
        boolean isWordCurrent = Character.isLetterOrDigit(text.charAt(0));
        List<Boolean> isWord = new ArrayList<>();
        int numberOfElements = 0;
        // У циклі проходимо по всім символам
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            // Якщо поточний символ не того ж роду, що і попередній, додаємо новий елемент
            if (Character.isLetterOrDigit(ch) != isWordCurrent) {
                if (isWordCurrent) {
                    words.add(currentElement);
                    isWord.add(true);

                }
                else {
                    delimitations.add(currentElement);
                    isWord.add(false);
                }
                isWordCurrent = !isWordCurrent;
                numberOfElements++;
                currentElement = new StringBuffer();
            }
            currentElement.append(ch); // Додаємо символ до поточного елементу
        }
        // За наявності додаємо останній елемент
        if (!currentElement.isEmpty()) {
            if (isWordCurrent) {
                words.add(currentElement);
                isWord.add(true);
            }
            else {
                delimitations.add(currentElement);
                isWord.add(false);
            }
            numberOfElements++;
        }

        // Сортуємо слова
        words.sort(Comparator.comparingInt(Main::countVowels));

        // Знову об'єднуємо все в єдиний текст
        StringBuffer sortedText = new StringBuffer();
        int wordCounter = 0;
        int delimitationsCounter = 0;
        for (int i = 0; i < numberOfElements; i++) {
            if (isWord.get(i)) { // Якщо на цій позиції слово
                sortedText.append(words.get(wordCounter));
                wordCounter++;
            }
            else { // Інакше
                sortedText.append(delimitations.get(delimitationsCounter));
                delimitationsCounter++;
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