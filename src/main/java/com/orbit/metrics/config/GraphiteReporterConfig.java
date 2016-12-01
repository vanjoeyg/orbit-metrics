package com.orbit.metrics.config;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * Created by jgong on 11/29/16.
 */
public class GraphiteReporterConfig extends ReporterConfig {
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
        final Graphite graphite = new Graphite(new InetSocketAddress(host, port));
        final GraphiteReporter reporter = GraphiteReporter.forRegistry(registry)
                .convertRatesTo(getRateTimeUnit())
                .convertDurationsTo(getDurationTimeUnit())
                .prefixedWith(getPrefix())
                .build(graphite);

        reporter.start(getPeriod(), getPeriodTimeUnit());

        return reporter;
    }
}
