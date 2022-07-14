package wordCounter;
import java.awt.event.*; 
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

// wordCounterMain extends from JFrame and implements ActionListener to make it easier to declare JFrame and actions for buttons
public class wordCounterMain extends JFrame implements ActionListener{
    // Declare variable names
    JTextArea textArea;
    JButton button1, button2, fileButton;
    JScrollPane scrollableTA;
    JLabel textLabel;
    
    /*
       Declare wordCounterSwing 
    */
    public wordCounterMain(){
        // Declare JFrame instance using super
        super("JAVA Word Counter");
            
        // Set flow layout for JFrame
        this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));  
        
        // Declare JLabel instance
        textLabel = new JLabel("Type into the text box or ONLY choose a text file");
        textLabel.setBounds(15, 15, 550, 30);
        
        // Declare JTextArea instance
        textArea = new JTextArea(32, 62); 
        textArea.setLineWrap(true); 
        textArea.setWrapStyleWord(true);
        
        // Declare JButton instances
        button1 = new JButton("Word Count");
        button1.setBounds(15, 430, 125, 30);
        
        button2 = new JButton("Character Count");
        button2.setBounds(155, 430, 150, 30);
        
        fileButton = new JButton("Pick a file");
        fileButton.setBounds(320, 430, 150, 30);
        
        // Declare JScrollPane instance for scrolling vertically in the text area
        scrollableTA = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // call ActionListener for buttons
        button1.addActionListener(this);  
        button2.addActionListener(this);  
        fileButton.addActionListener(this);
        
        // Add the declared J_Elements to JFrame 
        this.add(button1); this.add(button2); this.add(fileButton);
        this.add(textLabel);
        this.getContentPane().add(scrollableTA);

        // Set the configurations for JFrame
        this.setSize(600,600); 
        this.setResizable(false);
        this.setVisible(true);    
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
    
    // Declare actionPerformed method to perform an action when button is clicked
    public void actionPerformed(ActionEvent e){
        // Declare variables
        String textFromLabel = textArea.getText();
        
        // Declare if statement to get words from text area
        if (e.getSource() == button1){
            String words[] = textFromLabel.split("\\s"); // Splits the words and assigns them to wordS Array
            JOptionPane.showMessageDialog(this, "Total words: " + words.length); // Calls on message dialog to display the number of words
        }
        
        // Declare If statement to get characters from text area
        if (e.getSource() == button2){
            JOptionPane.showMessageDialog(this, "Total Characters with space: " + textFromLabel.length()); // Calls on message dialog to display the number of characters
        }
        
         // Declare If statement to allow the user to select a file 
        if (e.getSource() == fileButton){
            // Declare JFileChooser instance
            JFileChooser fileChooser = new JFileChooser();
            
            // Declare FileFilter
            FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            fileChooser.setFileFilter(fileFilter);
            
            // Declare fileGUI to open the fileChooser GUI
            int fileGUI = fileChooser.showOpenDialog(this);
            
            // Declare If statement to see if the file chosen is compatiable
            if (fileGUI == JFileChooser.APPROVE_OPTION) {
                // Declare variables for file
                File chosenFile = fileChooser.getSelectedFile();
                String filePath = chosenFile.getPath();
                
                // Declare Try-Catch statement to read file
                try {
                    // Declare BufferedReader to read the file
                    BufferedReader bufferReader = new BufferedReader(new FileReader(filePath));
                    
                    // Declare strings to hold contents from the file
                    String fileContent1 = "", fileContent2 = "";
                    
                    // Declare while loop to read the entire file until it reaches the EOF
                    while ((fileContent1 = bufferReader.readLine())!= null){
                        fileContent2 += fileContent1 + "\n"; 
                    }
                    
                    // Assigns the string fileContent2 to the text in textArea 
                    textArea.setText(fileContent2);
                    
                    // Close BufferedReader
                    bufferReader.close();
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                } // End Try-Catch
            } // End If statement
        } 
        
    } // End actionPerformed
    
    // main
    public static void main(String[] args) {  
        new wordCounterMain(); // Call wordCounterMain
        
    } // End main

} // End of class
