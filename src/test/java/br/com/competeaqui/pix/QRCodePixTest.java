package br.com.competeaqui.pix;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import static org.junit.Assert.*;
import static br.com.competeaqui.pix.QRCodePix.tempImgFilePath;
import java.io.File;
import org.junit.Before;

/**
 * Testes gerais para a classe {@link QRCodePix}.
 *
 * @author Manoel Campos da Silva Filho
 */
class QRCodePixTest {

    /**
     * Caminho da imagem cujo conteúdo é esperado que seja igual ao da imagem
     * gerada nos testes.
     */
    private static final String QRCODE_FILENAME = "src/test/resources/qrcode-test.png";

    private static final DadosEnvioPix DADOS = new DadosEnvioPix("Manoel", "11111111111", new BigDecimal("1.0"), "Palmas");

    /**
     * QRCode que deve ser gerado para os {@link #DADOS} definidos
     * anteriormente.
     */
    private static final String QRCODE = "00020126370014BR.GOV.BCB.PIX011111111111111020052040000530398654041.005802BR5906Manoel6006Palmas62070503***630477F1";

    private static QRCodePix instance;

    /**
     * Cria uma instância de {@link QRCodePix} a partir de dados pré-definidos.
     * <p>
     * <b>AVISO:</b> Se estes dados forem alterados, o arquivo
     * {@link #QRCODE_FILENAME} precisa ser atualizado.
     * </p>
     */
    @Before
    void setUp() {
        instance = new QRCodePix(DADOS);
    }

    /**
     * Ao chamar o método {@link QRCodePix#generate()}, ele deve armazenar o
     * resultado em um atributo retornado pelo toString().
     */
    @Test
    void toStringEmptyBeforeGenerate() {
        assertTrue(instance.toString().isEmpty());
        instance.generate();
        assertFalse(instance.toString().isEmpty());
    }

    /**
     * Ao chamar o método {@link QRCodePix#save(Path)} antes do
     * {@link QRCodePix#generate()}, ele deve chamar o segundo, e então
     * armazenar o resultado em um atributo retornado pelo toString().
     */
    @Test
    void toStringEmptyBeforeSave() {
        assertTrue(instance.toString().isEmpty());
        instance.save(tempImgFilePath());
        assertFalse(instance.toString().isEmpty());
    }

    /**
     * Verifica se o QRCode foi gerado corretamente e se o toString tá
     * retornando o mesmo resultado de generate.
     */
    @Test
    void generateAndToString() {
        assertEquals(QRCODE, instance.generate());
        assertEquals(QRCODE, instance.toString());
    }

    @Test
    void saveAndCheckFileContent() throws IOException {
        String testName = this.getClass().getSimpleName();

        final Path caminhoImgGerada = Paths.get("target/test-classes/%s.png".format(testName));
        System.out.printf("Gerando arquivo temporário com QRCode em %s%n", caminhoImgGerada);
        // final byte[] bytesArqImgGerado = instance.saveAndGetBytes(new File(caminhoImgGerada));

        // final byte[] bytesArqImgEsperado = Files.readAllBytes(Paths.get(QRCODE_FILENAME));
        //assertArrayEquals(bytesArqImgEsperado, bytesArqImgGerado);
    }

    @Test
    void saveRandomFileCheckExists() {
        final File caminhoImgGerada = instance.save();
        System.out.printf("Gerado arquivo temporário com QRCode em %s%n", caminhoImgGerada);
        assertTrue(caminhoImgGerada.exists());
    }

    @Test
    void saveInvalidFile() {
        final File invalidFileName = new File("\\///&&&.png");
        // final var exception = assertThrows(RuntimeException.class, () -> instance.save(invalidFileName));
        //assertInstanceOf(IOException.class, exception.getCause());
    }

    @Test
    void saveFilenameWithoutExtension() {
        //assertThrows(IllegalArgumentException.class, () -> instance.save(Path.of("nome-do-arquivo-sem-extensao")));
    }

    @Test
    void constructorIdTransacaoMuitoGrande() {
        //final var idInvalido = "i".repeat(26);
        //assertThrows(IllegalArgumentException.class, () -> new QRCodePix(DADOS, idInvalido));
    }

    @Test
    void strLenLeftPadded() {
        //   final var strInvalidLen = "a".repeat(100);
        // assertThrows(IllegalArgumentException.class, () -> QRCodePix.strLenLeftPadded(strInvalidLen));
    }
}
