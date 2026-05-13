/*
 * Copyright The WildFly Authors
 * SPDX-License-Identifier: Apache-2.0
 */
package org.wildfly.datasources.galleon.pack.test.layers.metadata;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.wildfly.glow.Arguments;
import org.wildfly.glow.GlowMessageWriter;
import org.wildfly.glow.GlowSession;
import org.wildfly.glow.ScanResults;
import org.wildfly.glow.maven.MavenResolver;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AbstractLayerMetaDataTestCase {

    private static String URL_PROPERTY = "wildfly-glow-galleon-feature-packs-url";
    private static Path ARCHIVES_PATH = Paths.get("target/glow-archives");

    private static Set<String> ALL_DRIVER_LAYERS = Set.of(new String[]{
            "mariadb-driver",
            "mssqlserver-driver",
            "mysql-driver",
            "oracle-driver",
            "postgresql-driver",
            "db2-driver",
            "awswrapper-driver"
    });

    @BeforeClass
    public static void prepareArchivesDirectory() throws Exception {
        Path glowXmlPath = Path.of("target/test-classes/glow");
        //URL url = new URL()
        System.out.println(glowXmlPath.toUri());
        System.setProperty(URL_PROPERTY, glowXmlPath.toUri().toString());
        if (Files.exists(ARCHIVES_PATH)) {
            Files.walkFileTree(ARCHIVES_PATH, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        Files.createDirectories(ARCHIVES_PATH);
    }


    protected static Path createWebArchive(String archiveName, String xmlName, String xmlContents) {
        WebArchive war = ShrinkWrap.create(WebArchive.class);
        Asset asset = new StringAsset(xmlContents);
        war.addAsWebInfResource(asset, xmlName);
        ZipExporter exporter = war.as(ZipExporter.class);
        Path path = ARCHIVES_PATH.resolve(archiveName);
        exporter.exportTo(path.toFile());
        return path;
    }

    protected static Path createJavaArchive(String archiveName, String xmlName, String xmlContents) {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
        Asset asset = new StringAsset(xmlContents);
        jar.addAsManifestResource(asset, xmlName);
        ZipExporter exporter = jar.as(ZipExporter.class);
        Path path = ARCHIVES_PATH.resolve(archiveName);
        exporter.exportTo(path.toFile());
        return path;
    }

    protected static Path createWebArchiveFromClass(String archiveName, Class<?> testClass) {
        WebArchive war = ShrinkWrap.create(WebArchive.class);
        war.addClass(testClass);
        ZipExporter exporter = war.as(ZipExporter.class);
        Path path = ARCHIVES_PATH.resolve(archiveName);
        exporter.exportTo(path.toFile());
        return path;
    }
 
    protected static String createDatasourceXml(boolean xa, String driverName) {
        String datasource = xa ? "xa-datasource" : "datasource";

        String s =
                "<datasources>" +
                "  <" + datasource + ">" +
                "    <driver>" + driverName + "</driver>" +
                "  </" + datasource + ">" +
                "</datasources>";

        return s;
    }

    protected Set<String> checkLayersForArchive(Path archivePath, String expectedLayer) throws Exception {
        Arguments arguments = Arguments.scanBuilder().setBinaries(Collections.singletonList(archivePath)).build();
        ScanResults scanResults = GlowSession.scan(MavenResolver.newMavenResolver(), arguments, GlowMessageWriter.DEFAULT);
        Set<String> foundLayers = scanResults.getDiscoveredLayers().stream().map(l -> l.getName()).collect(Collectors.toSet());


        Assert.assertTrue(foundLayers.contains(expectedLayer));

        Set<String> allLayers = new HashSet<>(ALL_DRIVER_LAYERS);
        Assert.assertTrue(allLayers.remove(expectedLayer));
        for (String layer : allLayers) {
            Assert.assertFalse(layer, foundLayers.contains(layer));
        }
        return foundLayers;
    }
}
