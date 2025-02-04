package dataparser.analytics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MostUsedWordModuleTest {

    @Test
    public void testMostUsedWord() {
        String content = "apple banana apple orange apple banana apple";
        MostUsedWordModule mostUsedWordModule = new MostUsedWordModule();
        mostUsedWordModule.analyze(content);

        assertEquals("apple", mostUsedWordModule.getMostUsedWord(), "Most used word should be 'apple'.");
    }

    @Test
    public void testNoWords() {
        String content = "";
        MostUsedWordModule mostUsedWordModule = new MostUsedWordModule();
        mostUsedWordModule.analyze(content);

        assertEquals("", mostUsedWordModule.getMostUsedWord(), "Most used word should be '' for empty content.");
    }
}
