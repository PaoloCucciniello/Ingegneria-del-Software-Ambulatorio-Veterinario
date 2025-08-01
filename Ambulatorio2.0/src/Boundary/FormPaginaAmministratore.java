package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPaginaAmministratore  extends JFrame{
    private JPanel FormAmministratore;
    private JButton Esci;
    private JButton AssociaPrescrizione;
    private JButton AggiornaDisponibilita;
    private JButton ControlloVaccinazioniScadute;
    private JButton ResocontoGiornaliero;
    private JButton RegistrazioneUtente;

    public FormPaginaAmministratore(String panel){
        //Questa pagina si occupa dell'indirizzamento verso altre pagine
        super(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.FormAmministratore.setBackground(new Color(25, 159, 214, 20));
        this.setContentPane(FormAmministratore);
        this.pack();


        AssociaPrescrizione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new FormAssociaPrescrizione("Associa Prescrizione");
                frame.setVisible(true);
                frame.setSize(700,500);
                setVisible(false);
            }
        });

        ResocontoGiornaliero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new FormResocontoGiornaliero("ResocontoGiornaliero");
                frame.setVisible(true);
                frame.setSize(500, 500);
                setVisible(false);

            }
        });

        AggiornaDisponibilita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new FormAggiornaDisponibilita("Aggiorna Disponibilità");
                frame.setVisible(true);
                frame.setSize(500,500);
                setVisible(false);
            }
        });

        ControlloVaccinazioniScadute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new FormControlloVaccinazione("Controllo Vaccinazioni");
                frame.setVisible(true);
                frame.setSize(500,500);
                setVisible(false);
            }
        });

        RegistrazioneUtente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new FormRegistrazioneUtente("Registrazione Utente");
                frame.setVisible(true);
                frame.setSize(500,500);
                setVisible(false);
            }
        });

        Esci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { setVisible(false); }
        });
    }
}
