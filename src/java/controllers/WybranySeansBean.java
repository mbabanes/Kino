package controllers;


public class WybranySeansBean
{
    private int idSeans;
    private String tytulFilmu;
    private String godzina;
    
    public WybranySeansBean()
    {
        
    }
    
    
    public int getIdSeans()
    {
        return idSeans;
    }
    
    public void setIdSeans(int idSeans)
    {
        this.idSeans = idSeans;
    }
    
    
    public String getTytulFilmu()
    {
        return tytulFilmu;
    }
    
    public void setTytulFilmu(String tytulFilmu)
    {
        this.tytulFilmu = tytulFilmu;
    }
    
    
    public String getGodzina()
    {
        return godzina;
    }
    
    public void setGodzina(String godzina)
    {
        this.godzina = godzina;
    }
    
    
    public String przejdzDoWyboruUslugi(int idSeans, String tytulFilmu, String godzina)
    {
        this.idSeans = idSeans;
        this.tytulFilmu = tytulFilmu;
        this.godzina = godzina;
        
        return "rezerCzyKup.xhtml";
    }
}
