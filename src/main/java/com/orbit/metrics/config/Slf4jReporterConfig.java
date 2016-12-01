package com.orbit.metrics.config;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.Slf4jReporter;

/**
 * Created by jgong on 11/29/16.
 */
public class Slf4jReporterConfig extends ReporterConfig {
    private String loggerName;

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(final String loggerName) {
        this.loggerName = loggerName;
    }

    @Override
    public synchronized ScheduledReporter enableReporter(MetricRegistry registry) {
        final Slf4jReporter reporter = Slf4jReporter.forRegistry(registry)
                .convertRatesTo(getRateTimeUnit())
                .convertDurationsTo(getDurationTimeUnit())
                .prefixedWith(getPrefix())
                .build();

        reporter.start(getPeriod(), getPeriodTimeUnit());

        return reporter;
    }


}