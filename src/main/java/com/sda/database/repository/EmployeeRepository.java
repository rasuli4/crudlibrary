package com.sda.database.repository;


import com.google.common.base.Optional;
import com.sda.database.connection.DatabaseConnection;
import com.sda.database.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class EmployeeRepository implements CrudRepository<EmployeeEntity> {


    private final DatabaseConnection databaseConnection;

    public List<EmployeeEntity> findAll() {

        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        try {
            ResultSet resultSet = databaseConnection.read("select * from employee");

            while (resultSet.next()) {
                EmployeeEntity employeeEntity = new EmployeeEntity();
                employeeEntity.setId(resultSet.getInt("id"));
                employeeEntity.setName(resultSet.getString("name"));
                employeeEntity.setAge(resultSet.getInt("age"));
                employeeEntity.setCity(resultSet.getString("city"));
                employeeEntity.setPhone(resultSet.getString("phone_no"));
                employeeEntities.add(employeeEntity);
            }
        } catch (
                SQLException ex) {

            ex.printStackTrace();
        }

        return employeeEntities;
    }

    public Optional<EmployeeEntity> findById(long id) {

        ResultSet resultSet = databaseConnection.read("select * from employee e where e.id=" + id);

        return null;
    }
}