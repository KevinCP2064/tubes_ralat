package com.tubes.dao;

import com.tubes.entity.Kendaraan;
import com.tubes.entity.Role;
import com.tubes.entity.Service;
import com.tubes.entity.User;
import com.tubes.util.DaoService;
import com.tubes.util.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoImpl implements DaoService<Service> {
    @Override
    public List<Service> fetchAll() throws SQLException, ClassNotFoundException {
        List<Service> services=new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT s.id_nota,s.tanggal,s.jns_service,s.keterangan,s.harga,k.no_plat,u.id,u.nama,r.id_role,r.role FROM service s INNER JOIN kendaraan k ON s.kendaraan_no_plat=k.no_plat" +
                    " INNER JOIN user u ON k.user_id=u.id INNER JOIN role r ON u.role_id_role=r.id_role WHERE role='owner'";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Role role = new Role();
                        role.setId_role(rs.getInt("id_role"));
                        role.setRole(rs.getString("role"));

                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setNama(rs.getString("nama"));
                        user.setRole(role);

                        Kendaraan kendaraan = new Kendaraan();
                        kendaraan.setNo_plat(rs.getString("no_plat"));
                        kendaraan.setUser(user);

                        Service service=new Service();
                        service.setId_nota(rs.getInt("id_nota"));
                        service.setTanggal(rs.getDate("tanggal"));
                        service.setJns_service(rs.getString("jns_service"));
                        service.setKeterangan(rs.getString("keterangan"));
                        service.setHarga(rs.getDouble("harga"));
                        service.setKendaraan(kendaraan);
                        services.add(service);
                    }
                }
            }
        }
        return services;
    }

    @Override
    public int addData(Service object) throws SQLException, ClassNotFoundException {
        int result=0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO service(tanggal,jns_service,keterangan,harga,kendaraan_no_plat) VALUES(?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setDate(1, (Date) object.getTanggal());
                ps.setString(2, object.getJns_service());
                ps.setString(3, object.getKeterangan());
                ps.setDouble(4, object.getHarga());
                ps.setString(5, object.getKendaraan().getNo_plat());
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
    public List<Service> showAllData(){
        return null;
    }

    @Override
    public int deleteData(Service object) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int updateData(Service object) throws SQLException, ClassNotFoundException {
        return 0;
    }
}
