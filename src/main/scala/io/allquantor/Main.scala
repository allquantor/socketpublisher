package io.allquantor


import akka.stream._
import akka.stream.scaladsl.{FileIO, Flow, Framing, GraphDSL, Merge, RunnableGraph, Sink, Source, Tcp}
import akka.util.ByteString
import io.allquantor.protocol.{Greets, PublisherMessage}
import io.allquantor.system.System
import io.allquantor.util.Config

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.Random


object Main extends App with System.Executor with System.ErrorHandling
  with Config.Client with Config.Primitives {

  import io.allquantor.protocol.polymorphism.CoordinatorParser._
  import io.allquantor.system.System._

  val socketPublisher = RunnableGraph.fromGraph(GraphDSL.create() { implicit b ⇒
    import GraphDSL.Implicits._

    // Client Socket Stream
    val tcpStream = Tcp().outgoingConnection(targetHost, targetPort).async
    // Coordinates Data from File

    val cSource = FileIO.fromPath(filePath).via(Framing.delimiter(ByteString(CR, LF), Int.MaxValue))
      .delay(100.millis, DelayOverflowStrategy.backpressure).async
    // Noise
    val gSource = Source.tick(Duration.Zero, Duration.Zero, Greets(s"Privet: ")).map { elem =>
      elem.copy(greet = elem.greet ++ s"{${Random.nextInt()}}")
    }
    // Conversions.
    val serializer = Flow[ByteString].map(s => parseCoordinates(s.decodeString("UTF-8")))
    val deserializer = Flow[PublisherMessage].mapAsync(4)(m => Future(m.toString)).map(ByteString.apply(_) concat ByteString(CR, LF))

    val sink = Sink.ignore

    val merger = b.add(Merge[PublisherMessage](2))

    cSource ~> serializer ~> merger
    gSource ~> merger ~> deserializer ~> tcpStream ~> sink

    ClosedShape
  }).withAttributes(errorHandling)


  socketPublisher.run()

}
