import actors.AssemblyWorkshop;
import actors.CoachworkProducer;
import actors.CoachworkWorkshop;
import actors.EngineProducer;
import actors.EngineWorkshop;
import actors.FactoryActor;
import actors.WheelProducer;
import actors.WheelWorkshop;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Scheduler;
import messages.Count;
import messages.Produce;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

public class CarFactory {
    public static void main(String[] args) {
        CarFactory cf = new CarFactory();
        cf.build();
    }

    public void build() {
        int defectPercentage = 0;
        ActorSystem system = ActorSystem.create("system");
        ActorRef factoryRef = system.actorOf(Props.create(FactoryActor.class));
        ActorRef assemblyRef = system.actorOf(Props.create(AssemblyWorkshop.class, factoryRef));
        ActorRef engineWSRef = system.actorOf(Props.create(EngineWorkshop.class, assemblyRef));
        ActorRef engineProducerRef = system.actorOf(Props.create(EngineProducer.class, engineWSRef));

        ActorRef wheelWSRef = system.actorOf(Props.create(WheelWorkshop.class, assemblyRef));
        ActorRef wheelProducerRef = system.actorOf(Props.create(WheelProducer.class, wheelWSRef));

        ActorRef coachworkWSRef = system.actorOf(Props.create(CoachworkWorkshop.class, assemblyRef));
        ActorRef coachworkProducerRef = system.actorOf(Props.create(CoachworkProducer.class, coachworkWSRef));

        Scheduler scheduler = system.scheduler();
        ExecutionContextExecutor dispatcher = system.dispatcher();
        scheduler.schedule(FiniteDuration.apply(0, TimeUnit.MILLISECONDS), FiniteDuration.apply(4, TimeUnit.MILLISECONDS), engineProducerRef, new Produce(defectPercentage), dispatcher,
                engineProducerRef);
        scheduler.schedule(FiniteDuration.apply(0, TimeUnit.MILLISECONDS), FiniteDuration.apply(1, TimeUnit.MILLISECONDS), wheelProducerRef, new Produce(defectPercentage), dispatcher,
                wheelProducerRef);
        scheduler.schedule(FiniteDuration.apply(0, TimeUnit.MILLISECONDS), FiniteDuration.apply(4, TimeUnit.MILLISECONDS), coachworkProducerRef, new Produce(defectPercentage), dispatcher,
                coachworkProducerRef);
        scheduler.scheduleOnce(FiniteDuration.apply(1, TimeUnit.MINUTES), factoryRef, new Count(), dispatcher, factoryRef);

    }

}
