package sample.company.Controller;

import com.google.common.collect.Maps;
import org.json.JSONException;
import org.json.JSONObject;
import sample.company.Point;
import java.io.IOException;
import java.util.Map;

public  class DeliveryTime  extends AbstractSample {
    public static double calculate(String addres1,String addres2,String mode) throws IOException, JSONException {
        if(mode.equals("quadro"))
        {
            Point p1=new Point();
            Point p2=new Point();
            p1=Geodecoding.decode(addres1);
            p2=Geodecoding.decode(addres2);
            double t= p1.distance(p1,p2)/50.0*60;
        }
         String baseUrl ="https://maps.googleapis.com/maps/api/directions/json";// путь к Geocoding API по
        // HTTP
         Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");// указывает, исходит ли запрос на геокодирование от устройства с датчиком
        params.put("language", "ru");// язык данные на котором мы хочем получить
        params.put("mode", mode);// способ перемещения, может быть driving, walking, bicycling
        params.put("origin", addres1);// адрес или текстовое значение широты и
        // отправного пункта маршрута
        params.put("destination", addres2);// адрес или текстовое значение широты и долготы
        // долготы конечного пункта маршрута
        params.put("key","AIzaSyDkByDpKl6fWkpJOfp9BFMEM-_Xoc4WSsM");
         String url = baseUrl + '?' + encodeParams(params);// генерируем путь с параметрами
//        System.out.println(url); // Можем проверить что вернет этот путь в браузере
         JSONObject response = JsonReader.read(url);// делаем запрос к вебсервису и получаем от него ответ
        // как правило наиболее подходящий ответ первый и данные о кординатах можно получить по пути
        // //results[0]/geometry/location/lng и //results[0]/geometry/location/lat
        String status = response.getString("status");
        if(status.equals("OK")) {
            JSONObject location = response.getJSONArray("routes").getJSONObject(0);
            location = location.getJSONArray("legs").getJSONObject(0);
            String distance = location.getJSONObject("distance").getString("text");
            String duration = location.getJSONObject("duration").getString("text");
//            System.out.println(distance + "\n" + duration);

            return Double.parseDouble(duration.substring(0,duration.length()-4));
        }
        System.out.println(status);
         return 0;
    }
}
