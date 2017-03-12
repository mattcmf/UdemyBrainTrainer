package demos.udemybraintrainer;

import android.support.test.espresso.core.deps.dagger.Component;

import javax.inject.Singleton;

/**
 * Created by matthewframpton on 12/03/2017.
 */


@Singleton
@Component(modules = MockAnswerGeneratorModule.class)
public interface DemoComponent {
    void inject(MainActivity mainActivity);
}