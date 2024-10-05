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
		marche = new Marche(nbEtalsMarche);
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
			StringBuilder chaine = new StringBuilder();
			int nbEtalVide = 0;

			for (int i = 0; i < etals.length; i++) {
				Etal etal = etals[i];
				if (etal.isEtalOccupe()) {
					chaine.append(etal.afficherEtal()).append("\n");
				} else {
					nbEtalVide++;
        }
    }
			if (nbEtalVide > 0) {
				chaine.append("Il reste ").append(nbEtalVide).append(" etals non utilises dans le marche.\n");
    }

			return chaine.toString();
		}
		
		public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
    int indiceEtalLibre = marche.trouverEtalLibre();
    StringBuilder message = new StringBuilder();
    
    if (indiceEtalLibre != -1) {
        marche.utiliserEtal(indiceEtalLibre, vendeur, produit, nbProduit);
        message.append(vendeur.getNom())
               .append(" vend ")
               .append(nbProduit)
               .append(" ")
               .append(produit)
               .append(" à l'etal ")
               .append(indiceEtalLibre)
               .append(".");
    } else {
        message.append("Aucun etal disponible pour installer le vendeur ")
               .append(vendeur.getNom())
               .append(".");
    }
    
    return message.toString();
}
	public String rechercherVendeursProduit(String produit) {
    StringBuilder chaine = new StringBuilder();
    chaine.append("Vendeurs de ").append(produit).append(" : ");
    
    Etal[] etals = marche.getEtals();
    boolean vendeurTrouve = false;

    for (int i = 0; i < etals.length; i++) {
        if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
            chaine.append(etals[i].getVendeur().getNom()).append(", ");
            vendeurTrouve = true;
        }
    }
    
    if (vendeurTrouve) {
        chaine.setLength(chaine.length() - 2);
    } else {
   
        chaine.append("Aucun vendeur trouvé.");
    }

    return chaine.toString();
}
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
}

	public String partirVendeur(Gaulois vendeur) throws EtalNonOccupeException {
    Etal etal = marche.trouverVendeur(vendeur);
    if (etal != null) {
        etal.libererEtal();
        return vendeur.getNom() + " quitte l etal.";
    } else {
        return vendeur.getNom() + " n'est pas un vendeur dans le marche.";
    }
}
		public String afficherMarche() {
		return marche.afficherMarche();
}
	}
	
}
