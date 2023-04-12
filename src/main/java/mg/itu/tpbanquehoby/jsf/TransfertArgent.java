/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquehoby.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Named;
import mg.itu.tpbanquehoby.ejb.GestionnaireCompte;
import mg.itu.tpbanquehoby.entities.CompteBancaire;



/**
 *
 * @author Hoby
 */
@Named(value = "transfertArgent")
@RequestScoped
public class TransfertArgent {

    private Long idSource;
    private Long idDestinataire;
    private int montant;
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    
    public TransfertArgent() {
    }
    
    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdDestinataire() {
        return idDestinataire;
    }

    public void setIdDestinataire(Long idDestinataire) {
        this.idDestinataire = idDestinataire;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    public String validerTransfert(){
        CompteBancaire source = gestionnaireCompte.findCompte(idSource);
        if(source == null){
            FacesMessage message = new FacesMessage("Pas de compte avec id "+idSource);
            // throw new ValidatorException(message);
            addFlashMessage(message);
            return null;
        } else {
            if(source.getSolde() < montant){
                FacesMessage message = new FacesMessage("Solde insuffisant pour cette opération ");
                // throw new ValidatorException(message);
                addFlashMessage(message);
                return null;
            }
        }
        
        CompteBancaire destinataire = gestionnaireCompte.findCompte(idDestinataire);
        if( destinataire == null){
            FacesMessage message = new FacesMessage("Pas de compte avec id "+idDestinataire);
            // throw new ValidatorException(message);
            addFlashMessage(message);
            return null;
        }
        gestionnaireCompte.transferer(source, destinataire, montant);
        FacesMessage message = new FacesMessage("Transfert d'un montant de "+montant+" de "+source.getNom()+" vers "+destinataire.getNom()+" effectué avec succès.");
        addFlashMessage(message);
        return "listeComptes?faces-redirect=true";
    }
    
     public static void addFlashMessage(FacesMessage message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        facesContext.addMessage(null, message);
    }
}
