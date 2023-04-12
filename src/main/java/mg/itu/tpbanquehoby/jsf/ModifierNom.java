/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquehoby.jsf;


import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import mg.itu.tpbanquehoby.ejb.GestionnaireCompte;
import mg.itu.tpbanquehoby.entities.CompteBancaire;

/**
 *
 * @author Hoby
 */
@Named(value = "modifierNom")
@ViewScoped
public class ModifierNom implements Serializable {

    private Long id;
    private CompteBancaire compte;
    
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    
    public ModifierNom() {
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

    public String modifierNom() {
        gestionnaireCompte.update(compte);
        return "listeComptes?faces-redirect=true";
    }

    public void loadCompte() {
        compte = gestionnaireCompte.findCompte(id);
    }
}
