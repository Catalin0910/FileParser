package dataparser.core;

import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import static org.mockito.Mockito.*;

public class FileMoverTest {

    @Test
    public void testMoveFile_directoryNotExist_createDirectoryAndMoveFile() throws IOException {
        // Arrange
        File mockFile = mock(File.class);
        String destinationDirectory = "mockDirectory";
        File mockDestDir = mock(File.class);

        // Mock for checking if destination directory exists
        when(mockDestDir.exists()).thenReturn(false);
        when(mockDestDir.mkdir()).thenReturn(true);
        when(mockFile.toPath()).thenReturn(Paths.get("mockFile.txt"));
        when(mockFile.getName()).thenReturn("mockFile.txt");

        // Mock static Files.move method
        PowerMockito.mockStatic(Files.class);
        doNothing().when(Files.class);
        Files.move(any(Path.class), any(Path.class), eq(StandardCopyOption.REPLACE_EXISTING));

        // Act
        FileMover.moveFile(mockFile, destinationDirectory);

        // Assert
        verify(mockDestDir, times(1)).mkdir();
        PowerMockito.verifyStatic(Files.class);
        Files.move(any(Path.class), any(Path.class), eq(StandardCopyOption.REPLACE_EXISTING));
    }
}
