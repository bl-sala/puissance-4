
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Joueur implements Serializable{

	private String nom;
	private int pGagnes;
	private int pPerdus;
	private File[] listePerso;
	
	
	public Joueur(String nom){
		this.nom = nom;
		this.pGagnes = 0;
		this.pPerdus = 0;
	}
	
	public Joueur(){}
	
	public String getNom(){
		return this.nom;
	}
	
	public int getPGagnes(){
		return this.pGagnes;
	}
	public void setPGagnes(int v){
		this.pGagnes=v;
	}
	
	public int nbPartiesTotales(){
		return this.pGagnes + this.pPerdus;
	}
	
	public int getPPerdus(){
		return this.pPerdus;
	}
	
	public void setPPerdus(int v){
		this.pPerdus=v;
	}
	
	public void setNom(String n){
		this.nom = n;
	}
	
	public String affichePerso(){
		listePerso = new File("Sauvegarde").listFiles();
		ArrayList<String> s = new ArrayList<String>();
		Scanner input = new Scanner(System.in);	
		String j;
		String a;
		
		System.out.println("\nListe joueurs:");
		
		for(int i = 0; i < listePerso.length; i++){
			a = listePerso[i].toString().replace("Sauvegarde\\","");
			s.add(a.replace(".txt",""));
			System.out.println(a.replace(".txt",""));
		}
		
		do{
			System.out.println("\nChoisissez votre joueur: ");
			j = input.nextLine();
		}while(joueurChoix(s,j));
		
		return j;
	}
	
	public boolean joueurChoix(ArrayList<String> tab,String j){
		
		for(int i = 0; i < tab.size(); i++){
			if(tab.get(i).equals(j)){
					return false;
			}
		}
		
		return true;
	}
	
	public void sauvegarde(){
		
		try{
			ObjectOutputStream oos = new ObjectOutputStream(
									new BufferedOutputStream(
									new FileOutputStream(
											new File("Sauvegarde/" + this.getNom() + ".txt"))));
			oos.writeObject(this);
			oos.flush();
			oos.close();
		}
		catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	public Joueur chargement(){
		
		Joueur joueur = null;
		try{
			String s = affichePerso();
			FileInputStream fichier = new FileInputStream("Sauvegarde/"+ s + ".txt");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			joueur = (Joueur) ois.readObject();
			this.nom = joueur.getNom();
			this.pGagnes = joueur.getPGagnes();
			this.pPerdus = joueur.getPPerdus();
			
		}
		catch (final java.io.IOException e) {
		e.printStackTrace();
		
		} catch (final ClassNotFoundException e) {
		
		e.printStackTrace();
		
		}
		
		return joueur;
	}
		
		
		
		
	
	public String toString(){
		return "\nJoueur: " + this.nom + "\nNombre de parties jouées: " + this.nbPartiesTotales() + 
				"\nNombre parties gagnées: " + this.getPGagnes() + "\nNombre parties perdues: " + this.getPPerdus() +"\n";
	}
}
