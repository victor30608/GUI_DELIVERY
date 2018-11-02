package sample.company.Controller;
import java.io.IOException;
import java.util.Map;
import sample.company.Point;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.collect.Maps;

public class Geodecoding extends AbstractSample {
    public static Point decode(String args) throws IOException, JSONException {
        Point area = new Point();
        area.name=args;
        final String baseUrl = "https://maps.googleapis.com/maps/api/geocode/json";// путь к Geocoding API по HTTP
        final Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");// исходит ли запрос на геокодирование от устройства с датчиком местоположения
        params.put("address","\""+args+"\"");// адрес, который нужно геокодировать
        params.put("key","AIzaSyDkByDpKl6fWkpJOfp9BFMEM-_Xoc4WSsM");
//        System.out.println(args);
        final String url = baseUrl + '?' + encodeParams(params);// генерируем путь с параметрами
//        System.out.println(url);// Путь, что бы можно было посмотреть в браузере ответ службы
        final JSONObject response = JsonReader.read(url);// делаем запрос к вебсервису и получаем от него ответ
        // как правило наиболее подходящий ответ первый и данные о кординатах можно получить по пути
        // //results[0]/geometry/location/lng и //results[0]/geometry/location/lat
        JSONObject location = response.getJSONArray("results").getJSONObject(0);
        location = location.getJSONObject("geometry");
        location = location.getJSONObject("location");
        area.Setlng(location.getDouble("lng"));// долгота
        area.Setlat(location.getDouble("lat"));// широта
        return area;
    }

}