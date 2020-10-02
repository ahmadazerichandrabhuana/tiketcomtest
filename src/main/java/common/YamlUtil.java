package common;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
/**
 * Usage : connector to yaml file
 * @author zorozeri@gmail.com
 */
public class YamlUtil {
    private static final Pattern EXPRESSION_PATTERN = Pattern
            .compile("#\\{([a-z0-9A-Z_.]+)\\s?(?:'([^']+)')?(?:,'([^']+)')*\\}");
    private List<Map<String, Object>> valuesMaps;

    public YamlUtil() {
    }

    public void setYamlPath(String path) {
        try {
            FileInputStream stream = new FileInputStream(new File(path));
            final List<Map<String, Object>> all = new ArrayList();
            all.add(new Yaml().loadAs(stream, Map.class));
            this.valuesMaps = Collections.unmodifiableList(all);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setYamlPathUsingClassName(String className) {
        String path = "." + className.replace(".", "/").substring(1, className.lastIndexOf(".")) + ".yaml";
        setYamlPath(path);
    }

    public Object fetchObject(String key) {
        String[] path = key.split("\\.");
        Object result = null;
        for (Map<String, Object> valuesMap : valuesMaps) {
            Object currentValue = valuesMap;
            for (int p = 0; currentValue != null && p < path.length; p++) {
                currentValue = ((Map<String, Object>) currentValue).get(path[p]);
            }
            result = currentValue;
            if (result != null) {
                break;
            }
        }
        return result;
    }
}