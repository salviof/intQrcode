package br.com.competeaqui.pix;

import java.math.BigDecimal;

import static br.com.competeaqui.pix.DadosEnvioPixValorTest.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes de validação de parâmetros dos construtores de {@link DadosEnvioPix}.
 *
 * <p>
 * As constantes com nomes abreviados são irrelevantes para os testes. Cada
 * teste considera apenas o valor inválido passado por meio de uma variável
 * (estas constantes são valores válidos).</p>
 *
 * @author Manoel Campos da Silva Filho
 */
class DadosEnvioPixInvalidosTest {

    static final String EMPTY = "";
    static final String BLANK = "    ";
    private static final BigDecimal V = new BigDecimal(1);

    /**
     * Nome no limite do tamanho máximo.
     */
    @Test
    void nomeDestinatarioNoLimite() {
        String nomeInvalido = "aaaaaaaaaaaaaaaaaaaaaaaaa";
        try {
            new DadosEnvioPix(nomeInvalido, CD, V, CR);
        } catch (Throwable t) {
            fail(t.getMessage());
        }

    }

    @Test
    void nomeDestinatarioMuitoGrande() {
        String nomeInvalido = "aaaaaaaaaaaaaaaaaaaaaaaaaa";
        boolean falhou = false;
        try {
            new DadosEnvioPix(nomeInvalido, CD, V, CR);
        } catch (Throwable t) {
            falhou = true;

        }
        if (!falhou) {
            fail("Aceitou nome com mais de 25 caracteres");
        }

    }

    @Test
    void chaveMuitoGrande() {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < 78; i++) {
            strb.append("a");
        }
        String chaveInvalido = strb.toString();
        boolean falhou = false;
        try {
            new DadosEnvioPix(ND, chaveInvalido, V, CR);
        } catch (Throwable t) {
            falhou = true;

        }
        if (!falhou) {
            fail("Aceitou nome com mais de 25 caracteres");
        }

    }

    /**
     * Valor no limite do tamanho máximo.
     */
    @Test
    void valorNoLimite() {
        BigDecimal valorInvalido = new BigDecimal("1234567890.00"); // 13 caracteres (com o ponto)

        boolean falhou = false;
        try {
            new DadosEnvioPix(ND, CD, valorInvalido, CR);
        } catch (Throwable t) {
            falhou = true;

        }
        if (!falhou) {
            fail("Aceitou nome com mais de 25 caracteres");
        }

    }

    /**
     * Quando o total de caracteres do valor (incluíndo o ponto) é maior do que
     * o suportado.
     */
    @Test
    void valorDoubleMuitoGrande() {
        BigDecimal valorInvalido = new BigDecimal("12345678901.00"); // 14 caracteres (com o ponto)

        boolean falhou = false;
        try {
            new DadosEnvioPix(ND, CD, valorInvalido, CR);
        } catch (Throwable t) {
            falhou = true;

        }
        if (!falhou) {
            fail("Aceitou nome com mais de 25 caracteres");
        }

    }

    /**
     * Quando o total de caracteres do valor (incluíndo o ponto) é maior do que
     * o suportado.
     */
    @Test
    void valorIntMuitoGrande() {
        // 11 caracteres, mas neste caso será incluído .00 ficando com 14 (além do limite)
        BigDecimal valorInvalido = new BigDecimal("12345678901");

        boolean falhou = false;
        try {
            new DadosEnvioPix(ND, CD, valorInvalido, CR);
        } catch (Throwable t) {
            falhou = true;

        }
        if (!falhou) {
            fail("Aceitou nome com mais de 25 caracteres");
        }

    }

    /**
     * Cidade no limite do tamanho máximo.
     */
    @Test
    void cidadeRemetenteNoLimite() {
        String cidadeInvalida = "aaaaaaaaaaaaaaa";
        new DadosEnvioPix(ND, CD, V, cidadeInvalida);
    }

    @Test
    void cidadeRemetenteMuitoGrande() {
        String cidadeInvalida = "aaaaaaaaaaaaaaaa";//16
        new DadosEnvioPix(ND, CD, V, cidadeInvalida);
    }

    @Test
    void descricaoBlankAlteradaPraEmpty() {
        DadosEnvioPix instance = new DadosEnvioPix(ND, CD, V, CR, BLANK);
        assertTrue(instance.getDescricao().isEmpty());
    }

    @Test
    void descricaoNull() {
        boolean falhou = false;
        try {
            new DadosEnvioPix(ND, CD, V, CR, null);
        } catch (Throwable t) {
            falhou = true;

        }
        if (!falhou) {
            fail("Aceitou nome com mais de 25 caracteres");
        }

    }

    @Test
    void descricaoMuitoGrande() {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < 78; i++) {
            strb.append("a");
        }
        String descricaoInvalida = strb.toString();
        boolean falhou = false;
        try {
            new DadosEnvioPix(ND, CD, V, CR, descricaoInvalida);
        } catch (Throwable t) {
            falhou = true;

        }
        if (!falhou) {
            fail("Aceitou descricao com mais de 78 caracteres");
        }

    }
}
