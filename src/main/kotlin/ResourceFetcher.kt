import java.net.URL

class ResourceFetcher {

    companion object {
        val instance = ResourceFetcher()
    }

    fun get(name: String): URL? {
        return ResourceFetcher::class.java.getResource(name)
    }
}