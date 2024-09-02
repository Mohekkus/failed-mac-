import java.io.File
import java.net.URI
import kotlin.io.path.createTempDirectory

class TemporaryCreator {

    lateinit var dir: File
    lateinit var file: File

    companion object {
        val instance = TemporaryCreator()
    }

    private fun getFileDirectoryInstall(): File =
        File(createDirectoryInstall()).also {
            dir = it
        }

    private fun createDirectoryInstall(): URI =
        createTempDirectory("inst").toUri()

    fun createFileTemp(): File =
        File.createTempFile("inst", ".tar.gz", getFileDirectoryInstall())
            .also {
                file = it
            }

}