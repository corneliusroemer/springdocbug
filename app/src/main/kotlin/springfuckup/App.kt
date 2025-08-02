package springfuckup

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.*

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

    @Operation(description = "Passing a map works")
    @PostMapping("/map-works", consumes = ["multipart/form-data"])
    fun map(
        @Parameter(
            description = "A JSON object for file mapping",
            required = true,
        )
        @RequestPart fileMapping: Map<String, Any>,
    ): String {
        return "Received file mapping with ${fileMapping.size} entries"
    }

}

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
