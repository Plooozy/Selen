package ru.netology.FormTest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormTest {
    @Test
    void shouldTest() {
        Configuration.headless = true;
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Никита Курлат");
        $("[data-test-id=phone] input").setValue("+37376720458");
        $("[data-test-id=agreement]").click();
        $("[role=button]").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
