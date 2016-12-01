package com.orbit.metrics.config;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Reporter;

import java.util.concurrent.TimeUnit;

/**
 * Created by jgong on 11/29/16.
 */
public abstract class ReporterConfig {

    private int period = 1;
    private String periodUnit = "MINUTES";
    private String rateUnit = "SECONDS";
    private String durationUnit = "MILLISECONDS";
    private String prefix = "";


    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit;
    }

    public String getRateUnit() {
        return rateUnit;
    }

    public void setRateUnit(String rateUnit) {
        this.rateUnit = rateUnit;
    }

    public String getDurationUnit() {
        return durationUnit;
    }

    public void setDurationUnit(String durationUnit) {
        this.durationUnit = durationUnit;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    protected TimeUnit getRateTimeUnit() {
        return TimeUnit.valueOf(getRateUnit());
    }

    protected TimeUnit getDurationTimeUnit() {
        return TimeUnit.valueOf(getDurationUnit());
    }

    protected TimeUnit getPeriodTimeUnit() {
        return TimeUnit.valueOf(getPeriodUnit());
    }

    public synchronized Reporter enableReporter(MetricRegistry registry) {
        return null;
    }
}
