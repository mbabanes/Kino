  var choosenCount = 0;
  var iloscRzedow = +($('#iloscRzedow').val());
  var iloscMiejscWRzedzie = +($('#iloscMiejscWRzedzie').val());
  var iloscMiejsc = iloscRzedow * iloscMiejscWRzedzie;
  
  
  var placesToChoosen = new Array();
   for (var i = 0; i < iloscMiejsc; i++)
        {
            placesToChoosen[i] = false;
        }
     var opened = false;
  


function sprawdz( id, a, zPotwierdzenia, rodzajBiletu )
{
    if (zPotwierdzenia)
    {
        id = id - 1;
        a = a.toLowerCase(a);
        
        switch(a)
        {
            case "b":
            {
                id = id + 17;
                break;
            }

            case "c":
            {
                id = id + 34;
                break;
            }

            case "d":
            {
                id = id + 51;
                break;
            }

            case "e":
            {
                id = id + 68;
                break;
            }
            case "f":
            {
                id = id + 85;
                break;
            }

        }
    }
    
    
    
    
    if ( !placesToChoosen[id] )
    {
        placesToChoosen[id] = true;
        choosenCount = choosenCount + 1;
        $('#'+ a + id).addClass("hallPlaceChoosen"); 
        var placeNr = $('#' + a + id).text().split();
        if (rodzajBiletu === "Ulgowy")
            $('.buttonsReservationContainer').before('<div class="reservationIntputsContainer" id="placesID' + a + id + '"><p><label for="rowInput' + a + id +'">Rz'+ String.fromCharCode(261) + 'd:</label><input name="rowIn' + a + placeNr + '" type="text" readonly="readonly" value="' + a + '" id="rowInput' + a + id +'" /></p><p><label for="placeInRowInput' + a + id +'">Miejsce:</label><input name="placeNr' + placeNr + a +'" type="text" readonly="readonly" value="' + placeNr + '" id="placeInRowInput' + a + id +'"/></p><p><select name="rodzaj' + a + placeNr + '"><option>Normalny</option><option selected="selected">Ulgowy</option></select></p></div>');
        else
            $('.buttonsReservationContainer').before('<div class="reservationIntputsContainer" id="placesID' + a + id + '"><p><label for="rowInput' + a + id +'">Rz'+ String.fromCharCode(261) + 'd:</label><input name="rowIn' + a + placeNr + '" type="text" readonly="readonly" value="' + a + '" id="rowInput' + a + id +'" /></p><p><label for="placeInRowInput' + a + id +'">Miejsce:</label><input name="placeNr' + placeNr + a +'" type="text" readonly="readonly" value="' + placeNr + '" id="placeInRowInput' + a + id +'"/></p><p><select name="rodzaj' + a + placeNr + '"><option>Normalny</option><option>Ulgowy</option></select></p></div>');
        
        $('.countTicket').text("Ilość:" + choosenCount);
        
    }
    else
    {
        placesToChoosen[id] = false;
        choosenCount = choosenCount - 1;
        $('#'+ a + id).removeClass("hallPlaceChoosen");
        $('#placesID' + a + id).remove();
        $('.countTicket').text("Ilość:" + choosenCount);
    }
    
    
    
     if (!opened)
     {
        $('.reservationPlaceFormContainer').slideToggle(500);
        opened = true;                  
     }
     
     if (choosenCount === 0)
     {
        
        $('.reservationPlaceFormContainer').slideToggle(500);
        opened = false; 
     }
}

function renderPlaces( iloscRzedow, iloscMiejscWRzedzie)
{
    var i = 0;
    var j = 0;
    var id = 0;

                

    for(i = 0; i < iloscRzedow; i++)
    {
        var z = String.fromCharCode(97 + i);

        $('.hallPlaceContainer').append('<div class="hallRow" id="' + z + '"></div>');
        $('#' + z).append('<div class="hallRowName">' + z.toUpperCase() + '</div>');

        for (j = 0; j < 17; j++)
        {
          $('#' + z).append('<div class="hallPlace" id="' + z + (id + j) + '" onclick="sprawdz(' + (id + j) + ',' +'\'' + z + '\'' + ',false)">' + (j + 1) +'</div>'); 
        }
        $('#' + z).append('<div class="hallRowName">' + z.toUpperCase() + '</div>');
        id = id + 17;
    }
}


function odczepOnClick(id, a)
{
    id = id - 1;
    a = a.toLowerCase(a);
    
    switch(a)
    {
        case "b":
        {
            id = id + 17;
            break;
        }
        
        case "c":
        {
            id = id + 34;
            break;
        }
        
        case "d":
        {
            id = id + 51;
            break;
        }
        
        case "e":
        {
            id = id + 68;
            break;
        }
        case "f":
        {
            id = id + 85;
            break;
        }
        
    }
    
    
    $('#'+ a + id).removeAttr('onclick');
    $('#'+ a + id).removeClass("hallPlaceChoosen");
    $('#' + a + id).addClass("hallPlaceUnavilable");
    
}