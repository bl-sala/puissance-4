
public class Grille {
	private Case[][] tab;
	public static final int NB_LIGNES=6;
	public static final int NB_COLONNES=7;
	
	
	public Grille(){
		this.tab=new Case[NB_LIGNES][NB_COLONNES];
		for(int i=0;i<NB_LIGNES;i++){
			for(int j=0;j<NB_COLONNES;j++){
				this.tab[i][j]=new Case();
			}
		}
	}
	
	public boolean pleine(){
		for(int i=0;i<NB_LIGNES;i++){
			for(int j=0;j<NB_COLONNES;j++){
				if(this.tab[i][j].estVide()){
					return false;
				}
			}
		}
		return true;
	}
	public void afficher(){
		System.out.println("* * * * * * * * * * * * * * *");
		for(int i=0;i<NB_LIGNES;i++){
			System.out.print("*");
			for(int j=0;j<NB_COLONNES;j++){
				if(this.tab[i][j].estRouge()){
					System.out.print(" R *");
				}
				else if (this.tab[i][j].estJaune()){
					System.out.print(" J *");
				}
				else{
					System.out.print("   *");
				}
			}
			System.out.print("\n");
			System.out.println("* * * * * * * * * * * * * * *");
		}
		System.out.println("  0   1   2   3   4   5   6");
	}
	
	public boolean peutPoser(int colonne){
		for(int i=NB_LIGNES-1;i>=0;i--){
			if(this.tab[i][colonne].estVide()){
				
				return true;
			}
		}
		return false;
	}
	
	public int poser(int colonne,int couleur){
		if(this.peutPoser(colonne)){
			for(int i=NB_LIGNES-1;i>=0;i--){
				if(this.tab[i][colonne].estVide()){
					this.tab[i][colonne].setCouleur(couleur);
					return i;
				}
			}
		}
		return -1;
	}
	/*
	 * 
	 * à ne pas effectuer sur une case vide !!
	 */
	public boolean gagneHorizontal(int ligne, int colonne){
		int nombreDroit=0;
		int i=colonne+1;
		boolean arrete=false;
		while(i<=NB_COLONNES-1 && !arrete){
			if(this.tab[ligne][i].getCouleur()==this.tab[ligne][colonne].getCouleur()){
				nombreDroit++;
			}
			else{
				arrete=true;
			}
			i++;
		}
		
		int nombreGuauche=0;
		int j=colonne-1;
		arrete=false;
		while(j>=0 && !arrete){
			if(this.tab[ligne][j].getCouleur()==this.tab[ligne][colonne].getCouleur()){
				nombreGuauche++;
			}
			else{
				arrete=true;
			}
			j--;
		}
		if(nombreDroit+nombreGuauche+1>=4){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean gagneVertical(int ligne, int colonne){
		int nombreHaut=0;
		int i=ligne-1;
		boolean arrete=false;
		while(i>=0 && !arrete){
			if(this.tab[i][colonne].getCouleur()==this.tab[ligne][colonne].getCouleur()){
				nombreHaut++;
			}
			else{
				arrete=true;
			}
			i--;
		}
		int nombreBas=0;
		int j=ligne+1;
		arrete=false;
		while(j<=NB_LIGNES-1 && !arrete){
			if(this.tab[j][colonne].getCouleur()==this.tab[ligne][colonne].getCouleur()){
				nombreBas++;
			}
			else{
				arrete=true;
			}
			j++;
		}
		if(nombreHaut+nombreBas+1>=4){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean gagneDiagonal1(int ligne, int colonne){
		int nombreHaut=0;
		int i=ligne-1;
		int j=colonne;
		if(colonne!=6){
			j=colonne+1;
		}
		
		boolean arrete=false;
		while(i>=0 && colonne<=NB_COLONNES-1 && !arrete){
			if(this.tab[i][j].getCouleur()==this.tab[ligne][colonne].getCouleur()){
				nombreHaut++;
			}
			else{
				arrete=true;
			}
			i--;
			j++;
		}
		int nombreBas=0;
		i=ligne+1;
		j=colonne-1;
		arrete=false;
		while(i<=NB_LIGNES-1 && j>=0 && !arrete){
			if(this.tab[i][j].getCouleur()==this.tab[ligne][colonne].getCouleur()){
				nombreBas++;
			}
			else{
				arrete=true;
			}
			j--;
			i++;
		}
		
		
		if(nombreHaut+nombreBas+1>=4){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean gagneDiagonal2(int ligne, int colonne){
		int nombreHaut=0;
		int i=ligne-1;
		if(colonne!=0){
			int j=colonne-1;
		
		boolean arrete=false;
		while(i>=0 && colonne>=0 && !arrete){
			if(this.tab[i][j].getCouleur()==this.tab[ligne][colonne].getCouleur()){
				nombreHaut++;
			}
			else{
				arrete=true;
			}
			i--;
			j--;
		}
		int nombreBas=0;
		i=ligne+1;
		j=colonne+1;
		arrete=false;
		while(i<=NB_LIGNES-1 && j<=NB_LIGNES-1 && !arrete){
			if(this.tab[i][j].getCouleur()==this.tab[ligne][colonne].getCouleur()){
				nombreBas++;
			}
			else{
				arrete=true;
			}
			j++;
			i++;
		}
		
		
		if(nombreHaut+nombreBas+1>=4){
			return true;
		}
		else{
			return false;
		}
		}
		else{
			return false;
		}
	}
	
	public boolean gagne(int ligne, int colonne){
		return gagneHorizontal(ligne,colonne) || gagneVertical(ligne,colonne) || gagneDiagonal1(ligne,colonne) || gagneDiagonal2(ligne,colonne);
	}

}
