package Boundary;

import Control.ControlAmministratore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FormAssociaPrescrizione extends JFrame {
    private JPanel FormPrescrizione;
    private JButton associaPrescrizioneButton;
    private JButton indietroButton;
    private JLabel dataLabel;
    private JLabel costoLabel;
    private JLabel diagnosiLabel;
    private JLabel cureMedicheLabel;
    private DefaultTableModel farmaciTableModel;
    private JTextField dataVisitaTextField;
    private JTextField costoVisitaTextField;
    private JTextField diagnosiTextField;
    private JLabel Diagnosi;
    private JTextField cureMedichePrestateTextField;
    private JButton aggiungiFarmacoButton;
    private JTextField nomeFarmacoTextField;
    private JTextArea textArea1;
    private JTextField nomeProduttoreTextField;
    private JLabel farmacoLabel;

    //Definizione "MACRO" di utilità
    private static final int MAX_DIAGNOSI_LENGHT = 100;
    private static final int MAX_CURE_LENGHT = 100;
    private static final int MAX_FARMACO_NOME_LENGHT = 30;
    private static final int MAX_PRODUTTORE_LENGHT = 20;
    private static final int TIMER_DELAY = 250;
    private static final int TIMER_TOTAL_TIME = 1000;
    Timer timer = new Timer(TIMER_DELAY, null);



    public FormAssociaPrescrizione(String panel) {
        super(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.FormPrescrizione.setBackground(new Color(25, 159, 214, 20));
        this.setContentPane(FormPrescrizione);
        this.pack();
        diagnosiTextField.setText("Diagnosi");
        costoVisitaTextField.setText("Costo");
        dataVisitaTextField.setText("yyyy-mm-dd hh:mm");
        cureMedichePrestateTextField.setText("Cure mediche");
        HashMap<String, String > listaFarmaci = new HashMap<>();
        //Stampare a testo le visite senza prescrizione
        StampaVisite();

        addPlaceHolderStyle(nomeFarmacoTextField);
        addPlaceHolderStyle(nomeProduttoreTextField);

        nomeFarmacoTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(nomeFarmacoTextField.getText().equals("Nome Farmaco")) {
                    nomeFarmacoTextField.setText("");
                    nomeFarmacoTextField.requestFocus();
                    removePlaceHolderStyle(nomeFarmacoTextField);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(nomeFarmacoTextField.getText().isEmpty()) {
                    addPlaceHolderStyle(nomeFarmacoTextField);
                    nomeFarmacoTextField.setText("Nome Farmaco");
                }
            }
        });

        nomeProduttoreTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(nomeProduttoreTextField.getText().equals("Nome Produttore")) {
                    nomeProduttoreTextField.setText("");
                    nomeProduttoreTextField.requestFocus();
                    removePlaceHolderStyle(nomeProduttoreTextField);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(nomeProduttoreTextField.getText().isEmpty()) {
                    addPlaceHolderStyle(nomeProduttoreTextField);
                    nomeProduttoreTextField.setText("Nome Produttore");
                }
            }
        });

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new FormPaginaAmministratore("Pagina Amministratore");
                frame.setVisible(true);
                frame.setSize(500, 500);
                setVisible(false);
            }
        });

        aggiungiFarmacoButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String NomeFarmaco = nomeFarmacoTextField.getText();
               String Produttore = nomeProduttoreTextField.getText();
               boolean error = checkErroriFarmaco();

               if (error) {
                   JOptionPane.showMessageDialog(null, "Ripetere inserimento Farmaco.");
               } else {
                   listaFarmaci.put(NomeFarmaco, Produttore);
                   System.out.println("Aggiunto farmaco:" + NomeFarmaco + " - " + Produttore);
                   nomeFarmacoTextField.setText("Nome Farmaco");
                   nomeProduttoreTextField.setText("Produttore");
               }
           }
       });

        associaPrescrizioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //----- CHECK ERRORI -----
                boolean error = checkErrori();
                if(error) {
                    JOptionPane.showMessageDialog(null, "Ripetere inserimento.");
                } else {
                    ControlAmministratore.AssociaPrescrizione(diagnosiTextField.getText(),costoVisitaTextField.getText(), dataVisitaTextField.getText(),cureMedichePrestateTextField.getText(),listaFarmaci);
                    JOptionPane.showMessageDialog(null, " Prescrizione Associata.");


                }
                if(!listaFarmaci.isEmpty()){
                for (Map.Entry<String, String> entry : listaFarmaci.entrySet()) {
                    String nomeFarmaco = entry.getKey();
                    String produttore = entry.getValue();
                    ControlAmministratore.aggiungiFarmaco(nomeFarmaco, produttore, dataVisitaTextField.getText());
                 }
                }
                diagnosiTextField.setText("Diagnosi");
                costoVisitaTextField.setText("Costo");
                dataVisitaTextField.setText("yyyy-mm-dd hh:mm");
                cureMedichePrestateTextField.setText("Cure mediche");
            }
        });
    }

    //Funzione di utilità per piazzare un font ai testi placeholder all'interno dei TextField
    private void addPlaceHolderStyle(JTextField field) {
        Font font = field.getFont();
        font = font.deriveFont(Font.ITALIC);
        field.setFont(font);
        field.setForeground(Color.GRAY);
    }
    //Duale della funzione precedente
    private void removePlaceHolderStyle(JTextField field) {
        Font font = field.getFont();
        font = font.deriveFont(Font.PLAIN | Font.BOLD);
        field.setFont(font);
        field.setForeground(Color.BLACK);
    }

    private void StampaVisite(){
        ArrayList<String> listaVisite = ControlAmministratore.VisiteSenzaPrescrizione();
        if (listaVisite.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Non ci sono visite senza prescrizione! ");
        }
        textArea1.append("Visite Senza Prescrizione: \n");
        for (String visita : listaVisite) {
            textArea1.append("Visita: " + visita.substring(0,visita.length()-3) + "\n");
            textArea1.append("------------------\n");
        }
    }

    //Funzione che permette l'highlight intermittente di un campo di testo.
    //Utile per il check degli errori.
    private void TextFieldFlash(final JTextField field) {
        final int totalCount = FormAssociaPrescrizione.TIMER_TOTAL_TIME / FormAssociaPrescrizione.TIMER_DELAY;
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

        if(diagnosiTextField.getText().isEmpty()) {
            error = true;
            diagnosiTextField.setText("ERRORE: Campo vuoto.");
            eraseText(diagnosiTextField);
            TextFieldFlash(diagnosiTextField);
        }
        if(diagnosiTextField.getText().length() > MAX_DIAGNOSI_LENGHT) {
            error = true;
            diagnosiTextField.setText("ERRORE: Testo troppo lungo. Max caratteri consentiti: " + MAX_DIAGNOSI_LENGHT);
            eraseText(diagnosiTextField);
            TextFieldFlash(diagnosiTextField);
        }

        if(Double.parseDouble(costoVisitaTextField.getText()) < 0) {
            error = true;
            costoVisitaTextField.setText("ERRORE: Il costo deve essere un intero positivo.");
            eraseText(costoVisitaTextField);
            TextFieldFlash(costoVisitaTextField);
        }

        if(cureMedichePrestateTextField.getText().isEmpty()){
            error = true;
            cureMedichePrestateTextField.setText("ERRORE: Campo vuoto.");
            eraseText(cureMedichePrestateTextField);
            TextFieldFlash(cureMedichePrestateTextField);
        }
        if(cureMedichePrestateTextField.getText().length() > MAX_CURE_LENGHT) {
            error = true;
            cureMedichePrestateTextField.setText("ERRORE: Testo troppo lungo. Max caratteri consentiti: " + MAX_CURE_LENGHT);
            eraseText(cureMedichePrestateTextField);
            TextFieldFlash(cureMedichePrestateTextField);
        }

        return error;
    }

    private boolean checkErroriFarmaco() {
        boolean error = false;

        if(nomeProduttoreTextField.getText().length() > MAX_PRODUTTORE_LENGHT) {
            error = true;
            nomeProduttoreTextField.setText("ERRORE: Nome troppo lungo. Max caratteri consentiti: " + MAX_PRODUTTORE_LENGHT);
            eraseText(nomeProduttoreTextField);
            TextFieldFlash(nomeProduttoreTextField);
        }
        if(nomeProduttoreTextField.getText().isEmpty()) {
            error = true;
            nomeProduttoreTextField.setText("ERRORE: Campo vuoto.");
            eraseText(nomeProduttoreTextField);
            TextFieldFlash(nomeProduttoreTextField);
        }

        if(nomeFarmacoTextField.getText().length() > MAX_FARMACO_NOME_LENGHT) {
            error = true;
            nomeFarmacoTextField.setText("ERRORE: Nome troppo lungo. Max caratteri consentiti: " + MAX_PRODUTTORE_LENGHT);
            eraseText(nomeFarmacoTextField);
            TextFieldFlash(nomeFarmacoTextField);
        }
        if(nomeFarmacoTextField.getText().isEmpty()) {
            error = true;
            nomeFarmacoTextField.setText("ERRORE: Campo vuoto.");
            eraseText(nomeFarmacoTextField);
            TextFieldFlash(nomeFarmacoTextField);
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





