package controllers.pracownik;

import config.DBManager;
import entity.Filmy;
import java.util.Date;
import javax.persistence.EntityManager;

public class FilmBean
{
    private Filmy film;
    
    public FilmBean()
    {
        this.initiation();
    }
    
    private void initiation()
    {
        film = new Filmy();
    }
    
    
    public Filmy getFilm()
    {
        return film;
    }
    
    public void setFilm(Filmy film)
    {
        this.film = film;
    }
    
    
    
    public String addFilm()
    {
        EntityManager em = DBManager.getManager().createEntityManager();
        
        this.film.setIdFilm(null);
        this.film.setDataPremiery( new Date() );
        em.getTransaction().begin();
        em.persist(film);
        em.getTransaction().commit();
        
        em.close();
        
        return "asdsad";
    }
    
            
    
    
}
