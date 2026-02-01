package pratikum5;

//interface BahanBakar [cite: 169, 172]
public interface BahanBakar {
 // Method [cite: 170]
 String jenisBahanBakar();

 // Default method [cite: 171, 174]
 default void infoKonsumsi() {
     System.out.println("Info Konsumsi: Konsumsi bahan bakar tergantung kapasitas mesin"); 
 }
}