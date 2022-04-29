package domain;

import static domain.Matches.Type.EXIST;
import static domain.Matches.Type.MATCH;
import static domain.Matches.Type.MISS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import helper.FakeWordProvider;
import infra.WordProvider;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class WordTest {

    private final WordProvider provider = new FakeWordProvider();

    private static final Word SPILL = new Word("spill", new FakeWordProvider());

    @DisplayName("정답과 답안은 단어 목에 존재하는 단어여야 한다.")
    @ValueSource(strings = {"aaaaa", "bbbbbb"})
    @NullAndEmptySource
    @ParameterizedTest
    void create(String word) {
        assertThatThrownBy(() -> new Word(word, provider))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("단어 비교 테스트 : spill - 모두 일치하는 경우")
    @Test
    void match01() {
        Matches matches = SPILL.match(SPILL);
        assertThat(matches).isEqualTo(Matches.PERFECT);
    }

    @DisplayName("단어 비교 테스트 lilps  - 위치가 다른 경우")
    @Test
    void match02() {
        Matches matches = SPILL.match(new Word("lilps", provider));
        assertThat(matches).isEqualTo(new Matches(List.of(EXIST, EXIST, EXIST, EXIST, EXIST)));
    }

    @DisplayName("매칭 테스트 ddddd - 모두 일치하지 않는 경우")
    @Test
    void match03() {
        Matches matches = SPILL.match(new Word("ddddd", provider));
        assertThat(matches).isEqualTo(new Matches(List.of(MISS, MISS, MISS, MISS, MISS)));
    }

    @DisplayName("단어 비교 테스트 shops - 일치하는 경우와 존재하는 경우 복합적으로 존재하는경우")
   @Test
    void match04() {
        Matches matches = SPILL.match(new Word("shops", provider));
        assertThat(matches).isEqualTo(new Matches(List.of(MATCH, MISS, MISS, EXIST, MISS)));
    }


}