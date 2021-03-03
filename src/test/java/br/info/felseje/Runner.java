package br.info.felseje;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * @author Feliphe Jesus
 * @version 3.0.0
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "br.info.felseje.configuration.listeners.TestCaseListener"
        },
        features = "./src/test/resources/features",
        glue = {
                "br.info.felseje.web.steps",
                "br.info.felseje.configuration",
                "br.info.felseje.commons"
        },
        tags = "@chrome")
public class Runner {
}
