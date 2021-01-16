package com.tubes.dao;

import com.tubes.entity.Kendaraan;
import com.tubes.entity.Role;
import com.tubes.entity.User;
import com.tubes.util.DaoService;
import com.tubes.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KendaraanDaoImpl implements DaoService<Kendaraan> {
    @Override
    public List<Kendaraan> fetchAll() throws SQLException, ClassNotFoundException {
        List<Kendaraan> kendaraans=new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT k.no_plat,k.alamat,k.merek,k.warna,k.tahun_buat,u.id,u.nama FROM kendaraan k JOIN user u ON k.user_id=u.id";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setNama(rs.getString("nama"));

                        Kendaraan kendaraan = new Kendaraan();
                        kendaraan.setNo_plat(rs.getString("no_plat"));
                        kendaraan.setAlamat(rs.getString("nama"));
                        kendaraan.setMerek(rs.getString("merek"));
                        kendaraan.setWarna(rs.getString("warna"));
                        kendaraan.setTahun_buat(rs.getString("tahun_buat"));
                        kendaraan.setUser(user);
                        kendaraans.add(kendaraan);
                    }
                }
            }
        }
        return kendaraans;
    }

    @Override
    public int addData(Kendaraan object) throws SQLException, ClassNotFoundException {
        int result=0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO kendaraan(no_plat,alamat,merek,warna,tahun_buat,user_id) VALUES(?,?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getNo_plat());
                ps.setString(2, object.getAlamat());
                ps.setString(3, object.getMerek());
                ps.setString(4, object.getWarna());
                ps.setString(5, object.getTahun_buat());
                ps.setInt(6, object.getUser().getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public List<Kendaraan> showAllData(){
        return null;
    }

    @Override
    public int deleteData(Kendaraan object) throws SQLException, ClassNotFoundException {
        int result=0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "DELETE FROM kendaraan WHERE no_plat=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getNo_plat());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int updateData(Kendaraan object) throws SQLException, ClassNotFoundException {
        int result=0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "UPDATE kendaraan SET alamat=?,merek=?,warna=?,tahun_buat=? WHERE no_plat = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getAlamat());
                ps.setString(2, object.getMerek());
                ps.setString(3, object.getWarna());
                ps.setString(4, object.getTahun_buat());
                ps.setString(5, object.getNo_plat());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }
}
