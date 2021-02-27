package br.com.runTest;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * @author Feliphe Jesus
 * @version 2.0.0_2021
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "br.com.projeto.configuration.listeners.TestCaseListener"
        },
        features = "./src/test/resources/features/web",
        glue = {
                "br.com.projeto.web.steps",
                "br.com.projeto.configuration",
                "br.com.projeto.commons",
                "br.com.projeto.bean"
        },
        tags = "@CT-001")
public class RunTest {
}
