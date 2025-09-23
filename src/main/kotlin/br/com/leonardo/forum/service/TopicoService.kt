package br.com.leonardo.forum.service

import br.com.leonardo.forum.dto.AtualizacaoTopicoForm
import br.com.leonardo.forum.dto.NovoTopicoForm
import br.com.leonardo.forum.dto.TopicoView
import br.com.leonardo.forum.exception.NotFoundException
import br.com.leonardo.forum.mapper.TopicoFormMapper
import br.com.leonardo.forum.mapper.TopicoViewMapper
import br.com.leonardo.forum.model.Topico
import org.springframework.stereotype.Service
import kotlin.collections.ArrayList

@Service
class TopicoService (
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não encontrado",
) {
    fun listar(): List<TopicoView> {
        return topicos.map {
            topico -> topicoViewMapper.map(topico)
        }
    }

    fun buscarPorId(id: Long): TopicoView? {
        val topico = topicos.find { it.id == id }
            ?: throw NotFoundException(notFoundMessage)

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(dto)
        topico.id = topicos.size.toLong() + 1
        topicos =  topicos.plus(topico)

        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = topicos.find { topico -> topico.id  == form.id }

        if (topico != null) {
            val topicoAtualizado = Topico(
                id = form.id,
                titulo = form.titulo,
                mensagem = form.mensagem,
                autor = topico.autor,
                curso = topico.curso,
                respostas = topico.respostas,
                status = topico.status,
                dataCriacao = topico.dataCriacao
            )
            topicos = topicos.minus(topico).plus(topicoAtualizado)

            return topicoViewMapper.map(topicoAtualizado)
        } else {
            throw NotFoundException(notFoundMessage)
        }
    }

    fun excluir(id: Long) {
        val topico = topicos.find { topico -> topico.id  == id }

        if (topico != null) {
            topicos = topicos.minus(topico)
        } else {
            throw NotFoundException(notFoundMessage)
        }
    }
}