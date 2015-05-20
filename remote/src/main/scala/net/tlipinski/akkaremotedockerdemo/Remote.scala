package net.tlipinski.akkaremotedockerdemo

import akka.actor.{ActorLogging, Props, Actor, ActorSystem}
import scala.concurrent.duration._

object Remote extends App {
  val system = ActorSystem("remoteSystem")
  system.actorOf(Props[RemoteActor], "remoteActor")
}

class RemoteActor extends Actor with ActorLogging {
  def receive = {
    case m => log.info("Remote actor received message: " + m)
  }
}