package com.ic.analisaSusy.commons;

import com.ic.analisaSusy.AnalisaSusy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo C. Araújo
 */
public class ErrorChecking {

    public static List<String> fileExistenceCheck(List<String> filepaths) throws FileNotFoundException {
        boolean anyFileExists = false;
        File aFile = null;
        List<String> validFiles = new ArrayList<>();
        for (String file : filepaths) {
            aFile = new File(file);
            if (aFile.exists()) {
                anyFileExists = true;
                validFiles.add(file);
            } else {
                String[] splits = file.split(File.separator);
                AnalisaSusy.errors.put(ApplicationError.NF_CODE, splits[splits.length - 1]);
            }
        }
        if (!anyFileExists) {
            AnalisaSusy.errors.removeAll(ApplicationError.NF_CODE);
            AnalisaSusy.errors.put(ApplicationError.NOT_FOUND_FILES, "");
            throw new FileNotFoundException("Nenhum arquivo informado na entrada foi encontrado");
        }
        return validFiles;
    }

    public static List<String> fileExtensionCheck(List<String> filepaths) throws IOException {
        boolean anyValidExtension = false;
        List<String> validFiles = new ArrayList<>();
        for (String file : filepaths) {
            if (file.endsWith(".c") || file.endsWith(".h")) {
                anyValidExtension = true;
                validFiles.add(file);
            } else {
                String[] splits = file.split(File.separator);
                AnalisaSusy.errors.put(ApplicationError.NF_CODE, splits[splits.length - 1]);
            }
        }
        if (!anyValidExtension) {
            AnalisaSusy.errors.removeAll(ApplicationError.NF_CODE);
            AnalisaSusy.errors.put(ApplicationError.NO_VALID_FILES, "");
            throw new IOException("Nenhum arquivo informado é válido");
        }
        return validFiles;
    }

    public static void verifyCCSM() throws Exception {
        ShellInterface aShellInterface = new ShellInterface();

        aShellInterface.executeCommand("ccsm -help");
       // System.out.println(aShellInterface.getOutput());
        if (aShellInterface.getOutput().length() < 200) {
            AnalisaSusy.errors.put(ApplicationError.NF_CCSM, "");
            throw new Exception("Ferramenta CCSM não encontrada");
        }
    }
}
