package pratikum5;


public class Pesawat extends Kendaraan implements TransportasiUdara, Maskapai {


 public Pesawat(String merk, String model, int tahunProduksi) {
     super(merk, model, tahunProduksi);
 }

 
 @Override
 public void nyalakanMesin() {
     System.out.println("Nyalakan Mesin: Bersiap lepas landas");
 }


 @Override
 public String jenisBahanBakar() {
     return "Avtur";
 }

 
 @Override
 public String jenisPenerbangan() {
     return "Komersial"; 
 }


 @Override
 public String namaMaskapai() {
     return this.getMerk(); 
 }
}
