package laundryapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import laundryapp.config.Database;

public class UserRepo implements UserDao {

    private Connection connection;
    
    // Query SQL untuk cek user & password
    private final String authQuery = "SELECT * FROM users WHERE username = ? AND password = ?";

    public UserRepo() {
        this.connection = Database.koneksi();
    }

    @Override
    public boolean authenticate(String username, String password) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(authQuery);
            st.setString(1, username);
            st.setString(2, password);
            
            rs = st.executeQuery();
            
            // Jika ada hasil (true), berarti login sukses
            return rs.next(); 
            
        } catch (SQLException e) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}