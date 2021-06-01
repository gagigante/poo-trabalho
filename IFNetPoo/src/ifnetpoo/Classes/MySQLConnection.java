/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gabri
 */
public class MySQLConnection {
    Connection conn = null;
	
    public MySQLConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exemplo", "elis", "07*13An26");
        } catch (SQLException e) {
            // TODO Bloco catch gerado automaticamente
            e.printStackTrace();
        }
    }
		
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
