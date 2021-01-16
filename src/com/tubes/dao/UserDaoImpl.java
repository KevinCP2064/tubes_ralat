package com.tubes.dao;

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

public class UserDaoImpl implements DaoService<User> {
    @Override
    public List<User> fetchAll() throws SQLException, ClassNotFoundException {
        List<User> users=new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT u.id,u.nama,u.email,u.telpon,u.username,u.password,r.id_role,r.role FROM user u JOIN role r ON u.role_id_role=r.id_role WHERE role='owner'";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Role role = new Role();
                        role.setId_role(rs.getInt("id_role"));
                        role.setRole(rs.getString("role"));

                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setNama(rs.getString("nama"));
                        user.setEmail(rs.getString("email"));
                        user.setTelpon(rs.getString("email"));
                        user.setUsername(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        user.setRole(role);
                        users.add(user);
                    }
                }
            }
        }
        return users;
    }

    @Override
    public int addData(User object) throws SQLException, ClassNotFoundException {
        int result=0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO user(nama,email,telpon,username,password,role_id_role) VALUES(?,?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getNama());
                ps.setString(2, object.getEmail());
                ps.setString(3, object.getTelpon());
                ps.setString(4, object.getUsername());
                ps.setString(5, object.getPassword());
                ps.setInt(6, object.getRole().getId_role());
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
    public List<User> showAllData(){
        List<User> users=new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT u.id,u.nama,u.email,u.telpon,u.username,u.password,r.id_role,r.role FROM user u JOIN role r ON u.role_id_role=r.id_role WHERE role='owner'";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Role role = new Role();
                        role.setId_role(rs.getInt("id_role"));
                        role.setRole(rs.getString("role"));

                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setNama(rs.getString("nama"));
                        user.setEmail(rs.getString("email"));
                        user.setTelpon(rs.getString("email"));
                        user.setUsername(rs.getString("email"));
                        user.setPassword(rs.getString("password"));
                        user.setRole(role);
                        users.add(user);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public int deleteData(User object) throws SQLException, ClassNotFoundException {
        int result=0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "DELETE FROM user WHERE id=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, object.getId());
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
    public int updateData(User object) throws SQLException, ClassNotFoundException {
        int result=0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "UPDATE user SET nama = ?,email=?,telpon=?,username=?,password=? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getNama());
                ps.setString(2, object.getEmail());
                ps.setString(3, object.getTelpon());
                ps.setString(4, object.getUsername());
                ps.setString(5, object.getPassword());
                ps.setInt(6, object.getId());
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
