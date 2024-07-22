package br.com.competeaqui.pix;

/**
 * Fonte = https://youtu.be/eO11iFgrdCA
 */
import java.text.DecimalFormat;

public class PixPayload {

    private static final String ID_PAYLOAD_FORMAT_INDICATOR = "00";
    private static final String ID_POINT_OF_INITIATION_METHOD = "01";
    private static final String ID_MERCHANT_ACCOUNT_INFORMATION = "26";
    private static final String ID_MERCHANT_ACCOUNT_INFORMATION_GUI = "00";
    private static final String ID_MERCHANT_ACCOUNT_INFORMATION_KEY = "01";
    private static final String ID_MERCHANT_ACCOUNT_INFORMATION_DESCRIPTION = "02";
    private static final String ID_MERCHANT_CATEGORY_CODE = "52";
    private static final String ID_TRANSACTION_CURRENCY = "53";
    private static final String ID_TRANSACTION_AMOUNT = "54";
    private static final String ID_COUNTRY_CODE = "58";
    private static final String ID_MERCHANT_NAME = "59";
    private static final String ID_MERCHANT_CITY = "60";
    private static final String ID_ADDITIONAL_DATA_FIELD_TEMPLATE = "62";
    private static final String ID_ADDITIONAL_DATA_FIELD_TEMPLATE_TXID = "05";
    private static final String ID_CRC16 = "63";
    private static final DecimalFormat VALUE = new DecimalFormat("00");
    private static final DecimalFormat PIX_VALOR = new DecimalFormat("#00.00");

    private String pixKey;
    private String descricaoPagamento;
    private String titularConta;
    private String cidade;
    private String txid;
    private String valor;

    public PixPayload(String pixKey, String descricaoPagamento, String titularConta, String cidade, String txid, String valor) {
        this.pixKey = pixKey;
        this.descricaoPagamento = descricaoPagamento;
        this.titularConta = titularConta;
        this.cidade = cidade;
        this.txid = txid;
        this.valor = valor;
    }

    public static PixPayloadBuilder builder() {
        return new PixPayloadBuilder();
    }

    public String getPayload() {
        StringBuilder pay = new StringBuilder(getValue(ID_PAYLOAD_FORMAT_INDICATOR, "01"));
        pay.append(getValue(ID_POINT_OF_INITIATION_METHOD, "12"));
        pay.append(getMerchantAccountInformation());
        pay.append(getValue(ID_MERCHANT_CATEGORY_CODE, "0000"));
        pay.append(getValue(ID_TRANSACTION_CURRENCY, "986"));
        pay.append(getValue(ID_TRANSACTION_AMOUNT, getValor()));
        pay.append(getValue(ID_COUNTRY_CODE, "BR"));
        pay.append(getValue(ID_MERCHANT_NAME, getTitularConta()));
        pay.append(getValue(ID_MERCHANT_CITY, getCidade()));
        pay.append(getAdditionalDatafieldTemplate());
        String p = pay + ID_CRC16 + "04";
        String codigoPixTexto = pay + crc16(p.getBytes());
        System.out.println(codigoPixTexto);
        return codigoPixTexto;
    }

    public static String crc16(final byte[] value) {
        final int polynomial = 0x1021; // 0001 0000 0010 0001 (0, 5, 12)
        int result = 0xFFFF; // initial value
        for (final byte b : value) {
            for (int i = 0; i < 8; i++) {
                final boolean bit = (b >> 7 - i & 1) == 1;
                final boolean c15 = (result >> 15 & 1) == 1;
                result <<= 1;
                if (c15 ^ bit) {
                    result ^= polynomial;
                }
            }
        }
        result &= 0xffff;
        return ID_CRC16 + "04" + esc(Integer.toHexString(result).toUpperCase(), 4, "0");
    }

    public static String esc(String valor, int qtd, String v) {
        StringBuilder valorBuilder = new StringBuilder(valor);
        while (valorBuilder.length() < qtd) {
            valorBuilder.insert(0, v);
        }
        return valorBuilder.toString();
    }

    private String getAdditionalDatafieldTemplate() {
        String txid = getValue(ID_ADDITIONAL_DATA_FIELD_TEMPLATE_TXID, getTxid());
        return getValue(ID_ADDITIONAL_DATA_FIELD_TEMPLATE, txid);

    }

    private String getValue(String id, String valor) {
        String size = VALUE.format(valor.length());
        return id + size + valor;
    }

    private String getMerchantAccountInformation() {
        String gui = getValue(ID_MERCHANT_ACCOUNT_INFORMATION_GUI, "br.gov.bcb.pix");
        String key = getValue(ID_MERCHANT_ACCOUNT_INFORMATION_KEY, getPixKey());
        String desc = getValue(ID_MERCHANT_ACCOUNT_INFORMATION_DESCRIPTION, descricaoPagamento);
        return getValue(ID_MERCHANT_ACCOUNT_INFORMATION, gui + key + desc);
    }

    public String getPixKey() {
        return this.pixKey;
    }

    public String getDescricaoPagamento() {
        return this.descricaoPagamento;
    }

    public String getTitularConta() {
        return this.titularConta;
    }

    public String getCidade() {
        return this.cidade;
    }

    public String getTxid() {
        return this.txid;
    }

    public String getValor() {
        return this.valor;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public void setDescricaoPagamento(String descricaoPagamento) {
        this.descricaoPagamento = descricaoPagamento;
    }

    public void setTitularConta(String titularConta) {
        this.titularConta = titularConta;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public static class PixPayloadBuilder {

        private String pixKey;
        private String descricaoPagamento;
        private String titularConta;
        private String cidade;
        private String txid;
        private String valor;

        PixPayloadBuilder() {
        }

        public PixPayloadBuilder pixKey(String pixKey) {
            this.pixKey = pixKey;
            return this;
        }

        public PixPayloadBuilder descricaoPagamento(String descricaoPagamento) {
            this.descricaoPagamento = descricaoPagamento;
            return this;
        }

        public PixPayloadBuilder titularConta(String titularConta) {
            this.titularConta = titularConta;
            return this;
        }

        public PixPayloadBuilder cidade(String cidade) {
            this.cidade = cidade;
            return this;
        }

        public PixPayloadBuilder txid(String txid) {
            this.txid = txid;
            return this;
        }

        public PixPayloadBuilder valor(double valor) {
            this.valor = PIX_VALOR.format(valor).replace(",", ".");
            return this;
        }

        public PixPayloadBuilder valor(String valor) {
            this.valor = valor;
            return this;
        }

        public PixPayload build() {
            return new PixPayload(pixKey, descricaoPagamento, titularConta, cidade, txid, valor);
        }

    }
}
