package Boundary;
import Control.ControlAmministratore;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormAggiornaDisponibilita  extends JFrame  {

    private JPanel FormDisponibilita;
    private JLabel Data;
    private JTextField FieldData;
    private JButton Aggiorna;
    private JButton Indietro;
     //formatter per trasformare le date da String a LocalDateTime
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public FormAggiornaDisponibilita(String panel){
        super(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.FormDisponibilita.setBackground(new Color(25, 159, 214, 20));
        this.setContentPane(FormDisponibilita);
        this.pack();


        Aggiorna.addActionListener(new ActionListener() {
            //funzione per aggiornare disponibilita
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlAmministratore.aggiornaDisponibilita(LocalDateTime.parse(FieldData.getText(), formatter));
            }
        });

        //Listener per poter aggiornare la data premendo il tasto "Invio"
        FieldData.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Aggiorna.doClick();
                }
            }
        });

        Indietro.addActionListener(new ActionListener() {
            //Torna alla pagina amministratore
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new FormPaginaAmministratore("Pagina Amministratore");
                frame.setVisible(true);
                frame.setSize(500,500);
                setVisible(false);
            }
        });
    }



}
