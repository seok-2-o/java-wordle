package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchesTest {

    @DisplayName("모든 문자가 일치하면 true를 응답힌다.")
    @Test
    void isAllMatch() {
        assertThat(Matches.PERFECT.isAllMatch()).isTrue();
    }
}