import java.util.*;

public class Menu {
	
	public Menu(){}
	
	public void creerJoueurs(){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Nom joueur ?\n");
		Joueur j = new Joueur(input.nextLine());
		j.sauvegarde();
		System.out.println(j);
	}
	
	public void affichageClassement(){
		Classement m = new Classement();
	}

	public void jouer(){
		
		Joueur[] joueurs = new Joueur[2];
		Joueur j = new Joueur();
		joueurs[0] = j.chargement();
		joueurs[1] = j.chargement();
		
		for(int i = 0; i < joueurs.length ; i++){
			System.out.println(joueurs[i]); 
		}
		
		Partie p = new Partie(joueurs);
		p.deroulement();
	}
	
	public void choix(int x){	
		if (x == 1)
			jouer();		
		else if (x == 2)
			creerJoueurs();
		else
			affichageClassement();
	}
	
	public void affichage(){
		Scanner input = new Scanner(System.in);
		
		int choix = 0;
		boolean exceptionLevee;
		
		do{
			do{
				try{
					System.out.println("Menu :");
					System.out.println("	- Tapez 1 pour Lancer une partie");
					System.out.println("	- Tapez 2 pour Créer un personnage");
					System.out.println("	- Tapez 3 pour Afficher le classement");
					System.out.println("	- Tapez 4 pour Quitter");
				
					exceptionLevee=false;
					choix = input.nextInt();
					
					if(choix<1 || choix>4)
						throw new Exception("ERREUR! Choix invalide!");
					
					choix(choix);											
				}
								
				catch(Exception e){
					System.out.println(e);
					exceptionLevee=true;
				}
			}while(exceptionLevee);
		}while(choix!=4);	
	}
		
	public static void main(String[] args){
		
		Menu m = new Menu();
		m.affichage();
	}
	
	
}
