package dao;

import core.Db;
import entity.Brand;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BrandDao {
    private final Connection connection;


    public BrandDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Brand> findAll(){
        ArrayList<Brand> brandlist = new ArrayList<>();
        String sql = "SELECT * FROM public.brand ORDER BY brand_id ASC";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()){
                brandlist.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brandlist;
    }

    public boolean save(Brand brand){
        String query = "INSERT INTO public.brand (brand_name) VALUES (?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, brand.getName());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Brand brand){
        String query = "UPDATE public.brand SET brand_name = ? WHERE brand_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, brand.getName());
            pr.setInt(2, brand.getId());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean delete(int id){
        String query = "DELETE FROM public.brand WHERE brand_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }



    public Brand getById( int id){
        Brand obj = null;
        String query = "SELECT * FROM public.brand WHERE brand_id = ? ";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    public Brand match(ResultSet rs) throws SQLException {
        Brand object = new Brand();
        object = new Brand();
        object.setId(rs.getInt("brand_id"));
        object.setName(rs.getString("brand_name"));
        return object;
    }

}