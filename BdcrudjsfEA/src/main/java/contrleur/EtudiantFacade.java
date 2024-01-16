package contrleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EtudiantFacade {
	private Etudiant etudiant = new Etudiant();
	private List<Etudiant>  listeEtudiants;
	
	
	public EtudiantFacade() { 
		// TODO Auto-generated constructor stub
	}
       
	
	//pour la connection de base de donnee	
		public Connection connectBd() {
			try {
				// choisi notre driver mysql
				Class.forName("com.mysql.jdbc.Driver");
				// connexion a la base de donnee
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdcrudjsf", "root", "");
				//return con
				return con;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Connection con = null;
				return con;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Connection con = null;
				return con;
			}
		}

		
		/**
		 * afficher la liste des etudians qui est dans la base de donne
		 */
		public List<Etudiant> affichListeEtudiants() {
			listeEtudiants = new ArrayList<Etudiant>();
			// la requet sql
			String req = "select * from étudiant";
			try {
				// preparer notre requete
				PreparedStatement ps = connectBd().prepareStatement(req);
				// execution de la requete
				ResultSet result = ps.executeQuery();
				// on recupere le resultat et le stock dans listeEtudian
				while(result.next()) {
	    			//on creer un instance d'etudiant
	    			Etudiant pers = new Etudiant();
	    			pers.setId(result.getInt("id"));
	    			pers.setNom(result.getString("nom"));
	    			pers.setPrenom(result.getString("prenom"));
	    			pers.setDatenaiss(result.getDate("datenaiss"));
	    	        
	    			//ajout des etudiants dans la liste etudiant 
	    			listeEtudiants.add(pers);
	    		}
				return listeEtudiants;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return listeEtudiants;
			}
		}
        
		
		/**
		 * afficher la liste des etudians qui est dans la base de donne
		 */
		public Etudiant affichEtudiant(int id) {
	         return filterListEtdians(affichListeEtudiants(), id);
		}
		
		/**
		 * pour filtre le tableau listeEtudiant
		 */
		  private static Etudiant filterListEtdians(List<Etudiant> etu, int id) {
		        Etudiant filteredPeople = new Etudiant();
		        for (Etudiant etud : etu) {
		            if (etud.getId() == id) {
		                filteredPeople = etud;
		            }
		        }
		        return filteredPeople;
		    }
		
		/**
		 * ajout d'un etudiant
		 */
		public void create(Etudiant e) {
			// la requet sql
			String req = "insert into étudiant (nom,prenom,datenaiss) value (?,?,?) ";
			try {
				//preparation de la requete
				PreparedStatement ps = connectBd().prepareStatement(req);
				//ajout dans la requete sql les ?
				ps.setString(1, e.getNom());
				ps.setString(2, e.getPrenom());
				ps.setDate(3, e.getDatenaiss());
				
				//execution de la requet
				ps.execute();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		/**
		 * @return suppresion d'un etudiant
		 */
		public void delete(Etudiant e) {
			String req ="delete from étudiant where id = ?";
			int id = e.getId();
			try {
				PreparedStatement ps = connectBd().prepareStatement(req);
				
				ps.setInt(1, id);
				ps.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	/**
	 * @return modifier un etudiant
	 */
	public void update(Etudiant e, Integer id) {
		
		try {
			String req = "UPDATE étudiant SET nom = ?, prenom = ?, datenaiss = ? WHERE id = ?";
			PreparedStatement ps = connectBd().prepareStatement(req);
			ps.setString(1, e.getNom());
			ps.setString(2, e.getPrenom());
			ps.setDate(3, e.getDatenaiss());
			ps.setInt(4, id);
			
			System.out.println(id);
			System.out.println(ps);
			
			ps.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * @return the etudiant
	 */
	public Etudiant getEtudiant() {
		return etudiant;
	}


	/**
	 * @param etudiant the etudiant to set
	 */
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}


	/**
	 * @return the listeEtudiants
	 */
	public List<Etudiant> getListeEtudiants() {
		return listeEtudiants;
	}


	/**
	 * @param listeEtudiants the listeEtudiants to set
	 */
	public void setListeEtudiants(List<Etudiant> listeEtudiants) {
		this.listeEtudiants = listeEtudiants;
	}

}
