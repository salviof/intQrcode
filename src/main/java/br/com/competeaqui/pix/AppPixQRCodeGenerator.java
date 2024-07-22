package br.com.competeaqui.pix;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;

/**
 * Gera um QRCode PIX "Copia e Cola" e a devida imagem na pasta atual. Este
 * projeto foi baseado em uma biblioteca PHP disponível em
 * <a href="https://github.com/renatomb/php_qrcode_pix">https://github.com/renatomb/php_qrcode_pix</a>.
 *
 * @see <a href="https://github.com/bacen/pix-api">Documentação oficial do PIX
 * no repositório do Banco Central</a>
 * @author Manoel Campos da Silva Filho
 */
class AppPixQRCodeGenerator {

    public static void main(String[] args) {
        String imagePath = "qrcode.png";

        DadosEnvioPix dadosPix
                = new DadosEnvioPix(
                        "Manoel Campos da Silva Fh", "manoelcampos@gmail.com",
                        new BigDecimal("1.0"), "Palmas", "PIX em Java");

        QRCodePix qrCodePix = new QRCodePix(dadosPix);
        System.out.println(qrCodePix.generate());
        qrCodePix.save(new File(imagePath));
        System.out.println("QRCode:");

        System.out.printf("%nArquivo gerado em " + imagePath);
    }
}
