/**
 * Created by IntelliJ IDEA
 * Author: Nkem Ohanenye
 * Date: 09/30/19
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

public class Finder {

    // The ReadAndWrite Functions
    private String fileN = "yuubu";
    private String fileNc;
    private String write = "";
    private String dir = "src/Resources/";
    private String pt = ".txt";

    private File file = new File(dir + fileN + pt);

    //The buttons in the JOptionPane
    private JButton oW = new JButton("Overwrite");
    private JButton aO = new JButton("Add On");
    private JButton c = new JButton("Cancel");
    private JButton close = new JButton("Ok");

    //The Dialog used to have the ability to close the pane rather than exiting the program
    private JDialog fileFoundDialog;

    Finder() throws FileNotFoundException, IOException{

        //Frame Settings
        JFrame fileFoundFrame = new JFrame();
        fileFoundDialog = new JDialog(fileFoundFrame, "Confirm Overwrite of: " + fileN, true);

        //Main Button Settings
        oW.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    //file.delete();
                } catch (Exception e) {

                }
                fileFoundDialog.dispose();
                System.exit(0);
            }
        });
        aO.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!file.exists()) {
                    fileFoundDialog.dispose();
                    JOptionPane pane = new JOptionPane(
                            JOptionPane.showConfirmDialog(
                                    null,
                                    "The file could not be written.",
                                    "Warning",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.WARNING_MESSAGE));

                    // recreates the closed dialog as if in while loop
                    fileFoundDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    fileFoundDialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    fileFoundDialog.setLocationRelativeTo(fileFoundFrame);
                    fileFoundDialog.setVisible(true);
                } else {
                    int i = Integer.parseInt(fileN.replaceAll("[\\D]", "") + 0);
                    if (i >= 1){
                        //fileN = fileN + "(" + i++ + ")";
                    } else{
                        fileN = fileN + "(" + (i+1) + ")";
                        try {
                            System.out.println(fileN);
                            if (!file.exists()) file.createNewFile();
                        } catch (Exception e) {

                        }
                    }
                }
                fileFoundDialog.dispose();
                System.exit(0);
            }
        });
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        //If A File Exists Settings
        if(Found()){

            Object[] buttons = new Object[]{oW, aO, c};
            JOptionPane pane = new JOptionPane(
                    "The file already exists.",
                    JOptionPane.WARNING_MESSAGE,
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    null,
                    buttons,
                    buttons[0]);

            fileFoundDialog.getContentPane().add(pane);     //Allows the pane to be edited by the Dialog
            fileFoundDialog.setSize(405,120);
            fileFoundDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fileFoundDialog.addWindowListener(new WindowAdapter() {     //Clicking x stops program
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            fileFoundDialog.setLocationRelativeTo(fileFoundFrame);      //keeps frame in the middle of the screen
            fileFoundDialog.setVisible(true);

        }else{
            fileFoundDialog.dispose();
            System.exit(0);
        }
    }
    //works
    boolean Found() throws FileNotFoundException, IOException{
        if (file.exists()) { //prints once
            System.out.println("found!");
            //new Finder();
        } else { //prints twice for some reason
            System.out.println("not found!");
            return false;
        }
        return true;
    }

    public static void main (String args[]) throws FileNotFoundException, IOException{
       Finder f = new Finder();
       f.Found();
    }
}
