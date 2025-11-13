package laundryapp.model;

/**
 * IMPLEMENTASI BUILDER PATTERN (Bagian 1: Objek Model)
 * * Ini adalah class Model (POJO) untuk Customer.
 * Perhatikan: 
 * 1. Constructor-nya menerima semua parameter.
 * 2. TIDAK ADA method setter (setNama(), setAlamat(), dll).
 * 3. Hanya ada method getter.
 * * Ini membuat objek Customer menjadi 'immutable' (tidak bisa diubah
 * setelah dibuat). Untuk membuatnya, kita akan pakai CustomerBuilder.
 */
public class Customer {

    private String id;
    private String nama;
    private String email;
    private String alamat;
    private String hp;

    // Constructor ini akan dipanggil oleh CustomerBuilder
    public Customer(String id, String nama, String email, String alamat, String hp) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.hp = hp;
    }

    // Hanya method Getter
    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getHp() {
        return hp;
    }
}