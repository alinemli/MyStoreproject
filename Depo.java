package java_depomini_proje;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Depo {

    Scanner scan = new Scanner(System.in);
    Map<Integer, Urun> urunler = new HashMap<>();

    public void menu() {
        try {
        System.out.println(ConsoleColors.BLUE+"=====================================================================================");
        System.out.println("                    ***       DEPO URUN ISLEM MENUSU        ***                      ");
        System.out.println("=====================================================================================");
        System.out.println("1-Urun tanimlama");
        System.out.println("2-Urun listele");
        System.out.println("3-Urun girisi");
        System.out.println("4-Urun raf girisi");
        System.out.println("5-Urun cikisi");
        System.out.println("0-Menuden cikis");
        System.out.print("Seciminiz : "+ConsoleColors.RESET);

        int secim = getInt();



        switch (secim) {
            case 1 -> urunTanimlama();
            case 2 -> urunListeleme();
            case 3 -> urunGirisi();
            case 4 -> urunRafGirisi();
            case 5 -> urunCikisi();
            case 0 -> {
                System.out.println("Menüden çıkış yapılıyor, iyi günler.");
                dosyaYaz();
                return;
            }
            default -> System.out.println("Hatalı giriş! Lütfen geçerli bir seçim yapınız.");
        }
    } catch (Exception e) {
        System.err.println("Beklenmeyen bir hata oluştu: " + e.getMessage());
    }

        menu();

    }

    private void urunCikisi() {
        System.out.println(ConsoleColors.PURPLE+"=====================================================================================");
        System.out.println("                    ***       DEPO URUN CIKIS       ***                      ");
        System.out.println("=====================================================================================");

        urunListeleme();
        System.out.println("Cikisini yapmak isteidiginiz urunun id sini giriniz");
        int id = getInt(); // kullancinin girdigi datanin int olup olmadigini daha önce olusturdugumut getInt methotu ile kontrol ettik

        Urun urun = urunVarmi(id); // map in icinde bu id ye sahip urun olup olmadigina bakiyoruz
        if(urun != null) {

        System.out.println(urun);


        System.out.println("Cikarmak istediginiz miktari girin");
        double miktar = getdouble(); //kullancinin sayisal deger girip girmedigini kontrol ederiz

        if (miktar <= 0) {
            System.out.println("Miktar 0 dan büyük olmalidir");
        } else if (miktar > urun.getMiktar()) {
            System.out.println("Miktar stoktan buyuk olamaz");

        } else {
            if (onay()) {
                urun.setMiktar(urun.getMiktar() - miktar);
                urunler.put(id, urun);

                System.out.println(urun);
                System.out.println("Urun basarili bir sekilde cikarilmistir "+ConsoleColors.RESET);
            } else {
                System.err.println("Isleminiz iptal edildi");

            }

        }

        }

    }

    private void urunRafGirisi() {
        System.out.println(ConsoleColors.PURPLE+"=====================================================================================");
        System.out.println("                    ***       DEPO URUN RAF GIRISI       ***                      ");
        System.out.println("=====================================================================================");

        urunListeleme();
        System.out.print("Rafini girmek istediginiz urunun id sini giriniz : ");
        int id = getInt();
        Urun urun = urunVarmi(id); // map in icinde bu id ye sahip urun olup olmadigina bakiyoruz
        if(urun != null) {

        System.out.println(urun);

        System.out.println("Koymak istediginiz rafi giriniz : ");
        String raf = scan.nextLine();

        if (onay()) {
            urun.setRaf(raf);
            urunler.put(id, urun);

            System.out.println(urun);

            System.out.println("Urun rafa basarili bir sekilde yerlestirilmistir. " +ConsoleColors.RESET);

        } else {
            System.err.println("Isleminiz iptal edildi");

        }

    }

    }

    private void urunGirisi() {
        System.out.println(ConsoleColors.PURPLE+"=====================================================================================");
        System.out.println("                    ***       DEPO URUN GIRIS       ***                      ");
        System.out.println("=====================================================================================");

        urunListeleme();
        System.out.println(ConsoleColors.PURPLE+"Eklemek isteidigniz urunun id sini giriniz");
        int id = getInt(); // kullancinin girdigi datanin int olup olmadigini daha önce olusturdugumut getInt methotu ile kontrol ettik

        Urun urun = urunVarmi(id); // map in icinde bu id ye sahip urun olup olmadigina bakiyoruz
        if(urun != null) {


            System.out.println(urun);

            System.out.println("Eklemek istediginiz miktari girin");
            double miktar = getdouble(); //kullancinin sayisal deger girip girmedigini kontrol ederiz

            if (miktar <= 0) {
                System.out.println("Miktar 0 dan büyük olmalidir");
            } else {

                if (onay()) {
                    urun.setMiktar(urun.getMiktar() + miktar);
                    urunler.put(id, urun);

                    System.out.println(urun);

                    System.out.println("Urun basarili bir sekilde girilmistir "+ ConsoleColors.RESET);
                } else {
                    System.err.println("Isleminiz iptal edildi");

                }


            }

        }
    }

    private void urunListeleme() {

        System.out.println(ConsoleColors.GREEN+"=====================================================================================");
        System.out.println("                    ***       DEPO URUN LISTELEME       ***                      ");
        System.out.println("=====================================================================================");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-18s %-20s %-15s %-15s %-10s\n", "ID", "İsmi", "Üreticisi", "Miktarı", "Birimi", "Raf");
        System.out.println("-----------------------------------------------------------------------------------------");

        urunler.values().forEach(urun -> System.out.printf("%-10d %-18s %-20s %-15s %-15s %-10s\n",
                urun.getId(),
                urun.getUrunIsmi(),
                urun.getUretici(),
                urun.getMiktar(),
                urun.getBirim(),
                urun.getRaf()));

        System.out.println("-----------------------------------------------------------------------------------------"+ConsoleColors.RESET);

    }


    private void urunTanimlama() {
        System.out.println(ConsoleColors.PURPLE+"=====================================================================================");
        System.out.println("                    ***       DEPO URUN TANIMLAMA         ***                      ");
        System.out.println("=====================================================================================");
        System.out.print("Urun ismi giriniz : ");
        String isim = scan.nextLine();
        System.out.print("Uretici bilgisini giriniz : ");
        String ureticiBilgisi = scan.nextLine();
        System.out.print("Urun birimini giriniz(adet, kg, ..... : ");
        String urunBirimi = scan.nextLine();

        if(onay()){
            if (urunVarmi(isim, ureticiBilgisi, urunBirimi)) {
                System.out.println("Bu isim ve uretici zaten sistemde mevcuttur. Urun girisi icin 3'e basiniz");
            } else {
                Urun urun = new Urun(isim, ureticiBilgisi, urunBirimi);
                urunler.put(urun.getId(), urun);
                System.out.println("Urun tanimlanmistir" +ConsoleColors.RESET);
            }
        }else {
            System.err.println("Iptal edildi");
        }

        // Yeni ürün mevcut degilse yeni urun  oluşturuluyor ve listeye ekleniyor




    }


    public int getInt() {

        while (true) {
            try {
                int num = scan.nextInt();
                scan.nextLine();
                return num;
            } catch (InputMismatchException e) {
                System.out.println("Gecersiz giris lutfen bir tamsayi giriniz");
                scan.nextLine();
            }
        }
    }

    public double getdouble() {

        while (true) {
            try {
                double num = scan.nextDouble();
                scan.nextLine();
                return num;
            } catch (InputMismatchException e) {
                System.out.println("Gecersiz giris lutfen bir sayi giriniz");
                scan.nextLine();
            }
        }
    }

    public void dosyaOku(){
        Path path = Paths.get("src/main/java/java_depomini_proje/urunler.txt");

        try {
            Files.
                    lines(path).
                    forEach(t -> urunler.put(Integer.valueOf(t.split(",")[0]) , new Urun(Integer.valueOf(t.split(",")[0]),t.split(",")[1],t.split(",")[2],Double.valueOf(t.split(",")[3]),t.split(",")[4],t.split(",")[5])));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void dosyaYaz(){
        File file = new File("src/main/java/java_depomini_proje/urunler.txt");
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String dosyaVeri = urunler.values().
                stream().
                map(t -> t.getId() + "," + t.getUrunIsmi() + "," + t.getUretici() + "," + t.getMiktar() + "," + t.getBirim() + "," + t.getRaf() + "\n").
                reduce("" , (t, u) -> t+u);

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(dosyaVeri);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Depo() {
       /* Urun urun = new Urun("Elma", "Golden", "Kg");
        urunler.put(urun.getId(), urun);
        Urun urun1 = new Urun("UN", "Piyale", "Paket");
        urunler.put(urun1.getId(), urun1);
        Urun urun2 = new Urun("Makarna", "Ankara", "Koli");
        urunler.put(urun2.getId(), urun2);

        */
        dosyaOku();

    }

    public boolean urunVarmi(String urunIsmi, String uretici, String birim) {

        boolean control = urunler.values().
                stream().
                anyMatch(t -> t.getUrunIsmi().equalsIgnoreCase(urunIsmi) && t.getUretici().equalsIgnoreCase(uretici) && t.getBirim().equalsIgnoreCase(birim));

        return control;

    }
    public Urun urunVarmi(int id){
        Urun urun = urunler.get(id);
        if(urun == null){
            System.out.println("Girdiginiz id'ye ait urun bulunamamistir.");

        }
        return urun;
    }

    public boolean onay() {

        System.out.println("Yaptiginiz islemi onayliyorsaniz 'E' iptal etmek icin 'H' giriniz.");
        char control = scan.next().toUpperCase().charAt(0);
        if (control == 'E') {
            return true;
        } else if (control == 'H') {
            return false;
        } else {
            System.out.println("E veya H giriniz");
            return onay();

        }

    }


}
