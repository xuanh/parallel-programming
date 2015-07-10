package actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import messages.Produce;
import messages.Wheel;

public class WheelProducer extends UntypedActor{
    private ActorRef wheelWorkshop;

    public WheelProducer(ActorRef wheelWorkshop) {
        this.wheelWorkshop = wheelWorkshop;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Produce) {
            Produce produce = (Produce) message;
            double v = Math.random() * 100;
            Wheel wheel;
            if (v > produce.getDefectPercentage()) {
                wheel = new Wheel(false);
            } else {
                wheel = new Wheel(true);
            }
//            System.out.println("wheel produced:" + wheel);
            wheelWorkshop.tell(wheel, this.getSelf());
        } else {
            unhandled(message);
        }
    }
}
