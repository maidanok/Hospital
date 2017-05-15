package by.hospital.pool;

import by.hospital.prop_managers.ConnectionManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectionPool {
    private static ConnectionPool instance = null;
    static Logger logger = Logger.getLogger(ConnectionPool.class);
    private int maxPool;
    private int minPool;
    private String url;
    private String user;
    private String password;
    private List<PooledConnection> free, used;

    private static final String DB_URL = "jdbc:mysql:" + ConnectionManager.getProperty("DB_URL");
    private static final String DB_USER = ConnectionManager.getProperty("DB_USER");
    private static final String DB_PASSWORD = ConnectionManager.getProperty("DB_PASSWORD");
    private static final String DB_DRIVER = ConnectionManager.getProperty("DB_DRIVER");



    private ConnectionPool(int minPool, int maxPool, String url, String user, String password) throws SQLException {
        this.minPool = minPool;
        this.maxPool = maxPool;
        this.url = url;
        this.user = user;
        this.password = password;

        free = Collections.synchronizedList(new ArrayList<PooledConnection>(maxPool));
        used = Collections.synchronizedList(new ArrayList<PooledConnection>(maxPool));

        for (int i = 0; i < minPool; i++) {
            free.add(createConnectionWrapper());
        }
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }

    public synchronized void destroy() {
        for (PooledConnection pooledConnection : free) {
            try {
                pooledConnection.getConnection().close();
            } catch (SQLException e) {
                logger.error("Can't close connection: " + e.getLocalizedMessage());
            }
            free.clear();
        }
        for (PooledConnection pooledConnection : used) {
            try {
                pooledConnection.getConnection().close();
            } catch (SQLException e) {
                logger.error("Can't close connection: " + e.getLocalizedMessage());
            }
            used.clear();
        }
    }

    public synchronized Connection getConnection() throws SQLException {
        PooledConnection pooledConnection = null;
        if (free.size() > 0) {
            pooledConnection = free.remove(free.size() - 1);
        } else if (used.size() < maxPool) {
            pooledConnection = createConnectionWrapper();
        } else {
            throw new RuntimeException("Unable to create a connection");
        }
        used.add(pooledConnection);
        return pooledConnection;
    }

    protected PooledConnection createConnectionWrapper() throws SQLException {
        Connection con = null;
        PooledConnection pcon = null;

        try {
            Class.forName(DB_DRIVER);

            con = DriverManager.getConnection(url, user, password);
            pcon = new PooledConnection(this, con);
            //logger.info("Created a new connection Free connection count - " + free.size() + " Used connection count - "
            //        + used.size());
            SQLWarning warning = con.getWarnings();

            while (warning != null) {
                //logger.info("Warning - " + warning);
                warning = warning.getNextWarning();
            }
        } catch (SQLException ex) {
            //logger.error("Can't create a new connection: " + ex.getLocalizedMessage());
            logger.error(
                    "Oops... Can't create a new connection. Contact the developer." + ex.getLocalizedMessage());
            try {
                if (con != null)
                    con.close();
            } catch (SQLException ex2) {
                logger.warn("Unable to close connection " + ex2.getLocalizedMessage());
            }
            throw ex;
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException "+e.getLocalizedMessage());
        }
        return pcon;
    }

    public void freeConnectionWrapper(PooledConnection con) {
        used.remove(con);
        free.add(con);
    }

    public static synchronized ConnectionPool getInstance() {
        try {
            if (instance == null) {
                instance = new ConnectionPool(5, 15, DB_URL, DB_USER, DB_PASSWORD);
            }
        } catch (SQLException e) {
            //logger.error("Can't create a new connection: " + e.getLocalizedMessage());
        }
        return instance;
    }
}
