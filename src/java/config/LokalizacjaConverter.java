package config;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.persistence.EntityManager;
import entity.Lokalizacja;



public class LokalizacjaConverter implements Converter
{
    @Override
    public String getAsString(FacesContext ctx, UIComponent c, Object o)
    {
        if (! (o instanceof Lokalizacja))
        {
            throw new ConverterException(new FacesMessage("Nie udalo sie dokonac konwersji!"));
        }
        Lokalizacja l = (Lokalizacja)o;
        return l.getIdLokalicacja().toString();
    }
    
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent c, String s)
    {
        Integer i = Integer.valueOf(s);
        EntityManager em = DBManager.getManager().createEntityManager();
        Lokalizacja l = em.find(Lokalizacja.class, i);
        em.close();
        return l;
    }
}
