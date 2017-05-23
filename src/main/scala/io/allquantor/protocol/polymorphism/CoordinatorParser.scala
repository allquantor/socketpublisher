package io.allquantor.protocol.polymorphism

import io.allquantor.protocol.Coordinates

import scala.annotation.implicitNotFound
import scala.util.Try

@implicitNotFound("Implicit not found for ${T}")
trait CoordinatorParser[T] {
  def parse(value: T): Coordinates
}

object CoordinatorParser {
  def parseCoordinates[T: CoordinatorParser](value: T): Coordinates = implicitly[CoordinatorParser[T]].parse(value)

  implicit val stringCoordinates = new CoordinatorParser[String] {
    override def parse(value: String): Coordinates = {
      val (lat, long, name): (Double, Double, String) = Try(value.split(",")).flatMap { c =>
        Try(
          (
            c(0).toDouble,
            c(1).toDouble,
            c(2)
          )
        )
      }.getOrElse(
        (0, 0, "----Error Parsed----")
      )
      Coordinates(lat, long, name)
    }
  }

}
