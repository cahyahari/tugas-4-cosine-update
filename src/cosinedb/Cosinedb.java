/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosinedb;

/**
 *
 * @author Cahya HS
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.StringTokenizer; //mbuh class mbuh fungsi ngunu -,-
import java.util.Vector;

public class Cosinedb {


    public static Vector<String> kata1 = new Vector();
    public static Vector<String> kata2 = new Vector();
    public static Vector<String> teks1 = new Vector();
    public static Vector<String> teks2 = new Vector();
    public static Vector<Integer> jmlteks1 = new Vector();
    public static Vector<Integer> jmlteks2 = new Vector();
    public static Vector<Double> hasil = new Vector();
    public static Vector<Integer> no_id = new Vector();

    public static boolean cekKata(int n, String x) {
        // inisialisasi
        boolean find = false;

        for (int i = 0; i < n; i++) {
            // jika ada yg sama
            if (kata1.get(i).equalsIgnoreCase(x)) {
                find = true;
                break;
            }
        }
        return find;
    }

    public static void tes(String teks) {
        //parameter e teks 3 
        StringTokenizer sa = new StringTokenizer(teks, " ");
        while (sa.hasMoreElements()) {
            //dri potongan tdi dmasukin vec1 buat total kta dmasukin kta1 smua kata gabungan dr kal1 kal2
            kata1.add(sa.nextToken());
        }
        //vektor kata1 buat nyimpan teks3 yg uda kena string tokenizer
//        for (int i = 0; i < kata1.size(); i++) {
//            System.out.println(kata1.get(i));
//        }
//        String temp = "";
//        boolean ketemu = false;
        int a, k, n, m, count = 0, jum;
        n = count + 1;// banyaknya kata dlm suatu kalimat
        k = 0;
        a = 0;
        for (int i = 0; i < kata1.size(); i++) {
            if (!cekKata(a, kata1.get(i))) {
                //bentuk kata baru dlm array
                //klo ada kta sama g dmasukkin kata2
                kata2.add(kata1.get(i));
                k++;
            }
            a++;
        }
        as();

        //  System.out.println(jumlahtotalkata);
//        System.out.println("hasil :" + jumlahkataterpilih / jumlahtotalkata);
    }

    public static void as() {
        int batas = 0;
        //
        for (int i = 0; i < kata2.size(); i++) {
            int jml = 0;
            for (int j = 0; j < teks1.size(); j++) {
                if (teks1.get(j).equalsIgnoreCase(kata2.get(i))) {
                    jml += 1;
                }
            }
            batas += 1;
            jmlteks1.add(jml); //hasil pencocokan kata2 dengan vector teks1 disimpan di jmlteks1
        }
//        for (int i = 0; i < jmlteks1.size(); i++) {
//            System.out.println(jmlteks1.get(i));
//        }

        for (int i = 0; i < kata2.size(); i++) {
            int jml = 0;
            for (int j = 0; j < teks2.size(); j++) {
                if (teks2.get(j).equalsIgnoreCase(kata2.get(i))) {
                    jml += 1;
                }
            }
//            batas += 1;
            jmlteks2.add(jml);
        }
//        System.out.println("    ");
//        for (int i = 0; i < kata2.size(); i++) {
//            System.out.print(kata2.get(i) + " ");
//        }
//        System.out.println("");
//        for (int i = 0; i < jmlteks1.size(); i++) {
//            System.out.print(jmlteks1.get(i) + " ");
//        }
//        System.out.println("");

//        for (int i = 0; i < jmlteks2.size(); i++) {
//            System.out.print(jmlteks2.get(i) + " ");
//        }
        //cari pembialng

//        System.out.println("");
        double pembilang = 0;
        for (int i = 0; i < jmlteks1.size(); i++) {
            //indeks / banyaknya atas bawah sama, dikali disimpan di doubl kali
            double kali = jmlteks1.get(i) * jmlteks2.get(i);
            pembilang = pembilang + kali;
            //kali=0 supaya pas di loop balik jd nol lg
            kali = 0;
        }
//        System.out.println("===============================");
//        System.out.println("Pembilangnya adalah = " + pembilang);

        //cri pnyebut
        double jumlah_satu = 0;
        for (int i = 0; i < jmlteks1.size(); i++) {
            double simpan = jmlteks1.get(i) * jmlteks1.get(i); //untk kuadrat
            jumlah_satu = jumlah_satu + simpan;//dijumlahkan dg sampingnya
            simpan = 0;
        }
        double jumlah_dua = 0;

        for (int i = 0; i < jmlteks2.size(); i++) {
            double simpan = jmlteks2.get(i) * jmlteks2.get(i);
            jumlah_dua = jumlah_dua + simpan;
        }

        double total = jumlah_dua * jumlah_satu;

        double penyebut = Math.sqrt(total);//AKAR
        
//        System.out.println("penyebutnya adalah = " + penyebut);
//        System.out.println("========================");
//        System.out.println("Hasil = " + pembilang / penyebut);
            hasil.add(pembilang / penyebut); //hasil perhitungan cosinus disimpan di vector hasil
    }

    public static void main(String args[]) {
        System.out.println("masukkan kalimat ke 1");
        Scanner a = new Scanner(System.in);
        String teks = a.nextLine(); //inputan disimpan di variabel teks
        // String teks = "saya saya sedang kuliah ";
        //class stringtkn buat motong + spasi,ktm spasi dpotong
        StringTokenizer sa = new StringTokenizer(teks, " ");
        while (sa.hasMoreElements()) {
            //ktm spasi di loop lg
            teks1.add(sa.nextToken()); //..tiap potongan dimasukkan vector teks1
        }
        String sql = "select * from pengumuman ;";
        ResultSet re = KoneksiDB.executeQuery(sql);
        try {
            while (re.next()) {
                int no = Integer.parseInt(re.getString(1)); //1 mununjukaN dapatin id nya
                no_id.add(no); //ID disimpan di no_id 
                
                if (no == 40) {//btasan run
                    break;
                }
                String teks22 = re.getString(2);//2 nunjukan ambil title nya
                StringTokenizer d = new StringTokenizer(teks22, " ");
                while (d.hasMoreElements()) {
                    teks2.add(d.nextToken()); //vector teks2 isinya title
                }
                
                String teks3 = teks + " " + teks22;
                tes(teks3);
                
                //vektor dikosongi biar tidak tertumpuk dg looping slanjutnya
                kata1.clear();
                kata2.clear();
                teks2.clear();
                jmlteks1.clear();
                jmlteks2.clear();
                
                
            }
        } catch (SQLException ax) {

        }
        
        int id_no[] = new int [no_id.size()];
        double [] hasila = new double[hasil.size()];
        //vektor hasil yg isinya hsil cosinus disimpan di array hasila biar bisa  di sorting  
        for (int i = 0; i < hasila.length; i++) {
            hasila[i] = hasil.get(i); 
            id_no[i] = no_id.get(i); 
        }
        
        //sorting soalnya vector hasil blm urut nilai besar kecilnya
        //diurutkan dari besar ke kecil cosinus
        for (int i = 0; i < hasila.length - 1; i++) {
            for (int j = 0; j < hasila.length -1; j++) {
                if(hasila[j] < hasila [j+1]){
                    double k = hasila[j];
                    hasila[j] = hasila [j+1] ;
                    hasila [j+1] = k;
                    int  g = id_no [i] ;
                    id_no [j] = id_no [j+1] ;
                    id_no [j+1] = g ;
                }
            }
        } 
        
        //id_no uda urut dari yg terbesar
        for (int i = 0; i < id_no.length; i++) {
            sql = "select * from pengumuman where id ='"+id_no[i]+"' "; 
            re =KoneksiDB.executeQuery(sql) ;
            
            try{
               while (re.next()) {
                   System.out.println(re.getString(2)+"\t\t"+hasila[i]);
                   
               } 
            } catch (SQLException ex) {
                
            }
        }
    }
}
