
public class Case {
	private int couleur;
	public static final int JAUNE=-1;
	public static final int ROUGE=1;
	public static final int VIDE=0;
	
	public Case(){
		this.couleur=VIDE;
	}
	public int getCouleur(){
		return this.couleur;
	}
	
	public void setCouleur(int couleur){
		this.couleur=couleur;
	}
	
	public boolean estVide(){
		return this.couleur==Case.VIDE;
	}
	
	public boolean estRouge(){
		return this.couleur==ROUGE;
	}
	public boolean estJaune(){
		return this.couleur==JAUNE;
	}
}
