package dataparser.analytics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DotCountModuleTest {

    @Test
    public void testDotCount() {
        String content = "This is a test... with some dots.... and more.";
        DotCountModule dotCountModule = new DotCountModule();
        dotCountModule.analyze(content);

        assertEquals(7, dotCountModule.getDotCount(), "Dot count should be 7.");
    }

    @Test
    public void testNoDots() {
        String content = "This is a test without dots";
        DotCountModule dotCountModule = new DotCountModule();
        dotCountModule.analyze(content);

        assertEquals(0, dotCountModule.getDotCount(), "Dot count should be 0 for text without dots.");
    }

    @Test
    public void testEmptyContent() {
        String content = "";
        DotCountModule dotCountModule = new DotCountModule();
        dotCountModule.analyze(content);

        assertEquals(0, dotCountModule.getDotCount(), "Dot count should be 0 for empty content.");
    }
}
