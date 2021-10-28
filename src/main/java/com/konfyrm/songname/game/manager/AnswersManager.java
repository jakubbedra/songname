package com.konfyrm.songname.game.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class AnswersManager {

    private final int NON_ASCII_CHARS_COUNT = 9;
    private final char[][] NON_ASCII_CHARS = {
            {'ą', 'ć', 'ę', 'ó', 'ś', 'ł', 'ń', 'ź', 'ż'},
            {'a', 'c', 'e', 'o', 's', 'l', 'n', 'z', 'z'}
    };

    @Autowired
    public AnswersManager() {

    }

    public boolean compare(String answer, String real) {
        answer = editForComparison(answer);
        real = editForComparison(real);
        return answer.equals(real);
    }

    private String editForComparison(String string) {
        string = string.toLowerCase(Locale.ROOT);
        string = replaceNonAsciiChars(string);
        string = removeWhiteSpaces(string);
        string = removeNonAlphanumericChars(string);
        return string;
    }

    private String replaceNonAsciiChars(String string) {
        for (int i = 0; i < NON_ASCII_CHARS_COUNT; i++) {
            string = string.replace(NON_ASCII_CHARS[0][i], NON_ASCII_CHARS[1][i]);
        }
        return string;
    }

    private String removeWhiteSpaces(String string) {
        string = string.replace(" ", "");
        return string;
    }

    private String removeNonAlphanumericChars(String string) {
        string = string.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}]", "");
        return string;
    }

}
