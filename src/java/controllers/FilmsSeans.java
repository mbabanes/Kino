package controllers;


import java.util.List;

public class FilmsSeans
{
    private List<GodzinaSeansId> godzDoPoludnia;
    private List<GodzinaSeansId> godzPoPoludniu;
    private List<GodzinaSeansId> godzWieczorem;
    private List<GodzinaSeansId> wszystk;
    private String tytulFilmu;
    
    public FilmsSeans()
    {
        
    }
    
    
    public List<GodzinaSeansId> getwszystkList()
    {
        return wszystk;
    }
    
    public void setwszystkList(List<GodzinaSeansId> wszystk)
    {
        this.wszystk = wszystk;
    }
    
    
    
    public List<GodzinaSeansId> getgodzDoPoludniaList()
    {
        return godzDoPoludnia;
    }
    
    public void setgodzDoPoludniaList(List<GodzinaSeansId> godzDoPoludnia)
    {
        this.godzDoPoludnia = godzDoPoludnia;
    }
    
    public List<GodzinaSeansId> getgodzPoPoludniuList()
    {
        return godzPoPoludniu;
    }
    
    public void setgodzPoPoludniuList(List<GodzinaSeansId> godzPoPoludniu)
    {
        this.godzPoPoludniu = godzPoPoludniu;
    }
    
    
    public List<GodzinaSeansId> getgodzWieczoremList()
    {
        return godzWieczorem;
    }
    
    public void setgodzWieczoremList(List<GodzinaSeansId> godzWieczorem)
    {
        this.godzWieczorem = godzWieczorem;
    }
    
  
    
    public String getTytulFilmu()
    {
        return tytulFilmu;
    }
    
    public void setTytylFilmu(String tytulFilmu)
    {
        this.tytulFilmu = tytulFilmu;
    }
    
    
    
    
    
  
}
