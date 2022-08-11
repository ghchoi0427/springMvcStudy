package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage() {
        String result = ms.getMessage("hello", null, null);
        System.out.println(ms.getMessage("hello", null, Locale.KOREA));
        System.out.println(ms.getMessage("hello", null, null));
        System.out.println(ms.getMessage("hello.name", null, null));
        System.out.println(ms.getMessage("hello", null, Locale.US));

        assertThat(result).isEqualTo("안녕");

    }

    @Test
    void notFoundMessage() {
        assertThatThrownBy(() -> ms.getMessage(("no_code"), null, null)).isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultmessage() {
        String result = ms.getMessage("no_code", null, "기본메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    void argumentMessage() {
        String result = ms.getMessage("hello.name", new Object[]{"Spring"}, "기본메시지", Locale.ENGLISH);
        assertThat(result).isEqualTo("hello Spring");
    }

}
