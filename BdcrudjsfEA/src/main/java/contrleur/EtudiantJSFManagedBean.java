package contrleur;



import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.ToggleEvent;

@RequestScoped
@Named
public class EtudiantJSFManagedBean {
	private EtudiantFacade etudiantFace = new EtudiantFacade();
	private Etudiant etudiant ;
	private Date dates;
	private boolean affich=false; 
	private static Integer idEtu;
	
	/**
	 * @return the dates
	 */

	public EtudiantJSFManagedBean() {
		// TODO Auto-generated constructor stub
		etudiant = new Etudiant();
	}
	
	/**
	 * pour l'ajout d'un etudiant dans la bd 
	 */
	public String ajouterEtudaint() {
		etudiant.setDatenaiss(sqlDate(dates));
		etudiantFace.create(etudiant);
		etudiantFace.affichListeEtudiants();
		etudiant = new Etudiant();
		dates = null;
		return "index.xhtml";
	}
	
	/**
	 * pour l'supprimer un etudiant dans la bd 
	 */
	public String supprimerEtudiant(Etudiant etu) {
		etudiantFace.delete(etu);
		etudiantFace.affichListeEtudiants();
		return "index.xhtm";
		
	}
	/**
	 * pour modifier un etudiant dans la bd 
	 */
	public String modifier(Etudiant etu) {
		System.out.println("## Appel "+ idEtu);
		etu.setDatenaiss(sqlDate(dates));
		etudiantFace.update(etu, idEtu);
		etudiantFace.affichListeEtudiants();
		affich = false;
		etudiant = new Etudiant();
		dates = null;
		 return "index.xhtml";
	}
	
	/**
	 * conversion de la date en java.sql.Date
	 */
	public java.sql.Date sqlDate(java.util.Date calendarDate) {
		  return new java.sql.Date(calendarDate.getTime());
		}
	/**
	 * pour afficher le formulaire du user a modifier
	 */
	
	public String affiche(Etudiant etu) {
		affich = true;
		idEtu = etu.getId();
		etudiant = etu;
		 System.out.println("## Appel "+ etudiant.toString());
		 System.out.println("## Appel "+ idEtu);
		 dates = etudiant.getDatenaiss();
		return null;
	}

	
	/**
	 * @return the idEtu
	 */
	public int getIdEtu() {
		return idEtu;
	}
	
	public String fermer() {
		affich = false;
        return "index.xhtml";
	}

	/**
	 * @param idEtu the idEtu to set
	 */
	
	public void onClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
        FacesContext.getCurrentInstance().addMessage(null, message);
        fermer();
    }
     
    public void onToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
	
	public Date getDates() {
		return dates;
	}

	/**
	 * @param dates the dates to set
	 */
	public void setDates(Date dates) {
		this.dates = dates;
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
		return etudiantFace.affichListeEtudiants();
	}

	/**
	 * @param listeEtudiants the listeEtudiants to set
	 */
	public void setListeEtudiants(List<Etudiant> listeEtudiants) {
	}

	public boolean isAffich() {
		return affich;
	}

	public void setAffich(boolean affich) {
		this.affich = affich;
	}
	
	 

}
