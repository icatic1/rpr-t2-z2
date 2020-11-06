package ba.unsa.etf.rpr.tutorijal02;

import java.util.Objects;

public class Interval {
private double pocetnaTacka;
private double krajnjaTacka;
private boolean pripadaPocetna;
private boolean pripadaKrajnja;

    public Interval(){
        this.pocetnaTacka = 0;
        this.krajnjaTacka = 0;
        this.pripadaPocetna = false;
        this.pripadaKrajnja = false;
    }

    public Interval(double pocetnaTacka, double krajnjaTacka, boolean pripadaPocetna, boolean pripadaKrajnja) {
        if(pocetnaTacka>krajnjaTacka) throw new IllegalArgumentException();

        this.pocetnaTacka = pocetnaTacka;
        this.krajnjaTacka = krajnjaTacka;
        this.pripadaPocetna = pripadaPocetna;
        this.pripadaKrajnja = pripadaKrajnja;
    }

    public boolean isNull() {
        if (pocetnaTacka == 0 && krajnjaTacka == 0 && !pripadaKrajnja && !pripadaPocetna) return true;
        return false;
    }

    public boolean isIn(double v) {
        if(pripadaPocetna && !pripadaKrajnja){
            if(v>pocetnaTacka && v<krajnjaTacka || v==pocetnaTacka) return true;
            return false;
        }
        else if(!pripadaPocetna && pripadaKrajnja){
            if(v>pocetnaTacka && v<krajnjaTacka || v==krajnjaTacka) return true;
            return false;
        }
        else if(pripadaPocetna && pripadaKrajnja){
            if(v>pocetnaTacka && v<krajnjaTacka || v==pocetnaTacka || v==krajnjaTacka) return true;
            return false;
        }
        else {
            if(v>pocetnaTacka && v<krajnjaTacka) return true;
            return false;
        }
    }

    public Interval intersect(Interval i) {

        if(krajnjaTacka < i.pocetnaTacka) return new Interval();
        else if(i.krajnjaTacka < pocetnaTacka) return new Interval();
        double nova_pocetna,nova_krajnja;
        boolean novi_pocetak,novi_kraj;

        if(pocetnaTacka > i.pocetnaTacka){
            nova_pocetna = pocetnaTacka;
            novi_pocetak = pripadaPocetna;
        }else{
            nova_pocetna = i.pocetnaTacka;
            novi_pocetak = i.pripadaPocetna;
        }

        if(krajnjaTacka < i.krajnjaTacka){
            nova_krajnja = krajnjaTacka;
            novi_kraj = pripadaKrajnja;
        }else{
            nova_krajnja = i.krajnjaTacka;
            novi_kraj = i.pripadaKrajnja;
        }
        return new Interval(nova_pocetna,nova_krajnja,novi_pocetak,novi_kraj);

    }

    public static Interval intersect(Interval i1,Interval i2) {
        if(i1.krajnjaTacka < i2.pocetnaTacka) return new Interval();
        else if(i2.krajnjaTacka < i1.pocetnaTacka) return new Interval();
        double nova_pocetna,nova_krajnja;
        boolean novi_pocetak,novi_kraj;

        if(i1.pocetnaTacka > i2.pocetnaTacka){
            nova_pocetna = i1.pocetnaTacka;
            novi_pocetak = i1.pripadaPocetna;
        }else{
            nova_pocetna = i2.pocetnaTacka;
            novi_pocetak = i2.pripadaPocetna;
        }

        if(i1.krajnjaTacka < i2.krajnjaTacka){
            nova_krajnja = i1.krajnjaTacka;
            novi_kraj = i1.pripadaKrajnja;
        }else{
            nova_krajnja = i2.krajnjaTacka;
            novi_kraj = i2.pripadaKrajnja;
        }
        return new Interval(nova_pocetna,nova_krajnja,novi_pocetak,novi_kraj);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(pripadaPocetna) sb.append("[");
        else sb.append("(");
        if(!(pocetnaTacka == 0 && krajnjaTacka ==0)){
            sb.append(pocetnaTacka);
            sb.append(",");
            sb.append(krajnjaTacka);
        }
        if(pripadaKrajnja) sb.append("]");
        else sb.append(")");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return Double.compare(interval.pocetnaTacka, pocetnaTacka) == 0 &&
                Double.compare(interval.krajnjaTacka, krajnjaTacka) == 0 &&
                pripadaPocetna == interval.pripadaPocetna &&
                pripadaKrajnja == interval.pripadaKrajnja;
    }

}
