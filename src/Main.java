package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main{
    private JLabel title;
    private JLabel cipher2;
    private JLabel plain;
    private JTextField cipherD;
    private JTextField keyD;
    private JLabel key2InputField;
    private JButton DE;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l5;
    private JTextField plaintextE;
    private JTextField keyE;
    private JLabel cipherE;
    private JButton PEE;
    private JButton EE;
    private JLabel decrypt;
    private JLabel encrypt;
    private JLabel ciphertext;
    private JLabel pleaseInput10BitLabel;
    private JPanel panel;
    private JLabel l7;
    private JLabel plainD;

    public String plaintxtE = new String(); //用户输入明文

    public String keytxtE = new String(); //用户输入key

    public String ciphertxtD = new String(); //用户输入密文

    public String keytxtD = new String(); //用户输入key


    public Main() {
        EE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plaintxtE = plaintextE.getText();
                System.out.println("明文是："+plaintxtE);
                keytxtE = keyE.getText();
                System.out.println("10-bit Key = "+keytxtE);
                keyScheduler key = new keyScheduler(keytxtE);
                S_DES sDes = new S_DES(plaintxtE, key.getKeys(), 1);
                cipherE.setText(sDes.getResultCipher());
            }
        });
        DE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ciphertxtD = cipherD.getText();
                System.out.println("密文是："+ciphertxtD);
                keytxtD = keyD.getText();
                System.out.println("10-bit Key = "+keytxtD);
                keyScheduler key = new keyScheduler(keytxtD);
                S_DES Des = new S_DES(ciphertxtD, key.getKeys(), 2);
                cipherD.setText(Des.getResultPlain());
                plainD.setText(Des.getResultPlain());
            }
        });
    }

    public static void main(String[] args) {

        Main main = new Main();
        JFrame frame = new JFrame("Main");
        frame.setContentPane(main.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
