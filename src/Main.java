package src;

import javax.swing.*;

public class Main{
    private JTextField plaintextInputField;
    private JTextField keyInputField;
    private JButton plaintextConfirmBtn;
    private JButton keyConfirmBtn;
    private JLabel ciphertext;
    private JLabel title;
    private JLabel enter1;
    private JLabel enter2;
    private JLabel cipher;
    private JLabel Dciphertext;
    private JLabel cipher2;
    private JLabel plain;
    private JTextField ciphertextInputField;
    private JTextField key2textInputField;
    private JLabel key2InputField;
    private JButton enter3;
    private JButton enter4;

    public static void main(String[] args){
          char[] a={'1','1','0','1','0','1','1','0','0','0'};
          char[] p=new char[]{'1','1','0','1','0','1','1','0'};
          keyScheduler key = new keyScheduler(a);
          String[] b =new String[2];
          b=key.getKeys();
          S_DES sDes = new S_DES(p, b);

      }

} 
