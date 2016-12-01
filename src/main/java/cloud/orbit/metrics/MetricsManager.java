package cloud.orbit.metrics;


import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Reporter;
import com.orbit.metrics.config.ReporterConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

/**
 * Created by jgong on 11/29/16.
 */
@Singleton
public class MetricsManager {

    private static MetricsManager instance = new MetricsManager();
    private static final MetricRegistry registry = new MetricRegistry();
    private Map<ReporterConfig, Reporter> reporters = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(MetricsManager.class);

    private boolean isInitialized = false;

    public MetricsManager() {

    }

    public static MetricsManager getInstance() {
        return instance;
    }

    public MetricRegistry getRegistry() {
        return registry;
    }

    public synchronized void initializeMetrics(List<ReporterConfig> reporterConfigs) {
        if (!isInitialized) {
            for (ReporterConfig reporterConfig : reporterConfigs) {
                Reporter reporter = reporterConfig.enableReporter(registry);
                if (reporter != null) {
                    reporters.put(reporterConfig, reporter);
                } else {
                    logger.warn("Failed to enable reporter " + reporterConfig.getClass().getName());
                }
            }
            isInitialized = true;
            logger.info("Orbit Metrics Initialized.");
        } else {
            if (logger.isWarnEnabled()) {
                logger.warn("Attempting to initialize the Metrics Manager when it is already initialized!");
            }
        }
    }

    public void registerMetric(String name, Metric metric) {
        try {
            registry.register(name, metric);

            if (logger.isDebugEnabled()) {
                logger.debug("Registered new metric " + name);
            }
        } catch (IllegalArgumentException iae) {
            logger.warn("Unable to register metric " + name + " because a metric already has been registered with the same name");
        }
    }

    public void unregisterMetric(String name) {
        registry.remove(name);
    }
}
