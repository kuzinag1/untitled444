package org.example.dao.impl;

import org.example.config.jdbc.connection.ConnectionDataBaseFactory;
import org.example.dao.PassportDao;
import org.example.model.Passport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassportDaoImpl implements PassportDao {

    //language=sql
    private static final String SAVE_PASSPORT = "insert into passport(series, number, department_code) VALUES (?,?,?);";
    //language=sql
    private static final String FIND_ALL = "select * from passport";

    @Override
    public void save(Passport model) {
        try {
            Connection connection = ConnectionDataBaseFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_PASSPORT);
            preparedStatement.setInt(1,model.getSeries().intValue());
            preparedStatement.setInt(2,model.getNumber().intValue());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void update(Passport model, Long aLong) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Passport> findAll() {
        List<Passport> passportList = new ArrayList<>();
        try {
            Statement statement = ConnectionDataBaseFactory.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Long series = resultSet.getLong("series");
                Long id = resultSet.getLong("id");
                Long number = resultSet.getLong("number");
                String departmentCode = resultSet.getString("department_code");
                Passport passport = Passport.builder()
                        .id(id)
                        .series(series)
                        .number(number)
                        .departmentCode(departmentCode)
                        .build();
                passportList.add(passport);
                            }
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
        return passportList;
    }
}
