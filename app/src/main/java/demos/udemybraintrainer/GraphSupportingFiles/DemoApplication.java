package demos.udemybraintrainer.GraphSupportingFiles;

import android.app.Application;

public class DemoApplication extends Application implements HasGraph {

    private Graph graph;
	public boolean started = false;

    public DemoApplication() {
        this.graph = new Graph(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
	    started = true;
    }

    @Override
    public Graph graph() {
        return graph;
    }
}
