package dataparser.analytics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCountModuleTest {

    @Test
    public void testWordCount() {
        String content = "This is a test file with several words.";
        WordCountModule wordCountModule = new WordCountModule();
        wordCountModule.analyze(content);

        assertEquals(8, wordCountModule.getWordCount(), "Word count should be 8.");
    }
}
