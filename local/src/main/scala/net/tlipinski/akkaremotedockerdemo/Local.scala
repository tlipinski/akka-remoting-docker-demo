package net.tlipinski.akkaremotedockerdemo

import akka.actor.{ActorLogging, Props, Actor, ActorSystem}
import scala.concurrent.duration._
import scala.util.Random

object Local extends App {
  val system = ActorSystem("localSystem")
  system.actorOf(Props[LocalActor], "localActor")
}

class LocalActor extends Actor with ActorLogging {

  override def preStart = {
    val selection = context.actorSelection("akka.tcp://remoteSystem@127.0.0.1:2552/user/remoteActor")

    context.system.scheduler.schedule(0 seconds, 1 second) {
      val random = Random.nextInt();
      log.info("Sending to remote actor: " + random)
      selection ! random
    }(context.dispatcher)
  }

  def receive = {
    case _ =>
  }
}