package dev.suresh

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.testcontainers.containers.*
import org.testcontainers.junit.jupiter.*
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.utility.*


@Testcontainers
class CassandraTest {

    @Container
    val cassandra = CassandraContainer(DockerImageName.parse("cassandra:latest"))

    @Test
    fun `Test Cassandra Keyspace`() {
        val cluster = cassandra.cluster
        val keyspaceName = "test"
        println("Connecting to Cassandra cluster: ${cluster.clusterName}")
        cluster.connect().use { session ->
            println("Creating keyspace: $keyspaceName")
            session.execute(
                """
                CREATE KEYSPACE IF NOT EXISTS $keyspaceName WITH replication = 
                {'class':'SimpleStrategy','replication_factor':'1'};
                """.trimIndent()
            )
            val keySpaces = session.cluster.metadata.keyspaces.filter { it.name == keyspaceName }
            println(keySpaces)
            assertEquals(1, keySpaces.size)
        }
    }
}