import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;

public class Classement {
	
	private File[] listePerso;

	public Classement(){
		chargementJoueurs();
	}
	
	public ArrayList<String> listePerso(){
		listePerso = new File("Sauvegarde").listFiles();
		ArrayList<String> s = new ArrayList<String>();
		Scanner input = new Scanner(System.in);	
		String j;
		String a;
		
		for(int i = 0; i < listePerso.length; i++){
			a = listePerso[i].toString().replace("Sauvegarde\\","");
			s.add(a.replace(".txt",""));
		}
				
		return s;
	}
	
	public void chargementJoueurs(){
		
		Joueur joueur = null;
		ArrayList<String> s = listePerso();
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		
		try{
			
			for(int i = 0; i < s.size();i++){
				FileInputStream fichier = new FileInputStream("Sauvegarde/"+ s.get(i) + ".txt");
				ObjectInputStream ois = new ObjectInputStream(fichier);
				joueur = (Joueur) ois.readObject();
				joueurs.add(joueur);
			}
		}
		catch (final java.io.IOException e) {
		e.printStackTrace();
		
		} catch (final ClassNotFoundException e) {
		
		e.printStackTrace();
		
		}
		
		affichageClassement(joueurs);
	}
	
	public void affichageClassement(ArrayList<Joueur> joueurs){
		
		for(int i = 0; i < joueurs.size() - 2; i++){
			for(int j = joueurs.size()-1 ; i < j; j--){
				if (joueurs.get(j).getPGagnes() < joueurs.get(j-1).getPGagnes())
                {
                        Joueur x = joueurs.get(j-1);
                        joueurs.set(j-1,joueurs.get(j));
                        joueurs.set(j,x);
                }
			}
		}
		
		for(int i = 0 ; i < joueurs.size() ; i++){
			System.out.println("Joueur n°" + (joueurs.size()-i) + ": " + joueurs.get(i));
		}
	}
	
}