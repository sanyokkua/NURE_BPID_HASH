package ua.nure.bpid.hash.GUI;

import ua.nure.bpid.hash.Functions.HashFunction;
import ua.nure.bpid.hash.Functions.PearsonHash;
import ua.nure.bpid.hash.Sum.FAQ6HashSum;
import ua.nure.bpid.hash.Sum.HashSum;
import ua.nure.bpid.hash.Sum.LRCHashSum;

import javax.swing.*;
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
        openFileButton.addActionListener((e) -> {
                    JFileChooser fileChooser = new JFileChooser();
                    if (fileChooser.showOpenDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION) {
                        current = new File(fileChooser.getSelectedFile().toString());
                    }
                    readFile();
                }
        );
        getPearsonHashButton.addActionListener((e) -> {
            if (bufferArray.length > 0) {
                HashFunction f = new PearsonHash();
                int[] res = f.hash(bufferArray);
                textAreaResult.setText(Arrays.toString(res));
            }
        });
        getLRCSumButton.addActionListener((e) -> {
            if (bufferArray.length > 0) {
                HashSum f = new LRCHashSum();
                getHashSum(f);
            }
        });
        getFAQ6SumButton.addActionListener((e) -> {
            if (bufferArray.length > 0) {
                HashSum f = new FAQ6HashSum();
                getHashSum(f);
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
            } catch (FileNotFoundException ex) {
                System.out.println(ex.toString());
                return;
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
