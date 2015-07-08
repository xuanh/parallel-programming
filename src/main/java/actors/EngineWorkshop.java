package actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import messages.Engine;

public class EngineWorkshop extends UntypedActor{
    ActorRef assembler;

    public EngineWorkshop(ActorRef assembler) {
        this.assembler = assembler;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Engine) {
            if (!((Engine) message).isDefected()) {
                assembler.tell(message, this.getSelf());
            } else {
                System.err.println("defected engine discarded");
            }
        } else {
            unhandled(message);
        }
    }
}
