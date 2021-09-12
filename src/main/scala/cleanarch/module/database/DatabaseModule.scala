package cleanarch.module.database

import com.zaxxer.hikari.HikariDataSource
import scalikejdbc._

import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

trait DatabaseModule {

  implicit val ec: ExecutionContextExecutor = DatabaseModule.ec

}

object DatabaseModule {

  val ec: ExecutionContextExecutor = ExecutionContext fromExecutor Executors.newCachedThreadPool()

  def init(name: String): String = {
    val ds = new HikariDataSource()
    ds.setPoolName(name)
    ds.setJdbcUrl("jdbc:postgresql://localhost:5432/cleanarchDB")
    ds.setUsername("amir")
    ds.setPassword("Am1397@2018")
    ds.setDriverClassName("org.postgresql.Driver")
    ds.setMinimumIdle(1)
    ds.setMaximumPoolSize(10)
    ds.setIdleTimeout(600000)
    ds.setMaxLifetime(600000)
    ConnectionPool.add(name, new DataSourceConnectionPool(ds))
    name
  }

  println("Before Init")
  val cleanArchDatabase: String = init("a")
  println("After Init")

  def close(): Unit = {
    ConnectionPool.close(cleanArchDatabase)
  }

}
