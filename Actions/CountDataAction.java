package Actions;
import Events.FsmEvents;
import Fsm.*;

public class CountDataAction extends Action{
    private int sDataCount = 0;
    private int rDataCount = 0;

    @Override
    public void execute(FSM fsm, Event event) {
        if(event.equals(FsmEvents.SDATA)) {
            sDataCount++;
            System.out.println("Data sent: " + String.valueOf(sDataCount));
        } else if (event.equals(FsmEvents.RDATA)) {
            rDataCount++;
            System.out.println("Data received: " + String.valueOf(rDataCount));
        }
    }
}
