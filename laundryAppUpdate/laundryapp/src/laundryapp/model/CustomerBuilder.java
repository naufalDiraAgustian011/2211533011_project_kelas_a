package laundryapp.model;

/**
 * IMPLEMENTASI BUILDER PATTERN (Bagian 2: Class Builder)
 * * Class ini bertugas untuk "membangun" objek Customer.
 * Ini mempermudah pembuatan objek Customer tanpa perlu constructor 
 * yang rumit atau banyak parameter.
 */
public class CustomerBuilder {

    // Atribut-atributnya sama persis dengan Customer
    private String id;
    private String nama;
    private String email = ""; // Bisa diberi nilai default
    private String alamat;
    private String hp;

    public CustomerBuilder() {
        // Constructor kosong
    }
    
    // Setiap method 'set' mengembalikan 'this' (objek CustomerBuilder itu sendiri).
    // Ini memungkinkan kita melakukan "chaining" (merangkai method).
    // Contoh: new CustomerBuilder().setId("1").setNama("Budi")....

    public CustomerBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder setNama(String nama) {
        this.nama = nama;
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder setAlamat(String alamat) {
        this.alamat = alamat;
        return this;
    }

    public CustomerBuilder setHp(String hp) {
        this.hp = hp;
        return this;
    }

    /**
     * Method 'build()' dipanggil di akhir untuk membuat
     * objek Customer yang sesungguhnya.
     * @return Customer
     */
    public Customer build() {
        // Memanggil constructor Customer dengan data yang sudah di-set
        return new Customer(id, nama, email, alamat, hp);
    }
}