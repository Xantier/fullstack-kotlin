package com.packtpub

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.validation.Valid

/**
 * An example Controller displaying annotation based
 * endpoint validation
 */
@RestController
class AnnotationBasedController {

    @PostMapping("example")
    fun test(@Valid @RequestBody project: Mono<ProjectDTO>, errors: BindingResult): Mono<ResponseEntity<ProjectDTO>> {
        return project.map {
            if (errors.hasErrors()) {
                it.fieldErrors = errors.fieldErrors
                    .map { violation ->
                        FieldErrorDTO(violation.field, violation.defaultMessage)
                    }
                ResponseEntity(it, HttpStatus.UNPROCESSABLE_ENTITY)
            }
            ResponseEntity(it, HttpStatus.OK)
        }
    }
}