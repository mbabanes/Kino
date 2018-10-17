


$(document).ready(function()
        
{
       $('#reservationloginForm').hide();
       $('#reservationRegistrationForm').hide();
       $('#reservationClientData').hide();
           var first = true; 
           
        $('#chooseingOptionForPersonForm').change(function()
        {
            var opcja = $('#chooseingOptionForPersonForm').val();
            
            if (!first)  
            {
                $('.reservationContainer form').hide();
            }
            
            switch(opcja)
            {
                case 'Posiadam konto':
                {
                   first = false;
                    $('#reservationloginForm').slideToggle(500);            
                    break;
                }
                            
                case 'Nie posiadam konta, ale chce się zarejestrować':
                {
                   first = false;
                    $('#reservationRegistrationForm').slideToggle(500); 
                    break;
                }
                            
                case 'Nie posiadam konta, ale chce kontynuować':
                {
                    first = false;
                    $('#reservationClientData').slideToggle(500);
                    break;
                } 
                
                default:
                {
                   first = true; 
                }
          
            }
            
            
        });
                    
                    
                    
        $('#logEmail').attr('placeholder', 'e-mail');
        $('#loginInput').attr('placeholder', 'e-mail');
        $('#passwordInput').attr('placeholder', 'hasło');
        
});

