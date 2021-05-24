import com.diligesoft.config.utils.cdi.ConfigMapConverter;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ConfigMapConverterTest {

    @Test
    public void convert() {
        String json = "{\"AC99\":[\"5000|5160|1\"], \"BR99\":[\"5000|5020,5000|5040\",\"5000|5080|2\",\"8000|8020|1\"]}";
        ConfigMapConverter converter = new ConfigMapConverter();

        Map<String, List<String>> obj = converter.convert(json);

        Assert.assertEquals(2, obj.size());

    }
}