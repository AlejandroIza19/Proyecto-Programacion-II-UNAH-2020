package clases;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.image.Image;

public class Jugador extends ObjetoJuego {
	private int vida;

	public Jugador(int x, int y, String nombreImagen, int velocidad, int vida) {
		super(x, y, nombreImagen, velocidad);
		this.vida = vida;
	}


	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
	
	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(nombreImagen), x, y);
	}
	
	@Override
	public void mover() {
		if(x>700)
			x=-80;
		
		if(Juego.derecha)//mover hacia la derecha 
			x+=velocidad;
		
		if(Juego.izquierda)//reduce la velocidad
			x-=velocidad;
		
		if(Juego.arriba)
			y-=velocidad;
		
		if(Juego.abajo)
			y+=velocidad;
		
		if(Juego.salto)
			y-=velocidad*4;
	}
}
