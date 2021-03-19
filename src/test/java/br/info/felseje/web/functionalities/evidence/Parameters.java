package br.info.felseje.web.functionalities.evidence;

import java.util.Map;
import br.info.felseje.commons.BaseTest;
import io.cucumber.datatable.DataTable;

public class Parameters extends BaseTest {

    public void estouExecutandoOTeste(DataTable parameters) {
        for (Map<Object, Object> map : parameters.asMaps(String.class, String.class)) {
            evidence.setTestIdNumber((String) map.get("Teste ID"));
            evidence.setTestName((String) map.get("Teste"));
            evidence.setTesterName((String) map.get("Executor"));
            evidence.setTestCycle((String) map.get("Sprint"));
        }
    }
}
