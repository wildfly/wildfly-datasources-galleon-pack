Galleon Feature Packs for integrating datasources into WildFly and WildFly Preview
==================================================

These feature-packs for WildFly and WildFly Preview provide JDBC drivers and datasources for various databases.
The versions of the JDBC drivers can be retrieved from the [pom.xml file](pom.xml) of the feature-pack version you are using.

The datasources (or datasources-preview) Galleon feature-pack is to be provisioned along with the WildFly (or WildFly Preview) Galleon feature-pack.

The Galleon layers defined in these feature-packs are decorator layers. This means that they need to be provisioned 
in addition to a WildFly base layer. The WildFly [Installation Guide](https://docs.wildfly.org/27/#installation-guides) covers the 
[base layers](https://docs.wildfly.org/27/Galleon_Guide.html#wildfly_foundational_galleon_layers) that WildFly defines.

NOTE: The base layer `datasources-web-server` (that provisions WildFly datasources) is the minimal base layer to use when provisioning Galleon layers that these 
feature-packs define.

Resources:

* [WildFly Installation Guide](https://docs.wildfly.org/27/#installation-guides)
* [Galleon documentation](https://docs.wildfly.org/galleon/)

Galleon feature-pack compatible with WildFly
============================

The Maven coordinates to use is: `org.wildfly:wildfly-datasources-galleon-pack:<version>`

Galleon feature-pack compatible with WildFly Preview
=================================

The Maven coordinates to use is: `org.wildfly:wildfly-datasources-preview-galleon-pack:<version>`

Supported database types
===============

For each database type it supports, each feature-pack provides 3 Galleon layers that build upon each other :

* One just to add the driver (eg: `postgresql-driver`). This installs a JBoss Modules module for the driver and 
adds a driver resource to the datasources subsystem in the server configuration.
* One that builds on the first to add a datasource (eg: `postgresql-datasource`).
* One that builds on the second to make the datasource the Jakarta EE default datasource (eg: `postgresql-default-datasource`).

The linked documentation provides information about how to configure each supported database type:

* [AWS Advanced JDBC Wrapper driver](doc/awswrapper/README.md)

* [DB2 driver and datasource](doc/db2/README.md)

* [H2Database driver and datasource](doc/h2database/README.md)

* [MariaDB driver and datasource](doc/mariadb/README.md)

* [Microsoft SQL Server driver and datasource](doc/mssqlserver/README.md)

* [MySQL driver and datasource](doc/mysql/README.md)

* [Oracle driver and datasource](doc/oracle/README.md)

* [PostgreSQL driver and datasource](doc/postgresql/README.md)

Using the Datasources Galleon feature-pack
==========================

Provisioning of driver and datasources Galleon layers can be done in multiple ways according to the provisioning tooling in use.

## Provisioning using CLI tool

You can download the latest Galleon CLI tool from the Galleon github project [releases](https://github.com/wildfly/galleon/releases).
 
You need to define a Galleon provisioning configuration file such as:

```
<?xml version="1.0" ?>
<installation xmlns="urn:jboss:galleon:provisioning:3.0">
  <feature-pack location="org.wildfly:wildfly-galleon-pack:27.0.0.Final">
    <default-configs inherit="false"/>
    <packages inherit="false"/>
  </feature-pack>
  <feature-pack location="org.wildfly:wildfly-datasources-galleon-pack:3.0.0.Final">
    <default-configs inherit="false"/>
    <packages inherit="false"/>
  </feature-pack>
  <config model="standalone" name="standalone.xml">
    <layers>
      <!-- Base layer -->
      <include name="datasources-web-server"/>
      <include name="postgresql-datasource"/>
      <include name="mysql-datasource"/>
    </layers>
  </config>
  <options>
    <option name="optional-packages" value="passive+"/>
    <option name="jboss-fork-embedded" value="true"/>
  </options>
</installation>
```
and provision it using the following command:

```
galleon.sh provision provisioning.xml --dir=my-wildfly-server
```

## Provisioning using the [WildFly Maven Plugin](https://github.com/wildfly/wildfly-maven-plugin/) or the [WildFly JAR Maven plugin](https://github.com/wildfly-extras/wildfly-jar-maven-plugin/)

You need to include the datasources feature-pack and layers in the Maven Plugin configuration. This looks like:

```
...
<feature-packs>
  <feature-pack>
    <location>org.wildfly:wildfly-galleon-pack:27.0.0.Final</location>
  </feature-pack>
  <feature-pack>
    <location>org.wildfly:wildfly-datasources-galleon-pack:3.0.0.Final</location>
  </feature-pack>
</feature-packs>
<layers>
  <!-- Base layer -->
  <layer>jaxrs-server</layer>
  <layer>postgresql-datasource</layer>
</layers>
...
```

This [example](https://github.com/wildfly-extras/wildfly-jar-maven-plugin/tree/8.1.0.Final/examples/postgresql) 
contains a complete WildFly JAR Maven Plugin configuration.

This [example](https://github.com/wildfly/wildfly-s2i/tree/main/examples/postgresql) 
contains a complete WildFly Maven Plugin configuration.