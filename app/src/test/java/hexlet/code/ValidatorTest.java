package hexlet.code;

import hexlet.code.schema.BaseSchema;
import hexlet.code.schema.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {

    @Test
    void testStringValidator() {
        String str = "abc";
        String str1 = "abcdf";
        StringSchema schema = new StringSchema();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid(str));
        schema.minLength(4);
        assertFalse(schema.isValid(str));
        schema.contains("df");
        assertTrue(schema.isValid(str1));
        assertFalse(schema.isValid(str));

    }

    @Test
    public void testNumberValidator() {
        var v = new Validator();
        var schema = v.number();

        assertTrue(schema.isValid(15));
        assertTrue(schema.isValid(-5));
        assertTrue(schema.isValid(null));

        schema.positive();
        assertFalse(schema.isValid(-3));
        assertTrue(schema.isValid(8));

        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(-12));
        assertTrue(schema.isValid(100));

        schema.range(20, 50);
        assertTrue(schema.isValid(20));
        assertTrue(schema.isValid(50));
        assertFalse(schema.isValid(10));
        assertFalse(schema.isValid(60));

        schema.range(25, 35);
        assertFalse(schema.isValid(24));
        assertTrue(schema.isValid(30));
    }

    @Test
    public void testMapValidator() {
        var v = new Validator();
        var schema = v.map();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        schema.sizeof(1);
        assertFalse(schema.isValid(new HashMap<>()));


        Map<String, String> sampleData1 = new HashMap<>();
        sampleData1.put("item1", "valueA");
        assertTrue(schema.isValid(sampleData1));

        sampleData1.put("item2", "valueB");
        assertFalse(schema.isValid(sampleData1));

        schema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("username", v.string().required().contains("user"));
        schemas.put("email", v.string().required().contains("@"));
        schema.shape(schemas);

        Map<String, String> sampleData2 = new HashMap<>();
        sampleData2.put("username", "testuser");
        sampleData2.put("email", "test@example.com");
        assertTrue(schema.isValid(sampleData2));

        Map<String, String> sampleData3 = new HashMap<>();
        sampleData3.put("username", "guest");
        sampleData3.put("email", "guestdomain.com");
        assertFalse(schema.isValid(sampleData3));

        Map<String, String> sampleData4 = new HashMap<>();
        sampleData4.put("username", "user123");
        sampleData4.put("address", "unknown");
        assertFalse(schema.isValid(sampleData4));
    }
}
