package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ComparisonTest {

    private static final String TEST_PATH = "./src/test/resources/fixtures/";
    private static final String FILE_PATH_1 = "file1.json";
    private static final String FILE_PATH_2 = "file2.json";
    private static final String FILE_PATH_3 = "file1.yml";
    private static final String FILE_PATH_4 = "file2.yml";

    private static String expectedResultStylish;
    private static String expectedResultPlain;
    private static String expectedResultJson;

    @BeforeEach
    public final void beforeEach() throws Exception {
        expectedResultStylish = Files.readString(Paths.get(TEST_PATH + "expectedResultStylish.txt")
                .toAbsolutePath().normalize()).trim();
        expectedResultPlain = Files.readString(Paths.get(TEST_PATH + "expectedResultPlain.txt")
                .toAbsolutePath().normalize()).trim();
        expectedResultJson = Files.readString(Paths.get(TEST_PATH + "expectedResultJson.json")
                .toAbsolutePath().normalize()).trim();
    }

    @Test
    public void testJSONtoStylish() throws Exception {
        String result = Differ.generate(FILE_PATH_1, FILE_PATH_2, "stylish");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultStylish);
    }

    @Test
    public void testYMLtoStylish2() throws Exception {
        String result = Differ.generate(FILE_PATH_3, FILE_PATH_4, "stylish");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultStylish);
    }

    @Test
    public void testJSONtoPlain() throws Exception {
        String result = Differ.generate(FILE_PATH_1, FILE_PATH_2, "plain");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultPlain);
    }

    @Test
    public void testYMLtoPlain() throws Exception {
        String result = Differ.generate(FILE_PATH_3, FILE_PATH_4, "plain");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultPlain);
    }

    @Test
    public void testJSONtoJson() throws Exception {
        String result = Differ.generate(FILE_PATH_1, FILE_PATH_2, "json");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultJson);
    }

    @Test
    public void testYMLtoJson() throws Exception {
        String result = Differ.generate(FILE_PATH_3, FILE_PATH_4, "json");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultJson);
    }

    @Test
    public void testGenerateDefault() throws Exception {
        String result1 = Differ.generate(FILE_PATH_1, FILE_PATH_2);
        String result2 = Differ.generate(FILE_PATH_3, FILE_PATH_4);
        assertThat(result1).isEqualToIgnoringWhitespace(expectedResultStylish);
        assertThat(result2).isEqualToIgnoringWhitespace(expectedResultStylish);
    }

    @Test
    public void testGenerate() throws Exception {
        String result1 = Differ.generate(FILE_PATH_1, FILE_PATH_2);
        String result2 = Differ.generate(FILE_PATH_3, FILE_PATH_4);
        assertNotNull(result1);
        assertNotNull(result2);
        assertFalse(result1.isEmpty());
        assertFalse(result2.isEmpty());
    }

    @Test
    public void testData() {
        String filepath = TEST_PATH + FILE_PATH_1;
        String result = Differ.getData(filepath);
        assertEquals(FILE_PATH_1, result);
    }
}
