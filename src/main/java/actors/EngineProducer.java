package actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import messages.Engine;
import messages.Produce;

public class EngineProducer extends UntypedActor {
    private ActorRef engineWorkshop;

    public EngineProducer(ActorRef engineWorkshop) {
        this.engineWorkshop = engineWorkshop;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Produce) {
            Produce produce = (Produce) message;
            double v = Math.random() * 100;
            Engine engine;
            if (v > produce.getDefectPercentage()) {
                engine = new Engine(false);
            } else {
                engine = new Engine(true);
            }
//            System.out.println("engine produced:" + engine);
            engineWorkshop.tell(engine, this.getSelf());
        } else {
            unhandled(message);
        }
    }
}
