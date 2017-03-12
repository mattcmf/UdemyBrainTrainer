package demos.udemybraintrainer;

import android.support.test.espresso.core.deps.dagger.Module;
import android.support.test.espresso.core.deps.dagger.Provides;

import org.mockito.Mockito;

import javax.inject.Singleton;

@Module
public class MockAnswerGeneratorModule {
    @Provides
    @Singleton
    AnswerGeneratorSingleton provideAnswerGenerator() {
        return Mockito.mock(AnswerGeneratorSingleton.class);
    }
}