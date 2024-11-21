import Fsm.*;
public class FsmEvents {
    public static final Event PASSIVE = new Event("PASSIVE") {};
    public static final Event ACTIVE = new Event("ACTIVE") {};
    public static final Event SYN = new Event("SYN") {};
    public static final Event SYNACK = new Event("SYNACK") {};
    public static final Event ACK = new Event("ACK") {};
    public static final Event RDATA = new Event("RDATA") {};
    public static final Event SDATA = new Event("SDATA") {};
    public static final Event FIN = new Event("FIN") {};
    public static final Event CLOSE = new Event("CLOSE") {};
    public static final Event TIMEOUT = new Event("TIMEOUT") {};
}
