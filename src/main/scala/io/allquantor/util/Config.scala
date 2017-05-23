package io.allquantor.util

import java.nio.file.{Path, Paths}


object Config {

  trait Client {
    val targetHost: String = "127.0.0.1"
    val targetPort: Int = 9011
    val filePath: Path = Paths.get("starbucks_data.csv")
  }

  trait Primitives {
    final val CR = 0xD
    final val LF = 0xA
  }

}
