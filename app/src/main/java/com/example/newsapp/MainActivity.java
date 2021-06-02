package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<model_class> allNews;
    private RVAdapter adapter;

    String im="https://cdnuploads.aa.com.tr/uploads/Contents/2021/06/02/thumbs_b_c_6b3c3a9ab2d1ef24c4cfb763226ecec1.jpg?v=025740";

    private ArrayList<Bitmap> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        toolbar.setTitle("Search");
        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        allNews=new ArrayList<>();
        images=new ArrayList<>();



        model_class a=new model_class("burası başlık asdadasd asdas das d sdasdasdassdsdadadaddsd skısmı","ATAV","haberin link","15/4/20", "https://cdnuploads.aa.com.tr/uploads/Contents/2021/06/02/thumbs_b_c_6b3c3a9ab2d1ef24c4cfb763226ecec1.jpg?v=025740");
        model_class b=new model_class("burası başlık kısmı","AA","source","05/6/2021","https://cdnuploads.aa.com.tr/uploads/Contents/2021/06/02/thumbs_b_c_6b3c3a9ab2d1ef24c4cfb763226ecec1.jpg?v=025740");

        allNews.add(a);
        allNews.add(a);
        allNews.add(b);
        allNews.add(b);
        allNews.add(b);
        allNews.add(b);

        adapter=new RVAdapter(this,allNews);
        recyclerView.setAdapter(adapter);
    }


    public InputStream getInputStream(URL url){
        try {

            return url.openConnection().getInputStream();

        }catch (IOException exception){
            return null;
        }
    }


    public class ProcessInBackground extends AsyncTask<Integer,Integer,String>{
        Exception exception=null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(Integer... integers) {

            try {
                URL url=new URL("https://www.aa.com.tr/tr/rss/default?cat=turkiye");
                XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp=factory.newPullParser();
                xpp.setInput(getInputStream(url),"UTF_8");

                boolean insideItem=false;

                int eventType= xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT){
                    if (eventType==XmlPullParser.START_TAG){
                        if (xpp.getName().equalsIgnoreCase("item")){

                            insideItem=true;

                        }else if (xpp.getName().equalsIgnoreCase("title")){

                            if()

                        }
                    }
                }


            }catch (MalformedURLException e){
                exception=e;
            }catch (XmlPullParserException e){
                exception=e;
            }catch (IOException e){
                exception=e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    /*public  void LoadingImage(View view){
        DownLoadImage downLoadImage=new DownLoadImage();
        try {
            Bitmap bitmap=downLoadImage.execute(im).get();


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public  class DownLoadImage extends AsyncTask<String,Void ,Bitmap>{


        @Override
        protected Bitmap doInBackground(String... strings) {


            Bitmap bitmap=null;
            URL url;
            HttpURLConnection httpURLConnection;

            InputStream in;

            try {
                url=new URL(strings[0]);
                httpURLConnection= (HttpURLConnection) url.openConnection();
                in=httpURLConnection.getInputStream();

                bitmap= BitmapFactory.decodeStream(in);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }*/












    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}