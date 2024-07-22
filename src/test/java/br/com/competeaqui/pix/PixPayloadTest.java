/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.com.competeaqui.pix;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class PixPayloadTest {

    public PixPayloadTest() {
    }

    /**
     * Test of builder method, of class PixPayload.
     */
    @Test
    public void testBuilder() {
        PixPayload pay = PixPayload.builder()
                .pixKey("plataforma@casanovadigital")
                .descricaoPagamento("descricao")
                .titularConta("CAMILAMIGUELBISSIGUINI")
                .cidade("Belohorizonte")
                .valor(50.0)
                .txid("COD1")
                .build();
        System.out.println(pay.getPayload());
    }

}
