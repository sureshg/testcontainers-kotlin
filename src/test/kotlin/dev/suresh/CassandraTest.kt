package dev.suresh

import com.datastax.driver.core.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.testcontainers.containers.*
import org.testcontainers.junit.jupiter.*
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.utility.*


@Testcontainers
class CassandraTest {

    @Container
    val cassandra = CassandraContainer<Nothing>(
        DockerImageName.parse("cassandra:latest")
    ).apply { start() }

    @Test
    fun `Test Cassandra connection`() {
        val cluster = cassandra.cluster
        println(cluster)
        cluster.connect().use { session ->
            session.execute(
                """
                    CREATE KEYSPACE IF NOT EXISTS test WITH replication = 
                    {'class':'SimpleStrategy','replication_factor':'1'};
                    """.trimIndent()
            )
            val keySpaces = session.cluster
                .metadata
                .keyspaces
                .filter { km: KeyspaceMetadata -> km.name == "test" }
            println(keySpaces)
            assertEquals(1, keySpaces.size)
        }
    }
}