package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPaginaUtente extends JFrame {
    private JButton AggiungiAnimale;
    private JButton PrenotaVisita;
    private JButton Esci;
    private JPanel FormPaginaUtente;

    public FormPaginaUtente(String panel) {
        //Questa pagina si occupa dell'indirizzamento verso altre pagine
        super(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.FormPaginaUtente.setBackground(new Color(25, 159, 214, 20));
        this.setContentPane(FormPaginaUtente);
        this.pack();

        AggiungiAnimale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new FormAggiungiAnimale("PanelAggiungiAnimale");
                frame.setVisible(true);
                frame.setSize(500, 500);
                setVisible(false);
            }
        });

        PrenotaVisita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new FormPrenotazioneVisita("Prenota Visita");
                frame.setVisible(true);
                frame.setSize(500, 500);
                setVisible(false);
            }
        });

        Esci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new FormAccesso("FormAccesso");
                frame.setVisible(true);
                frame.setSize(500, 500);
                setVisible(false);
            }
        });
    }
}

