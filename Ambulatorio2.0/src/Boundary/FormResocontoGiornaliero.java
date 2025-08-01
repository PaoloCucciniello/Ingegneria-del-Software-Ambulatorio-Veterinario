package Boundary;

import Control.ControlAmministratore;
import Control.ControlProprietario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;


public class FormResocontoGiornaliero extends JFrame {
    private JPanel ResocontoGiornaliero;
    private JFrame Resoconto;
    private JButton back;
    private JButton ottieniRicavato;
    private JTextArea textArea1;

    public FormResocontoGiornaliero(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ResocontoGiornaliero.setBackground(new Color(25, 159, 214, 20));
        this.setContentPane(ResocontoGiornaliero);
        this.pack();

        back.addActionListener(new ActionListener() {
            //Torna indietro alla pagina Amministratore
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new FormPaginaAmministratore("FormPaginaAmministratore");
                frame.setVisible(true);
                frame.setSize(500, 500);
                setVisible(false);
            }
        });

        ottieniRicavato.addActionListener(new ActionListener() {
            //Ottieni il ricavato giornaliero
            @Override
            public void actionPerformed(ActionEvent e) {
                ottieniRicavatoActionListener();
            }


        });
    }

    private void ottieniRicavatoActionListener() {
        Double totaleRicavato = 0.0;
        //Riempi una HashMap con i valori dell'orario in cui è avvenuta la visita, e il costo
        HashMap<String, Double> resoconto = ControlAmministratore.ottieniRicavato();
        //Check per vedere se è vuota
        if (resoconto.isEmpty()) {
            textArea1.setText("Nessuna visita oggi.");
            return; // Esci dalla funzione
        }
        //Scala la lista per ogni entry, scrivendo orario e costi, e somma al ricavato totale

        for(Map.Entry<String, Double> entry : resoconto.entrySet()) {
            String Data = entry.getKey();
            Double Costo = entry.getValue();

            // Stampa a testo i valori
            System.out.println("Key: " + Data + ", Value: " + Costo);

            textArea1.append("Orario: " + Data + "\n");
            textArea1.append("Costo: " + Costo + "\n");
            textArea1.append("------------------\n");

            totaleRicavato += Costo;
        }
        textArea1.append("Totale Ricavato: " + totaleRicavato.toString() + "€");
    }
}




