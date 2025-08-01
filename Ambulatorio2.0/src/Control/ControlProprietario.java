package Control;

import Entity.AnimaleDomestico;
import Entity.Vaccinazione;
import Entity.Visita;

import javax.swing.*;
import java.time.LocalDate;

public class ControlProprietario {

    public static String user; //Usiamo questa variabile cosi da poter avere  sempre l'username
    // disponibile durante la sessione dell'utente

    public static void saveUsername(String Username){
        user = Username;
    }
    public ControlProprietario() {
        System.out.println("Creato Control Proprietario");
    }

    public static int InserimentoAnimale(String Chip, String Nome, String Razza, String Colore, String Taglia) {
        AnimaleDomestico anim = new AnimaleDomestico(Chip, Nome, Razza, Colore, Taglia, user);
        return anim.addToDB();
    }

    public static int InserimentoVaccinazione(String tipo, LocalDate data, String chip) {
        AnimaleDomestico a = new AnimaleDomestico(chip);
        Vaccinazione v = new Vaccinazione(tipo, data, chip);
        return v.addToDB();
    }

    public static String PopulateBoxAnimali(int position){
        return AnimaleDomestico.list(user, position);
    }

    public static int countAnimali(){
        return AnimaleDomestico.count(user);
    }

    public static void PrenotaVisita(String Animale, String Data){

        if(!Visita.checkVisita(Animale)) {
            Visita.PrenotaVisita(user, Animale, Data);
            JOptionPane.showMessageDialog(null, "Visita Prenotata");
        } else {
            JOptionPane.showMessageDialog(null, "Hai già una visita prenotata per questo animale");
        }
    }


}

