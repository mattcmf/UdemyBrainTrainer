package demos.udemybraintrainer;

import android.support.test.espresso.core.deps.dagger.Module;
import android.support.test.espresso.core.deps.dagger.Provides;
import javax.inject.Singleton;


@Module
public class AnswerGeneratorModule {
    @Provides
    @Singleton
    AnswerGeneratorSingleton answerGeneratorSingleton() {
        return new AnswerGeneratorSingleton();
    }
}