package com.kostenko.hash.gui;

import com.kostenko.hash.functions.HashFunction;
import com.kostenko.hash.functions.PearsonHash;
import com.kostenko.hash.sum.HashSum;
import com.kostenko.hash.sum.LRCHashSum;
import com.kostenko.hash.sum.FAQ6HashSum;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;

public class MainWindow extends JFrame {
    private JTextArea textAreaResult;
    private JPanel JPanelMain;
    private JButton getPearsonHashButton;
    private JButton getLRCSumButton;
    private JButton openFileButton;
    private JButton getFAQ6SumButton;

    private File current;
    private byte[] bufferArray;

    public MainWindow() {
        setContentPane(JPanelMain);
        setMinimumSize(JPanelMain.getMinimumSize());
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setListeners();
        getFAQ6SumButton.setEnabled(false);
        getLRCSumButton.setEnabled(false);
        getPearsonHashButton.setEnabled(false);
    }

    private void setListeners() {
        openFileButton.addActionListener(new ActionListener() {
                                             @Override
                                             public void actionPerformed(ActionEvent e) {
                                                 JFileChooser fileChooser = new JFileChooser();
                                                 if (fileChooser.showOpenDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION) {
                                                     current = new File(fileChooser.getSelectedFile().toString());
                                                 }
                                                 MainWindow.this.readFile();
                                             }
                                         }
        );
        getPearsonHashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bufferArray.length > 0) {
                    HashFunction f = new PearsonHash();
                    int[] res = f.hash(bufferArray);
                    textAreaResult.setText(Arrays.toString(res));
                }
            }
        });
        getLRCSumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bufferArray.length > 0) {
                    HashSum f = new LRCHashSum();
                    MainWindow.this.getHashSum(f);
                }
            }
        });
        getFAQ6SumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bufferArray.length > 0) {
                    HashSum f = new FAQ6HashSum();
                    MainWindow.this.getHashSum(f);
                }
            }
        });
    }

    private void getHashSum(HashSum f) {
        long res = f.hash(bufferArray);
        textAreaResult.setText(res + "");
    }

    private void readFile() {
        if (current != null) {
            try (DataInputStream dis = new DataInputStream(new FileInputStream(current))) {
                int bufferLength = (int) current.length();
                bufferArray = new byte[bufferLength];
                dis.readFully(bufferArray);
            } catch (IOException ex) {
                System.out.println(ex.toString());
                return;
            }
            getFAQ6SumButton.setEnabled(true);
            getLRCSumButton.setEnabled(true);
            getPearsonHashButton.setEnabled(true);
        }
    }
}
