package actors;

import akka.actor.UntypedActor;
import messages.Car;

public class FactoryActor extends UntypedActor{
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Car) {
            System.err.println(" --- CAR PRODUCED ---");
        } else {
            unhandled(message);
        }
    }
}
