package br.udesc.ceavi.sigmaon.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Carrega arquivos properties. Centraliza a responsabilidade de carregar
 * arquivos properties.
 *
 * @author Weverton Otoni
 */
public class PropertiesLoader {

    public static final String DATABASE_CONFIG
            = "/br/udesc/ceavi/sigmaon/config/database.properties";

    public PropertiesLoader() {

    }

    public Properties getProperties(String fileName) {
        Properties prop = new Properties();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
