package pratikum5;


public abstract class Kendaraan {

 private String merk;
 private String model;
 private int tahunProduksi;


 public Kendaraan(String merk, String model, int tahunProduksi) {
     this.merk = merk; 
     this.model = model; 
     this.tahunProduksi = tahunProduksi; 
 }

 
 public abstract void nyalakanMesin();


 public final void tampilkanInfo() {
     System.out.println("Merk: " + merk); 
     System.out.println("Model: " + model); 
     System.out.println("Tahun Produksi: " + tahunProduksi); 
 }


 public String getMerk() {
     return merk;
 }

 public String getModel() {
     return model;
 }

 public int getTahunProduksi() {
     return tahunProduksi;
 }
}
