package Boundary;
import Control.ControlAmministratore;
import Control.ControlProprietario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.util.Random;

public class FormRegistrazioneUtente extends JFrame {
    private JPanel FormRegistrazione;
    private JLabel Nome;
    private JTextField FieldNome;
    private JLabel Cognome;
    private JTextField FieldCognome;
    private JLabel Indirizzo;
    private JTextField FieldIndirizzo;
    private JLabel Numero_di_telefono;
    private JTextField FieldNumero_di_telefono;
    private JButton indietroButton;
    private JButton Registra;

    //Definizione MACROs di utilità
    private static final int MAX_NUMERO_LENGHT = 10;
    private static final int MAX_NOME_LENGHT = 15;
    private static final int MAX_COGNOME_LENGHT = 20;
    private static final int MAX_INDIRIZZO_LENGHT = 50;
    private static final String REGEX_NUMERO = "^[0-9]*$";
    private static final int TIMER_DELAY = 250;
    private static final int TIMER_TOTAL_TIME = 1000;
    Timer timer = new Timer(TIMER_DELAY, null);


    public FormRegistrazioneUtente(String panel) {
        super(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.FormRegistrazione.setBackground(new Color(25, 159, 214, 20));
        this.setContentPane(FormRegistrazione);
        this.pack();
        ControlAmministratore Amministratore = new ControlAmministratore();

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new FormPaginaAmministratore("Pagina Amministratore");
                frame.setVisible(true);
                frame.setSize(500, 500);
                setVisible(false);
            }
        });

        Registra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean error = checkErrori();

                if (error) {
                    JOptionPane.showMessageDialog(null, "Ripetere inserimento.");
                } else {
                    String Username = Amministratore.Create_Username(FieldNome.getText(), FieldCognome.getText(), FieldNumero_di_telefono.getText());
                    String Password = Amministratore.Create_Password(Username);
                    if (ControlAmministratore.RegistraUtente(Username, Password, FieldNome.getText(), FieldCognome.getText(), FieldNumero_di_telefono.getText(), FieldIndirizzo.getText())) {
                        JOptionPane.showMessageDialog(null,
                                "Username: " + Username + "\nPassword: " + Password);
                    } else {
                        JOptionPane.showMessageDialog(null, "ERRORE: Proprietario già presente.");
                    }

                }
            }
        });
    }

            private boolean checkErrori() {
                boolean error = false;

                if (FieldNome.getText().isEmpty()) {
                    error = true;
                    FieldNome.setText("ERROR: Campo vuoto.");
                    eraseText(FieldNome);
                    TextFieldFlash(FieldNome);
                }
                if (FieldNome.getText().length() > MAX_NOME_LENGHT) {
                    error = true;
                    FieldNome.setText("ERROR: Nome troppo lungo. Max caratteri consentiti: " + MAX_NOME_LENGHT);
                    eraseText(FieldNome);
                    TextFieldFlash(FieldNome);
                }

                if (FieldCognome.getText().isEmpty()) {
                    error = true;
                    FieldCognome.setText("ERROR: Campo vuoto.");
                    eraseText(FieldCognome);
                    TextFieldFlash(FieldCognome);
                }
                if (FieldCognome.getText().length() > MAX_COGNOME_LENGHT) {
                    error = true;
                    FieldCognome.setText("ERROR: Cognome troppo lungo. Max caratteri consentiti: " + MAX_COGNOME_LENGHT);
                    eraseText(FieldCognome);
                    TextFieldFlash(FieldCognome);
                }

                if (FieldIndirizzo.getText().isEmpty()) {
                    error = true;
                    FieldIndirizzo.setText("ERROR: Campo vuoto.");
                    eraseText(FieldIndirizzo);
                    TextFieldFlash(FieldIndirizzo);
                }
                if (FieldIndirizzo.getText().length() > MAX_INDIRIZZO_LENGHT) {
                    error = true;
                    FieldIndirizzo.setText("ERROR: Indirizzo troppo lungo. Max caratteri consentiti: " + MAX_INDIRIZZO_LENGHT);
                    eraseText(FieldIndirizzo);
                    TextFieldFlash(FieldIndirizzo);
                }

                if (FieldNumero_di_telefono.getText().isEmpty()) {
                    error = true;
                    FieldNumero_di_telefono.setText("ERROR: Campo vuoto.");
                    eraseText(FieldNumero_di_telefono);
                    TextFieldFlash(FieldNumero_di_telefono);
                }
                if (FieldNumero_di_telefono.getText().length() > MAX_NUMERO_LENGHT) {
                    error = true;
                    FieldNumero_di_telefono.setText("ERROR: Numero troppo lungo. Max caratteri consentiti: " + MAX_NUMERO_LENGHT + ".");
                    eraseText(FieldNumero_di_telefono);
                    TextFieldFlash(FieldNumero_di_telefono);
                }
                if (!FieldNumero_di_telefono.getText().matches(REGEX_NUMERO)) {
                    error = true;
                    FieldNumero_di_telefono.setText("ERROR: Il numero non deve contenere caratteri.");
                    eraseText(FieldNumero_di_telefono);
                    TextFieldFlash(FieldNumero_di_telefono);
                }

                return error;
            }

            //Funzione che permette l'highlight intermittente di un campo di testo.
            //Utile per il check degli errori.
            private void TextFieldFlash(final JTextField field) {
                final int totalCount = FormRegistrazioneUtente.TIMER_TOTAL_TIME / FormRegistrazioneUtente.TIMER_DELAY;
                timer.addActionListener(new ActionListener() {
                    int count = 0;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (count % 2 == 0) {
                            field.setBackground(Color.RED);
                        } else {
                            field.setBackground(Color.WHITE);
                           if (count >= totalCount) {
                             field.setBackground(Color.WHITE);
                             timer.stop();
                        }
                    }
                    count++;
                }
            });
            timer.start();
    }

    //Funzione di utilità per effettuare il clear di un textField attraverso un click
    //NOTA: funziona solo per pulire il testo dopo un checkErrori
    void eraseText(JTextField dummy) {
        FocusListener reset = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(dummy.getText().startsWith("ERRORE"))
                    dummy.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                //USELESS
            }
        };
        dummy.addFocusListener(reset);
        if(dummy.getText().isEmpty())
            dummy.removeFocusListener(reset);
    }
}
