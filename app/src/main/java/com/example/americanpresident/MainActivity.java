package com.example.americanpresident;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<PresidentModel> mang;
    ArrayList<String> arrNames=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView) findViewById(R.id.listView);
        mang=new ArrayList<PresidentModel>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new docJSON().execute("https://run.mocky.io/v3/dd56c0bd-f4be-49de-b5c2-8e1bf630b38a");
            }
        });


    }

    class docJSON extends AsyncTask<String,Integer,String>{
        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            //  Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            try {
                JSONArray mangjSON =new JSONArray(s);
                for(int i=0;i<mangjSON.length();i++){
                    JSONObject pep=mangjSON.getJSONObject(i);
                    mang.add(new PresidentModel(
                            pep.getString("name"),
                            pep.getString("period"),
                            pep.getString("img")

                    ));
                }
                PresidentAdapter adapter=new PresidentAdapter(
                        getApplicationContext(),
                        R.layout.activity_customlistview,
                        mang
                );
                lv.setAdapter(adapter);


                //  Toast.makeText(getApplicationContext(),""+mang.size(),Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
    private static String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content=new StringBuilder();
        try{
            URL url =new URL(theUrl);
            URLConnection urlConnection=url.openConnection();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while((line=bufferedReader.readLine())!=null){
                content.append(line);
            }

            bufferedReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return content.toString();

    }
}
/*    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }*/
