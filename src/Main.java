package src;

import javax.swing.*;

public class Main{
    private JLabel title;
    private JLabel ciphertextD;
    private JLabel cipher2;
    private JLabel plain;
    private JTextField cipherD;
    private JTextField keyD;
    private JLabel key2InputField;
    private JButton CDE;
    private JButton KED;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l5;
    private JTextField plaintextE;
    private JTextField keyE;
    private JLabel cipherE;
    private JButton PEE;
    private JButton KEE;
    private JLabel decrypt;
    private JLabel encrypt;
    private JLabel ciphertext;
    private JLabel pleaseInputPlaintextLabel;
    private JLabel pleaseInput10BitLabel;
    private JPanel panel;

    public static void main(String[] args) {

        char[] a={'1','1','0','1','0','1','1','0','0','0'};
        char[] p=new char[]{'1','1','0','1','0','1','1','0'};
        keyScheduler key = new keyScheduler(a);
        String[] b =new String[2];
        b=key.getKeys();
        S_DES sDes = new S_DES(p, b);



        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
