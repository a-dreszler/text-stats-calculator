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
                                boolean lengthFlag,
                                boolean wordCountFlag,
                                boolean palindromeFlag,
                                boolean mostPopularWordFlag) {
        Integer textLength = calculateTextLength(text, lengthFlag);
        Integer wordCount = calculateWordCount(text, wordCountFlag);
        Boolean isPalindrome = checkIfPalindrome(text, palindromeFlag);
        Map.Entry<String, Integer> mostPopularWordEntry = getMostPopularWordEntry(text, mostPopularWordFlag);
        if (mostPopularWordEntry == null) {
            return new TextStatsDto(textLength, wordCount, isPalindrome, null, null);
        }
        return new TextStatsDto(textLength, wordCount, isPalindrome, mostPopularWordEntry.getKey(), mostPopularWordEntry.getValue());
    }

    private Integer calculateTextLength(String text, boolean lengthFlag) {
        if (lengthFlag) {
            return text.length();
        }
        return null;
    }

    private Integer calculateWordCount(String text, boolean wordCountFlag) {
        if (wordCountFlag) {
            return text.split(NOT_LETTER_REGEX).length;
        }
        return null;
    }

    private Boolean checkIfPalindrome(String text, boolean palindromeFlag) {
        if (palindromeFlag) {
            String onlyLowercaseLetterText = text.replaceAll("[^\\p{L}]+", "").toLowerCase();
            String reverseOnlyLowercaseLetterText = BUILDER.append(onlyLowercaseLetterText).reverse().toString();
            BUILDER.setLength(0);

            return onlyLowercaseLetterText.equals(reverseOnlyLowercaseLetterText);
        }

        return null;
    }

    private Map.Entry<String, Integer> getMostPopularWordEntry(String text, boolean mostPopularWordFlag) {
        if (mostPopularWordFlag) {
            Map<String, Integer> wordOccurences = new HashMap<>();
            Arrays.stream(text.split(NOT_LETTER_REGEX))
                    .map(String::toLowerCase)
                    .forEach(word -> countOccurence(word, wordOccurences));

            return wordOccurences.entrySet().stream()
                    .max(Comparator.comparingInt(Map.Entry::getValue))
                    .get();
        }

        return null;
    }

    private void countOccurence(String word, Map<String, Integer> wordOccurences) {
        if (wordOccurences.containsKey(word)) {
            wordOccurences.put(word, wordOccurences.get(word) + 1);
        } else {
            wordOccurences.put(word, 1);
        }
    }
}