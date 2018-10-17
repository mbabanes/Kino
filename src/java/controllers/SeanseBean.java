package controllers;

import entity.Filmy;
import entity.Seans;
import config.DBManager;
import entity.Lokalizacja;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.List;

import javax.persistence.EntityManager;



public class SeanseBean
{
    private List<FilmsSeans> filmsSeans;
    private Lokalizacja lokalizacja;
    
    private String cosTam;
    private List<DniRepertuaru> dni;
    private Date currentDay;
    private String currentDayToShow;
   
    
    
    public SeanseBean()
    {
        this.uzypelnijDaty();

    }
    
    public Lokalizacja getLokalizacja()
    {
        return lokalizacja;
    }
    
    public void setLokalizacja(Lokalizacja lokalizacja)
    {
        this.lokalizacja = lokalizacja;
    }
    
    
    
    public List<FilmsSeans> getFilmsSeansList()
    {
        return filmsSeans;
    }
    
    public void setFilmsSeansList(List<FilmsSeans> filmsSeans)
    {
        this.filmsSeans = filmsSeans;
    }
    
    
    
    public List<DniRepertuaru> getDni()
    {
        return dni;
    }
    
    public Date getCurrentDay()
    {
        return currentDay;
    }
    
    public String getCurrentDayToShow()
    {
        return currentDayToShow;
    }
    
    public String getCosTam()
    {
        return cosTam;
    }
    
   
    
    public List<FilmsSeans> getSeansFotTodayList() 
    {
       EntityManager em = DBManager.getManager().createEntityManager();
       

             GregorianCalendar today = new GregorianCalendar(2016, 5, 3 , 0, 0);
            
           List<Filmy> filmList = em.createQuery("SELECT DISTINCT(f) FROM Filmy f, Seans s WHERE s.data = :data AND f = s.filmySet AND s.idLokalicacja = :lokalizacja").setParameter("data", today.getTime()).setParameter("lokalizacja", lokalizacja).getResultList();
            
             FilmsSeans fs;
             GodzinaSeansId gsi;
             filmsSeans = new ArrayList<>();
             
             
             
             SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm");
             SimpleDateFormat seansHour = new SimpleDateFormat("HHmm");
            
             
             for (Filmy f : filmList)
             {
                 fs = new FilmsSeans();
                 fs.setTytylFilmu(f.getTytul());
                 fs.setgodzDoPoludniaList(new ArrayList<>());
                 fs.setgodzPoPoludniuList(new ArrayList<>());
                 fs.setgodzWieczoremList(new ArrayList<>());
                 fs.setwszystkList(new ArrayList<>());
                 
                 for (Seans s : f.getSeansSet())
                 {
                     if ( s.getData().equals(today.getTime()) )
                     {
                         gsi = new GodzinaSeansId(s.getIdSeans(), dataFormat.format(s.getGodzina()) );
                         
                         int godz = Integer.valueOf(seansHour.format(s.getGodzina()));
                         
                         if (godz < 1200)
                         {
                             fs.getgodzDoPoludniaList().add(gsi);
                         }
                         else if ( (godz >= 1200) && (godz < 1800) )
                         {
                             fs.getgodzPoPoludniuList().add(gsi);
                         }
                         else if (godz >= 1800)
                         {
                             fs.getgodzWieczoremList().add(gsi);
                         }
                         
                         fs.getwszystkList().add(gsi);
  
                     }
                 }
                 
                 filmsSeans.add(fs);
                 
             }
        
      
       em.close(); 
       currentDay = today.getTime();
       this.makeCurrentDayToShow();
       
       return filmsSeans;
    }
    
    
    
    public void uzypelnijDaty()
    {
        dni = new ArrayList();
        
        
        
        GregorianCalendar today = new GregorianCalendar();
        today.set(GregorianCalendar.HOUR_OF_DAY, 0);
        today.set(GregorianCalendar.MINUTE, 0);
        today.set(GregorianCalendar.SECOND, 0);
        
        SimpleDateFormat dataFormat = new SimpleDateFormat("EEE dd MMM");
       
        GregorianCalendar todayToSet = new GregorianCalendar(today.get(GregorianCalendar.YEAR), today.get(GregorianCalendar.MONTH), today.get(GregorianCalendar.DAY_OF_MONTH));
        
        this.currentDay = todayToSet.getTime();
         DniRepertuaru dr = new DniRepertuaru();
         
         dr.setData( todayToSet.getTime() );
         dr.setDateToShow( dataFormat.format( todayToSet.getTime() ) );
        
        dni.add( dr );
        
       
        
        
        for (int i = 1; i < 15; i++)
        {
           todayToSet.add(GregorianCalendar.DAY_OF_MONTH, 1);
           
           dr = new DniRepertuaru();
           
           dr.setData( todayToSet.getTime() );
           dr.setDateToShow( dataFormat.format( todayToSet.getTime() ) );
           
           
           
           dni.add( dr );
        }
        
        
        this.makeCurrentDayToShow();
        
    }
    
    
    private void makeCurrentDayToShow()
    {
        SimpleDateFormat dataFormat = new SimpleDateFormat("EEEEE dd.MM");
        
        StringBuilder sb = new StringBuilder( dataFormat.format( currentDay ) );
        
        this.currentDayToShow = sb.replace(0, 1, new Character( sb.toString().charAt(0) ).toString().toUpperCase() ).toString();
    }
    
    
    
    public String showSeanseForDay( DniRepertuaru dzien )
    {
        currentDay = dzien.getData();
        this.makeCurrentDayToShow();
        this.getSeanseForDay();
        
        
        return null;
    }
    

    
    private void getSeanseForDay()
    {
        EntityManager em = DBManager.getManager().createEntityManager();
        filmsSeans.clear();
            
            
            
           List<Filmy> filmList = em.createQuery("SELECT DISTINCT(f) FROM Filmy f, Seans s WHERE s.data = :data AND f = s.filmySet AND s.idLokalicacja = :lokalizacja").setParameter("data", currentDay).setParameter("lokalizacja", lokalizacja).getResultList();
            
             FilmsSeans fs;
             GodzinaSeansId gsi;
             filmsSeans = new ArrayList<>();
             
             SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm");
             SimpleDateFormat seansHour = new SimpleDateFormat("HHmm");
            
             
             for (Filmy f : filmList)
             {
                 fs = new FilmsSeans();
                 fs.setTytylFilmu(f.getTytul());
                 fs.setgodzDoPoludniaList(new ArrayList<>());
                 fs.setgodzPoPoludniuList(new ArrayList<>());
                 fs.setgodzWieczoremList(new ArrayList<>());
                 fs.setwszystkList(new ArrayList<>());
                 
                 for (Seans s : f.getSeansSet())
                 {
                     cosTam = currentDay.toString();
                     if ( s.getData().equals( currentDay ) )
                     {
                         gsi = new GodzinaSeansId(s.getIdSeans(), dataFormat.format(s.getGodzina()) );
                         cosTam="Jestem po equals";
                         int godz = Integer.valueOf(seansHour.format(s.getGodzina()));
                         
                         if (godz < 1200)
                         {
                             fs.getgodzDoPoludniaList().add(gsi);
                         }
                         else if ( (godz >= 1200) && (godz < 1800) )
                         {
                             fs.getgodzPoPoludniuList().add(gsi);
                         }
                         else if (godz >= 1800)
                         {
                             fs.getgodzWieczoremList().add(gsi);
                         }
                         
                         fs.getwszystkList().add(gsi);
  
                     }
                 }
                 
                 filmsSeans.add(fs);
                 
             }
        
      
       em.close();
    }
    
  
    
}
