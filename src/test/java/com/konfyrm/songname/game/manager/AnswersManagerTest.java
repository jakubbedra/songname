package com.konfyrm.songname.game.manager;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnswersManagerTest {

    private final AnswersManager answersManager;

    public AnswersManagerTest() {
        this.answersManager = new AnswersManager();
    }

    @Test
    public void nonAsciiCharsTest() {
        String answer = "Osąd";
        String real = "Osad";

        Assertions.assertThat(answersManager.compare(answer, real)).isTrue();
    }

    @Test
    public void whitespaceTest() {
        String answer = "6.3amg";
        String real = "6.3 amg";

        Assertions.assertThat(answersManager.compare(answer, real)).isTrue();
    }

    @Test
    public void upperCaseTest() {
        String answer = "test";
        String real = "TeSt";

        Assertions.assertThat(answersManager.compare(answer, real)).isTrue();
    }

    @Test
    public void nonAlphanumericTest() {
        String answer = "PSWIS";
        String real = "P.S.W.I.S";

        Assertions.assertThat(answersManager.compare(answer, real)).isTrue();
    }

    @Test
    public void nonAsciiCharsUpperCaseWhiteSpacesTest() {
        String answer = "janpawel2";
        String real = "Jan Paweł 2";

        Assertions.assertThat(answersManager.compare(answer, real)).isTrue();
    }

    @Test
    public void nonAsciiCharsUpperCaseWhitespaceNonAlphanumericTest() {
        String answer = "zyciektoreznam";
        String real = "Życie, które znam";

        Assertions.assertThat(answersManager.compare(answer, real)).isTrue();
    }

}
