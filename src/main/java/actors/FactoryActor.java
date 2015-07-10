package actors;

import akka.actor.UntypedActor;
import messages.Car;
import messages.Count;

public class FactoryActor extends UntypedActor{
    private int carProduced = 0;
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Car) {
            carProduced ++;
            System.out.println(carProduced);
        }else if(message instanceof Count){
            System.out.println(" +++ " + carProduced + " CAR PRODUCED +++ ");
            System.exit(0);
        }
        else {
            unhandled(message);
        }
    }


}
