package br.info.felseje;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * The Runner class. Run the specified annotated tests.
 * @author Feliphe Jesus
 * @version 1.0.0
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "br.info.felseje.config.listeners.TestListener"
        },
        features = "src/test/resources/features",
        glue = {
                "br.info.felseje.config",
                "br.info.felseje.commons",
                "br.info.felseje.web.steps"
        },
        tags = "@CT-002")
public class Runner {
}
