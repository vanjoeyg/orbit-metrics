package com.orbit.metrics.config;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.ganglia.GangliaReporter;

import info.ganglia.gmetric4j.gmetric.GMetric;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by jgong on 11/29/16.
 */
public class GangliaReporterConfig extends ReporterConfig {
    private static final Logger logger = LoggerFactory.getLogger(GangliaReporterConfig.class);
    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    @Override
    public synchronized ScheduledReporter enableReporter(MetricRegistry registry) {
        try {
            final GMetric ganglia = new GMetric(host, port, GMetric.UDPAddressingMode.MULTICAST, 1);
            final GangliaReporter reporter = GangliaReporter.forRegistry(registry)
                    .convertRatesTo(getRateTimeUnit())
                    .convertDurationsTo(getDurationTimeUnit())
                    .prefixedWith(getPrefix())
                    .build(ganglia);

            reporter.start(getPeriod(), getPeriodTimeUnit());

            return reporter;
        } catch (IOException iex) {
            logger.warn("Unable to enable Ganglia Reporter: " + iex.getMessage());
        }

        return null;
    }
}

