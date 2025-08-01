package Boundary;

import Control.ControlProprietario;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormAggiungiVaccinazione extends JFrame {
    private JPanel AggiungiVaccinazionePanel;
    private JButton aggiungiButton;
    private JLabel tipo;
    private JTextField fieldTipo;
    private JLabel dataLabel;
    private JTextField FieldData;
    private JButton indietroButton;

    //Definizione MACROs di utilità
    private static final String REGEX_CARATTERI_SPAZI = "^[a-zA-Z\\s]*$";
    private static final String REGEX_SOLO_CARATTERI = "^[a-zA-Z]*$";
    private static final String REGEX_DATA = "(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[1,2])\\/(19|20)\\d{2}";
    private static final int TIMER_DELAY = 250;
    private static final int TIMER_TOTAL_TIME = 1000;
    Timer timer = new Timer(TIMER_DELAY, null);

    public FormAggiungiVaccinazione(String panel, String chip) {
        super(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.AggiungiVaccinazionePanel.setBackground(new Color(25, 159, 214, 20));
        this.setContentPane(AggiungiVaccinazionePanel);
        this.pack();

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame frame = new FormAggiungiAnimale("FormAggiungiAnimale");
                frame.setVisible(true);
                frame.setSize(500, 500);
                setVisible(false);
            }
        });

        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
                try {
                    InserimentoVaccinazioneClickHandler(chip);
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void InserimentoVaccinazioneClickHandler(String chip) throws SQLException, ClassNotFoundException {
        //Definizione FORMATTER per effettuare parsing della data da passare
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //----- CHECK ERRORI -----
         boolean error = checkErrori();
        //----- FINE CHECK -----
        if(error){
            JOptionPane.showMessageDialog(null, "Ripetere inserimento.");
        } else {
            int result = ControlProprietario.InserimentoVaccinazione(fieldTipo.getText(), LocalDate.parse(FieldData.getText(), formatter), chip);
            JOptionPane.showMessageDialog(null, "Animale aggiunto con successo!");
        }
    }

    //Funzione che permette l'highlight intermittente di un campo di testo.
    //Utile per il check degli errori.
    private void TextFieldFlash(final JTextField field) {
        final int totalCount = FormAggiungiVaccinazione.TIMER_TOTAL_TIME / FormAggiungiVaccinazione.TIMER_DELAY;
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
    public  boolean checkErrori(){
        boolean error = false;
        if(!fieldTipo.getText().matches(REGEX_CARATTERI_SPAZI)) {
            error = true;
            fieldTipo.setText("ERRORE: Non ammessi numeri e/o caratteri speciali.");
            eraseText(fieldTipo);
            TextFieldFlash(fieldTipo);
        }
        if(fieldTipo.getText().isEmpty()) {
            error = true;
            fieldTipo.setText("ERRORE: Campo Vuoto.");
            eraseText(fieldTipo);
            TextFieldFlash(fieldTipo);
        }

        if(!FieldData.getText().matches(REGEX_DATA)) {
            error = true;
            FieldData.setText("ERRORE: Data in formato dd/MM/yyyy.");
            eraseText(FieldData);
            TextFieldFlash(FieldData);
        }
        if(FieldData.getText().isEmpty()) {
            error = true;
            FieldData.setText("ERRORE: Campo vuoto.");
            eraseText(FieldData);
            TextFieldFlash(FieldData);
        }

        return error;
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
