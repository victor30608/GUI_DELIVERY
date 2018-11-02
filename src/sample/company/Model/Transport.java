package sample.company.Model;
import java.time.LocalDateTime;
import java.util.*;
import sample.company.Controller.Product;

public class Transport {
   public TypeOfTransport.Type name;
    public LocalDateTime fordertime ;
    public ArrayList<Product> Allorder;
    public boolean available;
    public long alltime;
    public  Transport()
    {
        Allorder=new ArrayList<Product>();
        fordertime =LocalDateTime.now();
        name= TypeOfTransport.Type.driving;
        available=true;
        alltime =0;

    }
    public Transport(TypeOfTransport.Type type, LocalDateTime t ,boolean mode ) {
        name = type;
        fordertime = t;
        Allorder=new ArrayList<Product>();
        available = mode;
    }
    public void checktime()
    {
        if(available&&alltime>40) available=false;
        if(!available&&alltime<=40) available=true;

    }
    public String allorder()
    { String ans = "";
     for(Product i:Allorder)
     {
         ans=ans+" "+i.place.name;
     }
     return ans;
    }
    public int numoforder()
    {
        return Allorder.size();
    }
    public void set_avail(boolean mode)
    {
        this.available=mode;
    }
    public void addProduct(Product tmp)
    {
        this.Allorder.add(tmp);
    }

}
