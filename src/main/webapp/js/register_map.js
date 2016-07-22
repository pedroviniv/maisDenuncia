var selected = false;
var current_mark;

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
    map_ref.setCenter(location);
    current_mark = marker;
}

function setCoordinateOnInputs(coordinate){
    document.getElementById("placeLat").value = coordinate.lat();
    document.getElementById("placeLng").value = coordinate.lng();
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

    function initSearchBox() {
        var input = document.getElementById('pac-input');
        var searchBox = new google.maps.places.SearchBox(input);

        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        map.addListener('bounds_changed', function () {
            searchBox.setBounds(map.getBounds());
        });


        searchBox.addListener('places_changed', function () {
            var places = searchBox.getPlaces();

            if (places.length === 0) {
                return;
            }

            var bounds = new google.maps.LatLngBounds();

            places.forEach(function (place) {
                if (place.geometry.viewport) {
                    bounds.union(place.geometry.viewport);
                }
                else {
                    bounds.extend(place.geometry.location);
                }

            });

            map.fitBounds(bounds);
        });
    }

    function initGeolocation() {
        var infoWindow = new google.maps.InfoWindow({map: map});
         
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                var pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };

                infoWindow.setPosition(pos);
                infoWindow.setContent('Location found.');
                map.setCenter(pos);
            }, function () {
                handleLocationError(true, infoWindow, map.getCenter());
            });
        } else {
            // Browser doesn't support Geolocation
            handleLocationError(false, infoWindow, map.getCenter());
        }
    }
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