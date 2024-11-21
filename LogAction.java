import Fsm.*;

public class LogAction extends Action{
    @Override
    public void execute(FSM fsm, Event event) {
        System.out.println("Event " + event.getName() + " received, current state is " + fsm.currentState().getName());
    }
}
