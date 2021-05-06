/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipboneyard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
// import javax.swing.JLabel;
// import javax.swing.JOptionPane;


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
 * ToDo: UI class currently static, refactor as instance
 * 
 * Note: if switch to multi-player, need static -> instance
 *
 * @author leev
 */
class ShipBoneYardUI extends JFrame {
    

    // static private JTextArea gameTextArea;
    static Scanner consoleInput;
    // static JFrame frame;
    
    private static final int NUM_ROWS = 15;
    private static final int NUM_COLUMNS = 60;
    private final Dimension WINDOW_SIZE = new Dimension(550, 700);
    private static final int FRAME_LENGTH = 800;
    private static final int FRAME_WIDTH = 600;
    private static final JTextArea gameTextArea = new JTextArea(NUM_COLUMNS, NUM_ROWS);
    
    static final String GAME_TEXT = "Ship Bone Yard game";
    static final String DEFAULT_PROMPT = "> ";
    
    private static Log logUserInput = new Log();  // input logging setup
    
    /**
     * Constructor - set up JFRAME for Game Output
     */
    ShipBoneYardUI(Drop drop) {
        super(GAME_TEXT);
        // frame = new JFrame();
        JScrollPane scrollPane = new JScrollPane(gameTextArea);
        scrollPane.setPreferredSize(WINDOW_SIZE);

        gameTextArea.setLineWrap(true);
        gameTextArea.setWrapStyleWord(true);
        gameTextArea.setEditable(true);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        final JTextField userInputField = new JTextField(NUM_ROWS);
        
        userInputField.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    String fromUser = userInputField.getText();
                    if (fromUser != null) {

                        gameTextArea.append(fromUser + "\n\n");
                        gameTextArea.setCaretPosition(gameTextArea.getDocument().getLength());
                        userInputField.setText("");
                        System.out.println(fromUser);
                        drop.put(new String(fromUser));
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ev) {}
                        // String[] userInputArray = parser.parseInput(fromUser);
                        // player.parse(userInputArray);
                    }
                }
            }
        );
        this.setLayout(new FlowLayout());
        this.add(userInputField, SwingConstants.CENTER);
        this.add(scrollPane, SwingConstants.CENTER);
        this.setSize(FRAME_WIDTH, FRAME_LENGTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        
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

    /* static String getGameInput(String output) {
        String input = JOptionPane.showInputDialog(frame, output);
        logUserInput.logUserInput(input);
        return input;
    }
    static String getGameInput() {
        String input = JOptionPane.showInputDialog(frame, DEFAULT_PROMPT);
        logUserInput.logUserInput(input);
        return input;
    }
    */
    
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
