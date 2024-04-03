package dao;

import core.Db;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    private final Connection connection;

    public UserDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<User> findAll(){
        ArrayList<User> userlist = new ArrayList<>();
        String sql = "SELECT * FROM public.user";


        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()){
                userlist.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userlist;
    }

    public User findByLogin(String username, String password){
        User object = null;
        String query = "SELECT * FROM public.user WHERE user_name=? AND user_pass=?";

        try{
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                object = this.match(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return object;

    }

    public User match(ResultSet rs) throws SQLException {
        User object = new User();
        object = new User();
        object.setId(rs.getInt("user_id"));
        object.setUsername(rs.getString("user_name"));
        object.setPassword(rs.getString("user_pass"));

        return object;

    }
}