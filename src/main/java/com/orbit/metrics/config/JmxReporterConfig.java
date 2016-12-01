package com.orbit.metrics.config;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Reporter;

/**
 * Created by jgong on 11/29/16.
 */
public class JmxReporterConfig extends ReporterConfig {
    public JmxReporterConfig() {

    }

    @Override
    public synchronized Reporter enableReporter(MetricRegistry registry) {
        final JmxReporter reporter = JmxReporter.forRegistry(registry)
                .convertRatesTo(getRateTimeUnit())
                .convertDurationsTo(getDurationTimeUnit())
                .build();

        reporter.start();

        return reporter;
    }
}
