/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquehoby.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import mg.itu.tpbanquehoby.entities.CompteBancaire;

/**
 *
 * @author Hoby
 */
@Singleton
@Startup
public class Init {
    
    @EJB
    private GestionnaireCompte gestionnaireCompte;

    @PostConstruct
    public void initializeComptes() {
        if (gestionnaireCompte.countCompte() == 0) {
            CompteBancaire compte1 = new CompteBancaire("John Lennon", 150000);
            gestionnaireCompte.creerCompte(compte1);
            CompteBancaire compte2 = new CompteBancaire("Paul McCartney", 950000);
            gestionnaireCompte.creerCompte(compte2);
            CompteBancaire compte3 = new CompteBancaire("Ringo Starr,", 20000);
            gestionnaireCompte.creerCompte(compte3);
            CompteBancaire compte4 = new CompteBancaire("Georges Harrisson", 100000);
            gestionnaireCompte.creerCompte(compte4);
        }
    }
}
