package demos.udemybraintrainer.GraphSupportingFiles;

import android.app.Application;
import android.content.Context;

import demos.udemybraintrainer.Domain.GameTimer;
import demos.udemybraintrainer.Domain.QuestionGenerator;

public class Graph {

    private Application application;

    public Graph(Application application) {
        this.application = application;
    }

    public static Graph from(Context context) {
        if (null == context) {
            throw new IllegalArgumentException("A context is required to obtain a Graph instance.");
        }

        return ((HasGraph) context.getApplicationContext()).graph();
    }

    public QuestionGenerator questionGenerator(){
        QuestionGenerator questionGenerator = new QuestionGenerator();
        return questionGenerator;
    }

    public GameTimer gameTimer(){
        GameTimer gameTimer = new GameTimer();
        return gameTimer;
    }
}
