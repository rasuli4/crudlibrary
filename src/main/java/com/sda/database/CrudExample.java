package com.sda.database;

import com.sda.database.connection.DatabaseConnection;
import com.sda.database.connection.MySqlDatabaseConnection;
import com.sda.database.entity.EmployeeEntity;
import com.sda.database.property.ConnectionProperty;
import com.sda.database.repository.EmployeeRepository;

import java.util.List;


public class CrudExample {
    public static void main(String[] args) {

        DatabaseConnection mySqlDatabaseConnection = new MySqlDatabaseConnection();
        ConnectionProperty connectionProperty = mySqlDatabaseConnection.getConnectionProperty("src/main/resources/mysql.properties");

        System.out.println(String.format("Driver name: %s, Database name: %s, UserName: %s, Password: %s ", connectionProperty.getDriverName(),
                connectionProperty.getDatabaseURL(), connectionProperty.getUserName(), connectionProperty.getPassword()));
        //EmployeeRepository employeeRepository = new EmployeeRepository(mySqlDatabaseConnection);

        ((MySqlDatabaseConnection) mySqlDatabaseConnection).open(connectionProperty);


        EmployeeRepository employeeRepository = new EmployeeRepository(mySqlDatabaseConnection);
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        for (EmployeeEntity employeeEntity : employeeEntities) {
            System.out.println(String.format("Id: %d, Name: %s, City: %s, Phone: %s, Age: %d"
                    , employeeEntity.getId(), employeeEntity.getName(), employeeEntity.getCity()
                    , employeeEntity.getPhone(), employeeEntity.getAge()));
        }
    }
}
