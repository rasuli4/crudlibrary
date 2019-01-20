package com.sda.database.repository;

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

    public EmployeeEntity findById(long id) {

        EmployeeEntity employee = new EmployeeEntity();

        try {
            ResultSet resultSet = databaseConnection.read("select * from employee e where e.id=" + id);

            while (resultSet.next()) {
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setCity(resultSet.getString("city"));
                employee.setPhone(resultSet.getString("phone_no"));
            }
        } catch (
                SQLException ex) {

            ex.printStackTrace();
        }

        return employee;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public int delete(long id) {
        return databaseConnection.delete("delete from employee where id= " + id);
    }

    @Override
    public int update(EmployeeEntity updatedEntity) {
        if (updatedEntity.getId() > 0) {
            String updateStatement = "update employee e set ";

            if (updatedEntity.getName() != null) {
                updateStatement += " e.name = '" + updatedEntity.getName() + "' ,";
            }

            if (updatedEntity.getAge() > 0) {

                updateStatement += " e.age = " + updatedEntity.getAge() + " ,";
            }

            if (updatedEntity.getCity() != null) {

                updateStatement += " e.city = '" + updatedEntity.getCity() + "' ,";
            }

            if (updatedEntity.getPhone() != null) {

                updateStatement += " e.phone_no = '" + updatedEntity.getCity() + "' ,";
            }

            updateStatement += " where e.id=" + updatedEntity.getId();


            String[] updateStatements = updateStatement.split("where");
            String updateStatementPart = updateStatements[0];
            String whereStatementPart = updateStatements[1];

            updateStatementPart = updateStatementPart.substring(0, updateStatementPart.length() - 2);
            String updateQuery = updateStatementPart + " where " + whereStatementPart;

            databaseConnection.update(updateQuery);
        } else {
            throw new IllegalArgumentException("Please provide entity id in order to update entity");
        }
        return 0;
    }

    @Override
    public int create(EmployeeEntity newEntity) {
        return 0;
    }


}
