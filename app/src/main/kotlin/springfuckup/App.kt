package springfuckup

import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.media.*
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.*
import java.util.Dictionary

// Interface to test if this causes issues too
interface PayloadData {
    val name: String
    val value: String
}

// Data class to demonstrate that concrete classes work fine
data class MetaData(
    val name: String,
    val value: String,
    val settings: Map<String, String> = emptyMap()
)

@SpringBootApplication
@RestController
@Tag(name = "Hello World", description = "Simple Hello World endpoint")
class App {
    @Operation(description = "Passing a string works")
    @PostMapping("/string-works", consumes = ["multipart/form-data"])
    fun string(
        @Parameter(
            description = "A JSON object for file mapping",
            required = true,
        )
        @RequestPart fileMapping: String,
    ): String {
        return "Received file mapping with length ${fileMapping.length}"
    }

    @Operation(description = "Passing a map doesn't work")
    @PostMapping("/map-fails", consumes = ["multipart/form-data"])
    fun map(
        @Parameter(
            description = "A JSON object for file mapping",
            required = true,
        )
        @RequestPart fileMapping: Map<String, Any>,
    ): String {
        return "Received file mapping with ${fileMapping} entries"
    }

    @Operation(description = "Passing a data class works fine")
    @PostMapping("/class-works", consumes = ["multipart/form-data"])
    fun dataClass(
        @Parameter(
            description = "Metadata as a concrete class",
            required = true,
        )
        @RequestPart metadata: MetaData,
    ): String {
        return "Received metadata: ${metadata.name} = ${metadata.value}"
    }

    @Operation(description = "Passing an interface works")
    @PostMapping("/interface-test", consumes = ["multipart/form-data"])
    fun interfaceTest(
        @Parameter(
            description = "Payload as an interface",
            required = true,
        )
        @RequestPart payload: PayloadData,
    ): String {
        return "Received payload: ${payload.name} = ${payload.value}"
    }
}

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
