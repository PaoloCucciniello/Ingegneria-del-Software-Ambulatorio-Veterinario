package Entity;

import Database.VaccinazioniDB;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Vaccinazione {
    private String tipo;
    private LocalDate data;

    private String chip;

    public  Vaccinazione(String tp, LocalDate giorno, String chip){
        this.tipo= tp;
        this.data = giorno;
        this.chip = chip;
    }

    public static int count(){
        return VaccinazioniDB.count();
    }

    public static String printVaccinazioniScadute(){
        return VaccinazioniDB.printVaccinazioniScadute();
    }

    public String getChip() {
        return chip;
    }

    public LocalDate getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setAnimale(String chip) {
        this.chip = chip;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int addToDB() {
        VaccinazioniDB v = new VaccinazioniDB(this.tipo, this.data, this.chip);
        return v.insertDB();
    }

    public static void AggiornaVaccinazione(String CodiceChip, String Data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        VaccinazioniDB.AggiornaVaccinazione(CodiceChip, LocalDateTime.parse(Data, formatter));
    }
}
