package br.com.leonardo.forum.service

import br.com.leonardo.forum.model.Curso
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class CursoService (final var cursos: List<Curso> = ArrayList()) {
    init {
        val curso = Curso(
            id =  1,
            nome = "Kotlin",
            category = "Backend"
        )

        cursos = Arrays.asList(curso)
    }

    fun buscarPorId(id: Long): Curso? {
        return cursos.find { curso -> curso.id == id}
    }
}