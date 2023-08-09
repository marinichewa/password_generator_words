package org.example.controler;
import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ConsoleControllerTest {

// отримую доступ к приватному методу і полю
    void validate(Object consoleController, int length) throws Exception {
        try {
            Method method = consoleController.getClass().getDeclaredMethod("validate");
            method.setAccessible(true);
            method.invoke(consoleController);
            Field field = consoleController.getClass().getDeclaredField("length");
            field.setAccessible(true);
            length = (Integer) field.get(consoleController);
            field.set(consoleController, (Integer) length);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.exit(1);
        }
    }

    //тут пробую перехопити повідомлення на консолі, але працюэ тільки більше 4
    @Test
    void runTest() {
        PrintStream originalOut = System.out;
        String consoleOutput = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            ConsoleController consoleController = new ConsoleController();
//тут пробую передати аргументи, але якщо <3 одразу System.exit (1)
            new CommandLine(consoleController).execute("--lth=4");
            consoleController.run();
            capture.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("Minimal length must be 3", consoleOutput);
    }
    //тут вирішила спробувати перехоплювати System.exit (1), але видає помилку, пробувала також передавати аргументи через
    // CommandLine, теж не вдається перехопити
    @Test
    void validateTest() throws Exception {
        ConsoleController consoleController = new ConsoleController();
        int statusCode = SystemLambda.catchSystemExit(() -> {
            validate(consoleController, 4);
        });
        assertEquals(1, statusCode);
    }
}

