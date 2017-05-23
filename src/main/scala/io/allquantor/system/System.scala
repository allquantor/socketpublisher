package io.allquantor.system

import akka.actor.ActorSystem
import akka.stream.{ActorAttributes, ActorMaterializer, Attributes, Supervision}

import scala.concurrent.ExecutionContextExecutor

object System {
  implicit val actorSystem = ActorSystem()
  implicit val mat = ActorMaterializer()

  trait Executor {
    protected implicit val executor: ExecutionContextExecutor = actorSystem.dispatcher
  }

  trait ErrorHandling {

    val errorHandling: Attributes = ActorAttributes.supervisionStrategy {
      case ex: java.net.ConnectException =>
        // Damn side effects!!!
        println(s"Error: $ex" )
        Supervision.Restart
      case _ =>
        println("Error!")
        Supervision.Restart
    }

  }

}
