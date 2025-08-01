package Control;

import Boundary.FormPaginaAmministratore;
import Boundary.FormPaginaUtente;

import javax.swing.*;

import Entity.Account;
import Entity.Vaccinazione;
import Entity.Visita;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.swing.JOptionPane.showMessageDialog;

public class ControlSistema {

    public static void Login(String Username, String Password) {
        Account acc = new Account(Username,Password);
        if(acc.checkCrede()){
            if(acc.checkAcc()){
                JOptionPane.showMessageDialog(null, "Salve Dottore");
                JFrame frame = new FormPaginaAmministratore("Pagina Amministratore");
                frame.setVisible(true);
                frame.setSize(500,500);
            } else{
                JOptionPane.showMessageDialog(null, "Salve " + Username);
                JFrame frame = new FormPaginaUtente("Pagina Utente");
                frame.setVisible(true);
                frame.setSize(500,500);
            }
        } else{
            JOptionPane.showMessageDialog(null, "Credenziali Errate");
        }
    }

    public static String PopulateBoxDate(int position){
            return Visita.ListDate(position);
    }

    public static int countVisite(){ return Visita.count(); }

    public static void AggiornaVisite(){
        LocalDateTime Data = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for(int i = 0; i < countVisite(); i++){
            if(LocalDateTime.parse(Visita.printVisite(i), formatter).isBefore(Data)){
                System.out.println("Data valida " + Visita.printVisite(i));
                Visita.aggiornaVisite(LocalDateTime.parse(Visita.printVisite(i), formatter));
            }
        }
    }

    public static int countVaccinazioni(){
        return Vaccinazione.count();
    }

    public static String PopulateBox() {
        return Vaccinazione.printVaccinazioniScadute();
    }
}
