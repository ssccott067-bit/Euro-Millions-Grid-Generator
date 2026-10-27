class Grille {
    
    // attributs 
    private int[] numeros;
    private int[] etoiles;

    // constructeurs 
    // methode par defaut 
    public Grille() {

        this.numeros = new int[5];
        this.etoiles = new int[2];

        numeros[0] = ((int)(Math.random()*10)) + 1;
        numeros[1] = ((int)(Math.random()*10)) + 11;
        numeros[2] = ((int)(Math.random()*10)) + 21;
        numeros[3] = ((int)(Math.random()*10)) + 31;
        numeros[4] = ((int)(Math.random()*10)) + 41;

        etoiles[0] = ((int)(Math.random()*6)) + 1;
        etoiles[1] = ((int)(Math.random()*6)) + 7;
    }

    // methode par copie 
    public Grille(Grille grille) {

        this.setGrille(grille.getNumeros(), grille.getEtoiles());
    }

    // methode Getter
    // methode getNumeros 
    public int[] getNumeros() {

        int[] copieNumeros = new int[5];
        for (int i=0; i<5; i++) {
            copieNumeros[i] = this.numeros[i];
        }

        return copieNumeros;
    }

    // methode getEtoiles 
    public int[] getEtoiles() {

        int[] copieEtoiles = new int[2];
        for (int j=0; j<2; j++) {
            copieEtoiles[j] = this.etoiles[j];
        }

        return copieEtoiles;
    }

    // methode Setter
    // methode setNumeros()
    public void setNumeros(int[] numeros) {

        this.numeros = new int[5];
        
        this.numeros[0] = numeros[0];
        this.numeros[1] = numeros[1];
        this.numeros[2] = numeros[2];
        this.numeros[3] = numeros[3];
        this.numeros[4] = numeros[4];
    }

    // methode setEtoiles()
    public void setEtoiles(int[] etoiles) {

        this.etoiles = new int[2];

        this.etoiles[0] = etoiles[0];
        this.etoiles[1] = etoiles[1];
    }

    // methode setGrille() 
    public void setGrille(int[] numeros, int[] etoiles) {

        this.setNumeros(numeros);
        this.setEtoiles(etoiles);
    }

    // surcharge methode setGrille
    public void setGrille(int[] tirage) {

        for (int i=0; i<5; i++) {

            this.numeros[i] = tirage[i];
        }

        this.etoiles[0] = tirage[5];
        this.etoiles[1] = tirage[6];
    }

    // Autres constructeurs
    // methode estValide 
    public boolean estValide() {

        // verifier les valeurs des nombres
        for (int i=0; i<5; i++) {
            if (this.numeros[i] > 50 || this.numeros[i] < 1) {
                return false; 
            }
        }

        // verifier les valeurs des etoiles
        for (int j=0; j<2; j++) {
            if (this.etoiles[j] > 12 || this.etoiles[j] < 1) {
                return false;
            }
        }

        // verifier s'il y a des doublons dans les numeros
        for (int m=0; m<5; m++) {
            for (int n=m+1; n<5; n++) {
                if (this.numeros[m] == this.numeros[n]) {
                    return false;
                }
            }
        }

        // verifier s'il y a des doublons dans les etoiles
        if (this.etoiles[0] == this.etoiles[1]) {
            return false;
        }

        return true;

    }

    // methode contientNumero 
    public boolean contientNumero(int nombre) {

        int i=0;
        boolean numBool = false;
        while (i<5 & numBool == false) {
            if (nombre == numeros[i]) {
                numBool = true;
            }
            i++;
        }

        return numBool;
    }   

    // methode contientEtoiles 
    public boolean contientEtoiles(int nombre) {

        int i=0;
        boolean etoBool = false;
        while (i<2 & etoBool == false) {
            if (nombre == etoiles[i]) {
                etoBool = true;
            }
            i++;
        }

        return etoBool;
    }

    // methode compare 
    public int compare(Grille grille) {

        int bonNombre = 0;
        int bonEtoile = 0;

        for (int i=0; i<5; i++) {
            if (grille.contientNumero(this.numeros[i])) {
                bonNombre = bonNombre + 1;
            }
        }

        for (int j=0; j<2; j++) {
            if (grille.contientEtoiles(this.etoiles[j])) {
                bonEtoile = bonEtoile + 1;
            }
        }

        if (bonNombre == 5) {
            if (bonEtoile == 2) {
                return 1;
            } else if (bonEtoile == 1) {
                return 2;
            } else {
                return 3;
            }
        } 

        if (bonNombre == 4) {
            if (bonEtoile == 2) {
                return 4;
            } else if (bonEtoile == 1) {
                return 5;
            } else {
                return 7;
            }
        }

        if (bonNombre == 3) {
            if (bonEtoile == 2) {
                return 6;
            } else if (bonEtoile == 1) {
                return 9;
            } else {
                return 10;
            }
        }

        if (bonNombre == 2) {
            if (bonEtoile == 2) {
                return 8;
            } else if (bonEtoile == 1) {
                return 12;
            }
        }

        if (bonNombre == 1 & bonEtoile == 2) {
            return 11;
        }

        return 100;
    }

    // methode equals
    public boolean equals(Grille grille) {

        int i = 0;
        boolean numBool = true;

        while (i<5 & numBool == true) {
            if (this.numeros[i] != grille.numeros[i]) {
                numBool = false;
            }
            i++;
        }

        int j = 0;
        boolean etoBool = true;
        while (j<2 & etoBool == true) {
            if (this.etoiles[j] != grille.etoiles[j]) {
                etoBool = false;
            }
            j++;
        }
        
        if (etoBool == true & numBool == true) {
            return true;
        } else {
            return false;
        }
    }

    // methode toString
    public String toString() {

        return ("[" + this.numeros[0] + ", " + this.numeros[1] + ", " + this.numeros[2] + ", " + this.numeros[3] + ", " + this.numeros[4] + "] - ["
                + this.etoiles[0] + ", " + this.etoiles[1] + "]" );
    }
}
