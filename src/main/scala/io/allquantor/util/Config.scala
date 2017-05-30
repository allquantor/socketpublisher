package io.allquantor.util

import java.nio.file.{Path, Paths}

import scala.util.{Properties, Try}


object Config {

  trait Client {
    lazy val targetHost: String = Properties.envOrElse("C_TARGET_HOST", "127.0.0.1")
    lazy val targetPort: Int = Try(Properties.envOrElse("C_TARGET_PORT", "9011").toInt).getOrElse(9011)
    lazy val filePath: Path = Paths.get(Properties.envOrElse("C_DATA_PATH", "data.csv"))
  }

  trait Primitives {
    final val CR = 0xD
    final val LF = 0xA
  }

}
