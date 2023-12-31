package message.messages;

import app.App;
import app.Config;
import message.Message;
import servent.Servent;

public class JoinAskMessage extends Message {

    private static final long serialVersionUID = 1L;

    public JoinAskMessage() {
        super(null, Config.LOCAL, Config.BOOTSTRAP);
    }

    public JoinAskMessage(JoinAskMessage m) {
        super(m);
    }

    @Override
    protected Message copy() {
        return new JoinAskMessage(this);
    }

    @Override
    public void handle() {
        Servent[] servents = Config.NETWORK.getServents(this.getSender().getKey(), false);
        boolean joined = Config.NETWORK.addServent(this.getSender());
        App.send(new JoinTellMessage(getSender(), servents, joined));
    }


}
