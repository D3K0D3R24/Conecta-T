import java.util.Properties
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


object EmailSender {

    private val session: Session

    init {
        val properties = Properties()
        properties["mail.smtp.host"] = "smtp.gmail.com"
        properties["mail.smtp.port"] = "587"
        properties["mail.smtp.auth"] = "true"
        properties["mail.smtp.starttls.enable"] = "true"

        val username = "programacionmovil2002@gmail.com"
        val password = "ueoicugrbhnnrwqz"

        session = Session.getInstance(properties, object : javax.mail.Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })
    }

    // Callback para indicar el estado del envío
    interface EmailStatusCallback {
        fun onSuccess()
        fun onError(error: String)
    }

    /**
     * Envía un correo de texto simple en un hilo separado.
     * @param to Correo del destinatario
     * @param subject Asunto del correo
     * @param body Cuerpo del correo
     * @param callback Callback para el resultado del envío
     */
    fun sendEmailAsync(to: String, subject: String, body: String, callback: EmailStatusCallback) {
        Thread {
            try {
                val message = MimeMessage(session)
                message.setFrom(InternetAddress("noreply@tuapp.com","Soporte de Conectat"))
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to))
                message.subject = subject
                message.setContent(body,"text/html; charset=utf-8")

                Transport.send(message)

                // Llamar al callback de éxito
                callback.onSuccess()

            } catch (e: Exception) {
                e.printStackTrace()
                // Llamar al callback de error con el mensaje de error
                callback.onError(e.message ?: "Error desconocido al enviar el correo")
            }
        }.start()
    }

    /**
     * Envía un correo con HTML y adjunto en un hilo separado.
     * @param to Correo del destinatario
     * @param subject Asunto del correo
     * @param htmlBody Cuerpo del correo en HTML
     * @param attachmentPath Ruta del archivo a adjuntar
     * @param callback Callback para el resultado del envío
     */
    fun sendEmailWithAttachmentAsync(to: String, subject: String, htmlBody: String, attachmentPath: String, callback: EmailStatusCallback) {
        Thread {
            try {
                val message = MimeMessage(session)
                message.setFrom(InternetAddress("tu_correo@gmail.com"))
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to))
                message.subject = subject

                // Crear el cuerpo HTML
                val mimeBodyPart = javax.mail.internet.MimeBodyPart()
                mimeBodyPart.setContent(htmlBody, "text/html")

                // Crear el adjunto
                val attachmentBodyPart = javax.mail.internet.MimeBodyPart()
                val dataSource = javax.activation.FileDataSource(attachmentPath)
                attachmentBodyPart.dataHandler = javax.activation.DataHandler(dataSource)
                attachmentBodyPart.fileName = attachmentPath.substringAfterLast("/")

                // Combinar el cuerpo HTML y el adjunto
                val multipart = javax.mail.internet.MimeMultipart()
                multipart.addBodyPart(mimeBodyPart)
                multipart.addBodyPart(attachmentBodyPart)

                message.setContent(multipart)

                Transport.send(message)

                // Llamar al callback de éxito
                callback.onSuccess()

            } catch (e: Exception) {
                e.printStackTrace()
                // Llamar al callback de error con el mensaje de error
                callback.onError(e.message ?: "Error desconocido al enviar el correo")
            }
        }.start()
    }
}
