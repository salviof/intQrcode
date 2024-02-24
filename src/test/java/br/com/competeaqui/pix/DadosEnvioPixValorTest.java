package br.com.competeaqui.pix;

import org.junit.*;

import java.math.BigDecimal;

/**
 * Testes para o campo {@link DadosEnvioPix#valor()}.
 *
 * <p>
 * As constantes com nomes abreviados são irrelevantes para os testes. Cada
 * teste considera apenas o valor inválido passado por meio de uma variável
 * (estas constantes são valores válidos).</p>
 *
 * @author Manoel Campos da Silva Filho
 */
class DadosEnvioPixValorTest {

    /**
     * Nome do Destinatário.
     */
    static final String ND = "Manoel";

    /**
     * Chave PIX do Destinatário.
     */
    static final String CD = "11111111111";

    /**
     * Cidade do Remetente.
     */
    static final String CR = "Palmas";

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    void valorZero() {

//        assertThrows(IllegalArgumentException.class, () -> newInstance(BigDecimal.ZERO));
    }

    @Test
    void valorNegativo() {
        //      assertThrows(IllegalArgumentException.class, () -> newInstance(new BigDecimal(-1)));
    }

    @Test
    void valorStr1Casa() {
        DadosEnvioPix instance = newInstance(new BigDecimal("1.0"));
        Assert.assertEquals("Falha convertendo valor ", "1.00", instance.valorStr());
    }

    @Test
    void valorStr2Casas() {
        DadosEnvioPix instance = newInstance(new BigDecimal("1.00"));
        Assert.assertEquals("Falha convertendo valor com 2 casas ", "1.00", instance.valorStr());
    }

    @Test
    void valorStr3Casas() {
        DadosEnvioPix instance = newInstance(new BigDecimal("1.234"));
        Assert.assertEquals("Falha convertendo valor com 3 casas ", "1.23", instance.valorStr());
    }

    @Test
    void valorStr3CasasZerosNoMeio() {
        DadosEnvioPix instance = newInstance(new BigDecimal("1.001"));
        Assert.assertEquals("Falha convertendo valor com 3 casas e zero ", "1.00", instance.valorStr());
    }

    @Test
    void valorStr3CasasZeroFinal() {
        DadosEnvioPix instance = newInstance(new BigDecimal("1.230"));
        Assert.assertEquals("1.23", instance.valorStr());
    }

    /**
     * Teste de arredondamento pra cima.
     */
    @Test
    void valorStrMaisDe2CasasCeil() {
        DadosEnvioPix instance = newInstance(new BigDecimal("1.229"));
        Assert.assertEquals("1.23", instance.valorStr());
    }

    @Test
    void valorStrMaisDe2CasasZeros() {
        DadosEnvioPix instance = newInstance(new BigDecimal("1.000"));
        Assert.assertEquals("1.00", instance.valorStr());
    }

    private static DadosEnvioPix newInstance(final BigDecimal valor) {
        return new DadosEnvioPix(ND, CD, valor, CR);
    }
}
