package sample

import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
//import java.security.spec.AlgorithmParameterSpec

import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * Created by Admin on 27/11/2017.
 */

object AES256CipherUtil {

    @Throws(java.io.UnsupportedEncodingException::class, NoSuchAlgorithmException::class, NoSuchPaddingException::class, InvalidKeyException::class, InvalidAlgorithmParameterException::class, IllegalBlockSizeException::class, BadPaddingException::class)
    fun encrypt(ivBytes: ByteArray, keyBytes: ByteArray, textBytes: ByteArray): ByteArray {

        val ivSpec = IvParameterSpec(ivBytes)
        val newKey = SecretKeySpec(keyBytes, "AES")
        val cipher: Cipher? = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher!!.init(Cipher.ENCRYPT_MODE, newKey, ivSpec)
        return cipher.doFinal(textBytes)
    }
/*
    @Throws(java.io.UnsupportedEncodingException::class, NoSuchAlgorithmException::class, NoSuchPaddingException::class, InvalidKeyException::class, InvalidAlgorithmParameterException::class, IllegalBlockSizeException::class, BadPaddingException::class)
    fun decrypt(ivBytes: ByteArray, keyBytes: ByteArray, textBytes: ByteArray): ByteArray {

        val ivSpec = IvParameterSpec(ivBytes)
        val newKey = SecretKeySpec(keyBytes, "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec)
        return cipher.doFinal(textBytes)
    }*/
}