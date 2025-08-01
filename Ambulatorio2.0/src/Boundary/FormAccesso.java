package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static Control.ControlSistema.Login;
import Control.ControlProprietario;

public class FormAccesso extends JFrame {
    private JPanel FormLogin;
    private JLabel Username;
    private JTextField FieldUsername;
    private JLabel Password;
    private JTextField FieldPassword;
    private JButton accedi;

    public static void main(String[] args) {
        //Creazione pannello principale per l'accesso
        JFrame control = new FormAccesso("FormMainWindow");
        control.setVisible(true);
        control.setSize(500,500);
    }

    public FormAccesso (String panel){

        super(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.FormLogin.setBackground(new Color(25, 159, 214, 20));
        this.setContentPane(FormLogin);
        this.pack();

        accedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { AccediActionListener(); }
        });

        //Listener per poter accedere direttamente premendo il tasto "Invio"
        FieldPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    accedi.doClick();
                 }
            }
        });
    }

    //Funzione per l'accesso al sistema
    private void AccediActionListener(){
        System.out.println(FieldPassword.getText() + " " + FieldUsername.getText());
        Login(FieldUsername.getText(), FieldPassword.getText());
        ControlProprietario.saveUsername(FieldUsername.getText());
        setVisible(false);
    }
}


