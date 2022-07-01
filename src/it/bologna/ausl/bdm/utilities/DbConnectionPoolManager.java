package it.bologna.ausl.bdm.utilities;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 *
 * @author gdm
 */
public class DbConnectionPoolManager {
    private static final HashMap<String, DataSource> DATASOURCES = new HashMap<>();
//    private static final Logger log = LogManager.getLogger(ParametersClient.class);
    
    public static DataSource getConnection(String dbUri) throws IOException, URISyntaxException {

        synchronized (DATASOURCES) {
            if (DATASOURCES.get(dbUri) == null) {
                DATASOURCES.put(dbUri, setupDataSource(dbUri));
            }
        }
        return DATASOURCES.get(dbUri);
    }
    
    private static DataSource setupDataSource(String dbUri) throws URISyntaxException  {                
        PoolProperties p = new PoolProperties();
        p.setUrl(dbUri);
        p.setDriverClassName("org.postgresql.Driver");
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(1000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(10);
        p.setMaxIdle(10);
        p.setInitialSize(2);
        p.setMaxWait(10000);
        p.setMinIdle(0);
        p.setInitSQL("set application_name to '" + DbConnectionPoolManager.class.getSimpleName() + "'");
        DataSource ds = new DataSource();
        ds.setPoolProperties(p);
        return ds;
    }
}
