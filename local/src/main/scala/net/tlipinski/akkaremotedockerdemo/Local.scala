package net.tlipinski.akkaremotedockerdemo

import akka.actor.{ActorLogging, Props, Actor, ActorSystem}
import com.typesafe.config.{ConfigFactory, Config}
import scala.concurrent.duration._
import scala.util.Random

object Local extends App {
  val system = ActorSystem("localSystem")
  system.actorOf(Props[LocalActor], "localActor")
}

class LocalActor extends Actor with ActorLogging {

  override def preStart = {
    val config = ConfigFactory.load()
    val hostname = config.getString("remote-actor.hostname")
    val port = config.getString("remote-actor.port")
    val selection = context.actorSelection(s"akka.tcp://remoteSystem@$hostname:$port/user/remoteActor")

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