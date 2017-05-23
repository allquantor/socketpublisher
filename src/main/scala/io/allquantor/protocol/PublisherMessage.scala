package io.allquantor.protocol


sealed trait PublisherMessage

case class Coordinates(long: Double, lat: Double, name: String) extends PublisherMessage

case class Greets(greet: String) extends PublisherMessage





