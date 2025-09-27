package br.com.leonardo.forum.repository

import br.com.leonardo.forum.dto.TopicoPorCategoriaDto
import br.com.leonardo.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository: JpaRepository<Topico, Long> {
    fun findByCursoNome(nomeCurso: String, paginacao: Pageable): Page<Topico>

    fun findAllByOrderByDataCriacaoDesc(paginacao: Pageable): Page<Topico>

    @Query("SELECT new br.com.leonardo.forum.dto.TopicoPorCategoriaDto(curso.categoria, count(t)) FROM Topico t JOIN t.curso curso GROUP BY curso.categoria")
    fun buscarRelatorio(): List<TopicoPorCategoriaDto>
}