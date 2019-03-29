package com.example.techlab.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.techlab.R;

import java.util.ArrayList;

public class Inventaris extends AppCompatActivity {
    private static final String TAG = "Inventaris";

    // Array van de namen en afbeeldingen van elk product
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mProductDescription = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventaris);
        Log.d(TAG, "onCreate: started.");

        initImageBitmaps();
    }
    // Voeg hier Producten toe!
    // Product Naam + foto URL
    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");
        
        mNames.add("HoloLens");
        mImageUrls.add("https://cdn-images-1.medium.com/max/1600/1*Oltg1ajoJ1Xbs2fK0N644g.jpeg");
        mProductDescription.add("Eenmaal op, kunt je een holografische wereld zien binnen in welke binnenruimte je ook bent. " +
                "De headset kan gebruikt worden om op internet te surfen, een rondleiding door te brengen in de verre wereld of een indrukwekkend spel te spelen. \n\n" +
                "De AR-bril kan overtuigende 'hologrammen' in het gezichtsveld van de drager projecteren." +
                "De AR-bril houdt rekening met de omgeving van de brildrager: dankzij ingebouwde, diepte metende camera's 'weet' de AR-bril waar zich muren, tafels,etc bevinden.");

        mNames.add("Playstation 4");
        mImageUrls.add("https://www.bhphotovideo.com/images/images2500x2500/sony_3002337_playstation_4_console_1tb_1356689.jpg");
        mProductDescription.add("Hierop kun je games spelen");

        mNames.add("AR Drone Elite Edition");
        mImageUrls.add("https://images.crutchfieldonline.com/ImageHandler/trim/620/378/products/2015/26/333/g333AR2JUNG-o_front2.jpg");
        mProductDescription.add("De AR.Drone Elite Edition, is makkelijk bestuurbaar met wifi via een smartphone of tablet. Met een HD-camera met een video-opnamevoorziening, "+
                "een modus voor het delen van vluchtgegevens, een gepatenteerde besturingsmodus en een innovatieve druksensor voor meer stabiliteit op gelijk welke hoogte kan zelfs op commando vier asomwentelingen maken. ");

        mNames.add("FIFA 18");
        mImageUrls.add("https://s.s-bol.com/imgbase0/imagebase3/large/FC/5/3/3/0/9200000078940335.jpg");
        mProductDescription.add("FIFA 18 is zowel visueel als voetbaltechnisch een revolutie ten opzichte van zijn voorganger. Veel meer dan in FIFA 17 zijn de individuele voetballers als geen ander te herkennen aan hun typische lichaamshoudingen " +
                "en sprintjes. Daarnaast is er veel aandacht besteed aan het herkenbare samenspel van de verschillende teams. Zo ervaar je met FIFA 18 het razendsnelle aanvallende spel van Barcelona en" +
                "kom je maar moeilijk door de robuuste verdediging van Liverpool heen. Vergeleken met FIFA 17 gedragen de spelers van het team zich in FIFA 18 zoals ze dat ook doen in werkelijkheid.\n\n" +
                "Daarbij kun je dus denken aan een muurvaste verdediging of snelle één-tweetjes onder de aanvallers. Ten slotte krijgt The Journey een vervolg en kun je je bewijzen in bloedstollende toernooien met Ultimate Team Icons. ");

        mNames.add("Tekken 7");
        mImageUrls.add("https://s.s-bol.com/imgbase0/imagebase3/large/FC/1/1/5/8/9200000038448511.jpg");
        mProductDescription.add("Alle gevechten zijn persoonlijk. Liefde, Wraak, Trots. Elk van ons heeft een reden om te vechten. Waarden bepalen ons mens-zijn en maken ons menselijk, ongeacht onze krachten en zwakheden."+
                "Er zijn geen verkeerde motivaties, alleen het pad dat wij kiezen.\n\n" +
                "TEKKEN 7 wordt aangedreven door Unreal Engine 4 en wordt gekenmerkt door verbluffende op verhalen gebaseerde filmtechnische gevechten en intense duels. Je kunt dit allemaal beleven met zowel vrienden als rivalen via innovatieve vechtmechanieken.\n");

        mNames.add("Echo Dot");
        mImageUrls.add("https://s.s-bol.com/imgbase0/imagebase3/large/FC/6/5/7/0/9200000078150756.jpg");
        mProductDescription.add("Echo Dot is een spraakgestuurde intelligente luidspreker met Alexa en ideaal voor elke kamer. Vraag gewoon om muziek, het nieuws, informatie en nog veel meer."+
                "Je kunt ook mensen bellen met een echo-apparaat of de Alexa-app (Skype wordt binnenkort beschikbaar) en met spraakbesturing compatibele smart home-apparaten. \n\n" +
                "Vraag Alexa naar een nummer, artiest of muziekgenre op Amazon Music, Spotify, TuneIn en andere services. Met verschillende compatibele echovoorzieningen in verschillende kamers, kun je door het hele huis naar muziek luisteren."+
                "Luister naar Audible, radiostations en nog veel meer.");

        mNames.add("Raspberry Pi 3 Case");
        mImageUrls.add("https://cdn.shopify.com/s/files/1/0176/3274/products/102034_1024x1024.jpg?v=1542684026");
        mProductDescription.add("Officiele behuizing van de Raspberry Pi Foundation. Deze behuizing bestaat uit 5 onderdelen, met afneembare top-plaat en zijkanten. De losse panelen zorgen ervoor dat de camera,"+
                "display ports en aangekoppelde Raspberry HAT bereikt kunnen worden. Zo is de PoE-HAT te gebruiken in combinatie met deze behuizing, als de Raspberry Pi eerst in de behuizing gemonteerd wordt, " +
                "en daarna pas de PoE-HAT met de spacers er op vast geschroeft. De GPIO-kant heeft een eigen paneel die los gehaald kan worden om makkelijk bij de 40-poorts GPIO-header te komen.\n Afmetingen: 96x70x25mm ");

        mNames.add("Grove Pi");
        mImageUrls.add("https://www.pi-shop.ch/media/catalog/product/cache/1/image/650x/040ec09b1e35df139433887a97daa66f/d/s/dsc_0558-800x609.jpg");
        mProductDescription.add("GrovePi+ starter-kit voor de Raspberry Pi - wat is dat?\n\nDeze starter-kit is met een GrovePi+ board en 12 zorgvuldig geselecteerde Grove-sensoren met 10 stuks kabels uitgerust"+
                " en maakt de beginner bliksemsnel topfit met de Raspberry Pi. De GrovePi+ board wordt gewoon op de Raspberry Pi gezet en de sensoren met de board verbonden en weldra is het krachtige platform voor eigen hardware-experimenten en prototyping gereed.\n\n"
                +"De GrovePi+ wordt op de Raspberry Pi 'opgestackt'; verdere aansluitingen zijn er niet nodig. De communicatie tussen beide geschiedt via I²C-interface. Alle Grove-modules functioneren met de universele Grove-verbindingselementen op de GrovePi+ Shield middels de universele 4-pin-aansluitkabel.");

        mNames.add("Raspberry Pi 3 Model B");
        mImageUrls.add("https://cdn.shopify.com/s/files/1/0176/3274/products/100437_1024x1024.jpg?v=1511856952");
        mProductDescription.add("Raspberry Pi is dé computerspecialist voor educatieve doeleinden. Om jongeren meer te leren over computertechniek heeft dit merk een aantal producten ontwikkeld."+
                " Zo komt deze groep op een eenvoudige en leerzame manier in aanraking met de techniek. Naast educatieve doeleinden hebben ook het bedrijfsleven en hobbyisten Raspberry Pi ontdekt."+
                "De verschillende producten laat het hart van iedere computerfanaat sneller kloppen. Zo zijn de computers verkrijgbaar op een printplaat. Hierdoor leert iedere liefhebber alles over computers. Ook voor de niet-creatievelingen zijn er verschillende kant-en-klare producten. Zo speel je eenvoudig al je favoriete films en muziek af via de mediaplayer. Raspberry Pi biedt inmiddels producten aan voor iedereen. Wel staat de educatie voor jongeren nog altijd op de eerste plaats. Raspberry Pi biedt verschillende oplossingen om jouw persoonlijke computer te ontwikkelen, tegen een zeer betaalbare prijs.\n");

        mNames.add("Raspberry Pi Power Supply");
        mImageUrls.add("https://www.modmypi.com/image/cache/catalog/rpi-products/accessories/power/rpi-3/DSC_0289-1024x780.jpg");
        mProductDescription.add("AC/DC adapter\nInput: AC 100-240V 50/60Hz\nEU-stekker\nOutput: DC 5V 2500mA /2.5A\nmicro USB \nKabel lengte: 100cm\nKleur: zwart\n\n" +
                "Ideale adapter voor Raspberry Pi 3 Model B en B+ / Raspberry Pi 2 Model B / Raspberry Pi A/A+/B/B+\n\nDe adapter kan uiteraard ook gebruikt worden voor ander elektronisch materiaal voorzien van een micro-USB aansluiting: smart phones, tablets,...");

        mNames.add("LEAP Motion");
        mImageUrls.add("https://cdn.shopify.com/s/files/1/0404/3649/products/LM_Mount-Curved-Bundle-for_store_grande.png?v=1462782505");
        mProductDescription.add("De Leap Motion Controller registreert alle bewegingen van handen en vingers waardoor je op een geheel nieuwe en intuïtieve manier kan communiceren met de Mac en PC.\n\n" +
                "De controller is slank, licht en is slechts 8 centimeter lang waardoor het bijna geen plaats inneemt op je bureau. Met zijn 150 graden bereik heb je genoeg ruimte om er boven met je handen te navigeren. De controller werkt samen met toetsenbord, muis, smartpen of trackpad en is eenvoudig te gebruiken doormiddel van de USB-poort op de computer.\n\n" +
                "De controller maakt gebruik van infraroodcamera's en wiskundige algoritmes om de hand- en vingerbewegingen te vertalen naar een 3d input. Deze technologie zorgt ervoor dat deze controller een stuk nauwkeuriger is dan andere motion-controllers.");

        mNames.add("HTC Vive");
        mImageUrls.add("https://images-na.ssl-images-amazon.com/images/I/71ARMCztUBL._SX679_.jpg");
        mProductDescription.add("De HTC Vive is een virtual reality bril met bewegingscontrollers die je met je pc of laptop gebruikt. Met deze VR bril beleef je virtuele werelden in de hoogste kwaliteit."+
                "Met de bijgeleverde bewegingscontrollers en basisstations zie je jouw bewegingen in VR verschijnen. Zo pak je eenvoudig een object op en loop je er de virtuele ruimte mee door."+
                " Wees niet bang dat je tegen een muur aan loopt, het ingebouwde chaperone camerasysteem waarschuwt je wanneer je het einde van de speelruimte nadert. Verplaats jezelf naar de top van de Mount Everest, verdedig jezelf tegen een horde zombies of kijk een film op een gesimuleerd bioscoopscherm.");

        mNames.add("Oculus Rift");
        mImageUrls.add("https://images-na.ssl-images-amazon.com/images/I/61ahfXnBa0L._SX679_.jpg");
        mProductDescription.add("Ga in één keer aan de slag met de Oculus Rift zoals het bedoeld is: met 2 touch controllers. Dit pakket omvat namelijk de Oculus Rift VR Bril, 2 Oculus Rift Touch Controllers en 6 gratis meegeleverde games. Ook beschik je over 2 Oculus sensoren die zowel de bewegingen van de headset als de precieze bewegingen van de controller in 3D volgen. De headset dompelt je onder in de virtuele wereld en de controllers zorgen dat je op natuurlijk wijze de interactie opzoekt met de virtuele objecten en vijanden die je tegenkomt. Zo 'gooi' je granaten met dezelfde beweging als je dit in het echt zou doen. Op deze manier vergeet je dat je een controller in je handen hebt en ga je op in de game.");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        com.example.techlab.view.RecyclerViewAdapter adapter = new com.example.techlab.view.RecyclerViewAdapter(this, mNames, mImageUrls, mProductDescription);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
