package controllers;


public class GodzinaSeansId
{
        private int idSeans;
        private String godzina;
        
        public GodzinaSeansId()
        {
            
        }
        
        public GodzinaSeansId(int idSeans, String godzina)
        {
            this.idSeans = idSeans;
            this.godzina = godzina;
        }
        
        public int getIdSeans()
        {
            return idSeans;
        }

        public void setIdSeans(int idSeans)
        {
            this.idSeans = idSeans;
        }

        public String getGodzina()
        {
           return godzina;
        }

        public void setGodzina(String godzina)
        {
            this.godzina = godzina;
        }
        
      
}
