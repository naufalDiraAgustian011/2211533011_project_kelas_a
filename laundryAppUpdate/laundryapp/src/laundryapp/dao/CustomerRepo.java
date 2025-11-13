package laundryapp.dao;

import laundryapp.config.Database;
import laundryapp.model.Customer;
import laundryapp.model.CustomerBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO (Data Access Object) Pattern - Implementasi
 * * Class ini mengimplementasikan CustomerDao.
 * Di sinilah semua logika JDBC (query SQL) untuk Customer ditulis.
 */
public class CustomerRepo implements CustomerDao {
    
    private Connection connection;

    // Definisi query SQL
    private final String insert = "INSERT INTO customer (nama, email, alamat, hp) VALUES (?,?,?,?);";
    private final String select = "SELECT * FROM customer;";
    private final String update = "UPDATE customer SET nama=?, email=?, alamat=?, hp=? WHERE id=?;";
    private final String delete = "DELETE FROM customer WHERE id=?;";

    public CustomerRepo() {
        // Mengambil koneksi dari Database Singleton
        connection = Database.koneksi();
    }

    /**
     * Implementasi READ (Show)
     */
    @Override
    public List<Customer> show() {
        List<Customer> listCustomer = null;
        try {
            listCustomer = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            
            // Looping untuk setiap baris hasil query
            while (rs.next()) {
                // IMPLEMENTASI BUILDER PATTERN
                // Kita menggunakan CustomerBuilder untuk membuat objek Customer
                // dari data yang ada di ResultSet.
                Customer cs = new CustomerBuilder()
                        .setId(rs.getString("id"))
                        .setNama(rs.getString("nama"))
                        .setEmail(rs.getString("email"))
                        .setAlamat(rs.getString("alamat"))
                        .setHp(rs.getString("hp"))
                        .build(); // Membangun objek Customer
                listCustomer.add(cs);
            }
        } catch (SQLException e) {
            // Log error jika query gagal
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, null, e);
        }
        return listCustomer;
    }

    /**
     * Implementasi CREATE (Save)
     */
    @Override
    public void save(Customer cs) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);
            // Mengisi parameter (?) query
            st.setString(1, cs.getNama());
            st.setString(2, cs.getEmail());
            st.setString(3, cs.getAlamat());
            st.setString(4, cs.getHp());
            st.executeUpdate(); // Eksekusi query insert
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup PreparedStatement
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Implementasi UPDATE
     * (Ini adalah bagian yang harus dikerjakan di PDF)
     */
    @Override
    public void update(Customer cs) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(update);
            st.setString(1, cs.getNama());
            st.setString(2, cs.getEmail());
            st.setString(3, cs.getAlamat());
            st.setString(4, cs.getHp());
            st.setString(5, cs.getId()); // Parameter ke-5 adalah ID untuk WHERE clause
            st.executeUpdate(); // Eksekusi query update
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Implementasi DELETE
     * (Ini adalah bagian yang harus dikerjakan di PDF)
     */
    @Override
    public void delete(String id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(delete);
            st.setString(1, id); // Mengisi parameter ID
            st.executeUpdate(); // Eksekusi query delete
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}