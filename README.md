# FileParser

**FileParser** is a Java application designed to monitor a directory for `.txt` files. It processes each file, analyzes its content for specific statistics (word count, dot count, most used word), and moves the file to a `processed` sub-folder after completion. The project is structured using multiple modules, each responsible for analyzing a particular type of data in the text files.

## Features

- **Directory Monitoring**: Monitors a specified directory for newly added `.txt` files.
- **File Processing**: Analyzes each `.txt` file for:
    - Word count
    - Dot count
    - Most used word
- **File Movement**: After processing, files are moved to a `processed` sub-folder.
- **Extensible**: Easily extendable with additional file parsers (CSV, PDF, etc.).

## Requirements

- Java 11 or higher
- Maven (optional for build management)

## Setup

### Clone the Repository

Clone the repository to your local machine:

git clone https://github.com/Catalin0910/FileParser.git

You can build it manually using your preferred IDE (e.g., IntelliJ IDEA).

##Directory Structure

The project consists of the following modules:

**dataparser.analytics**: Contains modules for analyzing text content (word count, dot count, most used word).

**dataparser.core**: Contains the core logic for processing files and moving them.

**dataparser.factory**: Contains the file parser factory.

**dataparser.parser**: Contains interfaces and concrete implementations for parsing files.

**dataparser.main**: The main entry point of the application.
Configuration

Before running the application, set the directory path to monitor in the Main.java file:

String directoryPath = "C:/FileParser/src/main/resources/input", replace this path with the directory you wish to monitor for new .txt files.

##Processing

The application will automatically process each new file as it appears in the directory. It will analyze the file for word count, dot count, and most used word, then print the results to the console.

**File Movement**

After processing, the file will be moved to a processed sub-folder within the monitored directory.

**Modules**

**Analytics Modules**

**Word Count Module: Counts the number of words in a text file**.

**Dot Count Module: Counts the number of dots (.) in a text file**.

**Most Used Word Module: Identifies the most frequently occurring word in a text file**.

**File Parser**

The FileParser interface and its implementation TxtFileParser define how to read .txt files. Additional parsers can be added for different file types (CSV, PDF, etc.) through the FileParserFactory.

**File Watcher**

The FileWatcher class monitors the directory for new files and triggers the file processing logic.

**File Mover**
The FileMover class moves processed files to the processed sub-folder after analysis is complete.

**Extending the Application**

To extend the application, you can add new analytics modules or file parsers:

Create a new class implementing the AnalyticsModule interface for additional analysis features.
Add a new FileParser implementation for other file types (CSV, PDF, etc.).
Update the FileParserFactory to include new parsers.
