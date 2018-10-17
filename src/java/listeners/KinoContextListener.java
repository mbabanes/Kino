package listeners;


import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import config.DBManager;

public class KinoContextListener implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        DBManager.getManager().createEntityManagerFactory();
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        DBManager.getManager().closeEntityManagerFactory();
    }
}
