var map_ref;
var markers = [];
var heatmap;
var heatMapCoordinates = [];
var heatmap_activated = false;
var denuncias = [];

//Função que adiciona marcação recebendo as opções da mesma
function placeMarkerByOptions(markOptions) {
    marker = new google.maps.Marker(markOptions);
    addMarker(marker);
    return marker;
}

//RETORNA COR DE ACORDO COM O TIPO DA DENÚNCIA
function getColorFromTipoDenuncia(tipodenuncia) {
    if (tipodenuncia === 'AGRESSAO') {
        return "8B008B";
    } else if (tipodenuncia === 'ASSEDIO') {
        return  "20B2AA";
    } else if (tipodenuncia === 'ESTUPRO') {
        return  "FF6347";
    }
}

//Retorna idade em anos de acordo com a data de nascimento passada por parâmetro
function getAge(birthdate) {
    var today = new Date(Date.now());
    var diff = new Date(+today).setHours(12) - new Date(+birthdate).setHours(12);
    return Math.floor((Math.round(diff/8.64e7)/365));
}


//SETA UM MARCADOR NO MAPA DE ACORDO COM AS CONFIGURAÇÕES DE UM JSON REPRESENTANDO A DENUNCIA
function placeMarkerByJson(json) {
    var location = new google.maps.LatLng(json.ponto.coordinates.coordinates[0].x, json.ponto.coordinates.coordinates[0].y);
    var data = json.data.day + '/' + json.data.month + '/' + json.data.year;

    // var pinColor = getColorFromTipoDenuncia(json.tipoDenuncia);

    var pinColor = getColorFromTipoDenuncia(json.tipoDenuncia);

    var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
            new google.maps.Size(21, 34),
            new google.maps.Point(0, 0),
            new google.maps.Point(10, 34));

    var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
            new google.maps.Size(40, 37),
            new google.maps.Point(0, 0),
            new google.maps.Point(12, 35));

    var targetMap = map_ref;
    
    if(heatmap_activated)
        targetMap = null;
    
    var markOptions = {
        position: location,
        map: targetMap,
        title: json.descricao,
        icon: pinImage,
        shadow: pinShadow
    };
    
    var anonimo = "Não";
    if(json.ehAnonimo)
        anonimo = "Sim";
      
    var denuncia_content = "<div><p class='content-infoWindow'>" 
            + "<label style='font-size: 1.1em; margin-bottom: 0px;'>Denúncia</label>"
            + "<i class='fa fa-font' aria-hiden='true'></i> Descrição: " + json.descricao
            + "<br><i class='fa fa-tags' aria-hidden='true'></i> Tipo de Denúncia: " + json.tipoDenuncia
            + "<br><i class='fa fa-calendar' aria-hidden='true'></i> Data: " + data
            + "<br><i class='fa fa-eye-slash' aria-hidden='true'></i> Anônimo: " + anonimo
            + "<br><label style='font-size: 1.1em; margin-bottom: 0px;'>Denunciante</label>"
            + "<i class='fa fa-user' aria-hidden='true'></i> Tipo: " + json.tipoDenunciante;
    
    var date = json.denunciante.dataNascimento;
    
    var birthdate = new Date();
    birthdate.setFullYear(date.year, date.month-1, date.day);
           
    if(!json.ehAnonimo){
       denuncia_content = denuncia_content + "<br><i class='fa fa-font' aria-hiden='true'></i> Nome: " + json.denunciante.nome;
       denuncia_content = denuncia_content + "<br><i class='fa fa-venus-mars' aria-hiden='true'></i> sexo: " + json.denunciante.sexo;
       denuncia_content = denuncia_content + "<br><i class='fa fa-heart' aria-hiden='true'></i> Idade: " + getAge(birthdate) + " anos";
    }      

    var marker_ref = placeMarkerByOptions(markOptions);

    var infoWindow = new google.maps.InfoWindow({
        content: denuncia_content
    });

    google.maps.event.addListener(marker, 'click', (function (marker, infowindow) {
        return function () {
            infowindow.open(map_ref, marker);
        };
    })(marker_ref, infoWindow));
}

//Realiza a requisição HTTP para a url passada para o parâmetro e caso dê tudo certo o callback passado é chamado.
function requestComplaints(successCallback, url) {
    $.ajax({
        type: "POST",
        url: url,
        success: successCallback,
        error: function (xhr, status) {
            console.log(status); // Output as parseError
            console.log(xhr.responseText); // My sResponse string ! But Why Here ?
        }
    });
}

//Filtra os marcadores atuais pelo tipo da denúncia selecionada através de um checkbox
function filterMarksByType() {
    setMapOnAllMarkers(null);
    cleanMarks();
    cleanCoordinates();
    
    var ag = document.getElementById('agressao');
    var as = document.getElementById('assedio');
    var es = document.getElementById('estupro');
    var denunciaProcessed = 0;
    denuncias.forEach(function (denuncia) {
        if((ag.checked && denuncia.tipoDenuncia === 'AGRESSAO') 
                || (as.checked && denuncia.tipoDenuncia === 'ASSEDIO') 
                    || (es.checked && denuncia.tipoDenuncia === 'ESTUPRO')) {
            
                    addCoordinate(new google.maps.LatLng(denuncia.ponto.coordinates.coordinates[0].x, 
                        denuncia.ponto.coordinates.coordinates[0].y));
                        
                    placeMarkerByJson(denuncia);
                    
                }
        denunciaProcessed++;
        
        if(denunciaProcessed === denuncias.length && heatmap_activated)
                activeHeatMap();
    });
}

//Método que carrega todas as denúncias do mês
function loadMarksByMonth() {
    setMapOnAllMarkers(null);
    var url = "http://localhost:8080/maisdenuncia/searchByMonth";
    loadMarks(url);
}

//Método que carrega todas as denúncias
function loadAllMarks() {
    setMapOnAllMarkers(null);
    var url = "http://localhost:8080/maisdenuncia/ListComplaints";
    loadMarks(url);
}

//Método que carrega todas as denúncias entre duas datas (startDate e endDate)
function loadMarksByDate() {
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();
    setMapOnAllMarkers(null);
    var url = "http://localhost:8080/maisdenuncia/searchByDate?startDate=" + startDate + "&endDate=" + endDate;
    loadMarks(url);
}

//Carrega marcadores de um json (denuncia) no mapa
function loadMarks(url) {
    cleanMarks();
    cleanCoordinates();
    requestComplaints(function (data) {
        denuncias = data;
        var denunciaProcessed = 0;
        data.forEach(function (denuncia) {
            addCoordinate(new google.maps.LatLng(denuncia.ponto.coordinates.coordinates[0].x, denuncia.ponto.coordinates.coordinates[0].y));
            placeMarkerByJson(denuncia);
            denunciaProcessed++;
            
            if(denunciaProcessed === data.length && heatmap_activated)
                activeHeatMap();
        });
        
    }, url);
}

//adiciona marcador na lista markers
function addMarker(marker) {
    markers.push(marker);
}

//adiciona um objeto do tipo LatLng na lista de coordenadas
function addCoordinate(coordinate) {
    this.heatMapCoordinates.push(coordinate);
}

//Inicializa a geolocalização, se for possível pegar a coordenada essa função vai centralizar o maps na coordenada atual.
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

//Posiciona o campo de pesquisa do maps e o instancia com a api de autocomplete
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
function initializeGoogleMaps() {
    var maps = google.maps;

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
    var map = new google.maps.Map(document.getElementById("googleMaps"), mapOptions);

        map_ref = map;

    initGeolocation();
    loadAllMarks();
    initSearchBox();
    createHeatMap();
    heatmap.setMap(null);
}

//Cria o mapa de calor
function createHeatMap() {
    heatmap = new google.maps.visualization.HeatmapLayer({
        data: heatMapCoordinates,
        map: map_ref
    });
}

//ativa ou desativa mapa de calor dependendo do estado
function toggleHeatMap() {
    if (heatmap_activated === false)
        activeHeatMap();
    else
        activeMarks();
}

//ativa o mapa de calor
function activeHeatMap() {
    setMapOnAllMarkers(null);
    heatmap.setMap(map_ref);
    heatmap_activated = true;
}

//ativa marcações
function activeMarks() {
    heatmap.setMap(null);
    setMapOnAllMarkers(map_ref);
    heatmap_activated = false;
}

//Deleta todas os marcadores da lista de marcadores
function cleanMarks() {
    while (markers.length > 0) {
        markers.pop();
    }
}

//deleta todas as coordenadas da lista de coordenadas
function cleanCoordinates() {
    while (heatMapCoordinates.length > 0) {
        heatMapCoordinates.pop();
    }
}

//aplica as marcações para o mapa passado por parâmetro
function setMapOnAllMarkers(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

//carrega o script do google maps
function loadScript() {
    var myKey = "AIzaSyDgesfzerpawSKDeYXdMBNRudZMKrVe5zE";
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = "https://maps.googleapis.com/maps/api/js?v=3&key=" + myKey + "&sensor=false&callback=initializeGoogleMaps&libraries=visualization,places";
    document.body.appendChild(script);
}

window.onload = loadScript;