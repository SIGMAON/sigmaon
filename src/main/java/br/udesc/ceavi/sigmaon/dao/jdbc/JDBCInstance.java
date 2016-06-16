package br.udesc.ceavi.sigmaon.dao.jdbc;

import br.udesc.ceavi.sigmaon.util.PropertiesLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCInstance {

    private static JDBCInstance instance;

    private Properties prop = new PropertiesLoader().getProperties(PropertiesLoader.DATABASE_CONFIG);

    private String driver = prop.getProperty("db.jdbc.driver");
    private String url = prop.getProperty("db.jdbc.url");
    private String user = prop.getProperty("db.user");
    private String senha = prop.getProperty("db.password");
    private Connection conn = null;

    private JDBCInstance() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, senha);
            ((org.postgresql.PGConnection) conn).addDataType("geometry", Class.forName("org.postgis.PGgeometry"));
            ((org.postgresql.PGConnection) conn).addDataType("box3d", Class.forName("org.postgis.PGbox3d"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized JDBCInstance getInstance() {
        if (instance == null) {
            instance = new JDBCInstance();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

}
