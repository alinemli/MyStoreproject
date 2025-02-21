package java_depomini_proje;
//POJO class
public class Urun {

    private int id;
    private String urunIsmi;
    private String uretici;
    private double miktar;
    private String birim;
    private String raf;
    private static int count = 1000;

    public Urun(String urunIsmi, String uretici, String birim) {

        this.id = ++count;
        this.urunIsmi = urunIsmi;
        this.uretici = uretici;
        this.miktar = 0;
        this.birim = birim;
        this.raf = "-";
    }
    public Urun(int id, String urunIsmi, String uretici, double miktar, String birim, String raf) {
        this.count = Math.max(id, this.count);
        this.id = id;
        this.urunIsmi = urunIsmi;
        this.uretici = uretici;
        this.miktar = miktar;
        this.birim = birim;
        this.raf = raf;
    }


    //getter

    public int getId() {
        return id;
    }

    public String getUrunIsmi() {
        return urunIsmi;
    }

    public String getUretici() {
        return uretici;
    }

    public double getMiktar() {
        return miktar;
    }

    public String getBirim() {
        return birim;
    }

    public String getRaf() {
        return raf;
    }

    //setter

    public void setUrunIsmi(String urunIsmi) {
        this.urunIsmi = urunIsmi;
    }

    public void setUretici(String uretici) {
        this.uretici = uretici;
    }

    public void setMiktar(double miktar) {
        this.miktar = miktar;
    }

    public void setBirim(String birim) {
        this.birim = birim;
    }

    public void setRaf(String raf) {
        this.raf = raf;
    }


    @Override
    public String toString() {
        return "ID\t\t\t : " + id +
                "\nUrun Ismi\t : " + urunIsmi +
                "\nUretici\t\t : " + uretici +
                "\nMiktar\t\t : " + miktar +
                "\nBirim\t\t : " + birim +
                "\nRaf\t\t\t : " + raf;



    }



}
