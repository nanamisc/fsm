package States;

public class FsmStates {
    public static final StartState START = new StartState("START");
    public static final ClosedState CLOSED = new ClosedState("CLOSED");
    public static final ListenState LISTEN = new ListenState("LISTEN");
    public static final SynSentState SYN_SENT = new SynSentState("SYN_SENT");
    public static final SynRcvdState SYN_RCVD = new SynRcvdState("SYN_RCVD");
    public static final EstablishedState ESTABLISHED = new EstablishedState("ESTABLISHED");
    public static final FinWait1State FIN_WAIT_1 = new FinWait1State("FIN_WAIT_1");
    public static final FinWait2State FIN_WAIT_2 = new FinWait2State("FIN_WAIT_2");
    public static final ClosingState CLOSING = new ClosingState("CLOSING");
    public static final CloseWaitState CLOSE_WAIT = new CloseWaitState("CLOSE_WAIT");
    public static final LastAckState LAST_ACK = new LastAckState("LAST_ACK");
    public static final TimeWaitState TIME_WAIT = new TimeWaitState("TIME_WAIT");
}
