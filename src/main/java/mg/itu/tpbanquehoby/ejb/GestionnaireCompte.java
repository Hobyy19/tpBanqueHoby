/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquehoby.ejb;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import mg.itu.tpbanquehoby.entities.CompteBancaire;

/**
 *
 * @author Hoby
 */
@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="root",
    password="root",
    databaseName="banque",
    properties={
        "useSSL=false",
        "allowPublicKeyRetrieval=true"
    }      
)
@Stateless
public class GestionnaireCompte {
    
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;
    
    public void creerCompte(CompteBancaire c){
        em.persist(c);
    }
    
    public List<CompteBancaire> getAllComptes(){
        String s = " select c from CompteBancaire c";
        TypedQuery<CompteBancaire> query = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        List<CompteBancaire> liste = query.getResultList();
        return liste;
    }
    
    public long countCompte(){
        Query query = em.createNamedQuery("CompteBancaire.count");
        Long result = (Long)query.getSingleResult();
        return result.longValue() ;
    }
    
    public void update(CompteBancaire c){
        em.merge(c);
    }
    
    public CompteBancaire findCompte(Long id){
        return em.find(CompteBancaire.class,id);
    }
    
    public void transferer(CompteBancaire source, CompteBancaire destinataire , int montant){
        source.retirer(montant);
        destinataire.deposer(montant);
        update(source);
        update(destinataire);
    }
}
