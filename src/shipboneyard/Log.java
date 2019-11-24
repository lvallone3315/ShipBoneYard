package shipboneyard;



/**
 *
 * @author leev
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.PrintWriter;  
public class Log {
    static final String DEFAULT_FILE_NAME = "BoneYardLog.txt";
    
    File logFilePointer;
    FileOutputStream logFileStream;
    OutputStreamWriter logFileStreamWriter;
    Writer logWriter;
    PrintWriter userInputPrintWriter = null;
    
    /**
     *  Logging class  Log()
     *  constructors Log() or Log (filename)
     *    if no filename specified, uses default name BoneYardLog.txt
     *  if cannot open log file
     *    prints error message, but goes on
     *    subsequent writes to log file are ignored
     */
    Log () {
        this (DEFAULT_FILE_NAME);
    }
        
    Log (String filename) {
        try {
            userInputPrintWriter = new PrintWriter (filename);
        }
        catch (IOException e) {
            System.err.println ("ERROR: Cannot open " + filename);
            userInputPrintWriter = null;
        }
        System.err.println("log file constructor opened " + filename);
    }
    
    /**
     * if log file was created successfully, parameter written to file
     * assume <new line> was stripped off, so adds new line to string
     * Notes:
     *   flushes buffer after each write, so nothing lost if program crashes
     *   checks PrintWriter = null -> could not open log file, ignore log request
     * 
     * @param userInput - user input to be written to log file
     */
    public void logUserInput(String userInput) {
        if (userInputPrintWriter != null) {
            userInputPrintWriter.println(userInput);
            userInputPrintWriter.flush();
        }
        // else error opening log file, ignore call
    }
    
    public void closeUserInputLog() {
        if (userInputPrintWriter != null) {
            userInputPrintWriter.flush();
            userInputPrintWriter.close();
        }
    }
}