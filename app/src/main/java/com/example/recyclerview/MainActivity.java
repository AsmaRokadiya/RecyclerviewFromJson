package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button click;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private StringBuffer resp;
    private static final String ServerURLpath="https://api.myjson.com/bins/1a3l5o";
    private ContactAdapter adapter;
    private List<Contacts>contactsList=new ArrayList<>();
    private RecyclerView.LayoutManager  layoutManager;
    private URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click=(Button)findViewById(R.id.button);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        progressBar=(ProgressBar)findViewById(R.id.progresbar);

        adapter=new ContactAdapter(contactsList);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MainAsyncTask().execute();
            }
        });


    }

    public class MainAsyncTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {

            getwebserviceCall();
            return null;
        }

        private void getwebserviceCall() {
            try{

            url= new URL(ServerURLpath);
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                int respoode=conn.getResponseCode();
                if(respoode==HttpURLConnection.HTTP_OK){
                    BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String output;
                    resp=new StringBuffer();

                    while ((output=reader.readLine())!=null){

                        resp.append(output);

                    }
                    reader.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }

            String respText=resp.toString();
            System.out.println("Response" + respText);

            try {
                JSONObject mainobj=new JSONObject(respText);
                JSONArray jsonArray=mainobj.getJSONArray("contacts");

                for (int position=0; position<jsonArray.length(); position++){

                    JSONObject jsonObject=jsonArray.getJSONObject(position);
                    String id=jsonObject.getString("id");
                    String name=jsonObject.getString("name");
                    String email=jsonObject.getString("email");
                    String phone=jsonObject.getJSONObject("phone").getString("mobile");

                    Contacts contacts=new Contacts(id,name,email,phone);
                    contactsList.add(contacts);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressBar.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        }
    }
}
