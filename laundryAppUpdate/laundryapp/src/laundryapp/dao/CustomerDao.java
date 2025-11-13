package laundryapp.dao;

import java.util.List;
import laundryapp.model.Customer;

/**
 * DAO (Data Access Object) Pattern - Interface
 * * Interface ini mendefinisikan "kontrak" atau aturan untuk
 * operasi database pada tabel Customer.
 * Ini memisahkan logika bisnis dari cara mengakses data.
 */
public interface CustomerDao {
    
    /**
     * Menyimpan customer baru (CREATE)
     * @param customer Objek customer yang akan disimpan
     */
    public void save(Customer customer);

    /**
     * Mengubah data customer yang ada (UPDATE)
     * @param customer Objek customer yang berisi data baru
     */
    public void update(Customer customer);

    /**
     * Menghapus data customer berdasarkan ID (DELETE)
     * @param id ID customer yang akan dihapus
     */
    public void delete(String id);

    /**
     * Mengambil semua data customer (READ)
     * @return List dari semua objek Customer
     */
    public List<Customer> show();
}