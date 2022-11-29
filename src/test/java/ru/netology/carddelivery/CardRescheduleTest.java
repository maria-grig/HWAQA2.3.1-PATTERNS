package ru.netology.carddelivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardRescheduleTest {
    @BeforeEach
    void setup() {

        open("http://localhost:9999/");
    }

    @Test
    @DisplayName("Successfully schedule and reschedule card delivery")
    void shouldSuccessfullyScheduleAndRescheduleDate() throws InterruptedException {
        var validUser = DataGenerator.Registration.generateUser("ru");
        int daysToAddToFirstDelivery = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddToFirstDelivery);
        int daysToAddToSecondDelivery = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddToSecondDelivery);
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE, firstMeetingDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $x("//*[contains(text(),'Запланировать')]").click();
        $(".notification__content").shouldBe(visible)
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE, firstMeetingDate);
        $x("//*[contains(text(),'Запланировать')]").click();
        $(".notification__content").shouldBe(visible)
                .shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate));


    }

}
