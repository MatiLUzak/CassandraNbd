package org.example;

import org.example.config.CassandraSessionFactory;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CassandraConnectionTest {

    @BeforeAll
    static void setup() {
        CassandraSessionFactory.initSession();
    }

    @AfterAll
    static void teardown() {
        CassandraSessionFactory.closeSession();
    }

    @Test
    void testSessionNotNull() {
        assertNotNull(CassandraSessionFactory.getSession(), "Session should not be null after init");
    }

}
