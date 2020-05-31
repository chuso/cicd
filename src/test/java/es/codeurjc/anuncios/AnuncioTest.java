package es.codeurjc.anuncios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AnuncioTest {

	@Test
	public void givenAnuncioThenItIsConvertedToString() {
		
		Anuncio anuncio = new Anuncio("Nombre", "Asunto", "Comentario");
		assertEquals("Anuncio [nombre=Nombre, asunto=Asunto, comentario=Comentario]", anuncio.toString());
	}

}
