package Boundary;

import Control.ControlAmministratore;
import Control.ControlSistema;
import com.sun.jdi.PrimitiveValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class FormControlloVaccinazione extends JFrame {
    private JPanel FormVaccinazione;
    private JButton Back;
    private JButton AggiornaVaccinazione;
    private JComboBox<String> BoxVaccini;

    public FormControlloVaccinazione(String panel){
        super(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.FormVaccinazione.setBackground(new Color(25, 159, 214, 20));
        this.setContentPane(FormVaccinazione);
        this.pack();
        PopulateBoxVaccini();

        AggiornaVaccinazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { AggiornaVaccinazioneActionListener(); }
        });

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new FormPaginaAmministratore("Pagina Amministratore");
                frame.setVisible(true);
                frame.setSize(500, 500);
                setVisible(false);
            }
        });

    }

    private void AggiornaVaccinazioneActionListener() {
        String NuovaData = JOptionPane.showInputDialog("Inserire la data di scadenza della nuova vaccinazione (dd/MM/yyyy)");
        ControlAmministratore.AggiornaVaccinazione(BoxVaccini.getSelectedItem().toString().substring(0, 10), NuovaData + " 00:00");
        JOptionPane.showMessageDialog(null, "Vaccinazione aggiunta!");
        JFrame frame = new FormPaginaAmministratore("Pagina Amministratore");
        frame.setVisible(true);
        frame.setSize(500, 500);
        setVisible(false);
    }

    public void PopulateBoxVaccini(){
        for(int i = 0; i < countVaccinazioni(); i++){
            BoxVaccini.addItem(ControlSistema.PopulateBox());
        }
    }

    private static int countVaccinazioni(){
        return ControlSistema.countVaccinazioni();
    }
}
