import actors.AssemblyWorkshop;
import actors.CoachWorkProducer;
import actors.CoachWorkWorkshop;
import actors.EngineProducer;
import actors.EngineWorkshop;
import actors.FactoryActor;
import actors.WheelProducer;
import actors.WheelWorkshop;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import messages.Produce;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

public class CarFactory {
    public static void main(String[] args) {
        CarFactory cf = new CarFactory();
        cf.build();
    }

    public void build() {
        int defectPercentage = 5;
        ActorSystem system = ActorSystem.create("system");
        ActorRef factoryRef = system.actorOf(Props.create(FactoryActor.class));
        ActorRef assemblyRef = system.actorOf(Props.create(AssemblyWorkshop.class, factoryRef));
        ActorRef engineWSRef = system.actorOf(Props.create(EngineWorkshop.class, assemblyRef));
        ActorRef engineProducerRef = system.actorOf(Props.create(EngineProducer.class, engineWSRef));

        ActorRef wheelWSRef = system.actorOf(Props.create(WheelWorkshop.class, assemblyRef));
        ActorRef wheelProducerRef = system.actorOf(Props.create(WheelProducer.class, wheelWSRef));

        ActorRef coachworkWSRef = system.actorOf(Props.create(CoachWorkWorkshop.class, assemblyRef));
        ActorRef coachworkProducerRef = system.actorOf(Props.create(CoachWorkProducer.class, coachworkWSRef));

        system.scheduler().schedule(FiniteDuration.apply(0, TimeUnit.SECONDS), FiniteDuration.apply(4, TimeUnit.SECONDS), engineProducerRef, new Produce(defectPercentage),
                system.dispatcher(), engineProducerRef);
        system.scheduler().schedule(FiniteDuration.apply(0, TimeUnit.SECONDS), FiniteDuration.apply(1, TimeUnit.SECONDS), wheelProducerRef, new Produce(defectPercentage),
                system.dispatcher(), wheelProducerRef);
        system.scheduler().schedule(FiniteDuration.apply(0, TimeUnit.SECONDS), FiniteDuration.apply(4, TimeUnit.SECONDS), coachworkProducerRef, new Produce(defectPercentage),
                system.dispatcher(), coachworkProducerRef);

    }

}
