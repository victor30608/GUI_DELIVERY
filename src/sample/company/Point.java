package sample.company;

public class Point {
    private double lng;
    private double lat;
    public String name;
    private static final double EARTH_RADIUS = 6371.;

    public Point() {
        this.lng = 0;
        this.lat = 0;
    }

    public Point(double a, double b) {
        this.lng = a;
        this.lat = b;
    }

    public Point(Point b) {
        this.lng = b.Getlng();
        this.lat = b.Getlat();
        this.name=b.name;
    }

    public void Setlng(double lng) {
        this.lng = lng;
    }

    public void Setlat(double lat) {
        this.lat = lat;
    }

    public double Getlng() {
        return lng;
    }

    public double Getlat() {
        return lat;
    }

    public String toString() {
        return lat + "," + lng;
    }

    /**
     * Расстояние между 2 точкам
     *
     * @param Point1
     * @param Point2
     * @return distance
     */
    public double distance(Point Point1, Point Point2) {

        // Рассчитываем расстояние между точками
        final double dlng = deg2rad(Point1.lng - Point2.lng);
        final double dlat = deg2rad(Point1.lat - Point2.lat);
        final double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(deg2rad(Point2.lat))
                * Math.cos(deg2rad(Point1.lat)) * Math.sin(dlng / 2) * Math.sin(dlng / 2);
        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//        System.out.println("distance: " + c * EARTH_RADIUS); // получаем расстояние в километрах
        return c * EARTH_RADIUS;
    }

    /**
     * Преобразует значение из градусов в радианы
     *
     * @param degree
     * @return
     */

    private static double deg2rad(final double degree) {
        return degree * (Math.PI / 180);
    }
}
