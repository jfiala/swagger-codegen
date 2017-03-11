package io.swagger.generators;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.FileSystems;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.codegen.generators.JavaCXFServer_Generator_Petstore;
import io.swagger.codegen.generators.JaxrsSpec_Generator_Petstore;
import io.swagger.codegen.generators.util.ComparePathUtil;

public class JavaCXF_Generator_Petstore_Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaCXF_Generator_Petstore_Test.class);

    TemporaryFolder folder;

    @Before
    public void setup() {
        folder = JavaCXFServer_Generator_Petstore.generate();
        assertNotNull(folder);
    }

    @Test
    public void test() {
        File output = folder.getRoot();
        java.nio.file.Path actualPath = FileSystems.getDefault().getPath(output.getAbsolutePath());

        java.nio.file.Path samplePath = FileSystems.getDefault().getPath("../../samples/server/petstore/jaxrs-cxf");
        LOGGER.info("path: " + samplePath.toFile().getAbsolutePath());

        try {
            assertTrue(ComparePathUtil.isEqualDirectories(actualPath, samplePath, true));

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @After
    public void teardown() {
        // cleanup temporary folder if everything is OK
        folder.delete();
    }

}
