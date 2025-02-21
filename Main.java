package java_depomini_proje;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        if(systemGiris()){
            Depo depo = new Depo();
            depo.menu();
        }else {
            System.out.println("Giris icin yetkiniz yoktur");
        }


    }

    public static boolean systemGiris(){
        Scanner scan = new Scanner(System.in);
        int control =0;

        while (control<3){
            System.out.print("Kullanici adinizi giriniz : ");
            String kullaniciAdi = scan.next();
            System.out.print("Sifrenizi giriniz : ");
            String sifre = scan.next();
            if(kullaniciAdi.equals("admin") && sifre.equals("pwd123")){
                System.out.println("Basarili giris");
                return true;
            }else {
                control++;
                System.err.println("Bilgileriniz hatalidir..");
                System.out.println((3-control) + " Hakkiniz kalmistir");


            }
        }
        return false;


    }
}
