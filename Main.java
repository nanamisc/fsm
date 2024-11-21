import Actions.CountDataAction;
import Actions.LogAction;
import Events.FsmEvents;
import Fsm.*;
import States.FsmStates;
public class Main {
    public static void main(String[] args) {
        try {
            FSM fsm = new FSM("TCP FSM", FsmStates.START);
            fsm.nextState(FsmStates.CLOSED);
            LogAction log = new LogAction();
            CountDataAction countData = new CountDataAction();

            fsm.addTransition(new Transition(FsmStates.CLOSED, FsmEvents.PASSIVE, FsmStates.LISTEN, log));
            fsm.addTransition(new Transition(FsmStates.CLOSED, FsmEvents.ACTIVE, FsmStates.SYN_SENT, log));
    
            fsm.addTransition(new Transition(FsmStates.LISTEN, FsmEvents.SYN, FsmStates.SYN_RCVD, log));
            
            fsm.addTransition(new Transition(FsmStates.SYN_SENT, FsmEvents.SYNACK, FsmStates.ESTABLISHED, log));
            fsm.addTransition(new Transition(FsmStates.SYN_SENT, FsmEvents.SYN, FsmStates.SYN_RCVD, log));
            fsm.addTransition(new Transition(FsmStates.SYN_SENT, FsmEvents.CLOSE, FsmStates.CLOSED, log));

            fsm.addTransition(new Transition(FsmStates.SYN_RCVD, FsmEvents.CLOSE, FsmStates.FIN_WAIT_1, log));
            fsm.addTransition(new Transition(FsmStates.SYN_RCVD, FsmEvents.ACK, FsmStates.ESTABLISHED, log));

            fsm.addTransition(new Transition(FsmStates.ESTABLISHED, FsmEvents.CLOSE, FsmStates.FIN_WAIT_1, log));
            fsm.addTransition(new Transition(FsmStates.ESTABLISHED, FsmEvents.FIN, FsmStates.CLOSE_WAIT, log));
            fsm.addTransition(new Transition(FsmStates.ESTABLISHED, FsmEvents.SDATA, FsmStates.ESTABLISHED, countData));
            fsm.addTransition(new Transition(FsmStates.ESTABLISHED, FsmEvents.RDATA, FsmStates.ESTABLISHED, countData));

            fsm.addTransition(new Transition(FsmStates.FIN_WAIT_1, FsmEvents.ACK, FsmStates.FIN_WAIT_2, log));
            fsm.addTransition(new Transition(FsmStates.FIN_WAIT_1, FsmEvents.FIN, FsmStates.CLOSING, log));

            fsm.addTransition(new Transition(FsmStates.FIN_WAIT_2, FsmEvents.FIN, FsmStates.TIME_WAIT, log));

            fsm.addTransition(new Transition(FsmStates.TIME_WAIT, FsmEvents.TIMEOUT, FsmStates.CLOSED, log));

            fsm.addTransition(new Transition(FsmStates.CLOSING, FsmEvents.ACK, FsmStates.TIME_WAIT, log));

            fsm.addTransition(new Transition(FsmStates.CLOSE_WAIT, FsmEvents.CLOSE, FsmStates.LAST_ACK, log));

            fsm.addTransition(new Transition(FsmStates.LAST_ACK, FsmEvents.ACK, FsmStates.CLOSED, log));


            for(String event: args) {
                Event evnt = getEvent(event);
                if(evnt==null) {
                    System.out.println("Error: unexpected Event: " + event);
                } else {
                    try {
                        fsm.doEvent(evnt);
                    } catch (FsmException e) {
                        System.err.println("Error: " + e.toString());
                    }
                }
            }

        } catch(Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    private static Event getEvent(String evnt) {
        switch(evnt.toUpperCase()) {
            case "PASSIVE": return FsmEvents.PASSIVE;
            case "ACTIVE": return FsmEvents.ACTIVE;
            case "SYN": return FsmEvents.SYN;
            case "SYNACK": return FsmEvents.SYNACK;
            case "ACK": return FsmEvents.ACK;
            case "RDATA": return FsmEvents.RDATA;
            case "SDATA": return FsmEvents.SDATA;
            case "FIN": return FsmEvents.FIN;
            case "CLOSE": return FsmEvents.CLOSE;
            case "TIMEOUT": return FsmEvents.TIMEOUT;
            default: return null;
        }
    }
}
