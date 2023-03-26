import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.PreparedStatement;

public class TextEditor implements ActionListener {

    JFrame jFrame;

    JMenuBar menuBar;

    JMenu file, edit;

    // File menu item
    JMenuItem newFile, openFile, saveFile;

    // Edit menu item
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;

    TextEditor(){
        // Initialize a frame
        jFrame = new JFrame();

        // Initialize menubar
        menuBar = new JMenuBar();

        // Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Initialize File Menu Items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        // Initialize Edit Menu Items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // Initialize Textarea
        textArea = new JTextArea();


        // Add action listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // Add action listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Add file menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // Add edit menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);

        // Set menubar to frame
        jFrame.setJMenuBar(menuBar);

        // Create Content Panel
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        // Add textarea to the panel
        panel.add(textArea, BorderLayout.CENTER);

        // Create Scroll Pane
        JScrollPane jScrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add scroll pane to the panel
        panel.add(jScrollPane);

        // Add panel to the frame
        jFrame.add(panel);

        jFrame.setBounds(100,100,400,400);
        jFrame.setVisible(true);
        jFrame.setTitle("Text Editor");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut){
            // Perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            // Perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            // Perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            // Perform select all operation
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            // Perform close operation
            System.exit(0);
        }
        if(actionEvent.getSource() == openFile){
            // Perform Open File Operation
            // Open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            // If we have clicked on open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // Get selected file
                File file = fileChooser.getSelectedFile();

                // Get the path of the selected file
                String filePath = file.getPath();

                try {
                    // Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    // Initialize buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String intermediateLine = "", result = "";
                    while ((intermediateLine = bufferedReader.readLine()) != null) {
                        result += intermediateLine + "\n";
                    }

                    textArea.setText(result);
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }

            }
        }
        if(actionEvent.getSource() == saveFile){
            // Perform save file operation
            // Open file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            // Get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // If we have clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // Create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                try{
                    // Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    // Initialize buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    // Write contents of textarea to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource() == newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}