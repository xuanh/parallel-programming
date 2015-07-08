package actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import messages.Car;
import messages.Engine;
import messages.Wheel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class AssemblyWorkshop extends UntypedActor{
    ActorRef factory;
    List<Engine> engines = new ArrayList<>();
    LinkedList<Wheel> wheels = new LinkedList<>();

    public AssemblyWorkshop(ActorRef factory) {
        this.factory = factory;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        Optional<Car> car;
        if (message instanceof Engine) {
            engines.add((Engine) message);
            car = assemble();
            if (car.isPresent()) {
                factory.tell(car.get(), this.getSelf());
            }
        } else if (message instanceof Wheel) {
            wheels.add((Wheel) message);
            car = assemble();
            if (car.isPresent()) {
                factory.tell(car.get(), this.getSelf());
            }
        } else {
            unhandled(message);
        }
    }

    private Optional<Car> assemble() {

        if (engines.size() >= 1 && wheels.size() >= 4) {
            Car car = new Car(engines.remove(0), Arrays.asList(wheels.poll(), wheels.poll(), wheels.poll(), wheels.poll()));
            return Optional.of(car);
        }
        return Optional.empty();
    }
}
