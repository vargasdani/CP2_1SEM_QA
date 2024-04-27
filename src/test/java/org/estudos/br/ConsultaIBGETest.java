package org.estudos.br;

import org.json.JSONArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsultaIBGETest {
    private static final String ESTADOS_API_URL = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/";


    /////////////////////////////////CP2 DE QA 1SEMESTRE////////////////////////////////////////////////
    private static final String DISTRITOS_API_URL = "https://servicodados.ibge.gov.br/api/v1/localidades/distritos/";

    @Test
    @DisplayName("Teste para consulta de um distrito")
    public void testConsultarDistrito() throws IOException {
        int id = 230010125;

        String resposta = ConsultaIBGE.consultarDistrito(id);

        assert !resposta.isEmpty();

        HttpURLConnection connection = (HttpURLConnection) new URL(DISTRITOS_API_URL + id).openConnection();
        int statusCode = connection.getResponseCode();
        assertEquals(200, statusCode, "O status code da resposta da API deve ser 200 (OK)");
    }

    @Test
    @DisplayName("Teste para consulta de estado inexistente/n cadastrado")
    public void testConsultarEstadoInexistente() throws IOException {
        // Arrange
        String uf = "OI";

        String response = ConsultaIBGE.consultarEstado(uf);
        JSONArray jsonArrayResponse = new JSONArray(response);
        int length = jsonArrayResponse.length();
        assertEquals(0, length, "Deve retornar que o estado nao existe");


    }




}