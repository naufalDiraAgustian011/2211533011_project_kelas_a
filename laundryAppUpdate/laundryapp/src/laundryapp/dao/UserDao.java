package laundryapp.dao;

public interface UserDao {
    // Method untuk mengecek login
    public boolean authenticate(String username, String password);
}