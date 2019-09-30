/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipboneyard;

import java.awt.BorderLayout;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * UI Class
 *   Setup console output<br>
 *      printConsole(<string>) <br>
 *   Setup game output<br>
 *      printGameOutput(<string>) <br>
 *  Get input from game window <br>
 *      String getGameInput() <br>
 * <P>
 * Issues:
 *   Initially - string arg only, formatting done prior to calling
 *     equivalent to sprintf() only <br>
 *   Assuming static class - so no need to instantiate object to access <br>
 *   For jar version, IDE console not avail, map console to window &
 *     map game output to separate window
 *     
 * Consider:
 *   separate logging class, consider extending args
 *   destination totally within logging class
 * 
 * Note: if switch to multi-player, need static -> instance
 *
 * @author leev
 */
public class ShipBoneYardUI extends JFrame {
    
    static private JTextArea gameTextArea;
    static Scanner consoleInput;
    static JFrame frame;
    
    static final int FRAME_LENGTH = 800;
    static final int FRAME_WIDTH = 600;
    
    static final String GAME_TEXT = "Ship Bone Yard game";
    static final String DEFAULT_PROMPT = "> ";
    
    /**
     * Constructor - set up JFRAME for Game Output
     */
    ShipBoneYardUI() {
        frame = new JFrame();
        frame.add( new JLabel(GAME_TEXT ), BorderLayout.NORTH );

        gameTextArea = new JTextArea();
        gameTextArea.setLineWrap(true);
        gameTextArea.setWrapStyleWord(true);

        frame.add( new JScrollPane( gameTextArea )  );

        // frame.pack();   not needed for now, revisit
        frame.setVisible( true );
        frame.setSize(FRAME_WIDTH,FRAME_LENGTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // was used for gameInput2() method
        //   no longer used w/ switch to JOptionPane
        consoleInput = new Scanner(System.in);
        
    }
    
    static void printGameOutput(String printString) {
        gameTextArea.append(printString);
    }
    
    
    // print to console
    //  expect to replace this method, printConsole(), with log()
    static void printConsole(String printString) {
        System.out.print(printString);
    }

    // get user input e.g. move, pickup, etc.
    //   getGameInput(<string>) - displays string in JOptionPane & gets input
    //   getGameInput() - displays a default string & gets input
    //
    // for now, JOptionPane works okay, but is a little clunky
    // the good - contained in JFrame & blocks until input entered
    // the bad - fixed position in frame & not really console friendly
    //
    // needs careful redesign
    // how to get input from UI in a natural way and keep main loop control

    static String getGameInput(String output) {
        String input = JOptionPane.showInputDialog(frame, output);
        return input;
    }
    static String getGameInput() {
        String input = JOptionPane.showInputDialog(frame, DEFAULT_PROMPT);
        return input;
    }
    
    // No longer used - takes input from console
    //  won't work well in a JAR version
    //
    // if output specified, print to console & then get input via scanner
    // if no output specified, just get input
    // Note: funnelling console as input source to a minimum of methods
    //
    static String getGameInput2() {
        String userInput = consoleInput.nextLine();
        return (userInput);
    }
    static String getGameInput2(String output) {
        System.out.print(output);
        return getGameInput2();
    }
    
}
