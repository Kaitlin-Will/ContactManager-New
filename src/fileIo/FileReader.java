package fileIo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class FileReader {

    // properties for this class
    // read in the file and parse it
    protected String directoryName;   // i.e. 'data', 'src/fileIo', etc
    protected String fileName;        // i.e. 'day18.txt', 'jolts.txt', etc
    protected String logFileName;     // will hold logging info
    protected Path directoryPath;     // Path representation for 'data', 'src/fileIo', etc
    protected Path filePath;          // Path representation for 'data/day18.txt', 'src/fileIo/jolts.txt', etc
    protected List<String> fileLines; // A list to iterate through with each line of the file
    protected List<String> logLines;  // A String list to hold all of the logging messages
    protected Path logFilePath;       // Path representation of the logging file


    // Constructor
    public FileReader(String directoryName, String fileName, String logFileName) throws IOException {
        this.directoryName = directoryName;
        this.fileName = fileName;
        this.logFileName = logFileName;
        this.directoryPath = Paths.get(directoryName);
        this.filePath = Paths.get(directoryName, fileName);
        this.logFilePath = Paths.get(directoryName, logFileName);

        // Create a logging file if it doesn't already exist
        if (Files.notExists(this.logFilePath)) {
            try {
                Files.createFile(this.logFilePath);
            } catch (IOException e) {
                // if there is an issue creating the log file, let's just crash the whole party
                //   because we want to be able to log all errors
                throw new IOException("Unable to create the logfile (" + this.logFileName + ")!");
            }
        }

        // Check to see if the directory path even exists
        //    create it, if it doesn't exist
        if (Files.notExists(this.directoryPath)) {
            try {
                Files.createDirectories(this.directoryPath); // createDirectory (only creates the single directory) vs createDirectories (creates non-existent parent directories if necessary)
            } catch (IOException e) {
                // Add the error message to the log
                // this.logLines.add(e.getMessage());
                Files.write(this.logFilePath, Arrays.asList(e.getMessage()), StandardOpenOption.APPEND);
                throw new IOException("Unable to create the data directory (" + this.directoryName + ")!");
                // stop all execution so we can investigate what went wrong
            }
        }

        // Check to see if the file path even exists
        //    create it, if it doesn't exist
        if (Files.notExists(this.filePath)) {
            try {
                Files.createFile(this.filePath);
            } catch (IOException e) {
                // Add the error message to the log
                // this.logLines.add(e.getMessage());
                Files.write(this.logFilePath, Arrays.asList(e.getMessage()), StandardOpenOption.APPEND);
                throw new IOException("Unable to create the data file (" + this.fileName + ")!");
                // stop all execution so we can investigate what went wrong
            }
        }

        // Take a look at the actual filePath value as a string
        System.out.println(this.filePath.toString());
        this.fileLines = Files.readAllLines(this.filePath); // populate the fileLines String array
        this.logLines = Files.readAllLines(this.logFilePath);
    }

    public static void main(String[] args) throws IOException {

        FileReader contactReader = new FileReader("data", "contacts.txt", "contacts.log");
        System.out.println(contactReader.getFileLines());

    }

    // Custom message to easily write to the log whenever we want to!
    public void writeToLog(String message) throws IOException {
        try {
            Files.write(this.logFilePath, Arrays.asList(message), StandardOpenOption.APPEND);
        } catch(IOException e) {
            // store the exception message in our log file
            Files.write(this.logFilePath, Arrays.asList(e.getMessage()), StandardOpenOption.APPEND);
            throw new IOException("Unable to write exception message to log file!");
        }
    }

    // Getters & Setters
    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public Path getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(Path directoryPath) {
        this.directoryPath = directoryPath;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public List<String> getFileLines() {
        return fileLines;
    }

    public void setFileLines(List<String> fileLines) {
        this.fileLines = fileLines;
    }

    public List<String> getLogLines() {
        return logLines;
    }

    public void setLogLines(List<String> logLines) {
        this.logLines = logLines;
    }

    public Path getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(Path logFilePath) {
        this.logFilePath = logFilePath;
    }
}

