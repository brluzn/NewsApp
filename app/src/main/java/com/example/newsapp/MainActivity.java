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
import android.os.Debug;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<model_class> allNews;
    private RVAdapter adapter;
    public  ArrayList<String> titles;
    public  ArrayList<String> pubDates;
    public  ArrayList<String> links;
    public  ArrayList<String> images_urls;

    Date d ;
    String im="https://cdnuploads.aa.com.tr/uploads/Contents/2021/06/02/thumbs_b_c_6b3c3a9ab2d1ef24c4cfb763226ecec1.jpg?v=025740";


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
        titles=new ArrayList<>();
        pubDates=new ArrayList<>();
        links=new ArrayList<>();
        images_urls=new ArrayList<>();


        new ProcessInBackground().execute();




    }


    public InputStream getInputStream(URL url){
        try {

            return url.openConnection().getInputStream();

        }catch (IOException exception){
            return null;
        }
    }


    public class ProcessInBackground extends AsyncTask<Integer,Integer,Exception>{
        Exception exception=null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Exception doInBackground(Integer... integers) {

            try {
                URL url=new URL("https://www.aa.com.tr/tr/rss/default?cat=guncel");
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

                            if(insideItem){

                                titles.add(xpp.nextText());
                            }

                        }else if (xpp.getName().equalsIgnoreCase("link")){
                            if (insideItem){




                                links.add(xpp.nextText());
                            }
                        }else if(xpp.getName().equalsIgnoreCase("pubDate")){
                            if (insideItem){

                                pubDates.add(dateEdit(xpp.nextText()));
                            }
                        }else if(xpp.getName().equalsIgnoreCase("image")){
                            if (insideItem){

                                images_urls.add(xpp.nextText());
                            }
                        }




                    }else if (eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){

                        insideItem=false;
                    }
                    eventType=xpp.next();
                }


            }catch (MalformedURLException e){
                exception=e;
            }catch (XmlPullParserException e){
                exception=e;
            }catch (IOException e){
                exception=e;
            }
            return exception;
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);



            for (int i=0;i<titles.size();i++){

                //System.out.println(d);
                String source=links.get(i).substring(12,14).toUpperCase();
                model_class nw=new model_class(titles.get(i),source,links.get(i),pubDates.get(i),images_urls.get(i));
                allNews.add(nw);
            }

            adapter=new RVAdapter(MainActivity.this,allNews);
            recyclerView.setAdapter(adapter);


        }
    }







    public String dateEdit(String date)  {

        SimpleDateFormat input = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");

        Date date1= null;
        try {
            date1 = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return output.format(date1);

    }


    private void filter(String text){
        ArrayList<model_class> filteredList=new ArrayList<>();

        for(model_class item:allNews){
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {


        return false;

    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filter(newText);

        System.out.println(newText);
        return true;
    }
}

