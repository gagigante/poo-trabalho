/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Interfaces;

import java.sql.Connection;

/**
 *
 * @author gabri
 */
public interface IDatabaseConnection {
    public Connection getConn();
    
    public void setConn(Connection conn);
}
