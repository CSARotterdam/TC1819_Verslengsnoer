package com.example.inventaris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class Main_Inventaris extends AppCompatActivity {
//    private Button button;
    private static final String TAG = "Main_Inventaris";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventaris);
        Log.d(TAG, "onCreate: started.");

        initImageBitmaps();

//        button = (Button) findViewById(R.id.BRaspberry);
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                openDescription();
//            }
//        });
//
//    }
//    public void openDescription() {
//        Intent intent = new Intent(this, Des.class);
//        startActivity(intent);
    }

    //Lag
    //Categorie,Sorteer,Zoek Knop
    //When clicked to description screen
    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        // Add Buttons here!
        mNames.add("HoloLens");
        mImageUrls.add("https://cdn-images-1.medium.com/max/1600/1*Oltg1ajoJ1Xbs2fK0N644g.jpeg");

        mNames.add("Playstation 4");
        mImageUrls.add("https://www.bhphotovideo.com/images/images2500x2500/sony_3002337_playstation_4_console_1tb_1356689.jpg");

        mNames.add("AR Drone Elite Edition");
        mImageUrls.add("https://images.crutchfieldonline.com/ImageHandler/trim/620/378/products/2015/26/333/g333AR2JUNG-o_front2.jpg");

        mNames.add("FIFA 18");
        mImageUrls.add("https://s.s-bol.com/imgbase0/imagebase3/large/FC/5/3/3/0/9200000078940335.jpg");

        mNames.add("Tekken 7");
        mImageUrls.add("https://s.s-bol.com/imgbase0/imagebase3/large/FC/1/1/5/8/9200000038448511.jpg");

        mNames.add("Echo Dot");
        mImageUrls.add("https://s.s-bol.com/imgbase0/imagebase3/large/FC/6/5/7/0/9200000078150756.jpg");

        mNames.add("Raspberry Pi 3 Case");
        mImageUrls.add("https://cdn.shopify.com/s/files/1/0176/3274/products/102034_1024x1024.jpg?v=1542684026");

        mNames.add("Grove Pi");
        mImageUrls.add("https://www.pi-shop.ch/media/catalog/product/cache/1/image/650x/040ec09b1e35df139433887a97daa66f/d/s/dsc_0558-800x609.jpg");

        mNames.add("Raspberry Pi 3 Model B");
        mImageUrls.add("https://cdn.shopify.com/s/files/1/0176/3274/products/100437_1024x1024.jpg?v=1511856952");

        mNames.add("Raspberry Pi Power Supply");
        mImageUrls.add("https://www.modmypi.com/image/cache/catalog/rpi-products/accessories/power/rpi-3/DSC_0289-1024x780.jpg");

        mNames.add("LEAP Motion");
        mImageUrls.add("https://cdn.shopify.com/s/files/1/0404/3649/products/LM_Mount-Curved-Bundle-for_store_grande.png?v=1462782505");

        mNames.add("HTC Vive");
        mImageUrls.add("https://images-na.ssl-images-amazon.com/images/I/71ARMCztUBL._SX679_.jpg");

        mNames.add("Oculus Rift");
        mImageUrls.add("https://images-na.ssl-images-amazon.com/images/I/61ahfXnBa0L._SX679_.jpg");

        initRecyclerView();
    }
    
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
