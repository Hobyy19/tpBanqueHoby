/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquehoby.jsf;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import mg.itu.tpbanquehoby.ejb.GestionnaireCompte;
import mg.itu.tpbanquehoby.entities.CompteBancaire;
import mg.itu.tpbanquehoby.entities.OperationBancaire;



/**
 *
 * @author Hoby
 */
@Named(value = "historique")
@ViewScoped
public class Historique implements Serializable {

    private Long id;
    private CompteBancaire compte;
    
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    
    
    public Historique() {
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

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public GestionnaireCompte getGestionnaireCompte() {
        return gestionnaireCompte;
    }

    public void setGestionnaireCompte(GestionnaireCompte gestionnaireCompte) {
        this.gestionnaireCompte = gestionnaireCompte;
    }
    
    public void loadCompte() {
        this.compte = gestionnaireCompte.findCompte(id);
    }

    public List<OperationBancaire> getOperations(){
        return this.compte.getOperations();
    }
}
