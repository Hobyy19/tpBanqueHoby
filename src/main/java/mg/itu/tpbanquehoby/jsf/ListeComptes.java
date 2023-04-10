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


/**
 *
 * @author Hoby
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable  {

    private List<CompteBancaire> allComptes;  
    
    @EJB  
    private GestionnaireCompte gestionnaireCompte;
    
    public ListeComptes() {
    }
    
    public List<CompteBancaire> getAllComptes() {
    if (allComptes == null) {
      allComptes = gestionnaireCompte.getAllComptes();
    }
    return allComptes;
  }  
}
