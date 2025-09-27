package br.com.leonardo.forum.service

import br.com.leonardo.forum.dto.AtualizacaoTopicoForm
import br.com.leonardo.forum.dto.NovoTopicoForm
import br.com.leonardo.forum.dto.TopicoPorCategoriaDto
import br.com.leonardo.forum.dto.TopicoView
import br.com.leonardo.forum.exception.NotFoundException
import br.com.leonardo.forum.mapper.TopicoFormMapper
import br.com.leonardo.forum.mapper.TopicoViewMapper
import br.com.leonardo.forum.repository.TopicoRepository
import jakarta.persistence.EntityManager
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService (
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não encontrado",
) {
    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoView> {
        val topicos = if (nomeCurso == null) {
            repository.findAllByOrderByDataCriacaoDesc(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }

        return topicos.map { topico -> topicoViewMapper.map(topico)}
    }

    fun buscarPorId(id: Long): TopicoView? {
        return repository.findById(id)
            .map { topico -> topicoViewMapper.map(topico) }
            .orElseThrow { NotFoundException(notFoundMessage) }
    }

    fun cadastrar(dto: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(dto)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = repository.findById(form.id)
            .orElseThrow { NotFoundException(notFoundMessage) }

        topico.titulo = form.titulo
        topico.mensagem = form.mensagem

        return topicoViewMapper.map(topico)

    }

    fun excluir(id: Long) {
        repository.deleteById(id)
    }

    fun buscarRelatorio(): List<TopicoPorCategoriaDto> {
        return repository.buscarRelatorio()
    }
}