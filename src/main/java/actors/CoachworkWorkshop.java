package actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import messages.Coachwork;

public class CoachworkWorkshop extends UntypedActor{
    ActorRef assembler;

    public CoachworkWorkshop(ActorRef assembler) {
        this.assembler = assembler;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Coachwork) {
            if (!((Coachwork) message).isDefected()) {
                assembler.tell(message, this.getSelf());
            } else {
                System.err.println("defected coachwork discarded");
            }
        } else {
            unhandled(message);
        }
    }
}
