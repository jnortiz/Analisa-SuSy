package com.ic.analisaSusy.commons;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo C. Araújo
 */
public class ShellInterface {

    private String output="";
    
    public void executeCommand(String aCommand) throws IOException {

        List<String> commands = new ArrayList<>();
        commands.add("/bin/bash");
        commands.add("-c");
        commands.add(aCommand);

        BufferedReader aBufferedReader = null;

        try {
            ProcessBuilder aProcessBuilder = new ProcessBuilder(commands);
            Process aProcess = aProcessBuilder.start();
            InputStream anInputStream = aProcess.getInputStream();
            InputStreamReader anInputStreamReader = new InputStreamReader(anInputStream);
            aBufferedReader = new BufferedReader(anInputStreamReader);
            String aLine = null;            
            while ((aLine = aBufferedReader.readLine()) != null) {
                this.output += aLine+System.getProperty("line.separator");
            }
        } catch (IOException anException) {
            System.err.println("Error executing command");
            throw anException;
        } finally {
            secureClose(aBufferedReader);
        }
    }

    private void secureClose(final Closeable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException anException) {
            System.err.println("Error closing resource");
        }
    }

    public String getOutput() {
        return this.output;
    }
}
