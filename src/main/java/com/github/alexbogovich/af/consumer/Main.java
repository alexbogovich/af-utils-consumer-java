package com.github.alexbogovich.af.consumer;

import com.github.alexbogovich.af.AfFileTypeUtils;
import com.github.alexbogovich.af.AfValidationUtils;
import org.xml.sax.SAXParseException;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class Main {

    public static final String schema = "АФ 2.21.3д/Схемы";
    public static final String invalid = "RNPF_A_COMPRESS/negative/ПФР_7707492166_000_РНПФ-А_20170809_c23e739a-090f-49d8-a10a-c8ea16535f3f.XML.gz";
    public static final String valid = "RNPF_A_COMPRESS/positive/ПФР_7707492166_000_РНПФ-А_20170809_c23e739a-090f-49d8-a10a-c8ea16535f3f.XML.gz";

    public static void main(String[] args) throws URISyntaxException {
        Path schemaFolder = Paths.get(Main.class.getClassLoader().getResource(schema).toURI());
        Path invalidFile = Paths.get(Main.class.getClassLoader().getResource(invalid).toURI());
        Path validFile = Paths.get(Main.class.getClassLoader().getResource(valid).toURI());

        String uspnFileTypeValid = AfFileTypeUtils.INSTANCE.getUspnFileType(validFile);
        System.out.println("type = " + uspnFileTypeValid);
        Collection<SAXParseException> saxParseExceptions =
                AfValidationUtils.INSTANCE.validateDocument(validFile, uspnFileTypeValid, schemaFolder);

        System.out.println(saxParseExceptions);


        String uspnFileTypeInvalid = AfFileTypeUtils.INSTANCE.getUspnFileType(invalidFile, 2);
        System.out.println("type = " + uspnFileTypeValid);
        Collection<SAXParseException> saxParseExceptions2 =
                AfValidationUtils.INSTANCE.validateDocument(invalidFile, uspnFileTypeInvalid, schemaFolder);

        System.out.println(saxParseExceptions2.size());
        System.out.println(saxParseExceptions2);

    }
}
