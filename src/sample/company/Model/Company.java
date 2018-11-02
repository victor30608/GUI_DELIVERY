package sample.company.Model;


import sample.company.*;

import java.util.ArrayList;

/**
 * Created by 8160325 on 21.09.2018.
 */

public class Company {
    public Point place;
    public String address;
    public ArrayList<Transport> worker;

    public Company()
    {
        place = new Point();
        worker = new ArrayList<Transport>();
    }

    public Company(Point p, ArrayList<Transport> all) {
        place = new Point(p);
        worker = (ArrayList<Transport>) all.clone();
    }
}
