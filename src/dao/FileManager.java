package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {

    private static final String FILE_NAME = "file.txt";

    public synchronized String readFromFile() {
        StringBuffer sb = new StringBuffer();
        String line;
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader(FILE_NAME));
            while ((line = fileReader.readLine()) != null) {
                sb.append(line);
            }
            fileReader.close();
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "File data loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("ERROR. File '" + FILE_NAME + "' not found.");
        } catch (IOException e) {
            System.out.println("ERROR while reading from file '" + FILE_NAME + "'.");
        }
        return sb.toString();
    }

    public synchronized void writeToFile(String textToWrite) {
        File f = new File(FILE_NAME);
        FileWriter fw;
        try {
            fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.write(textToWrite);
            pw.close();
            bw.close();
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Autosave Done...");
        } catch (IOException e) {
            System.out.println("ERROR writing in file '" + FILE_NAME + "'.");
        }
    }
    
    
    
}

// ************* LAZY SINGLETON
//public class LazyInitializedSingleton {
//
//    private static LazyInitializedSingleton instance;
//    
//    private LazyInitializedSingleton(){}
//    
//    public static LazyInitializedSingleton getInstance(){
//        if(instance == null){
//            instance = new LazyInitializedSingleton();
//        }
//            return instance;
//        }
//    }

// ********** THREAD SAFE
//public class ThreadSafeSingleton {
//
//    private static ThreadSafeSingleton instance;
//    
//    private ThreadSafeSingleton(){}
//    
//    public static synchronized ThreadSafeSingleton getInstance(){
//        if(instance == null){
//            instance = new ThreadSafeSingleton();
//        }
//        return instance;
//    }
//    
//}



// *********** DOUBLE LOCKING FOR MULTITHREADING
//public static ThreadSafeSingleton getInstanceUsingDoubleLocking(){
//    if(instance == null){
//        synchronized (ThreadSafeSingleton.class) {
//            if(instance == null){
//                instance = new ThreadSafeSingleton();
//            }
//        }
//    }
//    return instance;
//}
