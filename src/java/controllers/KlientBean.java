package controllers;

import config.DBManager;
import entity.Klient;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

public class KlientBean
{
    private Klient klient;
    private String email;
    private String haslo;
    private String haslo2;
    private String imie;
    private String nazwisko;
    
    
    private boolean registration;
    
    public KlientBean()
    {
        klient = new Klient();
        registration = true;
    }
    
    public Klient getKlient()
    {
        return klient;
    }
    
    public void setKlient(Klient klient)
    {
        this.klient = klient;
    }
    
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getHaslo()
    {
        return haslo;
    }
    
    public void setHaslo(String haslo)
    {
        this.haslo = haslo;
    }
    
     public String getHaslo2()
    {
        return haslo2;
    }
    
    public void setHaslo2(String haslo2)
    {
        this.haslo2 = haslo2;
    }
    
    public String getImie()
    {
        return imie;
    }
    
    public void setImie(String imie)
    {
        this.imie = imie;
    }
    
    public String getNazwisko()
    {
        return nazwisko;
    }
    
    public void setNazwisko(String nazwisko)
    {
        this.nazwisko = nazwisko;
    }
    
    
    
    
    
    private boolean validationKlient()
    {
        if (!this.checkEmail())
        {
            this.dodajInformacje(email);
            return false;
        }
        if (!this.checkHaslo())
        {
            this.dodajInformacje("Blad hasla");
            return false;
        }
           
            
        if ( !this.klientExsist() )
        {
            this.dodajInformacje("Klient exist");
            return false;
        }
           
       return true; 
        
    }
    
    
    private boolean checkHaslo()
    {
        if (haslo == null) return false;
        
        if (haslo.length() > 20 || haslo.length() == 0) return false;
        
        if (!haslo.equals(haslo2)) return false;
        
        return true;
    }
    
    private boolean checkEmail()
    {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        
        boolean matchFound = m.matches();
        
        return matchFound;
    }
    
    
    public boolean klientExsist()
    {
        EntityManager em = DBManager.getManager().createEntityManager();
        boolean klientExist;
        klientExist = em.createNamedQuery("Klient.findByEmail").setParameter("email", email).getResultList().isEmpty();
        
        em.close();
        return klientExist;
        
    }
    
    public String addKlient()
    {
        if ( !this.validationKlient() )
        {
            registration = false;
            return null;
        }
        
        EntityManager em = DBManager.getManager().createEntityManager();
        klient.setEmail(email);
        klient.setHaslo(haslo);
        klient.setImie(imie);
        klient.setNazwisko(nazwisko);
        klient.setIdKlient(null);
        
        em.getTransaction().begin();
        em.persist(klient);
        em.getTransaction().commit();
        em.close();
        
        klient = new Klient();
        
        return "/logowanie.xhtml";
        
    }
    
     public void addKlientReservation()
    {
        if ( !this.validationKlient() )
        {
            registration = false;
            
        }
        else
        {
           EntityManager em = DBManager.getManager().createEntityManager();
            klient.setEmail(email);
            klient.setHaslo(haslo);
            klient.setImie(imie);
            klient.setNazwisko(nazwisko);
            klient.setIdKlient(null);

            em.getTransaction().begin();
            em.persist(klient);
            em.getTransaction().commit();
            em.close();

            klient = new Klient();
 
        }
    }
    
    
    public void dodajInformacje(String s)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, s, ""));
    }
            
    
}
