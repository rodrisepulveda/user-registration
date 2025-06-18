package com.nisum.challenge.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@Transactional
class DatabaseEngineIntegrationTest extends IntegrationTestBase {

    @Autowired
    private DataSource dataSource;

    @Test
    void givenPostgresContainer_whenGettingMetadata_thenDatabaseProductIsPostgreSQL() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            String product = conn.getMetaData().getDatabaseProductName();
            System.out.println("Database Engine: " + product);
            // Esto va a FALLAR si la base NO es PostgreSQL
            assertThat(product).isEqualTo("PostgreSQL");
        }
    }
}
