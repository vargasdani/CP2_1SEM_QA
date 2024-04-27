package org.estudos.br;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class ConsultaMockTest {
    @Mock
    private HttpURLConnection connectionMock;
    private static final String jsonResponse = "{\"id\":29,\"sigla\":\"BA\",\"nome\":\"Bahia\"," +
            "\"regiao\":{\"id\":2,\"sigla\":\"NE\",\"nome\":\"Nordeste\"}}";
    @BeforeEach
    public void setup() throws IOException {
        MockitoAnnotations.openMocks(this);
        InputStream inputStream = new ByteArrayInputStream(jsonResponse.getBytes(StandardCharsets.UTF_8));
        when(connectionMock.getInputStream()).thenReturn(inputStream);
    }
    @Test
    public void testConsultaEstadoBAComMock() throws IOException {
        try (MockedStatic<ConsultaIBGE> consultaIBGE = Mockito.mockStatic(ConsultaIBGE.class)) {
            consultaIBGE.when(() -> ConsultaIBGE.consultarEstado(anyString()))
                    .thenReturn(jsonResponse);

            String uf = "BA";
            String response = ConsultaIBGE.consultarEstado(uf);
            System.out.println(response);
            assertEquals(jsonResponse, response, "Se(como no exemplo) o estado for a Bahia, deve retornar BA.");


        }
    }
}
