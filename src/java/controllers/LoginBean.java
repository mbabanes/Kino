package controllers;

import entity.Klient;
import config.DBManager;
import java.util.List;
import javax.persistence.EntityManager;


public class LoginBean
{ 
    private CurrentKlient currentKlient;
    private String haslo;
    private String email;
    
    
    
    
    public LoginBean()
    {       
        currentKlient = new CurrentKlient();
    }
    
    
    public CurrentKlient getCurrentKlient()
    {
        return currentKlient;
    }
    
    public void setCurrentKlient(CurrentKlient currentKlient)
    {
        this.currentKlient = currentKlient;
    }
    
    public String getHaslo()
    {
        return haslo;
    }
    
    public void setHaslo(String haslo)
    {
        this.haslo = haslo;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public String loginAction()
    {
        if (this.checkKlient())
        {
            return "/okinie.xhtml";
        }
        
        currentKlient.setLogin(true);
        
        return "/index.xhtml";
    }
    
    
    public String loginActionReservation()
    {
        
        if (this.checkKlient())
        {
            return null;
        }
        
        currentKlient.setLogin(true);
        
        return "rezerwacjePodsumowanie.xhtml";
    }
    
    
    public String loginActionBuying()
    {
        
        if (this.checkKlient())
        {
            return null;
        }
        
        currentKlient.setLogin(true);
        
        return "kupowaniePodsumowanie.xhtml";
    }
    
    
    private boolean checkKlient()
    {
        EntityManager em = DBManager.getManager().createEntityManager();
        List<Klient> klientList = em.createNamedQuery("Klient.loginAutorization").setParameter("email", email).setParameter("haslo", haslo).getResultList();
        em.close();
        boolean klientExist = klientList.isEmpty();
        
        if (klientExist)
        {
            return klientExist;
        }
        
        currentKlient.setKlient(klientList.get(0)); 
        
        return klientExist;
    }
    
    
    public String logoutAction()
    {
        currentKlient = new CurrentKlient();
        haslo = new String();
        email = new String();
        return "index.xhtml";
    }
            
    
    
}
