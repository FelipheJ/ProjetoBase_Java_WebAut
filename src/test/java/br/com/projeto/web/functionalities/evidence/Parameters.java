package br.com.projeto.web.functionalities.evidence;

import java.util.Map;
import br.com.projeto.commons.BaseTest;
import io.cucumber.datatable.DataTable;

public class Parameters extends BaseTest {

    public void estouExecutandoOTeste(DataTable parameters) {
        super.initializeEvidence();
        for (Map<Object, Object> map : parameters.asMaps(String.class, String.class)) {
            evidence.setCicloCT((String) map.get("Sprint"));
            evidence.setNumeroCT((String) map.get("Número CT"));
            evidence.setNomeProjeto((String) map.get("Projeto"));
            evidence.setNomeExecutor((String) map.get("Executor"));
            evidence.setNomeCasoDeTeste((String) map.get("Nome CT"));
        }
    }
}
