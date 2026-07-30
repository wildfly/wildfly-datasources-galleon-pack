Galleon Layers
=========

* `oracle-default-datasource`: Provision the `OracleDS` non xa datasource and configure it as the default one. Depends on `oracle-datasource` layer.
* `oracle-datasource`: Provision the `OracleDS` non xa datasource. Depends on `oracle-driver` layer.
* `oracle-driver`: Provision the `oracle` driver. This layer installs the JBoss Modules module `com.oracle.database.jdbc`.

Build time configuration
========================

The following variable can be set at Galleon provisioning time to override the
default driver version that is embedded in the feature pack.

Optional configuration
-----------------------

* `ORACLE_DRIVER_VERSION`

  * Description: The version of the `com.oracle.database.jdbc:ojdbc17` Maven artifact.
    When set, this version is used instead of the default version bundled with the
    feature pack.
  * Default value: current version bundled with the feature pack
  * Required: False
  * System Property: `org.wildfly.datasources.oracle.driver.version`

Configuration
========

The following set of environment variables and corresponding Java system properties can be used to configure the datasource.

Required configuration
==============

* `ORACLE_PASSWORD` (or `OPENSHIFT_ORACLE_DB_PASSWORD`)

  * Description: Defines the password for the datasource.
  * No default value.
  * Required: True
  * System Property: `org.wildfly.datasources.oracle.password`

* `ORACLE_URL` (or `OPENSHIFT_ORACLE_URL`)

  * Description: Defines the connection URL for the datasource.  For example: `jdbc:oracle:thin:@localhost:51521:XE`.
  * No default value.
  * Required: True
  * System Property: `org.wildfly.datasources.oracle.connection-url`

* `ORACLE_USER` (or `OPENSHIFT_ORACLE_DB_USERNAME`)

  * Description: Defines the username for the datasource. 
  * No default value.
  * Required: True
  * System Property: `org.wildfly.datasources.oracle.user-name`

Optional configuration
==============

* `ORACLE_BACKGROUND_VALIDATION`

  * Description: When set to true database connections are validated periodically in a background thread prior to use. Defaults to false, meaning the `validate-on-match` method is enabled by default instead.  
  * Default Value: `false`
  * System Property: `org.wildfly.datasources.oracle.background-validation`

* `ORACLE_BACKGROUND_VALIDATION_MILLIS`

  * Description: Specifies frequency of the validation, in milliseconds, when the `background-validation` database connection validation mechanism is enabled.    
  * Default Value: `10000`
  * System Property: `org.wildfly.datasources.oracle.background-validation-millis`

* `ORACLE_CONNECTION_CHECKER`

  * Description: Specifies a connection checker class that is used to validate connections. Valid value: `org.jboss.jca.adapters.jdbc.extensions.oracle.OracleValidConnectionChecker`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.oracle.OracleValidConnectionChecker`
  * System Property: `org.wildfly.datasources.oracle.valid-connection-checker-class-name`

* `ORACLE_DATASOURCE` (or `OPENSHIFT_ORACLE_DATASOURCE`)

  * Description: Datasource name used in the `jndi-name`.
  * Default Value: `OracleDS`
  * System Property: `org.wildfly.datasources.oracle.datasource`

* `ORACLE_ENABLED`

  * Description: Set to false to disable the datasource.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.oracle.enabled`

* `ORACLE_EXCEPTION_SORTER`

  * Description: Specifies the exception sorter class that is used to properly detect and clean up after fatal database connection exceptions. Valid value: `org.jboss.jca.adapters.jdbc.extensions.oracle.OracleExceptionSorter`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.oracle.OracleExceptionSorter`
  * System Property: `org.wildfly.datasources.oracle.exception-sorter-class-name`

* `ORACLE_FLUSH_STRATEGY`

  * Description: Specifies how the datasource should be flushed in case of an error.    
  * Default Value: `FailingConnectionOnly`
  * System Property: `org.wildfly.datasources.oracle.flush-strategy`

* `ORACLE_IDLE_TIMEOUT_MINUTES`

  * Description: Specifies the maximum time, in minutes, a connection may be idle before being closed.
  * Default Value:`30`
  * System Property: `org.wildfly.datasources.oracle.idle-timeout-minutes`

* `ORACLE_JNDI`

  * Description: Defines the JNDI name for the datasource.
  * Default Value: `java:jboss/datasources/${ORACLE_DATASOURCE}`
  * System Property: `org.wildfly.datasources.oracle.jndi-name`

* `ORACLE_JTA`

  * Description: Defines Java Transaction API (JTA) option for the non-XA datasource.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.oracle.jta`

* `ORACLE_MAX_POOL_SIZE`

  * Description: Defines the maximum pool size option for the datasource.
  * Default Value: `20`
  * System Property: `org.wildfly.datasources.oracle.max-pool-size`

* `ORACLE_MIN_POOL_SIZE`

  * Description: Defines the minimum pool size option for the datasource.
  * Default Value: `0`
  * System Property: `org.wildfly.datasources.oracle.min-pool-size`

* `ORACLE_STALE_CONNECTION_CHECKER`

  * Description: Specifies a connection checker class that is used to check stale connections. Valid values: `org.jboss.jca.adapters.jdbc.extensions.oracle.OracleStaleConnectionChecker`or `org.jboss.jca.adapters.jdbc.extensions.novendor.NullStaleConnectionChecker`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.novendor.NullStaleConnectionChecker`
  * System Property: `org.wildfly.datasources.oracle.stale-connection-checker-class-name`

* `ORACLE_TX_ISOLATION`

  * Description: Defines the `java.sql.Connection` transaction isolation level for the datasource.    
  * Default Value: `TRANSACTION_READ_COMMITTED`
  * System Property: `org.wildfly.datasources.oracle.transaction-isolation`

* `ORACLE_VALIDATE_ON_MATCH`

  * Description: Indicates whether or not connection level validation should be done when a connection factory attempts to match a managed connection for a given set. This is typically exclusive to the use of background validation.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.oracle.validate-on-match`

