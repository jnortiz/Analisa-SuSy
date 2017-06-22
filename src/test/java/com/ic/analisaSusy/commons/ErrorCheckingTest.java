package com.ic.analisaSusy.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

/**
 * @author dmarinho
 *
 */
public class ErrorCheckingTest {

    private static final String PATH_FILE = "/Files/carregados.txt";
    private static final String PATH_FILE_1 = "/Files/test1.c";
    private static final String PATH_FILE_2 = "/Files/test1.c";

    @Test
    public void testFileExistenceCheck() throws ParseException, FileNotFoundException {

        final String filePath = this.getClass().getResource(PATH_FILE).getFile();
        final List<String> fileExistenceCheck = ErrorChecking.fileExistenceCheck(Arrays.asList(filePath));

        assertTrue(fileExistenceCheck.size() > 0);
        assertEquals(filePath, fileExistenceCheck.get(0));
    }

    @Test
    public void testFileExtensionCheck() throws ParseException, IOException {

        final String filePath1 = this.getClass().getResource(PATH_FILE_1).getFile();
        final String filePath2 = this.getClass().getResource(PATH_FILE_2).getFile();
        final List<String> fileExtensionCheck = ErrorChecking.fileExtensionCheck(Arrays.asList(filePath1, filePath2));

        assertTrue(fileExtensionCheck.size() > 0);
        assertEquals(filePath1, fileExtensionCheck.get(0));
        assertEquals(filePath2, fileExtensionCheck.get(1));
    }

}
