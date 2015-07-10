package actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import messages.Car;
import messages.Coachwork;
import messages.Engine;
import messages.Wheel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class AssemblyWorkshop extends UntypedActor {
    private ActorRef factory;
    private List<Engine> engines = new ArrayList<>();
    private LinkedList<Wheel> wheels = new LinkedList<>();
    private List<Coachwork> coachworks = new ArrayList<>();

    public AssemblyWorkshop(ActorRef factory) {
        this.factory = factory;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Engine) {
            engines.add((Engine) message);
            assembleCarAndSendToFactory();
        } else if (message instanceof Wheel) {
            wheels.add((Wheel) message);
            assembleCarAndSendToFactory();
        } else if (message instanceof Coachwork) {
            coachworks.add((Coachwork) message);
            assembleCarAndSendToFactory();
        } else {
            unhandled(message);
        }
    }

    private Optional<Car> assemble() {

        if (engines.size() >= 1 && wheels.size() >= 4 && coachworks.size() >= 1) {
            Car car = new Car(engines.remove(0), Arrays.asList(wheels.poll(), wheels.poll(), wheels.poll(), wheels.poll()), coachworks.remove(0));
            return Optional.of(car);
        }
        return Optional.empty();
    }

    private void assembleCarAndSendToFactory() {
        Optional<Car> car = assemble();
        if (car.isPresent()) {
            factory.tell(car.get(), this.getSelf());
        }
    }
}
