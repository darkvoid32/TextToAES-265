package sample

import com.sun.deploy.util.Base64Wrapper.encodeToString
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage

// By Tang Yetong to convert text to AES-265 encrypted text easily

class Main : Application() {

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val vBox = VBox(20.0)
        vBox.prefWidth = 500.0
        vBox.prefHeight = 400.0
        vBox.children.add(Label("Text to QR"))

        val textFieldBox = HBox(Label("Text here"))
        val textField = TextField()
        textFieldBox.children.add(textField)
        vBox.children.add(textFieldBox)

        val textFieldBox2 = HBox(Label("Encrypted text here"))
        val textField2 = TextField()
        textField2.prefWidth = 300.0
        textFieldBox2.children.add(textField2)
        vBox.children.add(textFieldBox2)

        val button = Button("Convert to QR")
        button.setOnAction {textField2.text = convertText(textField.text)}
        vBox.children.add(button)

        primaryStage.scene = Scene(vBox)
        primaryStage.show()
    }

    private fun convertText(text: String): String? {
        val key = "e8ffc7e56311679f12b6fc91aa77a5eb"
        val keyBytes: ByteArray
        var cipherData = ByteArray(0)
        try {
            keyBytes = key.toByteArray(charset("UTF-8"))
            val ivBytes = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00)
            cipherData = AES256CipherUtil.encrypt(ivBytes, keyBytes, text.toByteArray())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return encodeToString(cipherData)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}
