package br.com.leonardo.forum.mapper

import br.com.leonardo.forum.dto.NovoTopicoForm
import br.com.leonardo.forum.model.Topico
import br.com.leonardo.forum.service.CursoService
import br.com.leonardo.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper (
    private var cursoService: CursoService,
    private var usuarioService: UsuarioService,
): Mapper<NovoTopicoForm, Topico> {

    override fun map(dto: NovoTopicoForm): Topico {
        val curso = cursoService.buscarPorId(dto.idCurso)
        val usuario = usuarioService.buscarPorId(dto.idAutor)

        if (curso == null) {
            throw IllegalArgumentException("Curso não encontrado com o ID: ${dto.idCurso}")
        }

        if (usuario == null) {
            throw IllegalArgumentException("Usuário não encontrado com o ID: ${dto.idAutor}")
        }

        return Topico(
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            curso = curso,
            autor = usuario
        )
    }
}
