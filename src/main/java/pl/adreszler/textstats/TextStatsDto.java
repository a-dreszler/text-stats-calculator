package pl.adreszler.textstats;

class TextStatsDto {
    private final Integer length;
    private final Integer wordCount;
    private final Boolean isPalindrome;
    private final String mostPopularWord;
    private final Integer mostPopularWordOccurences;

    public TextStatsDto(Integer length, Integer wordCount, Boolean isPalindrome, String mostPopularWord, Integer mostPopularWordOccurences) {
        this.length = length;
        this.wordCount = wordCount;
        this.isPalindrome = isPalindrome;
        this.mostPopularWord = mostPopularWord;
        this.mostPopularWordOccurences = mostPopularWordOccurences;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public Boolean getPalindrome() {
        return isPalindrome;
    }

    public String getMostPopularWord() {
        return mostPopularWord;
    }

    public Integer getMostPopularWordOccurences() {
        return mostPopularWordOccurences;
    }
}
