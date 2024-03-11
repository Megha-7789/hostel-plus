package com.example.hostell_plus

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction


fun main() {
    Database.connect("jdbc:sqlite:hostell_plus.db", "org.sqlite.JDBC")

    transaction {
        // Create the "users" table
        SchemaUtils.create(Users)

        // For testing purposes, add some initial users to the database
        Users.insert {
            it[username] = "user1"
            it[password] = "password1"
        }
        Users.insert {
            it[username] = "user2"
            it[password] = "password2"
        }
    }


    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            jackson {}
        }
        install(StatusPages) {
            exception<Throwable> { cause ->
                call.respond(HttpStatusCode.InternalServerError)
            }
        }
        routing {
            post("/api/register") {
                val user = call.receive<User>()

                // Check if the username already exists in the database
                val existingUser = transaction {
                    Users.select { Users.username eq user.username }.singleOrNull()
                }

                if (existingUser != null) {
                    call.respond(HttpStatusCode.BadRequest, "Username already exists")
                } else {
                    // Insert the new user into the database
                    val userId = transaction {
                        Users.insert {
                            it[username] = user.username

                        } get Users.id
                    }
                    // Respond with the new user's ID
                    call.respond(HttpStatusCode.Created, userId)
                }
            }

            post("/api/login") {
                val user = call.receive<User>()

                // Check if the username and password match in the database
                val existingUser = transaction {
                    Users.select { Users.username eq user.username and (Users.password eq user.password) }
                        .singleOrNull()
                }

                if (existingUser != null) {
                    call.respond(HttpStatusCode.OK, existingUser[Users.id].value)
                } else {
                    call.respond(HttpStatusCode.Unauthorized, "Invalid username or password")
                }
            }
        }
    }.start(wait = true)
}

private infix fun <T> Column<T>.eq(password: User): Expression<Boolean> {
    TODO("Not yet implemented")
}

// Define the "users" table
object Users : IntIdTable() {
    val username = varchar("username", 50).uniqueIndex()
    val password = varchar("password", 100)
}
