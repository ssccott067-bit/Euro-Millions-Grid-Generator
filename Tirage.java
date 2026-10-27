class Tirage {
    
    // attributs
    private Date date;
    private Grille grille;
    private double[] rapports;

    // constant
    private static double[] GAINS = {250000000, 200738, 20851, 1299, 120, 57, 39, 14, 11, 9, 7, 6};

    // constructeurs 
    // methode par defaut 
    public Tirage() {

        this.rapports = new double[12];

        this.date = new Date();
        this.grille = new Grille();
        this.setRapports(GAINS);
    }

    // methode par copie 
    public Tirage(Tirage tirage) {

        this.date = new Date(tirage.date);
        this.grille = new Grille(tirage.grille);
        this.setRapports(tirage.rapports);
    }

    // methode Tirage special
    public Tirage(int[] tirage) {

        this.date = new Date();
        this.setRapports(GAINS);
        this.grille = new Grille();
        this.grille.setGrille(tirage);
        
    }

    // methode equals()
    public boolean equals(Tirage tirage) {

        boolean boolDate = this.date.equals(tirage.date);
        boolean boolGrille = this.grille.equals(tirage.grille);

        if (boolDate != true || boolGrille != true) {

            return false;
        }

        for (int i=0; i<12; i++) {
            if (this.rapports[i] != tirage.rapports[i]) {
                
                return false;
            }
        }

        return true;
    }

    // methode Setter 
    // methode setGrille()
    public void setGrille(int[] numeros, int[] etoiles) {

        this.grille.setNumeros(numeros);
        this.grille.setEtoiles(etoiles);
    }
    // methode setRapports()
    public void setRapports(double[] rapports) {

        this.rapports = new double[12];

        this.rapports[0] = rapports[0];
        this.rapports[1] = rapports[1];
        this.rapports[2] = rapports[2];
        this.rapports[3] = rapports[3];
        this.rapports[4] = rapports[4];
        this.rapports[5] = rapports[5];
        this.rapports[6] = rapports[6];
        this.rapports[7] = rapports[7];
        this.rapports[8] = rapports[8];
        this.rapports[9] = rapports[9];
        this.rapports[10] = rapports[10];
        this.rapports[11] = rapports[11];
    }

    // methode setDate() 
    public void setDate(Date date) {

        this.date = new Date(date);
    }

    // methode getDate()
    public Date getDate() {

        Date copyDate = new Date(this.date);

        return copyDate;
    }

    // methode getGrille()
    public Grille getGrille() {

        Grille copyGrille = new Grille(this.grille);

        return copyGrille;
    }

    // methode getRapport() (la somme pour un rang)
    public double getRapport(int rang) {

        return rapports[rang - 1];
    }

    // methode getRapport() (la somme pour chaque rang)
    public String getRapport() {

        String rapport = "";

        for (int i=0; i<12; i++) {

            int rang = i+1;

            if (rang<10) {

                rapport += "Rang " + 0 + rang + " - " + this.rapports[i] + "\n";
            } else {
                
                rapport += "Rang " + rang + " - " + this.rapports[i] + "\n";
            }
        }

        return rapport;
    }

    // methode toString() 
    public String toString() {

        return new String("Le " + this.date.toString() + " : " + this.grille.toString());
    }
}
