package controllers;

import entity.Klient;

public class CurrentKlient
{
    private boolean login;
    private Klient klient;
    
    public CurrentKlient()
    {
        login = false;
        klient = new Klient();
    }
    
    
    public boolean isLogin()
    {
        return login;
    }
    
    public void setLogin(boolean login)
    {
        this.login = login;
    }
    
    public Klient getKlient()
    {
        return klient;
    }
    
    public void setKlient(Klient klient)
    {
        this.klient = klient;
    }
}
