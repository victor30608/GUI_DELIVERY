package sample.company.Controller;

import  sample.company.Model.Company;
import  sample.company.Model.Transport;
import org.json.JSONException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Calculation {
    public static void calculate(Company MC, ArrayList<Product> AllProd) throws IOException, JSONException {
//        Date today = Calendar.getInstance().getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd MM HH:mm:ss");
//        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
//        String date = sdf.format(today);
//        System.out.println(date);
        boolean free = false;
        for (Product i : AllProd) {
            free = false;
            for (Transport tr : MC.worker) {
                if (!i.av) break;
                tr.checktime();
                int deltime = 0;
                if (tr.Allorder.size() == 0) {
                    deltime = (int) DeliveryTime.calculate(MC.place.toString(), i.place.toString(), tr.name.toString());
                    tr.alltime += deltime;//время , прошедшее с выезда
                    tr.addProduct(i);
                    free = true;
                    tr.fordertime = i.t_order.plusMinutes((int) i.time); // время выезда из фирмы
                    tr.fordertime = tr.fordertime.minusMinutes((int) deltime);// время выезда из фирмы для доставки 1 заказа
                    i.av = false;
                    break;
                }
                if (!tr.Allorder.get(tr.numoforder() - 1).place.name.equals(MC.address)) {
                    long tobase = (int) DeliveryTime.calculate(tr.Allorder.get(tr.numoforder() - 1).place.toString(), MC.place.toString(), tr.name.toString());
                    long t = ChronoUnit.MINUTES.between(tr.fordertime.plusMinutes(tobase + tr.alltime), i.t_order);
                    if (t >= 0) {
                        tr.fordertime = tr.fordertime.plusMinutes(tobase + tr.alltime);
                        Product tmp = new Product("base", 0, MC.place, tr.fordertime);
                        tmp.place.name = MC.address;
                        tr.addProduct(tmp);
                        tr.alltime = 0;
                        tr.available = true;
                    }
                }
                double diff = -ChronoUnit.MINUTES.between(tr.fordertime, i.t_order); // был ли получен заказ до того, как транспорт "ушёл"
                if (diff > 0 || tr.Allorder.get(tr.numoforder() - 1).place.name.equals(MC.address)) {
                    deltime = (int) DeliveryTime.calculate(tr.Allorder.get(tr.numoforder() - 1).place.toString(), i.place.toString(), tr.name.toString());
                    double prevdel = DeliveryTime.calculate(tr.Allorder.get(tr.numoforder() - 1).place.toString(), MC.place.toString(), tr.name.toString());//время от последнего заказа к фирме
                    double basetothis = DeliveryTime.calculate(MC.place.toString(), i.place.toString(), tr.name.toString()); // время от  фирмы к текущему заказу
                    double need = -ChronoUnit.MINUTES.between(tr.Allorder.get(tr.numoforder() - 1).t_order.plusMinutes(tr.Allorder.get(tr.numoforder() - 1).time), i.t_order.plusMinutes(i.time)) + prevdel + basetothis;
                    if (need <= 0) //вернулись на фирму
                    {
                        tr.fordertime = tr.Allorder.get(tr.numoforder() - 1).t_order.plusMinutes(tr.Allorder.get(tr.numoforder() - 1).time).plusMinutes((int) prevdel);
                        tr.alltime = -ChronoUnit.MINUTES.between(i.t_order.plusMinutes(i.time), tr.fordertime);
                        tr.available = true;
                        tr.Allorder.add(i);
                        i.av = false;
                        free = true;
                        break;
                    } else {
                        double razn = -ChronoUnit.MINUTES.between(tr.fordertime.plusMinutes(tr.alltime + deltime), i.t_order.plusMinutes(i.time));
                        if (razn <= 0) {
                            tr.addProduct(i);
                            tr.alltime = -ChronoUnit.MINUTES.between(i.t_order.plusMinutes(i.time), tr.fordertime);
                            i.av = false;
                            break;
                        }
                    }
                }
            }
        }

        int i = 1;
        for (Transport tr : MC.worker) {

            System.out.println(i);
            System.out.println(tr.name + " " + tr.alltime + " " + tr.fordertime + " " + tr.allorder());
            i++;
        }
    }

    public static String singecalc(Company MC, Product i) throws IOException, JSONException {
//        Date today = Calendar.getInstance().getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd MM HH:mm:ss");
//        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
//        String date = sdf.format(today);
//        System.out.println(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        boolean free =false;
        String ans = new String("");
        for (Transport tr : MC.worker) {
            free =false;
            if (!i.av) break;
            tr.checktime();
            int deltime = 0;
            if (tr.Allorder.size() == 0) {
                deltime = (int) DeliveryTime.calculate(MC.place.toString(), i.place.toString(), tr.name.toString());
                tr.alltime += deltime;//время , прошедшее с выезда
                tr.addProduct(i);
                tr.fordertime = LocalDateTime.now(); // время выезда из фирмы
                tr.fordertime = tr.fordertime.plusMinutes((int) deltime);// время выезда из фирмы для доставки 1 заказа
                i.av = false;
                free = true;
                ans= formatter.format(tr.fordertime.plusMinutes(tr.alltime));
                return ans;
            }
            if (!tr.Allorder.get(tr.numoforder() - 1).place.name.equals(MC.address)) {
                long tobase = (int) DeliveryTime.calculate(tr.Allorder.get(tr.numoforder() - 1).place.toString(), MC.place.toString(), tr.name.toString());
                long t = ChronoUnit.MINUTES.between(tr.fordertime.plusMinutes(tobase + tr.alltime), i.t_order);
                if (t >= 0) {
                    tr.fordertime = tr.fordertime.plusMinutes(tobase + tr.alltime);
                    Product tmp = new Product("base", 0, MC.place, tr.fordertime);
                    tmp.place.name = MC.address;
                    tr.addProduct(tmp);
                    tr.alltime = 0;
                    tr.available = true;
                }
            }
            double diff = -ChronoUnit.MINUTES.between(tr.fordertime, i.t_order); // был ли получен заказ до того, как транспорт "ушёл"
            if (diff > 0 || tr.Allorder.get(tr.numoforder() - 1).place.name.equals(MC.address)) {
                deltime = (int) DeliveryTime.calculate(tr.Allorder.get(tr.numoforder() - 1).place.toString(), i.place.toString(), tr.name.toString());
                double prevdel = DeliveryTime.calculate(tr.Allorder.get(tr.numoforder() - 1).place.toString(), MC.place.toString(), tr.name.toString());//время от последнего заказа к фирме
                double basetothis = DeliveryTime.calculate(MC.place.toString(), i.place.toString(), tr.name.toString()); // время от  фирмы к текущему заказу
                double need = -ChronoUnit.MINUTES.between(tr.Allorder.get(tr.numoforder() - 1).t_order.plusMinutes(tr.Allorder.get(tr.numoforder() - 1).time), i.t_order.plusMinutes(i.time)) + prevdel + basetothis;
                if (need <= 0) //вернулись на фирму
                {
                    tr.fordertime = tr.Allorder.get(tr.numoforder() - 1).t_order.plusMinutes(tr.Allorder.get(tr.numoforder() - 1).time).plusMinutes((int) prevdel);
                    tr.alltime = -ChronoUnit.MINUTES.between(i.t_order.plusMinutes(i.time), tr.fordertime);
                    tr.available = true;
                    tr.Allorder.add(i);
                    i.av = false;
                    free = true;
                    ans= formatter.format(tr.fordertime.plusMinutes(tr.alltime));
                    return ans;
                } else {
                    double razn = -ChronoUnit.MINUTES.between(tr.fordertime.plusMinutes(tr.alltime + deltime), i.t_order.plusMinutes(i.time));
                    if (razn <= 0)
                    {
                        tr.addProduct(i);
                        tr.alltime = -ChronoUnit.MINUTES.between(i.t_order.plusMinutes(i.time), tr.fordertime);
                        ans= formatter.format(tr.fordertime.plusMinutes(tr.alltime));
                        i.av = false;
                        free = true;
                        return ans;
                    }
                }
            }
        }
        System.out.println(free+" is"+ans);
        if(!free)  ans="Sorry, but all couriers are busy";
        return ans;
    }
}
