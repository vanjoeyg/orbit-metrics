package cloud.orbit.metrics;

import cloud.orbit.actors.extensions.ActorExtension;
import cloud.orbit.concurrent.Task;

import com.orbit.metrics.config.ReporterConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgong on 11/29/16.
 */
public class MetricsExtension implements ActorExtension {

    private List<ReporterConfig> metricsConfig = new ArrayList<>();

    @Override
    public Task<?> start() {
        MetricsManager.getInstance().initializeMetrics(metricsConfig);
        return Task.done();
    }

    @Override
    public Task<?> stop() {
        return Task.done();
    }
}
