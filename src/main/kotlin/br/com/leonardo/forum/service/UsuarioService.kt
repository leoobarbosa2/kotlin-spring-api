package br.com.leonardo.forum.service

import br.com.leonardo.forum.exception.NotFoundException
import br.com.leonardo.forum.model.Usuario
import br.com.leonardo.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService (
    private val repository: UsuarioRepository,
) {

    fun buscarPorId(id: Long): Usuario? {
        return repository.findById(id)
            .orElseThrow { NotFoundException("Usuário não encontrado") }
    }

}
