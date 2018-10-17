package controllers;

import entity.Lokalizacja;
import config.DBManager;
import java.util.List;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManager;


public class LokalizacjaBean
{
    private Lokalizacja lokalizacja;
  
    
    public LokalizacjaBean()
    {
        lokalizacja = new Lokalizacja();
    }
    
    public Lokalizacja getLokalizacja()
    {
        return lokalizacja;
    }
    
    public void setLokalizacja(Lokalizacja lokalizacja)
    {
        this.lokalizacja = lokalizacja;
    }
    
    
  
    
    public List<Lokalizacja>getLista()
    {
        EntityManager em = DBManager.getManager().createEntityManager();
        List list = em.createNamedQuery("Lokalizacja.findAll").getResultList();
        em.close();
        return list;
    }
    
    public String jakasAkcja()
    {
        return "/okinie.xhtml";
    }
    
    public void ustawLokalizacje(ValueChangeEvent e)
    {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.lokalizacja = em.find(Lokalizacja.class, lokalizacja.getIdLokalicacja());
        em.close();
    }
    
    public String przejdzDoKina(int idLokalizacji)
    {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.lokalizacja = em.find(Lokalizacja.class, idLokalizacji);      
        
        em.close();
        
        return "index.xhtml";
    }
}
