package actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import messages.Coachwork;
import messages.Produce;

public class CoachworkProducer extends UntypedActor{
    private ActorRef coachworkWorkshop;

    public CoachworkProducer(ActorRef coachworkWorkshop) {
        this.coachworkWorkshop = coachworkWorkshop;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Produce) {
            Produce produce = (Produce) message;
            double v = Math.random() * 100;
            Coachwork coachwork;
            if (v > produce.getDefectPercentage()) {
                coachwork = new Coachwork(false);
            } else {
                coachwork = new Coachwork(true);
            }
//            System.out.println("coachwork produced:" + coachwork);
            coachworkWorkshop.tell(coachwork, this.getSelf());
        } else {
            unhandled(message);
        }
    }

}
