package Control;

import Entity.*;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControlAmministratore {

    public static void AggiornaVaccinazione(String CodiceChip, String Data){
        Vaccinazione.AggiornaVaccinazione(CodiceChip, Data);
    }

    public static ArrayList<String> VisiteSenzaPrescrizione() {
        Prescrizione pre = new Prescrizione();
       return  pre.VisiteSenzaPre();
    }



    public static int  aggiungiFarmaco(String nome, String produttore, String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(data, formatter);
        Farmaco farm = new Farmaco(nome, produttore, dateTime);
        return farm.addToDB();
    }

    public static boolean RegistraUtente(String Username, String Password, String Nome, String Cognome, String Numero, String Indirizzo){
        Account acc = new Account(Username, Password);
        if(!acc.checkAcc() && !Proprietario.checkPro(Numero)){
            acc.aggiungiAccount();
            Proprietario prop = new Proprietario(Nome, Cognome, Indirizzo, Numero, Username);
            prop.aggiungiProprietario();
            return true;
        }
        return false;
    }

    //Funzione per generare Username
    //Semplicemente vengono prese le prime 3 lettere del Nome, le prime 3
    //del Cognome e concatenate attraverso l'underscore ("_").
    //Alla fine viene aggiunta (append) la somma di tutte le cifre del numero di telefono.
    public String Create_Username(String Nome, String Cognome, String Numero_di_telefono){
        int somma = 0;
        for (int i = 0; i < Numero_di_telefono.length(); i++) {
            char cifra = Numero_di_telefono.charAt(i);
            if (Character.isDigit(cifra)) {
                int digit = Character.getNumericValue(cifra);
                somma += digit;
            }
        }
        return Nome.substring(0, 3) + "_" + Cognome.substring(0,3) + somma;
    }

    //Funzione per generare Password.
    //In questo caso viene generata sfruttando il relativo codice ASCII di ogni
    //carattere dell'Username, compreso l'underscore ("_").
    //Ad ogni codice preso, questo valore viene moltiplicato per il successivo
    //codice ASCII incontrato e alla fine la password generata sarà composta dalle
    //prime 5 cifre ottenute.
    public String Create_Password(String username){
        StringBuilder password = new StringBuilder();
        int ascii = 0;
        for (int i = 0; i < username.length(); i++) {
            ascii = username.charAt(i);
            ascii *= ascii;
            password.append(ascii);
        }

        return password.substring(0,5);
    }

    public static int  AssociaPrescrizione(String Diagn, String costo, String data, String cure, HashMap<String, String> farmaci){
        //trasformiamo l'hashMap in una lista di farmaci
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime dataPresc = LocalDateTime.parse(data, formatter);
        ArrayList<Farmaco> listaFarmaci = new ArrayList<>();

        if(!farmaci.isEmpty()) {

            for (Map.Entry<String, String> entry : farmaci.entrySet()) {
                String nomeFarmaco = entry.getKey();
                String produttore = entry.getValue();

                // Creare un oggetto Farmaco e aggiungerlo alla lista
                Farmaco farmaco = new Farmaco(nomeFarmaco, produttore, dataPresc);
                listaFarmaci.add(farmaco);
            }
        }
      Prescrizione p= new Prescrizione(dataPresc,Diagn, cure, Double.parseDouble(costo),listaFarmaci);
       return p.addToDB();
     }

    public static  HashMap<String, Double> ottieniRicavato(){
        //Creo un HashMap e la popolo, per tenere i valori della visita.
        Prescrizione pre= new Prescrizione();
        ArrayList<Prescrizione> lista = pre.resocontoGiornaliero();
        HashMap<String, Double> ricavato = new HashMap<String, Double>();
        for (Prescrizione visita : lista){
            ricavato.put(visita.getData().toString(), visita.getCosto());
        }
        return ricavato;
    }

    public static void aggiornaDisponibilita(LocalDateTime Data){
        if(!Visita.controlloData(Data) && Data.isAfter(LocalDateTime.now())){
            Visita.aggiungiData(Data);
            JOptionPane.showMessageDialog(null, "Data Aggiunta");
            } else if (Data.isBefore(LocalDateTime.now())) {
                JOptionPane.showMessageDialog(null, "La data è passata");
            } else if (Visita.controlloData(Data)) {
                JOptionPane.showMessageDialog(null, "La data è già presente nel database");
            }
    }
}



