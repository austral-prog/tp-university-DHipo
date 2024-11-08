package com.university;

import com.university.csv.CSVManager;
import com.university.csv.CSVWritable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CSVManagerTest {

    private static final String TEST_FILE = "test.csv";
    private static final String HEADER = "Name,Email,Course";
    private static final String DATA_1 = "John Doe,john@example.com,Math";
    private static final String DATA_2 = "Jane Doe,jane@example.com,Science";

    @BeforeEach
    void setUp() {
        // Crear un archivo CSV de prueba con encabezado y datos
        try (FileWriter writer = new FileWriter(TEST_FILE)) {
            writer.write(HEADER + "\n");
            writer.write(DATA_1 + "\n");
            writer.write(DATA_2 + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetDataFromFileAsMap() {
        // Llamamos al método y verificamos el contenido del mapa
        Map<String, List<String>> data = CSVManager.getDataFromFileAsMap(TEST_FILE);

        assertNotNull(data, "El mapa no debe ser nulo.");
        assertEquals(3, data.size(), "Debe haber 3 columnas.");

        List<String> names = data.get("Name");
        assertNotNull(names, "La columna 'Name' no debe ser nula.");
        assertEquals(2, names.size(), "Debe haber 2 nombres.");
        assertEquals("John Doe", names.get(0), "El primer nombre debe ser 'John Doe'.");
        assertEquals("Jane Doe", names.get(1), "El segundo nombre debe ser 'Jane Doe'.");

        List<String> emails = data.get("Email");
        assertNotNull(emails, "La columna 'Email' no debe ser nula.");
        assertEquals("john@example.com", emails.get(0), "El primer email debe ser 'john@example.com'.");
        assertEquals("jane@example.com", emails.get(1), "El segundo email debe ser 'jane@example.com'.");

        List<String> courses = data.get("Course");
        assertNotNull(courses, "La columna 'Course' no debe ser nula.");
        assertEquals("Math", courses.get(0), "El primer curso debe ser 'Math'.");
        assertEquals("Science", courses.get(1), "El segundo curso debe ser 'Science'.");
    }

    @Test
    void testGetDataFromFileAsList() {
        // Llamamos al método y verificamos las líneas del archivo
        List<String> lines = CSVManager.getDataFromFileAsList(TEST_FILE);

        assertNotNull(lines, "La lista no debe ser nula.");
        assertEquals(3, lines.size(), "Debe haber 3 líneas en el archivo.");

        assertEquals(HEADER, lines.get(0), "La primera línea debe ser el encabezado.");
        assertEquals(DATA_1, lines.get(1), "La segunda línea debe ser los datos de John.");
        assertEquals(DATA_2, lines.get(2), "La tercera línea debe ser los datos de Jane.");
    }

    @Test
    void testWriteCSV() {
        // Verificar si el método writeCSV no lanza excepciones
        CSVManager csvManager = new CSVManager();
        CSVWritable writable = new CSVWritable() {
            @Override
            public List<String> toCSV() {
                return List.of("Sample Data");
            }
        };

        assertDoesNotThrow(() -> csvManager.writeCSV(writable), "El método writeCSV no debería lanzar excepciones.");
    }

    @Test
    void testWriteCSVWithList() {
        // Verificar si el método writeCSV con lista no lanza excepciones
        CSVManager csvManager = new CSVManager();
        List<CSVWritable> writableList = List.of(new CSVWritable() {
            @Override
            public List<String> toCSV() {
                return List.of("Data 1", "Data 2");
            }
        });

        assertDoesNotThrow(() -> csvManager.writeCSV(writableList), "El método writeCSV con lista no debería lanzar excepciones.");
    }

    @Test
    void testFileNotFound() {
        // Probar que si el archivo no existe, se maneja correctamente
        Map<String, List<String>> data = CSVManager.getDataFromFileAsMap("non_existent_file.csv");

        assertNull(data, "El resultado debería ser nulo si el archivo no existe.");
    }
}