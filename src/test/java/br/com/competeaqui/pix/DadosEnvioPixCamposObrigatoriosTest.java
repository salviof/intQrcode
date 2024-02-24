package br.com.competeaqui.pix;

import java.math.BigDecimal;

import static br.com.competeaqui.pix.DadosEnvioPixInvalidosTest.BLANK;
import static br.com.competeaqui.pix.DadosEnvioPixInvalidosTest.EMPTY;
import static br.com.competeaqui.pix.DadosEnvioPixValorTest.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes para verificar se os campos obrigatórios da classe
 * {@link DadosEnvioPix} estão sendo verificados.
 *
 * @author Manoel Campos da Silva Filho
 */
class DadosEnvioPixCamposObrigatoriosTest {

    private static final BigDecimal V = BigDecimal.ONE;

    @Test
    void nomeEmpty() {
        boolean falhou = false;
        try {
            new DadosEnvioPix(EMPTY, CD, V, CR);
        } catch (Throwable t) {
            falhou = true;
        }
        if (!falhou) {
            fail("Validação falhou");
        }

    }

    @Test
    void nomeBlank() {
        boolean falhou = false;
        try {
            new DadosEnvioPix(BLANK, CD, V, CR);
        } catch (Throwable t) {
            falhou = true;
        }
        if (!falhou) {
            fail("Validação falhou");
        }

    }

    @Test
    void nomeNull() {
        boolean falhou = false;
        try {
            new DadosEnvioPix(null, CD, V, CR);
        } catch (Throwable t) {
            falhou = true;
        }
        if (!falhou) {
            fail("Validação falhou");
        }

    }

    @Test
    void chaveEmpty() {
        boolean falhou = false;
        try {
            new DadosEnvioPix(ND, EMPTY, V, CR);
        } catch (Throwable t) {
            falhou = true;
        }
        if (!falhou) {
            fail("Validação falhou");
        }

    }

    @Test
    void chaveBlank() {
        boolean falhou = false;
        try {
            new DadosEnvioPix(ND, BLANK, V, CR);
        } catch (Throwable t) {
            falhou = true;
        }
        if (!falhou) {
            fail("Validação falhou");
        }

    }

    @Test
    void chaveNull() {
        boolean falhou = false;
        try {
            new DadosEnvioPix(ND, null, V, CR);
        } catch (Throwable t) {
            falhou = true;
        }
        if (!falhou) {
            fail("Validação falhou");
        }

    }

    @Test
    void cidadeEmpty() {
        boolean falhou = false;
        try {
            new DadosEnvioPix(ND, CD, V, EMPTY);
        } catch (Throwable t) {
            falhou = true;
        }
        if (!falhou) {
            fail("Validação falhou");
        }

    }

    @Test
    void cidadeBlank() {
        boolean falhou = false;
        try {
            new DadosEnvioPix(ND, CD, V, BLANK);
        } catch (Throwable t) {
            falhou = true;
        }
        if (!falhou) {
            fail("Validação falhou");
        }

    }

    @Test
    void cidadeNull() {
        boolean falhou = false;
        try {
            new DadosEnvioPix(ND, CD, V, null);
        } catch (Throwable t) {
            falhou = true;
        }
        if (!falhou) {
            fail("Validação falhou");
        }

    }
}
