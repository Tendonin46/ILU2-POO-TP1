package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	private static class Marche {
	    private Etal[] etals;
	   
	    public Marche(int nbEtals) {
	        etals = new Etal[nbEtals];
	        for (int i = 0; i < etals.length; i++) {
				etals[i]=new Etal();
			}
	        
	    }
	    public void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) { 
			if(!etals[indiceEtal].isEtalOccupe()) {
				etals[indiceEtal].occuperEtal(vendeur, produit,nbProduit);
			} else {
				System.out.println("L'etal" + indiceEtal + " est deja occupe par " +etals[indiceEtal].getVendeur().getNom());
		}	
	    }
		public int trouverEtalLibre() {
			for (int i=0; i<etals.length; i++) {
				if(!etals[i].isEtalOccupe()) {
					return i;
			}
		}
				return -1;
	}
		public Etal[] trouverEtals(String produit) {
            		int c = 0;
            		for (int i = 0; i < etals.length; i++) {
                		if (etals[i].contientProduit(produit)) 
                    			c++;
           		 }

           		Etal[] etalsAvecProduit = new Etal[c];
            		int index = 0;

            		for (int i = 0; i < etals.length; i++) {
               			 if (etals[i].contientProduit(produit)) {
                    			etalsAvecProduit[index] = etals[i];
                   	 		index++;
                		}
            		}
            		return etalsAvecProduit;
        	}
		public Etal trouverVendeur (Gaulois gaulois) {
			for (int i=0; i<etals.length; i ++ ) {
				Etal etal = etals[i];
				if(etal.isEtalOccupe() && etal.getVendeur() == gaulois) {
					return etal;  
					}
		}
			return null;
		} 
		public String afficherMarche () {
			StringBuilder result = new StringBuilder();
			int nbEtalVide = 0;

			for (int i = 0; i < etals.length; i++) {
				Etal etal = etals[i];
				if (etal.isEtalOccupe()) {
					result.append(etal.afficherEtal()).append("\n");
				} else {
					nbEtalVide++;
        }
    }
			if (nbEtalVide > 0) {
				result.append("Il reste ").append(nbEtalVide).append(" etals non utilises dans le marche.\n");
    }

			return result.toString();
		}
		
		
	}
	
}
