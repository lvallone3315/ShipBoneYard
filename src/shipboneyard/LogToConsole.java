/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipboneyard;

/**
 * logToConsole
 *    debugging info, used to log
 *    initially, log everything at same level
 *    
 *    ToDo: add verbose settings (poss overload log method w/ another arg)
 * 
 * @author leev
 */
public class LogToConsole {
    // no constructor for now, define methods as static
    // for JAR version - redirect console to a window (add to constructor)
    //    ConsoleRedirect.setConsole();
    
    public static void log (String str){
        System.out.print(str);
    }
    public static void logln (String str) {
        System.out.println(str + "\n");
    }
    public static void logerr (String str) {
        System.err.print(str);
    }
}
