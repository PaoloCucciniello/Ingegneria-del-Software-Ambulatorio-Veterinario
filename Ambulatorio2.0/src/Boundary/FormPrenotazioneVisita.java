package Boundary;

import Control.ControlProprietario;
import Control.ControlSistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static Control.ControlProprietario.PopulateBoxAnimali;
import static Control.ControlSistema.PopulateBoxDate;

public class FormPrenotazioneVisita extends JFrame {
    private JPanel FormPrenotazione;
    private JComboBox<String> BoxDate;
    private JComboBox<String> BoxAnimali;
    private JButton Prenota;
    private JButton Back;

    public FormPrenotazioneVisita(String panel){
        super(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.FormPrenotazione.setBackground(new Color(25, 159, 214, 20));
        this.setContentPane(FormPrenotazione);
        this.pack();
        ControlSistema.AggiornaVisite();
        PopulateBoxA();
        PopulateBoxD();

        Prenota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlProprietario.PrenotaVisita(BoxAnimali.getSelectedItem().toString().substring(0, 10), BoxDate.getSelectedItem().toString());
                JFrame frame = new FormPrenotazioneVisita("Prenotazione Visita");
                frame.setVisible(true);
                frame.setSize(500, 500);
                setVisible(false);
            }
        });

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new FormPaginaUtente("Pagina Utente");
                frame.setVisible(true);
                frame.setSize(500, 500);
                setVisible(false);
            }
        });
    }

    private void PopulateBoxA(){
        for (int i = 0; i < countAnimali(); i++) { BoxAnimali.addItem(PopulateBoxAnimali(i)); }
    }
    private int countAnimali(){
        return ControlProprietario.countAnimali();
    }

    private void PopulateBoxD(){
        for (int i = 0; i < countVisite(); i++) {
            if(!PopulateBoxDate(i).isBlank()) { BoxDate.addItem(PopulateBoxDate(i)); }
        }
    }
    private int countVisite(){ return ControlSistema.countVisite();}


}
