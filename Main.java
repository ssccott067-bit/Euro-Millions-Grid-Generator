class Main{
    public static void main(String[] args){
	ListeTirage lt = new ListeTirage();
	lt.ajouter("./historique_tirages/euromillions.csv");
	lt.ajouter("./historique_tirages/euromillions_2.csv");
	lt.ajouter("./historique_tirages/euromillions_3.csv");
	lt.ajouter("./historique_tirages/euromillions_4.csv");
	lt.ajouter("./historique_tirages/euromillions_201902.csv");
	lt.ajouter("./historique_tirages/euromillions_202002.csv");

	// Tirage t =  new Tirage(SynaveRemi.predictionTirage(lt));
	Tirage t = new Tirage(new AmmalNajman().predictionTirage(lt));

	System.out.println("Pour le prochain tirage, vous devriez jouer :");
	System.out.println(t);
	
    }
}
