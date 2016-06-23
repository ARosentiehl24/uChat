package com.arrg.android.app.uchat.lib;

public class GreenRobotEventBus implements EventBus {

    private de.greenrobot.event.EventBus eventBus;

    public static GreenRobotEventBus getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public GreenRobotEventBus() {
        this.eventBus = de.greenrobot.event.EventBus.getDefault();
    }

    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }

    public static class SingletonHolder {
        private static final GreenRobotEventBus INSTANCE = new GreenRobotEventBus();
    }
}
