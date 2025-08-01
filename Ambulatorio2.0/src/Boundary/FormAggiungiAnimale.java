package Boundary;
import Control.ControlProprietario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.Objects;

public class FormAggiungiAnimale extends JFrame {
    private JPanel PanelAggiungiAnimale;
    private JTextField ChipAnimaleTextField;
    private JTextField tagliaTextField;
    private JTextField nomeTextField;
    private JTextField razzaTextField;
    private JTextField coloreTextField;
    private JButton aggiungiVaccinazioniButton;
    private JButton indietroButton;
    private JButton aggiungiSenzaVaccinazioniButton;

    private JOptionPane errorText;

    //Definizione MACRO di utilità
    private static final String REGEX_CHIP = "^[0-9]*$";
    private static final String REGEX_SOLO_CARATTERI = "^[a-zA-Z]*$";
    private static final String REGEX_CARATTERI_SPAZI = "^[a-zA-Z\\s]*$";
    private static final int  LENGHT_CHIP = 10;
    private static final int TIMER_DELAY = 250;
    private static final int TIMER_TOTAL_TIME = 1000;
    Timer timer = new Timer(TIMER_DELAY, null);


    public FormAggiungiAnimale(String panel) {
        super(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.PanelAggiungiAnimale.setBackground(new Color(25, 159, 214, 20));
        this.setContentPane(PanelAggiungiAnimale);
        this.pack();
         aggiungiSenzaVaccinazioniButton.addActionListener(new ActionListener() {
             //Funzione per aggiungere un animale al DB senza vaccinazioni
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AggiungiSenzaVaccinazioniClickHandler();
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Funzione per permettere all'utente di inserire le vaccinazioni all'animale
        //NOTA IMPORTANTE: Viene richiamato comunque a priori l'handler per aggiungere
        //l'animale al DB prima di poter aggiungere la vaccinazione, questo per vincoli
        //costruttivi del DB, dati dalle Foreign keys
        aggiungiVaccinazioniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!AggiungiSenzaVaccinazioniClickHandler())
                        AggiungiVaccinazioniClickHandler();
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        indietroButton.addActionListener(new ActionListener() {
            //Funzione per tornare alla pagina utente
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    IndietroClickHandler();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    //Funzioni principali
    boolean AggiungiSenzaVaccinazioniClickHandler() throws SQLException, ClassNotFoundException {
        //AccessoProprietario Caso d'uso, si richiama il control

        //----- CHECK ERRORI -----
        boolean error= checkErrori();

        if(error){
            //Se ci sono errori, viene richiesto di correggere
            JOptionPane.showMessageDialog(null, "Ripetere inserimento.");
        } else {
            //se no, viene chiamata la funzione InserimentoAnimale per aggiungere l'animale al database
            int result =  ControlProprietario.InserimentoAnimale(ChipAnimaleTextField.getText(), nomeTextField.getText(), razzaTextField.getText(), coloreTextField.getText(),tagliaTextField.getText());
            System.out.println(result);
            if(result != 0){

            JOptionPane.showMessageDialog(null, "Animale aggiunto con successo!");}
            else{
                JOptionPane.showMessageDialog(null, "Erorre aggiunta Animale!");}


        }
        return error;
    }

    private void AggiungiVaccinazioniClickHandler() throws SQLException, ClassNotFoundException {
        String chip = ChipAnimaleTextField.getText();
        Frame frame = new FormAggiungiVaccinazione("FormAggiungiVaccinazione", chip);
        frame.setVisible(true);
        frame.setSize(500, 500);
        setVisible(false);
    }

    private void IndietroClickHandler() throws ClassNotFoundException{
        Frame frame = new FormPaginaUtente("FormPaginaUtente");
        frame.setVisible(true);
        frame.setSize(500, 500);
        setVisible(false);

    }

    private boolean checkErrori(){
        boolean error = false;

        if(!ChipAnimaleTextField.getText().matches(REGEX_CHIP) || ChipAnimaleTextField.getText().length() != LENGHT_CHIP) {
            error = true;
            ChipAnimaleTextField.setText("ERRORE: Il chip è numerico di 10 cifre.");
            eraseText(ChipAnimaleTextField);
            TextFieldFlash(ChipAnimaleTextField);
        }

        if(nomeTextField.getText().isEmpty()){
            error = true;
            nomeTextField.setText("ERRORE: Campo vuoto.");
            eraseText(nomeTextField);
            TextFieldFlash(nomeTextField);
        }
        if(!nomeTextField.getText().matches(REGEX_SOLO_CARATTERI)) {
            error = true;
            nomeTextField.setText("ERRORE: Non ammessi numeri e/o caratteri speciali");
            eraseText(nomeTextField);
            TextFieldFlash(nomeTextField);
        }

        if(!razzaTextField.getText().matches(REGEX_CARATTERI_SPAZI)) {
            error = true;
            razzaTextField.setText("ERRORE: Non ammessi numeri e/o caratteri speciali");
            eraseText(razzaTextField);
            TextFieldFlash(razzaTextField);
        }
        if(razzaTextField.getText().isEmpty()) {
            error = true;
            razzaTextField.setText("ERRORE: Campo vuoto");
            eraseText(razzaTextField);
            TextFieldFlash(razzaTextField);
        }

        if(!coloreTextField.getText().matches(REGEX_CARATTERI_SPAZI)) {
            error = true;
            coloreTextField.setText("ERRORE: Non ammessi numeri e/o caratteri speciali.");
            eraseText(coloreTextField);
            TextFieldFlash(coloreTextField);
        }
        if(coloreTextField.getText().isEmpty()) {
            error = true;
            coloreTextField.setText("ERRORE: Campo vuoto.");
            eraseText(coloreTextField);
            TextFieldFlash(coloreTextField);
        }

        String inputText = tagliaTextField.getText().toLowerCase(); // Converti il testo in minuscolo
        if (!(inputText.equals("piccolo") || inputText.equals("medio") || inputText.equals("grande"))) {
            error = true;
            tagliaTextField.setText("ERRORE: Ammessi solo \"piccolo\", \"medio\", \"grande\".");
            eraseText(tagliaTextField);
            TextFieldFlash(tagliaTextField);
        }
        if(tagliaTextField.getText().isEmpty()) {
            error = true;
            tagliaTextField.setText("ERRORE: Campo vuoto.");
            eraseText(tagliaTextField);
            TextFieldFlash(tagliaTextField);
        }
        return error;
    }

    //Funzione che permette l'highlight intermittente di un campo di testo.
    //Utile per il check degli errori.
    private void TextFieldFlash(final JTextField field) {
        final int totalCount = FormAggiungiAnimale.TIMER_TOTAL_TIME / FormAggiungiAnimale.TIMER_DELAY;
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