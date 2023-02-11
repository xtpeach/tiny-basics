package com.xtpeach.tiny.basics.common.config.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.hikari")
public class HikariProperties {

    private Integer minimumIdle;

    private Integer maximumPoolSize;

    private Boolean autoCommit;

    private Integer idleTimeout;

    private String poolName;

    private Integer maxLifetime;

    private Integer connectionTimeout;

    private String connectionTestQuery;

    private Integer validationTimeout;

    public Integer getMinimumIdle() {
        return minimumIdle;
    }

    public HikariProperties setMinimumIdle(Integer minimumIdle) {
        this.minimumIdle = minimumIdle;
        return this;
    }

    public Integer getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public HikariProperties setMaximumPoolSize(Integer maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        return this;
    }

    public Boolean getAutoCommit() {
        return autoCommit;
    }

    public HikariProperties setAutoCommit(Boolean autoCommit) {
        this.autoCommit = autoCommit;
        return this;
    }

    public Integer getIdleTimeout() {
        return idleTimeout;
    }

    public HikariProperties setIdleTimeout(Integer idleTimeout) {
        this.idleTimeout = idleTimeout;
        return this;
    }

    public String getPoolName() {
        return poolName;
    }

    public HikariProperties setPoolName(String poolName) {
        this.poolName = poolName;
        return this;
    }

    public Integer getMaxLifetime() {
        return maxLifetime;
    }

    public HikariProperties setMaxLifetime(Integer maxLifetime) {
        this.maxLifetime = maxLifetime;
        return this;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public HikariProperties setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    public String getConnectionTestQuery() {
        return connectionTestQuery;
    }

    public HikariProperties setConnectionTestQuery(String connectionTestQuery) {
        this.connectionTestQuery = connectionTestQuery;
        return this;
    }

    public Integer getValidationTimeout() {
        return validationTimeout;
    }

    public HikariProperties setValidationTimeout(Integer validationTimeout) {
        this.validationTimeout = validationTimeout;
        return this;
    }
}
