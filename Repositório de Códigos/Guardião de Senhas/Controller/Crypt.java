/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @Pessoa da internet
 * Ajudou-me muito a entender
 */
public class Crypt {
    /**
     * A chave secreta
     */
    private SecretKey skey;
    /**
     * A especificação da chave
     */
    private KeySpec ks;
    /**
     * Parâmetros passados para converter uma senha em uma chave
     */
    private PBEParameterSpec ps;
    // Nome do algoritmo usado - veja em PKCS#5, ftp://ftp.rsasecurity.com/pub/pkcs/pkcs-5v2/pkcs5v2-0.pdf
    private final String algorithm = "PBEWithMD5AndDES";
    private BASE64Encoder enc = new BASE64Encoder();
    private BASE64Decoder dec = new BASE64Decoder();

    private void convertPasswordToKey(final String password) {
        try {
            // Obtendo uma instância da fábrica de chaves secretas.
            // O algoritmo dessa fábrica, chamado PBEWithMD5AndDES,
            // transforma uma senha (conjunto de caracteres digitáveis)
            // em uma chave (bits aleatórios que devem ser mantidos em sigilo).
            // Essa chave é gerada segundo um algoritmo especificado em:
            // PKCS#5 - disponível em: ftp://ftp.rsasecurity.com/pub/pkcs/pkcs-5v2/pkcs5v2-0.pdf
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
            // Um dos parâmetros necessários para gerar a senha a partir da chave é um 
            // "salt" (que neste caso especifiquei como uma série de bytes que
            // são a representação decimal dos primeiros dígitos de pi) e
            // um "iteration count".
            ps = new PBEParameterSpec(new byte[]{3, 1, 4, 1, 5, 9, 2, 6}, 20);
            // Aqui temos a senha...
            ks = new PBEKeySpec(password.toCharArray());
            // E aqui temos o resultado final, que é a chave secreta.
            skey = skf.generateSecret(ks);
        } catch (java.security.NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (java.security.spec.InvalidKeySpecException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Cifra um texto com a senha passada.
     */
    public final String encrypt(final String password, final String text)
            throws
            BadPaddingException,
            NoSuchPaddingException,
            IllegalBlockSizeException,
            InvalidKeyException,
            NoSuchAlgorithmException,
            InvalidAlgorithmParameterException {
        // Converte a senha em uma chave
        convertPasswordToKey(password);
        // Obtém o algoritmo de criptografia (no caso DES)
        final Cipher cipher = Cipher.getInstance(algorithm);
        // Inicializa o algoritmo com a chave
        cipher.init(Cipher.ENCRYPT_MODE, skey, ps);
        // Efetua a criptografia, usando "doFinal", e converte para Base-64
        return enc.encode(cipher.doFinal(text.getBytes()));
    }

    /**
     * Decifra um texto com a senha passada
     */
    public final String decrypt(final String password, final String text)
            throws
            BadPaddingException,
            NoSuchPaddingException,
            IllegalBlockSizeException,
            InvalidKeyException,
            NoSuchAlgorithmException,
            InvalidAlgorithmParameterException {
        // Converte a senha em uma chave
        convertPasswordToKey(password);
        // Obtém o algoritmo de criptografia (no caso DES)
        final Cipher cipher = Cipher.getInstance(algorithm);
        // Inicializa o algoritmo com a chave
        cipher.init(Cipher.DECRYPT_MODE, skey, ps);
        String ret = null;
        try {
            // Converte de Base-64 para bytes, e então efetua a decifração ("descriptografia").
            ret = new String(cipher.doFinal(dec.decodeBuffer(text)));
        } catch (Exception ex) {
        }
        return ret;
    }

}
