import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ListeTirage {

    // attributs
    private ArrayList<Tirage> listeTirage;

    // constructeurs 
    // methode par defaut 
    public ListeTirage() {

        this.listeTirage = new ArrayList<Tirage>();
    }

    // autres methodes 
    // methode ajouter 
    public void ajouter(Tirage tirage) {

        this.listeTirage.add(tirage);
    }

    // mathode ajouter()
    // public void ajouter(String cheminFichier) {

    //     String ligne;
    //     String separateur = ";"; // could be change to "," or ";"

    //     try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {

    //         br.readLine();

    //         while ((ligne = br.readLine()) != null) {

    //             String[] donnees = ligne.split(separateur);

    //             // Dealing with Date
    //             String dateBrute = donnees[2];
    //             String[] partiesDate = dateBrute.split("/");
    //             // String[] partiesDate = donnees[0].trim().split("/");
    //             Date date = new Date(
    //                 Integer.parseInt(partiesDate[0]),
    //                 Integer.parseInt(partiesDate[1]),
    //                 Integer.parseInt(partiesDate[2])
    //             );

    //             // Dealing with number
    //             int[] numeros = new int[5];
    //             for (int i=0; i<5; i++) {

    //                 numeros[i] = Integer.parseInt(donnees[i+5]);
    //                 // numeros[i] = Integer.parseInt(donnees[i+2]);
    //             }

    //             // Dealing with star
    //             int[] etoiles = new int[2];
    //             etoiles[0] = Integer.parseInt(donnees[10]);
    //             etoiles[1] = Integer.parseInt(donnees[11]);
    //             // etoiles[0] = Integer.parseInt(donnees[7]);
    //             // etoiles[1] = Integer.parseInt(donnees[8]);

    //             // Dealing with prizes (rapports)
    //             double[] rapports = new double[12];
    //             int arrayIndex = 0;
    //             for (int m=16; m<51; m=m+3) {

    //                 String rapportsBrute = donnees[m];
    //                 String rapportsFormatted = rapportsBrute.replace(",", ".");
    //                 rapports[arrayIndex] = Double.parseDouble(rapportsFormatted);
    //                 arrayIndex++;
    //             }
    //             // for (int r=0; r<12; r++) {
    //             //     String brut = donnees[11 + 3 * r].replace(",", ".");
    //             //     rapports[r] = Double.parseDouble(brut);
    //             // }

    //             // copy and add in the listeTirage 
    //             Tirage t = new Tirage();
    //             t.setGrille(numeros, etoiles);
    //             t.setDate(date);
    //             t.setRapports(rapports);

    //             this.ajouter(t);
    //         }

    //     } catch (IOException e) {

    //         System.err.println("Error reading the file : " + e.getMessage());
    //     }
    // }

    private int trouverIndice(String[] entete, String... noms) {
    for (int i = 0; i < entete.length; i++) {
        String col = entete[i].replace("\uFEFF", "").trim();
        for (String nom : noms) {
            if (col.equals(nom)) return i;
        }
    }
    throw new IllegalArgumentException("Colonne introuvable : " + noms[0]);
}

public void ajouter(String cheminFichier) {

    try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {

        String[] entete = br.readLine().split(";");

        int iDate    = trouverIndice(entete, "date_de_tirage");
        int iNumeros = trouverIndice(entete, "boule_1");
        int iEtoiles = trouverIndice(entete, "etoile_1");
        int iRapport = trouverIndice(entete, "rapport_du_rang1", "rapport_du_rang1_Euro_Millions");

        String ligne;
        while ((ligne = br.readLine()) != null) {

            String[] donnees = ligne.split(";");

            String[] p = donnees[iDate].trim().split("/");
            Date date = new Date(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]));

            int[] numeros = new int[5];
            for (int i = 0; i < 5; i++) {
                numeros[i] = Integer.parseInt(donnees[iNumeros + i]);
            }

            int[] etoiles = {
                Integer.parseInt(donnees[iEtoiles]),
                Integer.parseInt(donnees[iEtoiles + 1])
            };

            double[] rapports = new double[12];
            for (int r = 0; r < 12; r++) {
                rapports[r] = Double.parseDouble(donnees[iRapport + 3 * r].replace(",", "."));
            }

            Tirage t = new Tirage();
            t.setGrille(numeros, etoiles);
            t.setDate(date);
            t.setRapports(rapports);
            this.ajouter(t);
        }

    } catch (IOException e) {
        System.err.println("Error reading the file : " + e.getMessage());
    }
}

    // methode getNbTirage 
    public int getNbTirage() {

        return this.listeTirage.size();
    }

    // methode getTirage (par indice)
    public Tirage getTirage(int index) {

        Tirage copyTirage = new Tirage(listeTirage.get(index -1));

        return copyTirage;
    }

    // methode getTirage (par date) 
    public Tirage getTirage(Date date) {

        int i = listeTirage.size() -1;
        while (i>=0) {

            if (listeTirage.get(i).getDate().equals(date)) {

                Tirage copyTirage = new Tirage(listeTirage.get(i));
                return copyTirage;
            }
            i--;
        }

        return null;
    }

    // methode nbTiragePourNumero
    public int nbTiragePourNumero(int numero) {

        Grille tempGrille = new Grille();
        int count = 0;

        for (int i=listeTirage.size() -1; i>=0; i--) {

            tempGrille = new Grille(listeTirage.get(i).getGrille());

            if (tempGrille.contientNumero(numero)) {

                count++;
            }
        }
        
        return count;
    }

    // methode nbTiragePourNumero 
    public int[] nbTiragePourNumero() {

        int[] countNumeros = new int[50];
        
        for (int i=1; i<51; i++) {

            countNumeros[i-1] = this.nbTiragePourNumero(i);
        }

        return countNumeros;
    }

    // methode nbTiragePourEtoile 
    public int nbTiragePourEtoile(int etoile) {

        Grille tempGrille = new Grille();
        int count = 0;

        for (int i=listeTirage.size() -1; i>=0; i--) {

            tempGrille = new Grille(listeTirage.get(i).getGrille());

            if (tempGrille.contientEtoiles(etoile)) {

                count++;
            }
        }

        return count;
    }

    // methode nbTiragePourEtoile
    public int[] nbTiragePourEtoile() {

        int[] countEtoiles = new int[12];

        for (int i=1; i<13; i++) {

            countEtoiles[i-1] = this.nbTiragePourEtoile(i);
        }

        return countEtoiles;
    }

    // methode dateDernierTiragePourNumero
    public Date dateDernierTiragePourNumero(int numero) {

        Date tempDate = null;
        Grille tempGrille = new Grille();

        for (int i=listeTirage.size() -1; i>=0; i--) {

            tempGrille = new Grille(listeTirage.get(i).getGrille());

            if (tempGrille.contientNumero(numero)) {

                tempDate = new Date(listeTirage.get(i).getDate());
            }
        }

        return tempDate;
    }

    // methode dateDernierTiragePourEtoile
    public Date dateDernierTiragePourEtoile(int etoile) {

        Date tempDate = null; // null
        Grille tempGrille = new Grille();

        for (int i=listeTirage.size() -1; i>=0; i--) {

            tempGrille = new Grille(listeTirage.get(i).getGrille());

            if (tempGrille.contientEtoiles(etoile)) {

                tempDate = new Date(listeTirage.get(i).getDate());
            }
        }

        return tempDate;
    }

    // methode triRecentAncien
    public void triRecentAncien() {

        int n = listeTirage.size();

        for (int i=n-1; i>=0; i--) {

            int indiceRecent = i;

            for (int j=i-1; j>=0; j--) {

                long diffInitial = listeTirage.get(j).getDate().calculerDifference();
                long diffRecent = listeTirage.get(indiceRecent).getDate().calculerDifference();

                if (diffInitial < diffRecent) {

                    indiceRecent = j;
                }
            }

            if (indiceRecent != i) {

                Tirage currentTirage = listeTirage.remove(i);
                Tirage tirageRecent = listeTirage.remove(indiceRecent);

                listeTirage.add(indiceRecent, currentTirage);
                listeTirage.add(i, tirageRecent);
            }
        }
    }

    // methode triAncienRecent
    public void triAncienRecent() {

        int n = listeTirage.size();

        for (int i=n-1; i>=0; i--) {

            int indiceAncien = i;

            for (int j=i-1; j>=0; j--) {

                long diffInitial = listeTirage.get(j).getDate().calculerDifference();
                long diffAncien = listeTirage.get(indiceAncien).getDate().calculerDifference();

                if (diffInitial > diffAncien) {

                    indiceAncien = j;
                }
            }

            if (indiceAncien != i) {

                Tirage currentTirage = listeTirage.remove(i);
                Tirage tirageAncien = listeTirage.remove(indiceAncien);

                listeTirage.add(indiceAncien, currentTirage);
                listeTirage.add(i, tirageAncien);
            }
        }
    }

    // methode toString
    public String toString() {

        this.triAncienRecent();

        String resultat = "";

        for (int i=listeTirage.size() -1; i>=0; i--) {

            resultat += this.listeTirage.get(i).toString() + "\n";
        }

        return resultat;
    }
}