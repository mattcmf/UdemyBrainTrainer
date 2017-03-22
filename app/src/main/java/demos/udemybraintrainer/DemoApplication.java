package demos.udemybraintrainer;

import android.app.Application;

public class DemoApplication extends Application implements HasGraph {

    private Graph graph;

    public DemoApplication() {
        this.graph = new Graph(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public Graph graph() {
        return graph;
    }

}