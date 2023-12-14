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

    @Test
    void shouldNotPassName() throws InterruptedException {
        Configuration.headless = true;
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Ivan Ivanov");
        $("[data-test-id=phone] input").setValue("+37376720458");
        $("[data-test-id=agreement]").click();
        $("[role=button]").click();
        $(".input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotPassNoName() throws InterruptedException {
        Configuration.headless = true;
        open("http://localhost:9999/");
        $("[data-test-id=phone] input").setValue("+37376720458");
        $("[data-test-id=agreement]").click();
        $("[role=button]").click();
        $(".input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotPassPhone() throws InterruptedException {
        Configuration.headless = true;
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=phone] input").setValue("876720458");
        $("[data-test-id=agreement]").click();
        $("[role=button]").click();
        $(".input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotPassNoPhone() throws InterruptedException {
        Configuration.headless = true;
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=agreement]").click();
        $("[role=button]").click();
        $(".input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotPassCheckbox() {
        Configuration.headless = true;
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=phone] input").setValue("+37376720458");
        $("[role=button]").click();
        $(".input_invalid .checkbox__text").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
