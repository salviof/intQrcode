/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.competeaqui.pix;

import com.github.mattnicee7.pixqrcode.exception.InvalidValueFormatException;
import com.github.mattnicee7.pixqrcode.pixqrcode.PixQRCode;
import com.github.mattnicee7.pixqrcode.pixqrcode.PixQRCodeBuilder;
import java.io.File;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class TestePagamento {

    public void testePagamento() {
        DadosEnvioPix DADOS = new DadosEnvioPix("Salvio furbino ", "salvio@casanovadigital.com.br",
                new BigDecimal("1000.10"), "Belo Horizonte", "Pagamento referente ao teste");
        QRCodePix instance = new QRCodePix(DADOS);

        //  instance.save(new File("/home/salvio/Imagens"));
        String codigo = instance.generate();
        System.out.println(codigo);
//        instance.saveAndGetBytes(new File("/home/salvio/Imagens/qrcode.png"));
    }

    @Test
    public void testePagamento2() {
        final PixQRCode myPixWithoutValue;
        try {
            myPixWithoutValue = new PixQRCodeBuilder()
                    .receiverFullName("CAMILA MIGUEL")
                    .transactionIdentifier("Refrecibo223")
                    .receiverCity("Belo Horizonte")
                    .pixKey("plataforma@casanovadigital.com.br")
                    .withValue(true)
                    .value(1.2)
                    .build();
            System.out.println(myPixWithoutValue.getAsText());
        } catch (InvalidValueFormatException ex) {
            Logger.getLogger(TestePagamento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
