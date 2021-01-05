package fileIo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class FileReader {


    protected String directoryName;
    protected String fileName;
    protected String logFileName;
    protected Path directoryPath;
    protected Path filePath;
    protected List<String> fileLines;
    protected List<String> logLines;
    protected Path logFilePath;


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
                throw new IOException("Unable to create the logfile (" + this.logFileName + ")!");
            }
        }

        // Check to see if the directory path even exists
        //    create it, if it doesn't exist
        if (Files.notExists(this.directoryPath)) {
            try {
                Files.createDirectories(this.directoryPath);
            } catch (IOException e) {
                // Add the error message to the log
                Files.write(this.logFilePath, Arrays.asList(e.getMessage()), StandardOpenOption.APPEND);
                throw new IOException("Unable to create the data directory (" + this.directoryName + ")!");
            }
        }

        // Check to see if the file path even exists
        //    create it, if it doesn't exist
        if (Files.notExists(this.filePath)) {
            try {
                Files.createFile(this.filePath);
            } catch (IOException e) {
                // Add the error message to the log
                Files.write(this.logFilePath, Arrays.asList(e.getMessage()), StandardOpenOption.APPEND);
                throw new IOException("Unable to create the data file (" + this.fileName + ")!");
            }
        }


        this.fileLines = Files.readAllLines(this.filePath); // populate the fileLines String array
        this.logLines = Files.readAllLines(this.logFilePath);
    }

    public static void main(String[] args) throws IOException {

        FileReader contactReader = new FileReader("data", "contacts.txt", "contacts.log");
        System.out.println(contactReader.getFileLines());

    }

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

