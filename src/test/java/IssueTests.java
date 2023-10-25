import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class IssueTests {
    private static final String URL = "https://github.com/";
    private static final String LOCATOR = ".search-input-container";
    private static final String SEARCH = "#query-builder-test";
    private static final String REPOSITORY = "qa-guru/knowledge-base";
    private static final String ISSUES = "Need to change from 'JUnit' to 'Selenide'";

    @BeforeEach
    void enableAllure(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @Test
    @Feature("Поиск в репозитории")
    @Story("Поиск в репозитории qa-guru")
    @Owner("Maxim N")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "knowledge-base", url = "https://github.com/qa-guru/knowledge-base")
    @DisplayName("Проверка наличия Issue в репозитории " + REPOSITORY + " с Listener")
    public void CheckIssueName() {
        open(URL);
        $(LOCATOR).click();
        $(SEARCH).setValue(REPOSITORY).pressEnter();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText(ISSUES)).should(Condition.exist);
    }
    @Test
    @Feature("Поиск в репозитории")
    @Story("Поиск в репозитории qa-guru")
    @Owner("Maxim N")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "knowledge-base", url = "https://github.com/qa-guru/knowledge-base")
    @DisplayName("Проверка Issue в репозитории " + REPOSITORY + " с лямбда-шагами через step")
    public void CheckIssueNameWithLambda() {
        step("Открываем главную страницу", () -> {
            open(URL);
        });
        step("Поиск репозитория " + REPOSITORY, () -> {
            $(LOCATOR).click();
            $(SEARCH).setValue(REPOSITORY).pressEnter();
        });
        step("Открываем репозиторий " + REPOSITORY , () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие на странице Issues: " + ISSUES, () -> {
            $(withText(ISSUES)).should(Condition.exist);
        });
    }
    @Test
    @Feature("Поиск в репозитории")
    @Story("Поиск в репозитории qa-guru")
    @Owner("Maxim N")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "knowledge-base", url = "https://github.com/qa-guru/knowledge-base")
    @DisplayName("Проверка Issue в репозитории " + REPOSITORY +" шагами с аннотацией @Step")
    public void checkIssueNameWithAnnotatedStep() {
        Steps steps = new Steps();

        steps.openMainPage();
        steps.searchRepository(REPOSITORY, LOCATOR, SEARCH);
        steps.openRepository(REPOSITORY);
        steps.openTabIssues();
        steps.checkNameIssues(ISSUES);
    }
}
