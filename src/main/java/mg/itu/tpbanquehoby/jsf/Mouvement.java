/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquehoby.jsf;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.OptimisticLockException;
import java.io.Serializable;
import mg.itu.tpbanquehoby.ejb.GestionnaireCompte;
import mg.itu.tpbanquehoby.entities.CompteBancaire;
import mg.itu.tpbanquehoby.jsf.util.Util;



/**
 *
 * @author Hoby
 */
@Named(value = "mouvementBean")
@ViewScoped
public class Mouvement implements Serializable{

  @EJB
  private GestionnaireCompte gestionnaireCompte;
  
  private Long id;
  private CompteBancaire compte;
  private String typeMouvement;
  private int montant;

  public int getMontant() {
    return montant;
  }

  public void setMontant(int montant) {
    this.montant = montant;
  }

  public String getTypeMouvement() {
    return typeMouvement;
  }

  public void setTypeMouvement(String typeMouvement) {
    this.typeMouvement = typeMouvement;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CompteBancaire getCompte() {
    return compte;
  }
  
  public void loadCompte() {
    compte = gestionnaireCompte.findCompte(id);
  }
  
  // La méthode doit avoir cette signature.
  public void validateSolde(FacesContext fc, UIComponent composant, Object valeur) {
    UIInput composantTypeMouvement = (UIInput)composant.findComponent("typeMouvement");
    // Sans entrer dans les détails, il faut parfois utiliser
    // getSubmittedValue() à la place de getLocalValue.
    // typeMouvement n'est pas encore mis tant que la validation n'est pas finie.
    String valeurTypeMouvement = (String)composantTypeMouvement.getLocalValue();
    if (valeurTypeMouvement == null) {
      // Pour le cas où l'utilisateur a soumis le formulaire sans indiquer le type du mouvement
      return;
    }
    if (valeurTypeMouvement.equals("****")) {
      int retrait = (int) valeur;
      if (compte.getSolde() < retrait) {
        FacesMessage message = new FacesMessage("Le retrait doit être inférieur au solde du compte");
        Util.addFlashMessage(message);
        
      }
    }
  }
  
  public String enregistrerMouvement() {
    try{
      if (typeMouvement.equals("ajout")) {
        gestionnaireCompte.deposer(compte, montant);
      } else {
        gestionnaireCompte.retirer(compte, montant);
      }
      Util.addFlashInfoMessage("Mouvement enregistré sur compte de " + compte.getNom());
      return "listeComptes?faces-redirect=true";  
    } catch ( EJBException e){
        Throwable cause = e.getCause();
        if (cause != null) {
            if (cause instanceof OptimisticLockException) {
              Util.messageErreur("Le compte de " + compte.getNom()+ " a été modifié ou supprimé par un autre utilisateur !");  
            } else { 
              Util.messageErreur(cause.getMessage());
            }
        } else { 
            Util.messageErreur(e.getMessage());
        }
        return null;
    }
    
  }
    
}
