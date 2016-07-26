var selected = false;
var current_mark;
var map_ref;

function validate(event) {
    
    if($('#complaintType :selected').val() !== '0')
         $('#alertDenunciaNotSelected').slideUp();
    if($('#lat').val() !== '' && $('#lng').val() !== '')
        $('#alertPlaceNotSelected').slideUp();
    
    if($("#complaintType :selected").val() === '0'){
        $('#alertDenunciaNotSelected').slideDown();
        event.preventDefault();
    }
    else if($('#lat').val() === '' || $('#lng').val() === ''){
        $('#alertPlaceNotSelected').slideDown();
        event.preventDefault();
    }
}

function cleanComplaintForm() {
    $('#complaintForm').trigger("reset");
}


//Função que adiciona marcação recebendo a localização de onde marcar
function placeMarkerByLocation(location) {
    setCoordinateOnInputs(location);
    selected = true;
    //Caso o marcador atual seja diferente de nulo, ele é destroído.
    if(current_mark != null)
        current_mark.setMap(null);
    marker = new google.maps.Marker({
        position: location,
        map: map_ref,
        title: 'Novo Marcador',
        animation: google.maps.Animation.BOUNCE,
    });
    //map_ref.setCenter(location);
    current_mark = marker;
}

function setCoordinateOnInputs(coordinate) {
    document.getElementById("lat").value = coordinate.lat();
    document.getElementById("lng").value = coordinate.lng();
}

function initGeolocation() {
    var infoWindow = new google.maps.InfoWindow({map: map_ref});
         
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };

            infoWindow.setPosition(pos);
            infoWindow.setContent('Você está aqui!');
            map_ref.setCenter(pos);
        }, function () {
            handleLocationError(true, infoWindow, map_ref.getCenter());
        });
    } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map_ref.getCenter());
    }
}


function initSearchBox() {

    var input = document.getElementById('pac-input');
    console.log(input);
    var searchBox = new google.maps.places.SearchBox(input);

    map_ref.controls[google.maps.ControlPosition.TOP_RIGHT].push(input);

    map_ref.addListener('bounds_changed', function () {
        searchBox.setBounds(map_ref.getBounds());
    });

    searchBox.addListener('places_changed', function () {
        var places = searchBox.getPlaces();

        if (places.length === 0) {
            return;
        }

        var bounds = new google.maps.LatLngBounds();

        places.forEach(function (place) {
            
            placeMarkerByLocation(place.geometry.location);
            
            if (place.geometry.viewport) {
                bounds.union(place.geometry.viewport);
            }
            else {
                bounds.extend(place.geometry.location);
            }

        });

        map_ref.fitBounds(bounds);
    });
}

//função que constrói o mapa
function initialize() {
    var maps = google.maps;
    google_ref = google;
    // console.log(maps);

    //Instancia uma variavel do tipo Latlng com as coordenadas de cajazeiras
    var myLatlng = new google.maps.LatLng(-6.889797, -38.561197);
    //insancia um objeto json com as configurações do mapa
    var mapOptions = {
        zoom: 14,
        center: {
            lat: -33.9,
            lng: 151.2
        },
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        backgroundColor: "lightgrey",
        mapTypeControlOptions: {
            style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
            position: google.maps.ControlPosition.BOTTOM_LEFT
        }

    }
    /*Instancia o mapa passando o objeto DOM que vai printar o mapa do google juntamente com as 
     Configurações que criamos anteriormente.*/
    var map = new google.maps.Map(document.getElementById("map"), mapOptions);


    map_ref = map;

    /*Adicionamos um listener na variavel do mapa que criamos e associamos à nossa div "maps"
     Pra que quando cliquemos na nossa div na posição desejada, peguemos do evento o latLng 
     e chamemos a função placeMarker com a localização passada por parâmetro.*/
    google.maps.event.addListener(map, 'click', function(event) {
         placeMarkerByLocation(event.latLng);
    });

    initGeolocation();
    initSearchBox();
}


//carrega o script do google maps
function loadScript() {
    var myKey = "AIzaSyDgesfzerpawSKDeYXdMBNRudZMKrVe5zE";
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = "https://maps.googleapis.com/maps/api/js?key=" + myKey + "&sensor=false&callback=initialize&libraries=places";
    document.body.appendChild(script);
}

window.onload = loadScript;