package controllers;

import entity.Seans;
import entity.Miejsca;
import entity.Rezerwacja;
import entity.RezerSeanse;
import entity.RezerSeansePK;
import entity.Klient;
import java.util.List;
import config.DBManager;
import entity.RodzajBiletu;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;


public class RezerwacjeBean
{
    private int idSeans;
    private Rezerwacja rezerwacja;
    private RezerSeanse rezerwacjeSeanse;
    private RezerSeansePK rezerSeansePK;
    private RodzajBiletu rodzajBiletu;
    
    private Seans seans;
    private String tytylFilmu;
    private String godzina;
    private String data;
   
    
    private int iloscRzedow;
    private int iloscMiejscWRzedzie;
    private List<Miejsca> miejscaZajete;
    
    
    private List<WybraneMiejsce> wybraneMiejsca;
    
    private int ile;
    
    private boolean wybranoMiejsca;
    
    private Klient klientId;
    private boolean czyKlientJestWBazie;
    
    private int idRezerwacji;
    
    public RezerwacjeBean()
    {
        this.initiation();
    }
    
    public void initiation()
    {
        wybranoMiejsca = false;
        rezerwacja = new Rezerwacja();
        rezerSeansePK = new RezerSeansePK();
        rezerwacjeSeanse = new RezerSeanse();
        seans = new Seans();
    }
    
    public int getIloscRzedow()
    {
        return iloscRzedow;
    }
    
    public void setIloscRzedow(int iloscRzedow)
    {
        this.iloscRzedow = iloscRzedow;
    }
    
    public int getIloscMiejscWRzedzie()
    {
        return iloscMiejscWRzedzie;
    }
    
    
    public int getIdSeans()
    {
        return idSeans;
    }
    
    public void setIdSeans(int idSeans)
    {
        this.idSeans = idSeans;
    }
    
    
    public Seans getSeans()
    {
        return seans;
    }
    
    
    
    public String getGodzina()
    {
        return godzina;
    }
    
    public String getData()
    {
        return data;
    }
    
    
    
    public List<Miejsca> getMiejscaZajete()
    {
        return miejscaZajete;
    }
    
    
     public String getTytulFilmu()
     {
        return tytylFilmu;
     }
     
     public void setTytulFilmu(String tytulFilmu)
     {
        this.tytylFilmu = tytulFilmu;
     }
     
     public boolean isWybranoMiejsca()
     {
        return wybranoMiejsca;
     }
  
    
    
     
     
     public void setWybraneMiejsca(List<WybraneMiejsce> wybraneMiejsca)
     {
        this.wybraneMiejsca = wybraneMiejsca;
     }
     
     public int getIle()
     {
        return ile;
     }
     
     
     
     public Klient getKlientId()
     {
        return klientId;
     }
     
     
     public void setKlientId(Klient klientId)
     {
        this.klientId = klientId;
     }
     
     public List<WybraneMiejsce> getWybraneMiejsca()
     {
        return wybraneMiejsca;
     }
     
     public int getIdRezerwacji()
     {
         return idRezerwacji;
     }
     
     public List<WybraneMiejsce> getWybraneMiejscaList()
     {    
         boolean zmianaMiejsc = false;
         
         Map<String, String> daneZFormularza = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
         if ( !(daneZFormularza.isEmpty() ) )
            if ( wybranoMiejsca  )
            {
                zmianaMiejsc = true;
                this.wybraneMiejsca.clear();
            }
                      
         if ( (wybraneMiejsca == null) || zmianaMiejsc )
         {
            this.wybraneMiejsca = new ArrayList();
            ile = daneZFormularza.size() / 3 ;
            wybranoMiejsca = true;

            WybraneMiejsce wM = new WybraneMiejsce();
            int i = 0;
            for (String key : daneZFormularza.keySet())
            {
               switch(i)
               {
                   case 0:
                   {
                       i++;
                       wM.setRzad(daneZFormularza.get(key));
                       System.out.println("setRzad");
                       break;
                   }

                   case 1:
                   {
                       i++;
                       wM.setMiejsceWRzedzie(daneZFormularza.get(key));
                        System.out.println("setMiejsceWRzad");
                       break;
                   }

                   case 2:
                   {
                       i++;
                       wM.setRodzajBiletu(daneZFormularza.get(key));
                        System.out.println("setRodzajBiletu");
                       break;
                   }
               }

               if ( i == 3)
               {
                    i = 0;
                    this.wybraneMiejsca.add(wM);
                    wM = new WybraneMiejsce();
               }
            }
         }
         return wybraneMiejsca;
     }
     
     
     
    
    private void pobierzMiejsca()
    {
        EntityManager em = DBManager.getManager().createEntityManager();
   
        seans = em.find(Seans.class, new Integer(idSeans));
        iloscRzedow = seans.getIdSali().getIloscrzedow();
        iloscMiejscWRzedzie = seans.getIdSali().getIloscMiejscWrzedzie();
          
        SimpleDateFormat dataFormat = new SimpleDateFormat("EEEE dd MMMMM");
        
        StringBuilder sb = new StringBuilder( dataFormat.format( seans.getData() ) );
        
        this.data = sb.replace(0, 1, new Character( sb.toString().charAt(0) ).toString().toUpperCase() ).toString();
        
        
 
        miejscaZajete = em.createNamedQuery("Miejsca.findUnavilableByIdSeans").setParameter("idSeans", seans).getResultList();
        
        em.close();
    }
    
    
    
    
    public  String przejdzDoWyboruMiejsc( int idSeans, String tytulFilmu, String godzina)
    {
        this.idSeans = idSeans;
        this.godzina = godzina;
        this.tytylFilmu = tytulFilmu;
        this.pobierzMiejsca();
        
        return "rezerwacje.xhtml";
    }
    
    
    public String sprawdzCzyZalogowany(boolean zalogowany)
    {
        if (zalogowany) 
        {
            czyKlientJestWBazie = true;
            return "rezerwacjePodsumowanie.xhtml";
        }
        else
        {
            czyKlientJestWBazie = false;
            return "rezerwacjeDaneOsob.xhtml";
        }
    }
    
    
    public void setCzyJestWBazie(boolean czyJestWBazie)
    {
        this.czyKlientJestWBazie = czyJestWBazie;
    }
    
    public String previousStep(boolean zalogowany)
    {
        if (zalogowany) 
        {       
            return "rezerwacjePotwier.xhtml";
        }
        else
        {        
            return "rezerwacjeDaneOsob.xhtml";
        }
    }
    
    
    private void addKlient()
    {   
        EntityManager em = DBManager.getManager().createEntityManager();
  
        klientId.setIdKlient(null);
        
        em.getTransaction().begin();
        em.persist(klientId);
        em.getTransaction().commit();
        em.close(); 
    }
    
    
    public String goNextStep()
    {
        return "rezerwacjePodsumowanie.xhtml";
    }
    
    
    private void setMiejsceToUnavilable(Miejsca m)
    {
        EntityManager em = DBManager.getManager().createEntityManager();
        m.setWolne(false);
        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
        
        em.close();       
    }
    
    
    private void addReservationPlaces(Miejsca m)
    {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.rezerSeansePK.setIdMiejsca( m.getIdMiejsca() );
        this.rezerSeansePK.setIdRezerwacja(this.rezerwacja.getIdRezerwacja());
        this.rezerwacjeSeanse.setIdSeans(seans);
        this.rezerwacjeSeanse.setRezerSeansePK(rezerSeansePK);
        this.rezerwacjeSeanse.setMiejsca(m);
        this.rezerwacjeSeanse.setRezerwacja(rezerwacja);
                    
        em.getTransaction().begin();
        em.persist(this.rezerwacjeSeanse);
        em.getTransaction().commit();
                    
        this.rezerSeansePK = new RezerSeansePK();
        this.rezerwacjeSeanse = new RezerSeanse();
        
        em.close();
    }
    
    
    
    private void setRodzajBiletoToRezer(WybraneMiejsce wm)
    {
        EntityManager em = DBManager.getManager().createEntityManager();
        
        rodzajBiletu = (RodzajBiletu) em.createNamedQuery("RodzajBiletu.findByRodzaj").setParameter("rodzaj", wm.getRodzajBiletu()).getSingleResult();
        
        this.rezerwacjeSeanse.setIdRodzBi(rodzajBiletu);
        
        em.close();
    }
    
    
    public String wykonajRezerwacje()
    {
        if(!czyKlientJestWBazie)
        {
            this.addKlient();
        }
        
        EntityManager em = DBManager.getManager().createEntityManager();
        
        this.rezerwacja.setIdKlient(klientId);
        this.rezerwacja.setDataRez(new Date());
        this.rezerwacja.setIdRezerwacja(null);

        em.getTransaction().begin();
        em.persist(rezerwacja);
        em.getTransaction().commit();
       
        
        List<Miejsca> wolneMiejsca = em.createNamedQuery("Miejsca.findByAllFree").setParameter("idSeans", seans).getResultList();
        
       
        em.close();
         
         
        for (WybraneMiejsce wm : wybraneMiejsca)
        {
            for(Miejsca m : wolneMiejsca)
            {
                if (m.getNrrzedu().equals( wm.getRzad().toUpperCase() ) && (m.getNrMiejsca() == Integer.parseInt(wm.getMiejsceWRzedzie()))) 
                {
                    this.setMiejsceToUnavilable(m);
                    this.setRodzajBiletoToRezer(wm);
                    this.addReservationPlaces(m);
                    
                    break;
                }
            }
        } 
        
        this.idRezerwacji = this.rezerwacja.getIdRezerwacja();
        this.initiation();
        
        return "rezerwacjeKoniec.xhtml";  
    }
            
}
