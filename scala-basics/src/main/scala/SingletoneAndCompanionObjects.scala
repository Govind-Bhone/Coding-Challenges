import java.sql.{DriverManager, Driver}

/**
  * Created by govind.bhone on 11/19/2016.
  */
object SingletoneAndCompanionObjects extends App {

  object DBAccess {
    Class.forName("oracle.jdbc.driver.OracleDriver")

    def connection(driver: DriverManager) {
      val con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");
    }
  }

  class IntPair(val x: Int, val y: Int)

  object IntPair {

    import math.Ordering

    implicit def ipord: Ordering[IntPair] =
      Ordering.by(ip => (ip.x, ip.y))
  }


}
