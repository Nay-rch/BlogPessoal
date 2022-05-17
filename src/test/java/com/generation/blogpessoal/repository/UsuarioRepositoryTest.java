package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.generation.blogpessoal.model.Usuario;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class UsuarioRepositoryTest {
	    
		@Autowired
		private UsuarioRepository usuarioRepository;
		
		@BeforeAll
		void start(){

			usuarioRepository.deleteAll();
			
			usuarioRepository.save(new Usuario(0L, "Daniel Santos", "https://i.imgur.com/h4t8loa.jpg", "daniel@email.com.br", "13465278"));
			
			usuarioRepository.save(new Usuario(0L, "Vivian Ribeiro", "https://i.imgur.com/NtyGneo.jpg", "vivian@email.com.br", "13465278"));
			
			usuarioRepository.save(new Usuario(0L, "Lucas Silva", "https://i.imgur.com/5M2p5Wb.jpg", "lucas@email.com.br", "13465278"));

	        usuarioRepository.save(new Usuario(0L, "Pedro Gomes", "https://i.imgur.com/FETvs2O.jpg", "pedro@email.com.br", "13465278"));

		}

		@Test
		@DisplayName("Retorna 1 usuario")
		public void deveRetornarUmUsuario() {

			Optional<Usuario> usuario = usuarioRepository.findByUsuario("pedro@email.com.br");
			assertTrue(usuario.get().getUsuario().equals("pedro@email.com.br"));
		}

		@Test
		@DisplayName("Retorna 3 usuarios")
		public void deveRetornarTresUsuarios() {

			List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
			assertEquals(3, listaDeUsuarios.size());
			assertTrue(listaDeUsuarios.get(0).getNome().equals("Daniel Santos"));
			assertTrue(listaDeUsuarios.get(1).getNome().equals("Vivian Ribeiro"));
			assertTrue(listaDeUsuarios.get(2).getNome().equals("Lucas Silva"));
}
}
