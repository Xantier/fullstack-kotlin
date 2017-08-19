package com.packtpub

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono


class ViewHandler(private val helloSayer: HelloSayer) {

    private val links = mapOf(
        "Kotlin" to "https://github.com/JetBrains/kotlin",
        "Spring" to "https://github.com/spring-projects/spring-framework",
        "React" to "https://github.com/facebook/react",
        "Full Stack Development" to "https://github.com/Xantier/fullstack-kotlin"
    )

    fun hello(req: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().contentType(MediaType.TEXT_HTML)
            .body(BodyInserters.fromObject(
                createHTML(true).html {
                    head {
                        title = "Full Stack Kotlin"
                        styleLink("static/css/style.css")
                    }
                    body {
                        h4 {
                            +helloSayer.sayHello(req.queryParam("name").orElse("User"))
                        }
                        p {
                            +"Welcome to full stack Kotlin"
                        }
                        p {
                            +"Our Resouces: "
                            ul {
                                links.map { (name, url) ->
                                    li {
                                        a(url) {
                                            target = ATarget.blank
                                            +name
                                        }
                                    }
                                }
                            }
                        }
                    }

                }

            ))
}