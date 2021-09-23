package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Класс для передачи параметров из файла, указанного в системных переменных.
 */
public final class ParametersProvider {
    /**
     * Список параметров.
     */
    private ArrayList<Properties> propertiesList = new ArrayList<>();

    /**
     * Получение названия файла с параметрами из системных переменных.
     *
     * @return ArrayList<String>
     */
    private static ArrayList<String> getConfigFileNames() {
        ArrayList<String> configFileNames = new ArrayList<>();
        for (String key : System.getProperties().stringPropertyNames()) {
            if (key.startsWith("config.location")) {
                String[] fileNames = System.getProperties().getProperty(key)
                        .split(";");
                for (String fileName : fileNames) {
                    configFileNames.add(fileName);
                }
            }
        }
        return configFileNames;
    }

    /**
     * ParametersProvider конструктор.
     *
     * @throws IOException когда файл параметров не найден
     */
    private ParametersProvider() throws IOException {
        ArrayList<String> configFileNames = getConfigFileNames();
        for (String fileName : configFileNames) {
            Properties properties = new Properties();
            properties.loadFromXML(new FileInputStream(fileName));
            propertiesList.add(properties);
        }
    }

    /**
     * Состояние ParametersProvider.
     */
    private static ParametersProvider instance;

    /**
     * Проверка состояния ParametersProvider.
     *
     * @return состояние ParametersProvider
     * @throws IOException когда файл параметров не найден
     */
    private static ParametersProvider getInstance() throws IOException {
        if (instance == null) {
            instance = new ParametersProvider();
        }
        return instance;
    }

    /**
     * Получение параметра по его ключу.
     *
     * @param key ключ для поиска параметра в файле.
     * @return значение параметра или пустая строка, если параметр не найден.
     * @throws IOException когда файл параметров не найден
     */
    public static String getProperty(final String key) throws IOException {
        for (Properties properties : getInstance().propertiesList) {
            String result = properties.getProperty(key, null);
            if (result != null) {
                return result;
            }
        }
        return "";
    }
}
