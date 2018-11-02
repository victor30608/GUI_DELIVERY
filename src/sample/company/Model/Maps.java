package sample.company.Model;

import java.util.ArrayList;


/**
 * @author 8160325
 */
public class Maps {

    public ArrayList<Integer> Area;
    public int N;
    public int M;

    Maps() {
        this.Area = new ArrayList<>();
        this.N = 0;
        this.M = 0;
    }

    public void SetN(int n) {
        this.N = n;
    }

    public void SetM(int m) {
        this.M = m;
    }

    public int GetN() {
        return N;
    }

    public int GetM() {
        return M;
    }

    public void add(int a) {
        this.Area.add(a);
    }

}