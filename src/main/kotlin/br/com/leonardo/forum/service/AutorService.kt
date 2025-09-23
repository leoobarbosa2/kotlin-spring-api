package br.com.leonardo.forum.service

import br.com.leonardo.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService (final private var usuarios: List<Usuario>) {

    init {
        val autor = Usuario(
            id =  1,
            nome = "Kotlin",
            email = "user@email.com"
        )

        usuarios = Arrays.asList(autor)
    }

    fun buscarPorId(id: Long): Usuario? {
        return usuarios.find { usuario -> usuario.id == id }
    }

}
