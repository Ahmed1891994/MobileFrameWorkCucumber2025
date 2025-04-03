package Utils.helpers;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestStepStarted;
import io.cucumber.plugin.event.*;
import lombok.Getter;

@Getter
public class StepListener implements ConcurrentEventListener {
    public static Step step;

    public EventHandler<TestStepStarted> handler = this::getStep;

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestStepStarted.class, handler);
    }

    private void getStep(TestStepStarted testStepStarted) {
        if (testStepStarted.getTestStep() != null && testStepStarted.getTestStep() instanceof PickleStepTestStep testStep) {
            step = testStep.getStep();
        }
    }
}
