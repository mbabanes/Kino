package controllers;

import config.DBManager;
import entity.Bilet;
import entity.Klient;
import entity.Miejsca;
import entity.RodzajBiletu;
import entity.Seans;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;





public class BiletBean
{
    private int idSeans;
    private Seans seans;
    private String tytylFilmu;
    private String godzina;
    private String data;
    
    private Bilet bilet;
    private RodzajBiletu rodzajBiletu;
    
    
    private int iloscRzedow;
    private int iloscMiejscWRzedzie;
    private List<Miejsca> miejscaZajete;
    
    
    private List<WybraneMiejsce> wybraneMiejsca;
    
    private int ile;
    private double koszt;
    
    private boolean wybranoMiejsca;
    
    private Klient klientId;
    private boolean czyKlientJestWBazie;
    
    private int idRezerwacji;
    
    
    public BiletBean()
    {
        this.initiation();
    }
    
    
  
    
    private void initiation()
    {
        wybranoMiejsca = false;
        seans = new Seans();
        bilet = new Bilet();
        rodzajBiletu = new RodzajBiletu();
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
     
     
     public List<WybraneMiejsce> getWybraneMiejsca()
     {
        return wybraneMiejsca;
     }
     
     public int getIle()
     {
        return ile;
     }
     
     
     public double getKoszt()
     {
         return koszt;
     }
     
     
     
     public Klient getKlientId()
     {
        return klientId;
     }
     
     
     public void setKlientId(Klient klientId)
     {
        this.klientId = klientId;
     }
     
    
     
     public int getIdRezerwacji()
     {
         return idRezerwacji;
     }
     
     
     
     
    private void pobierzZajeteMiejsca()
    {
        EntityManager em = DBManager.getManager().createEntityManager();
   

        miejscaZajete = em.createNamedQuery("Miejsca.findUnavilableByIdSeans").setParameter("idSeans", seans).getResultList();
        
        em.close();
    }
    
    
    private void pobierzSeans()
    {
        EntityManager em = DBManager.getManager().createEntityManager();
   
        seans = em.find(Seans.class, new Integer(idSeans));
        
        em.close();
    }
    
    private void pobierzInfoOSali()
    {
        iloscRzedow = seans.getIdSali().getIloscrzedow();
        iloscMiejscWRzedzie = seans.getIdSali().getIloscMiejscWrzedzie();
    }
    
    private void ustawDate()
    {
        SimpleDateFormat dataFormat = new SimpleDateFormat("EEEE dd MMMMM");
        
        StringBuilder sb = new StringBuilder( dataFormat.format( seans.getData() ) );
        
        this.data = sb.replace(0, 1, new Character( sb.toString().charAt(0) ).toString().toUpperCase() ).toString();
    }
    
    
    public String przejdzDoWyboruMiejsc(int IdSeans, String tytulFilmu, String godzina)
    {
        this.idSeans = IdSeans;
        this.tytylFilmu = tytulFilmu;
        this.godzina = godzina;
        
        this.pobierzSeans();
        this.pobierzInfoOSali();
        this.ustawDate();
        
        this.pobierzZajeteMiejsca();
        
        return "kupowanieWyborMiejsc.xhtml";
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
            koszt  = ile * 18 + 0.00;
            
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
                       break;
                   }

                   case 1:
                   {
                       i++;
                       wM.setMiejsceWRzedzie(daneZFormularza.get(key));
                       break;
                   }

                   case 2:
                   {
                       i++;
                       wM.setRodzajBiletu(daneZFormularza.get(key));
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
    
    public String sprawdzCzyZalogowany(boolean zalogowany)
    {
        if (zalogowany) 
        {
            czyKlientJestWBazie = true;
            return "kupowaniePodsumowanie.xhtml";
        }
        else
        {
            czyKlientJestWBazie = false;
            return "kupowanieDaneOsob.xhtml";
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
            return "kupowaniePotwierdzenie.xhtml";
        }
        else
        {        
            return "kupowanieDaneOsob.xhtml";
        }
    }
    
    public String goNextStep()
    {
        return "kupowaniePodsumowanie.xhtml";
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
    
    
    
    private void setMiejsceToUnavilable(Miejsca m)
    {
        EntityManager em = DBManager.getManager().createEntityManager();
        m.setWolne(false);
        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
        
        em.close();       
    }
    
    
    private void setRodzajBiletoToBilet(WybraneMiejsce wm)
    {
        EntityManager em = DBManager.getManager().createEntityManager();
        
        rodzajBiletu = (RodzajBiletu) em.createNamedQuery("RodzajBiletu.findByRodzaj").setParameter("rodzaj", wm.getRodzajBiletu()).getSingleResult();
        
        this.bilet.setIdRodzBi(rodzajBiletu);
        
        em.close();
    }
            
    
    
    private void checkMiejsce(WybraneMiejsce wm, List<Miejsca> wolneMiejsca)
    {
        for ( Miejsca m : wolneMiejsca)
        {
            if (m.getNrrzedu().equals( wm.getRzad().toUpperCase() ) && (m.getNrMiejsca() == Integer.parseInt(wm.getMiejsceWRzedzie())))
            {
                this.setMiejsceToUnavilable(m);
                this.bilet.setIdMiejsca(m);
                break;
            }
        }
    }
    
    public String kupBilet()
    {
        if(!czyKlientJestWBazie)
        {
            this.addKlient();
        }
        
        
        EntityManager em = DBManager.getManager().createEntityManager();
        
        List<Miejsca> wolneMiejsca = em.createNamedQuery("Miejsca.findByAllFree").setParameter("idSeans", seans).getResultList();
        
        
        for(WybraneMiejsce wm : wybraneMiejsca)
        {
            bilet.setIdBilet(null);
            bilet.setIdKlient(klientId);
            bilet.setIdPracownik(null);
            bilet.setIdSeans(seans);
            this.checkMiejsce(wm, wolneMiejsca);
            this.setRodzajBiletoToBilet(wm);
            
            em.getTransaction().begin();
            em.persist(bilet);
            em.getTransaction().commit();
            
            bilet = new Bilet();
            rodzajBiletu = new RodzajBiletu();
            
            
        }
        klientId = new Klient();
        seans = new Seans();
        em.close();
        
        
        return "kupowanieKoniec.xhtml";
    }
    
}
