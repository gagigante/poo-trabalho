/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Database;

import ifnetpoo.Interfaces.IDatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gabri
 */
public class MySQLConnection implements IDatabaseConnection {
    Connection conn = null;
	
    public MySQLConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_ifnet", "gagigante", "290501");
        } catch (SQLException e) {
            // TODO Bloco catch gerado automaticamente
            e.printStackTrace();
        }
    }
		
    @Override
    public Connection getConn() {
        return conn;
    }

    @Override
    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
