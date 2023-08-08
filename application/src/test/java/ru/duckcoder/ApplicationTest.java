package ru.duckcoder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ApplicationTest {
    @Test
    public void applicationTest() {
        String[] args = {};
        Application testApplication = new Application();
        assertThatNoException().isThrownBy(() -> Application.main(args));
        assertThatNoException().isThrownBy(testApplication::testInjection);
    }
}