import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import java.io.File
import java.util.zip.GZIPInputStream

class TarExtractor {
    companion object {
        val instance = TarExtractor()
    }

    fun extract(file: File, outputDir: File): Boolean {
        GZIPInputStream(file.inputStream()).use { gzipInput ->
            TarArchiveInputStream(gzipInput).use { tarInput ->
                var entry =  tarInput.nextEntry
                while (entry != null) {
                    val outputFile = File(outputDir, entry.name)
                    if (entry.isDirectory) {
                        outputFile.mkdirs()
                    } else {
                        outputFile.outputStream().use { output ->
                            tarInput.copyTo(output)
                        }
                    }
                    entry = tarInput.nextEntry
                }

                return true
            }
        }
    }
}