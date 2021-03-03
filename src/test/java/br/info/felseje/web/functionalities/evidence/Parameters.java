package br.info.felseje.web.functionalities.evidence;

import java.util.Map;
import br.info.felseje.commons.BaseTest;
import io.cucumber.datatable.DataTable;

public class Parameters extends BaseTest {

    public void estouExecutandoOTeste(DataTable parameters) {
        for (Map<Object, Object> map : parameters.asMaps(String.class, String.class)) {
            evidence.setSystemName((String) map.get("Sistema"));
            evidence.setSystemVersion((String) map.get("Versão"));
            evidence.setTestId((String) map.get("Número CT"));
            evidence.setTestName((String) map.get("Nome CT"));
            evidence.setTesterName((String) map.get("Executor"));
            evidence.setTestCycle((String) map.get("Sprint"));
        }
    }
}
