import java.util.Random;
import java.util.Scanner;


public class Partie {
	private Grille grille;
	private Joueur[] joueurs;
	private int joueurCourant;
	
	public Partie(Joueur[] tab){
		this.grille=new Grille();
		this.joueurs=tab;
		System.out.println("coco");
	}
	
	public int avoirCouleur(){
		if(this.joueurCourant==0){
			return 1;
		}
		else if(this.joueurCourant==1){
			return -1;
		}
		return 0;
	}
	
	public int getAutreJoueur(){
		if(this.joueurCourant==1)
			return 0;
		else
			return 1;
	}
	
	public void changerJoueur(){
		if(this.joueurCourant==0){
			this.joueurCourant=1;
		}
		else if(this.joueurCourant==1){
			this.joueurCourant=0;
		}
	}
	public void deroulement(){
		/*
		 * Déterminer le joueur qui commence
		 */
		Random r=new Random();
		this.joueurCourant=r.nextInt(2);
		System.out.println("Le joueur "+this.joueurs[this.joueurCourant].getNom()+" a la couleur rouge et va commencer, "+"le joueur "+this.joueurs[this.getAutreJoueur()].getNom());
		boolean gagne=false,valide=false;
		int choix=-1,res=-1;
		this.grille.afficher();
		while(!this.grille.pleine() && !gagne){
			System.out.println("C'est le tour du joueur "+this.joueurs[this.joueurCourant].getNom()+" :");
			do{
				System.out.println("Tape le numero de la colonne !");
				Scanner input=new Scanner(System.in);
				choix=input.nextInt();
				if(choix>=0 && choix <7){
					res=this.grille.poser(choix,this.avoirCouleur());
					if(res!=-1){
						valide=true;
					}
				}
			}while(choix<0 || choix >6 || valide==false);
			if(this.grille.gagne(res,choix)){
				gagne=true;
			}
			this.changerJoueur();
			this.grille.afficher();
		}
		if(gagne){
			this.changerJoueur();
			System.out.println("C'est le joueur "+this.joueurs[this.joueurCourant].getNom()+" qui a gagné !");
			this.joueurs[this.joueurCourant].setPGagnes(this.joueurs[this.joueurCourant].getPGagnes()+1);
			this.joueurs[this.getAutreJoueur()].setPPerdus(this.joueurs[this.getAutreJoueur()].getPPerdus()+1);
			this.joueurs[this.joueurCourant].sauvegarde();
			this.joueurs[this.getAutreJoueur()].sauvegarde();
		
		}
		else{
			System.out.println("Partie nulle !");
		}
	}	
}