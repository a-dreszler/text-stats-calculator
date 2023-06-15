package pl.adreszler.textstats;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Service
class TextStatsService {

    private static final StringBuilder BUILDER = new StringBuilder();
    private static final String NOT_LETTER_REGEX = "[^\\p{L}]+";
    TextStatsDto calculateStats(String text,
                                boolean calculateTextLength,
                                boolean calculateWordCount,
                                boolean checkIfPalindrome,
                                boolean findWordWithMostOccurences) {
        Integer textLength = calculateTextLength ? text.length() : null;
        Integer wordCount = calculateWordCount ? calculateWordCount(text) : null;
        Boolean isPalindrome = checkIfPalindrome ? checkIfPalindrome(text) : null;
        Map.Entry<String, Integer> mostPopularWordEntry = findWordWithMostOccurences ? getMostPopularWord(text) : null;
        String mostPopularWord = mostPopularWordEntry != null ? mostPopularWordEntry.getKey() : null;
        Integer mostPopularWordOccurences = mostPopularWordEntry != null ? mostPopularWordEntry.getValue() : null;

        return new TextStatsDto(textLength, wordCount, isPalindrome, mostPopularWord, mostPopularWordOccurences);
    }

    private int calculateWordCount(String text) {
        System.out.println();
        return text.split(NOT_LETTER_REGEX).length;
    }

    private boolean checkIfPalindrome(String text) {
        String onlyLowercaseLetterText = text.replaceAll("[^\\p{L}]+", "").toLowerCase();
        String reverseOnlyLowercaseLetterText = BUILDER.append(onlyLowercaseLetterText).reverse().toString();
        BUILDER.setLength(0);

        return onlyLowercaseLetterText.equals(reverseOnlyLowercaseLetterText);
    }

    private Map.Entry<String, Integer> getMostPopularWord(String text) {
        Map<String, Integer> wordOccurences = new HashMap<>();
        Arrays.stream(text.split(NOT_LETTER_REGEX))
                .map(String::toLowerCase)
                .forEach(word -> countOccurence(word, wordOccurences));

        return wordOccurences.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .get();
    }

    private void countOccurence(String word, Map<String, Integer> wordOccurences) {
        if (wordOccurences.containsKey(word)) {
            wordOccurences.put(word, wordOccurences.get(word) + 1);
        } else {
            wordOccurences.put(word, 1);
        }
    }
}
