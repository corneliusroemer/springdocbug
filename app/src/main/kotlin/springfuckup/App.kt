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

// Typealias to reproduce the bug
typealias FileMappingPayload = Map<String, Any>

@SpringBootApplication
@RestController
@Tag(name = "Hello World", description = "Simple Hello World endpoint")
class App {

    @Operation(description = "Simple endpoint with file mapping")
    @ApiResponse(responseCode = "200", description = "Success")
    @PostMapping("/submit", consumes = ["multipart/form-data"])
    fun submit(
        @Parameter(
            description = "A JSON object for file mapping",
            required = true,
            content = [Content(
                mediaType = "application/json"
            )]
        )
        @RequestPart fileMapping: FileMappingPayload,
    ): String {
        return "Received file mapping with ${fileMapping.size} entries"
    }

}

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
