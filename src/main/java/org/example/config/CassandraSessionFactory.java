package org.example.config;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

import java.net.InetSocketAddress;

public class CassandraSessionFactory {

    private static CqlSession session;

    public static void initSession() {
        if (session == null) {
            session = CqlSession.builder()
                    .addContactPoint(new InetSocketAddress("localhost", 9042))
                    .withLocalDatacenter("dc1")
                    .withKeyspace("CassandraBaseTask")
                    .withAuthCredentials("cassandra", "cassandrapassword")
                    .withConfigLoader(
                            DriverConfigLoader.fromClasspath("application.conf")
                    )
                    .build();
        }
    }

    public static CqlSession getSession() {
        return session;
    }

    public static void closeSession() {
        if (session != null) {
            session.close();
            session = null;
        }
    }
}
