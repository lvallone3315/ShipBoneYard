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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * UI Class
 *   Setup console output<br>
 *      printConsole(<string>)<br>
 *   Setup game output<br>
 *      printGameOutput(<string>)<br>
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
    
    /**
     * Constructor - set up JFRAME for Game Output
     */
    ShipBoneYardUI() {
        JFrame frame = new JFrame();
        frame.add( new JLabel("Ship Bone Yard game" ), BorderLayout.NORTH );

        gameTextArea = new JTextArea();
        gameTextArea.setLineWrap(true);
        gameTextArea.setWrapStyleWord(true);

        frame.add( new JScrollPane( gameTextArea )  );

        // frame.pack();
        frame.setVisible( true );
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // for now - take input from console
        consoleInput = new Scanner(System.in);
        
    }
    
    static void printGameOutput(String printString) {
        gameTextArea.append(printString);
    }
    
    static void printConsole(String printString) {
        System.out.print(printString);
    }

    // if output specified, print to console & then get input via scanner
    // if no output specified, just get input
    // Note: funnelling console as input source to a minimum of methods
    //
    // needs careful redesign
    // how to get input from UI in a natural way and keep main loop control
    static String getGameInput(String output) {
        System.out.print(output);
        return getGameInput();
    }
    static String getGameInput() {
        String userInput = consoleInput.nextLine();
        return (userInput);
    }
    
}
