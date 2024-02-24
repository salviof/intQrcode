package br.com.competeaqui.pix;

import java.math.BigDecimal;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Testes para os construtores de {@link DadosEnvioPix}.
 *
 * @author Manoel Campos da Silva Filho
 */
class DadosEnvioPixConstrutoresTest {

    private static final String NOME_DESTINATARIO = "Manoel";
    private static final String CHAVE_DESTINATARIO = "11111111111";
    private static final BigDecimal VALOR = new BigDecimal(1);
    private static final String CIDADE_REMETENTE = "Palmas";
    private static final String DESCRICAO = "PIX em Java";
    private static DadosEnvioPix instance;

    @Before
    void setUp() {
        instance = new DadosEnvioPix(
                NOME_DESTINATARIO, CHAVE_DESTINATARIO,
                VALOR, CIDADE_REMETENTE, DESCRICAO);
    }

    @Test
    void valor() {
        assertEquals(new BigDecimal(1), instance.getValor());
        assertSame(VALOR, instance.getValor());
    }

    @Test
    void valorStr() {
        assertEquals("1.00", instance.valorStr());
    }

    @Test
    void nomeDestinatario() {
        assertEquals(NOME_DESTINATARIO, instance.getNomeDestinatario());
    }

    @Test
    void chaveDestinatario() {
        assertEquals(CHAVE_DESTINATARIO, instance.getChaveDestinatario());
    }

    @Test
    void cidadeRemetente() {
        assertEquals(CIDADE_REMETENTE, instance.getCidadeRemetente());
    }

    @Test
    void descricao() {
        assertEquals(DESCRICAO, instance.getDescricao());
    }

    @Test
    void descricaoVazia() {
        DadosEnvioPix instance = new DadosEnvioPix(
                NOME_DESTINATARIO, CHAVE_DESTINATARIO,
                VALOR, CIDADE_REMETENTE);
        assertEquals("", instance.getDescricao());
    }
}
