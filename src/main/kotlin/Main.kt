import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.io.File
import java.nio.file.FileSystem

@Composable
@Preview
fun App() {
    var text by remember {
        mutableStateOf(
            ResourceFetcher.instance.get("openvpn-2.6.12.tar.gz")?.file ?: "noope"
        )
    }

    MaterialTheme {
        Button(onClick = {
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }
    }
}

fun main() {
    val temporaryCreator = TemporaryCreator.instance
    val path = ResourceFetcher.instance.get("openvpn-2.6.12.tar.gz")?.file ?: "x"
    val temp = File(path).copyTo(temporaryCreator.createFileTemp(), true, DEFAULT_BUFFER_SIZE)

    if (TarExtractor.instance.extract(temp, temporaryCreator.dir))
        temp.delete()

}

//fun main() = application {
//    Window(
//        onCloseRequest = ::exitApplication
//    ) {
//        App()
//    }
//}
