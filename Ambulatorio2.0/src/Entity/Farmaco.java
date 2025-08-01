package Entity;

import Database.FarmacoDB;

import java.time.LocalDateTime;

public class Farmaco {
    private String nome;
    private String produttore;

    private LocalDateTime data;
    public Farmaco(String n, String p, LocalDateTime dat) {
        this.nome = n;
        this.produttore = p;
        this.data = dat;
    }

    public String getNome() {
        return nome;
    }

    public String getProduttore() {
        return produttore;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProduttore(String produttore) {
        this.produttore = produttore;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public LocalDateTime getData() {
        return data;
    }

    public  int addToDB(){
        FarmacoDB farmDB = new FarmacoDB();
        farmDB.setNome(this.getNome());
        farmDB.setProduttore(this.getProduttore());
        farmDB.setData(this.getData());
        return farmDB.insertDB();


    }
}
