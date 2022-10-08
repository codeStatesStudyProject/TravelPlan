package travelplanrepo.global;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.net.BindException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ExceptionTest {
    static void func() throws Exception {
        throw new Exception("Exception");
    }

    @Test
    void testExceptionAll() {
        assertThrows(Exception.class, () -> {
            ExceptionTest.func();
        });
    }
}
