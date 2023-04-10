/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquehoby.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
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
        CompteBancaire destinataire = gestionnaireCompte.findCompte(idDestinataire);
        gestionnaireCompte.transferer(source, destinataire, montant);
        return "listeComptes";
    }
}
