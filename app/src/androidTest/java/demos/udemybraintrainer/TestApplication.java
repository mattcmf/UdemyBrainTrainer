package demos.udemybraintrainer;

import android.app.Application;

public class TestApplication extends Application implements HasGraph {

    private static Application instance;
    private static Graph graph;

    @Override
    public void onCreate() {
        super.onCreate();
        TestApplication.instance = this;
    }

    @Override
    public Graph graph() {
        if (null == graph) {
            throw new IllegalStateException("Graph has not been initialized.");
        }

        return graph;
    }

    public static Application instance() {
        return instance;
    }

    public static void setGraph(Graph graph) {
        TestApplication.graph = graph;
    }

}
