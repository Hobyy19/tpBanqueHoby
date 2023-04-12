/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquehoby.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import mg.itu.tpbanquehoby.ejb.GestionnaireCompte;
import mg.itu.tpbanquehoby.entities.CompteBancaire;
import mg.itu.tpbanquehoby.jsf.util.Util;



/**
 *
 * @author Hoby
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {

    private String nom;
    private int solde;

    @EJB
    private GestionnaireCompte gestionnaireCompte;
    
    public AjoutCompte() {
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    public String ajouterCompte(){
        CompteBancaire c = new CompteBancaire(nom , solde);
        gestionnaireCompte.creerCompte(c);
        FacesMessage message = new FacesMessage("Compte créé avec succès.");
        Util.addFlashMessage(message);
        return "listeComptes?faces-redirect=true";
    }
}
