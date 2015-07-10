package actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import messages.Wheel;

public class WheelWorkshop extends UntypedActor{
    private ActorRef assembler;

    public WheelWorkshop(ActorRef assembler) {
        this.assembler = assembler;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Wheel) {
            if (!((Wheel) message).isDefected()) {
                assembler.tell(message, this.getSelf());
            } else {
                System.err.println("defected wheel discarded");
            }
        } else {
            unhandled(message);
        }
    }
}
