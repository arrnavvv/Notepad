import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {
    JMenuBar menuBar;
    JMenu fileMenu,editMenu,helpMenu;
    JMenuItem newItem,openItem,saveItem,printItem,exitItem;
    JMenuItem copyItem,pasteItem,cutItem,selectAllItem,aboutItem;
    JTextArea textArea;
    JScrollPane scrollPane;
    String text;


    Notepad(){
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        newItem = new JMenuItem("New");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        newItem.addActionListener(this);
        openItem = new JMenuItem("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        openItem.addActionListener(this);
        saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        saveItem.addActionListener(this);
        printItem = new JMenuItem("Print");
        printItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        printItem.addActionListener(this);
        exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        exitItem.addActionListener(this);
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(printItem);
        fileMenu.add(exitItem);


        editMenu = new JMenu("Edit");
        copyItem = new JMenuItem("Copy");
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        copyItem.addActionListener(this);
        pasteItem = new JMenuItem("Paste");
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        pasteItem.addActionListener(this);
        cutItem = new JMenuItem("Cut");
        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        cutItem.addActionListener(this);
        selectAllItem = new JMenuItem("Select All");
        selectAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        selectAllItem.addActionListener(this);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(cutItem);
        editMenu.add(selectAllItem);


        helpMenu = new JMenu("Help");
        aboutItem = new JMenuItem("About the Notepad");
        aboutItem.addActionListener(this);
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);


        this.setBounds(0,0,1950,1050);
        this.setTitle("My Notepad");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
        textArea.setLineWrap(true);
        textArea.setBackground(Color.darkGray);
        textArea.setForeground(Color.white);
        textArea.setCaretColor(Color.white);
        textArea.setWrapStyleWord(true);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setViewportView(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollPane);


        this.setPreferredSize(new Dimension(600,600));
        this.setJMenuBar(menuBar);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == newItem){
            textArea.setText("");
        }else if(e.getSource()== openItem){
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files","txt");
            chooser.addChoosableFileFilter(restrict);
            chooser.setApproveButtonText("Open");
            int action = chooser.showOpenDialog(this);
            if(action!=JFileChooser.APPROVE_OPTION){
                return;
            }
            File fileName = chooser.getSelectedFile();
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(fileName));
                textArea.read(bufferedReader,null);

            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

        }else if(e.getSource()==saveItem){
            JFileChooser chooser = new JFileChooser();
            chooser.setApproveButtonText("Save");
            int action = chooser.showSaveDialog(this);
            if(action!=JFileChooser.APPROVE_OPTION){
                return;
            }

            File filename = new File(chooser.getSelectedFile() + ".txt");
            BufferedWriter bufferedWriter = null;

            try {
                bufferedWriter = new BufferedWriter(new FileWriter(filename));
                textArea.write(bufferedWriter);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }else if(e.getSource()==printItem){
            try {
                textArea.print();
            } catch (PrinterException printerException) {
                printerException.printStackTrace();
            }
        }else if(e.getSource()==exitItem){
            System.exit(0);
        }else if(e.getSource()==copyItem){
            text = textArea.getSelectedText();
        }else if(e.getSource()==pasteItem){
        textArea.insert(text,textArea.getCaretPosition());
    }else if(e.getSource()==cutItem){
            text = textArea.getSelectedText();
            textArea.replaceRange("",textArea.getSelectionStart(),textArea.getSelectionEnd());
        }else if(e.getSource()==selectAllItem){
            textArea.selectAll();
        }else if(e.getSource() == aboutItem){
                new About();
        }

    }
}
