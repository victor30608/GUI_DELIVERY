//package com.company.View;
//
//import com.company.Controller.Calculation;
//import com.company.Controller.DeliveryTime;
//import com.company.Controller.Geodecoding;
//import com.company.Controller.Product;
//import com.company.Model.Company;
//import com.company.Model.Configuration;
//import com.company.Model.Transport;
//import com.company.Model.TypeOfTransport;
//import org.json.JSONException;
//
//import java.io.Console;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.Scanner;
//import java.util.ArrayList;
//import com.company.Point;
//import static com.company.Model.Configuration.loadconf;
///**
// * Created by 8160325 on 21.09.2018.
// */
//public class Menu {
//    public void start() {
//        Company comp = new Company();
//        try{
//            Configuration.loadconf(comp);
//        }
//        catch(IOException e)
//        {
//            e.printStackTrace();
//        }
//        catch (JSONException e)
//        {
//            e.printStackTrace();
//        }
//        ArrayList<Product> Allor=new ArrayList<>();
//
//        boolean developer_mode = false;
//        if(!developer_mode) {
//
//                System.out.print("1.Edit something\n");
//                System.out.print("2.Make an order\n");
//                Scanner in = new Scanner(System.in);
//                int ans = Integer.parseInt(in.nextLine());
//                switch (ans) {
//                    case 1: {
//                        System.out.print("Adress of company: ");
//                        String address1 = in.nextLine();
//                        Point p1 = new Point();
//                        try {
//                            p1 = Geodecoding.decode(address1);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        if (comp.worker.size() != 0) {
//                            comp.worker.clear();
//                        }
//                        ArrayList<Transport> workers = new ArrayList<Transport>();
//                        System.out.print("How many cars?");
//                        int cars = in.nextInt();
//                        for (int i = 0; i < cars; i++) {
//                            workers.add(new Transport());
//                        }
//                        System.out.print("How many quadro?");
//                        int q = in.nextInt();
//                        for (int i = 0; i < q; i++) {
//                            workers.add(new Transport());
//                        }
//                        System.out.print("How many men?");
//                        int men = in.nextInt();
//                        for (int i = 0; i < men; i++) {
//                            workers.add(new Transport());
//                        }
//                        System.out.print("How many bycircles?");
//                        int byc = in.nextInt();
//                        for (int i = 0; i < byc; i++) {
//                            workers.add(new Transport());
//                        }
//
//
//                        comp.worker = workers;
//                        try {
//                            Configuration.saveconf(comp);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    case 2: {
//                        while (true) {
//                        //System.out.print("Enter an order: ");
//                        //String order = in.nextLine();
//                        System.out.print("Time for delive: ");
//                        int time = Integer.parseInt(in.nextLine());
//
//                        System.out.print("Enter address: ");
//                        String address2 = in.nextLine();
//                        Point p2 = new Point();
//                        try {
//                            p2 = Geodecoding.decode(address2);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        p2.name=address2;
//                        Product tmp = new Product("hz", 1, p2, LocalDateTime.now().plusMinutes(10));
//                        Allor.add(tmp);
//                        //поиск пути
//                        try {
//                            System.out.println(Calculation.singecalc(comp, tmp));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }
//
//            }
//        }
////        else
////        {
////            System.out.print("Enter an order: ");
////            Scanner in = new Scanner(System.in);
////            String order = in.nextLine();
////
////            System.out.print("Enter address: ");
////            String address2 = in.nextLine();
////            Point p2 = new Point();
////            p2.SetX(in.nextInt());
////            p2.SetY(in.nextInt());
////            try
////            {
////                Geodecoding.decode(address2, p2);
////            }
////            catch(IOException e)
////            {
////                e.printStackTrace();
////            }
////            catch (JSONException e)
////            {
////                e.printStackTrace();
////            }
////
////            //поиск пути
////            try {
////                DeliveryTime.calculate(address1,address2,"bicycling");
////            }
////            catch(IOException e) {
////                e.printStackTrace();
////            }
////            catch (JSONException e) {
////                e.printStackTrace();
////            }
//    }
//
//
////save
//    /*try{
//    Configuration.saveconf(comp);//????????
//}
//    catch(IOException e)
//    {
//        e.printStackTrace();
//    }
//    catch (JSONException e)
//    {
//        e.printStackTrace();
//    }*/
//}
