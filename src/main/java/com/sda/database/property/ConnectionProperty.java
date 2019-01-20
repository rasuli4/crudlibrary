package com.sda.database.property;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConnectionProperty {
    private String driverName;
    private String databaseURL;
    private String userName;
    private String password;
}
