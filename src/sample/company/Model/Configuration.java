package sample.company.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;

/**
 * Created by 8160994 on 05.10.2018.
 */

public class Configuration {
    public static void loadconf(Company mycomp) throws  IOException, JSONException
    {
            BufferedReader br = new BufferedReader(new FileReader("config.json"));
            String s = br.readLine();
            JSONObject config = new JSONObject(s);
            double lat =config.getDouble("latitude");
            double lon =config.getDouble("longitude");
            String add =config.getString("address");
            mycomp.place.Setlat(lat);
            mycomp.place.Setlng(lon);
            mycomp.address=add;
            br.close();
             BufferedReader read = new BufferedReader(new FileReader("workers.json"));
             s=read.readLine();
             JSONArray work = new JSONArray(s);

             for(int i=0;i<work.length();i++)
             {
                 Transport tmp = new Transport();
               tmp.name= TypeOfTransport.Type.valueOf(work.getJSONObject(i).getString("name"));
               mycomp.worker.add(tmp);
             }
             read.close();
    }

    public static void saveconf(Company mycomp) throws JSONException,IOException
    {
            JSONObject config = new JSONObject();
            config.put("latitude", mycomp.place.Getlat());
            config.put("longitude", mycomp.place.Getlng());
            config.put("address", mycomp.address);
            JSONArray work = new JSONArray();
            FileWriter file = new FileWriter("config.json");
            file.write(config.toString());
            file.close();
            for(int i=0;i<mycomp.worker.size();i++) {

                JSONObject tr=new JSONObject();
                tr.put("name",mycomp.worker.get(i).name);
                work.put(tr);
            }
            FileWriter file1 = new FileWriter("workers.json");
            file1.write(work.toString());
            file1.close();
        }
    }
