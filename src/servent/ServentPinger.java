package servent;

import app.Config;

public class ServentPinger implements Runnable{

    private volatile boolean working = true;

    public ServentPinger() {}

    @Override
    public void run() {
        while (working) {
            Config.NETWORK.ping();
        }
    }

    public void stop() {
        working = false;
    }


}
