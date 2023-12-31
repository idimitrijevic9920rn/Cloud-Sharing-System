package message.messages;

import app.App;
import app.Config;
import app.ServentMain;
import message.Message;
import servent.Servent;

import java.util.Arrays;

public class JoinTellMessage extends Message {

    private static final long serialVersionUID = 1L;

    private final Servent[] servents;
    private final boolean joined;


    public JoinTellMessage(Servent receiver, Servent[] servents, boolean joined) {
        super(null, Config.LOCAL, receiver);

        this.servents = servents;
        this.joined = joined;
    }

    public JoinTellMessage(JoinTellMessage m) {
        super(m);

        servents = m.servents;
        joined = m.joined;
    }

    @Override
    protected Message copy() {
        return new JoinTellMessage(this);
    }

    @Override
    public void handle() {
        if (isJoined()) {
            App.print(String.format("Servent %s joined", getReceiver()));

            for (Servent servent : getServents()) {
                Config.NETWORK.addServent(servent);
            }

        } else {
            App.error(String.format("Servent %s cannot join", getReceiver()));
            ServentMain.stop();
        }
    }

    @Override
    public String toString() {
        return super.toString() + " with servents " + Arrays.toString(getServents());
    }

    public Servent[] getServents() {
        return servents;
    }

    public boolean isJoined() {
        return joined;
    }
}

