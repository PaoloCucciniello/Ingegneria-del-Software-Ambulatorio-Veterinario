package Entity;
import Database.AnimaleDomesticoDB;

public class AnimaleDomestico {
    private String chip;
    private String nome;
    private String razza;
    private String taglia;
    private String colore;
    private String proprietario;

    public AnimaleDomestico(String chip) {
        AnimaleDomesticoDB animale = new AnimaleDomesticoDB(chip);
        this.nome = animale.getNome();
        this.razza = animale.getRazza();
        this.taglia = animale.getTaglia();
        this.colore = animale.getColore();
        this.proprietario = animale.getProprietario();
    }

    public  AnimaleDomestico(String chp, String nom, String raz, String tagl, String colo, String Username){
        this.chip = chp;
        this.nome = nom;
        this.razza = raz;
        this.taglia = tagl;
        this.colore = colo;
        this.proprietario = Username;
    }

    public AnimaleDomestico(AnimaleDomesticoDB animale) {
        this.chip = animale.getChip();
        this.nome = animale.getNome();
        this.razza = animale.getRazza();
        this.taglia = animale.getTaglia();
        this.colore = animale.getColore();
        this.proprietario = animale.getProprietario();
    }

    public int addToDB() {
        AnimaleDomesticoDB animale = new AnimaleDomesticoDB();
        animale.setNome(this.nome);
        animale.setProprietario(this.proprietario);
        animale.setTaglia(this.taglia);
        animale.setColore(this.colore);
        animale.setRazza(this.razza);
        animale.setChip(this.chip);
        return animale.insertDB();
    }

    public static String list(String Username, int count){
        return AnimaleDomesticoDB.AnimalName(Username, count);
    }

    public static int count(String Username){
        return AnimaleDomesticoDB.CountAnimali(Username);
    }

    public String getChip() { return chip;}
    public String getColore() { return colore;}
    public String getNome() { return nome;}
    public String getProprietario() { return proprietario;}
    public String getRazza() { return razza;}
    public String getTaglia() { return taglia;}
    public void setChip(String chip) { this.chip = chip;}
    public void setNome(String nome) { this.nome = nome;}
    public void setColore(String colore) { this.colore = colore;}
    public void setProprietario(String proprietario) { this.proprietario = proprietario;}
    public void setRazza(String razza) { this.razza = razza;}
    public void setTaglia(String taglia) { this.taglia = taglia;}



}
