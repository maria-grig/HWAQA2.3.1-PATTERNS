package ru.netology.carddelivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class  DeliveryTest {

    private final DataGenerator.UserInfo validUser = DataGenerator.Registration.generateUser("ru");
    private final int daysToAddToFirstDate = 4;
    private final String firstDeliveryDate = DataGenerator.generateDate(daysToAddToFirstDate);

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessfullyScheduleAndRescheduleDate() {
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(firstDeliveryDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(".button__text").click();
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("успешно запланирована на " + firstDeliveryDate));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(secondMeetingDate);
        $(".button__text").click();
        $("[data-test-id=replan-notification] .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Перепланировать"));
        $(byText("Перепланировать")).click();
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("успешно запланирована на " + secondMeetingDate));

    }
    @Test
    void shouldSuccessfullyScheduleAndRescheduleDat() {
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(firstDeliveryDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(".button__text").click();
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("успешно запланирована на " + firstDeliveryDate));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(secondMeetingDate);
        $(".button__text").click();
        $("[data-test-id=replan-notification] .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Перепланировать"));
        $(byText("Перепланировать")).click();
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("успешно запланирована на " + secondMeetingDate));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=city] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=name] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=phone] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $(".button__text").click();
        $("[data-test-id='city'] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}