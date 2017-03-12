package demos.udemybraintrainer;


import android.app.Application;
import android.support.test.espresso.core.deps.dagger.Component;

import javax.inject.Singleton;

public class DemoApplication extends Application{
    @Singleton
    @Component(modules = AnswerGeneratorModule.class)
    public interface ApplicationComponent extends DemoComponent {
    }

    private final DemoComponent component = createComponent();

    protected DemoComponent createComponent() {
        return DaggerDemoComponent.builder().build();
    }

    public DemoComponent component() {
        return component;
    }
}
}
