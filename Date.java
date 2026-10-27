import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Date {
    
    // attributs
    private int jour;
    private int mois;
    private int annee;

    // constructeurs 
    // methode par copie 
    public Date(Date date) {

        this.jour = date.jour;
        this.mois = date.mois;
        this.annee = date.annee;

    }

    // methode par defaut 
    public Date() {

        LocalDate localDate = LocalDate.now();
        this.jour = localDate.getDayOfMonth();
        this.mois = localDate.getMonthValue();
        this.annee = localDate.getYear();
    }

    // methode special
    public Date(int jour, int mois, int annee) {

        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    // methode equals
    public boolean equals(Date date) {
        
        if (this.annee != date.annee) {
            return false;
        }

        if (this.mois != date.mois) {
            return false;
        }

        if (this.jour != date.jour) {
            return false;
        }

        return true;
    }

    // methode Getter
    // methode getJour & getMois & getAnnee
    public int getJour() {

        return this.jour;
    }

    public int getMois() {

        return this.mois;
    }

    public int getAnnee() {

        return this.annee;
    }

    // methode toString()
    public String toString() {

        if (this.jour < 10 & this.mois < 10) {
            return new String("0" + this.jour + "/0" + this.mois + "/" + this.annee);

        } else if (this.jour < 10) {
            return new String("0" + this.jour + "/" + this.mois + "/" + this.annee);

        } else if (this.mois < 10) {
            return new String(this.jour + "/0" + this.mois + "/" + this.annee);
        } 

        return (this.jour + "/" + this.mois + "/" + this.annee);
    }

    // methode calculer la difference
    public long calculerDifference() {

        // 1, Create a LocalDate from the existing attributs
        LocalDate dateStockee = LocalDate.of(this.annee, this.mois, this.jour);

        // 2. Get today's date
        LocalDate aujourdHui =  LocalDate.now();

        // 3. Calculate the difference and return it
        return Math.abs(ChronoUnit.DAYS.between(dateStockee, aujourdHui));
    }
}
