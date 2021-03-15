import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import  java.io.FileWriter;
import java.io.IOException;


public class Editor implements ActionListener {
    JTextArea textarea;
    JFrame frame;
    JMenuBar menubar;
    Editor()
    {
        //creating instance of JFrame with the name "editor"
        frame = new JFrame("Text Editor");

        //set close operation to close the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set frame size
        frame.setSize(500,500);

        //Instantiating JMenuBar
        menubar = new JMenuBar();

        //Instantating menu objects
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");

        //creating the menu items
        JMenuItem nu,save,open,print,cut,copy,paste;
        nu = new JMenuItem("New");
        save = new JMenuItem("Save");
        open = new JMenuItem("Open");
        print = new JMenuItem("Print");
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");

        //add the menu items to the correct menu
        file.add(nu);
        file.add(save);
        file.add(open);
        file.add(print);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);

        //add the menu objects to the menu bars
        menubar.add(file);
        menubar.add(edit);

        //set the menu bar
        frame.setJMenuBar(menubar);

        //Add action listeners to menu items
        nu.addActionListener(this);
        save.addActionListener(this);
        print.addActionListener(this);
        open.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);

        //creating a text area
        textarea = new JTextArea();
        //adding text area to the frame
        frame.add(textarea);

        //make the frame visible
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
      String s = e.getActionCommand();
        String path_of_file = null;
      //System.out.println(s+" => "+" selected ");

        if(s.equals("New")){
            textarea.setText("");
        }
        else if(s.equals("Save")) {
            //creating an object of JFileChooser class
            JFileChooser filechooser = new JFileChooser(String.valueOf(JFileChooser.FILES_AND_DIRECTORIES));
            int show = filechooser.showSaveDialog(null);

            //if user selects a file
            if (show == JFileChooser.APPROVE_OPTION) {
                path_of_file = filechooser.getSelectedFile().getAbsolutePath();

            //System.out.println("The file path==> "+path_of_file);

            //attaching the file to FileWriter
            try {
                //creating a file writer
                FileWriter filewriter = new FileWriter(path_of_file);
                //creating buffered writer
                BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
                bufferedwriter.write(textarea.getText());
                bufferedwriter.flush();
                bufferedwriter.close();

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
            else
            {
                System.out.println("User has cancelled the action");
            }

        }

    }
    public static void main(String args[])
    {
        Editor e = new Editor();
    }
}
