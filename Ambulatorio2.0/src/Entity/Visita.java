package Entity;

import Database.VisitaDB;

import java.time.LocalDateTime;

public class Visita {
    private LocalDateTime dataPrenotata;
    private Proprietario proprietario;
    private  AnimaleDomestico animale;

    public Visita(LocalDateTime giorno, Proprietario prop, AnimaleDomestico a){
        this.animale = a;
        this.dataPrenotata = giorno;
        this.proprietario = prop;
    }

    public static int count(){
        return VisitaDB.ConteggioVisiteTotali();
    }
    public static boolean checkVisita(String CodiceChip){
        return VisitaDB.checkPrenotazione(CodiceChip);
    }
    public static String ListDate(int position){
        return VisitaDB.VisitePrenotabili(position);
    }
    public static int PrenotaVisita(String Username, String Animale, String Data){
        return VisitaDB.PrenotaVisita(Username, Animale, Data);
    }

    public static String printVisite(int position){
        return VisitaDB.printDB(position);
    }
    public static int aggiornaVisite(LocalDateTime Data){
        VisitaDB visDB = new VisitaDB(Data);
        return visDB.VisitePassate();
    }

    public static boolean controlloData(LocalDateTime Data){
        VisitaDB visDB = new VisitaDB(Data);
        return visDB.checkDB();
    }

    public static int aggiungiData(LocalDateTime Data){
        VisitaDB visDB = new VisitaDB(Data);
        return visDB.AggiungiVisitaAlDB();
    }

    public AnimaleDomestico getAnimale() {
        return animale;
    }

    public LocalDateTime getDataPrenotata() {
        return dataPrenotata;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setAnimale(AnimaleDomestico animale) {
        this.animale = animale;
    }

    public void setDataPrenotata(LocalDateTime dataPrenotata) {
        this.dataPrenotata = dataPrenotata;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

}
