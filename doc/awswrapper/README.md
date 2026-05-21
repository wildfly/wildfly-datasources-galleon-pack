Galleon Layers
=========

* `awswrapper-driver`: Provision the `aws-wrapper` driver. This layer installs the JBoss Modules module `software.amazon.jdbc`.

Configuration
========

See https://github.com/aws/aws-advanced-jdbc-wrapper.

The following set of environment variables and corresponding Java system properties can be used to configure the datasource.

Using plugins (https://github.com/aws/aws-advanced-jdbc-wrapper/blob/main/docs/using-the-jdbc-driver/UsingTheJdbcDriver.md#list-of-available-plugins) that require additional dependencies is currently not supported.

* AWS Advanced JDBC Wrapper configuration parameters can be supplied in two ways:
    * As part of the JDBC URL
        * `jdbc:aws-wrapper:postgresql://${POSTGRESQL_HOST}:${POSTGRESQL_PORT}/${POSTGRESQL_DATABASE}?wrapperLoggerLevel=DEBUG`
        * `jdbc:aws-wrapper:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DATABASE}?wrapperLoggerLevel=DEBUG`
        * `jdbc:aws-wrapper:mariadb://${MARIADB_HOST}:${MARIADB_PORT}/${MARIADB_DATABASE}?wrapperLoggerLevel=DEBUG`
    * Or as connection-properties in the underlying datasource
        * `/subsystem=datasources/data-source=PostgreSQLDS/connection-properties=wrapperLoggerLevel:add(value=DEBUG)`
        * `/subsystem=datasources/data-source=MySQLDS/connection-properties=wrapperLoggerLevel:add(value=DEBUG)`
        * `/subsystem=datasources/data-source=MariaDBDS/connection-properties=wrapperLoggerLevel:add(value=DEBUG)`

Required configuration
==============

The AWS Advanced JDBC Wrapper module imports all supported native JDBC datasources.

* Datasource: Users must explicitly include the desired native datasource layer
    * `postgresql-datasource`
    * `mysql-datasource`
    * `mariadb-datasource`


* Wrapper Protocol: The AWS Advanced JDBC Wrapper uses the protocol prefix `jdbc:aws-wrapper:`
    * When using PostgreSQL, the `POSTGRESQL_URL` must be set to include the `aws-wrapper` as driver name:`jdbc:aws-wrapper:postgresql://${POSTGRESQL_HOST}:${POSTGRESQL_PORT}/${POSTGRESQL_DATABASE}`
    * When using MySQL, the `MYSQL_URL` must be set to include the `aws-wrapper` as driver name:`jdbc:aws-wrapper:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DATABASE}`
    * When using MariaDB, the `MARIADB_URL` must be set to include the `aws-wrapper` as driver name:`jdbc:aws-wrapper:mariadb://${MARIADB_HOST}:${MARIADB_PORT}/${MARIADB_DATABASE}`


* Driver: Bind the AWS Advanced JDBC Wrapper driver to the underlying datasource by overriding the `driver-name` parameter
    * `/subsystem=datasources/data-source=PostgreSQLDS:write-attribute(name=driver-name,value=aws-wrapper)`
    * `/subsystem=datasources/data-source=MySQLDS:write-attribute(name=driver-name,value=aws-wrapper)`
    * `/subsystem=datasources/data-source=MariaDBDS:write-attribute(name=driver-name,value=aws-wrapper)`


* `clusterId`: wrapper configuration parameter, used to identify connections to different database clusters
    * If connecting to multiple database clusters, this parameter is required
    * Otherwise, it is optional
    * `/subsystem=datasources/data-source=PostgreSQLDS/connection-properties=clusterId:add(value=123)`
    * `/subsystem=datasources/data-source=MySQLDS/connection-properties=clusterId:add(value=123)`
    * `/subsystem=datasources/data-source=MariaDBDS/connection-properties=clusterId:add(value=123)`
    * For detailed information, see https://github.com/aws/aws-advanced-jdbc-wrapper/blob/main/docs/using-the-jdbc-driver/ClusterId.md

Optional configuration
==============

* See https://github.com/aws/aws-advanced-jdbc-wrapper
* and https://github.com/aws/aws-advanced-jdbc-wrapper/blob/main/docs/using-the-jdbc-driver/UsingTheJdbcDriver.md#aws-advanced-jdbc-wrapper-parameters