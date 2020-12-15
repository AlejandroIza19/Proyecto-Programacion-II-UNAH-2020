package clases;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;

public class Tile extends ObjetoJuego {
	private int xImagen;
	private int yImagen;
	private int tipoTile;
	public Tile(int tipoTile, int x, int y, String nombreImagen, int velocidad, int ancho, int alto) {
		super(x, y, nombreImagen, velocidad);
		this.alto = alto;
		this.ancho = ancho;
		
		switch(tipoTile) {
			case 1:
				this.xImagen = 0;
				this.yImagen = 0;
				break;
			case 2:
				this.xImagen = 0;
				this.yImagen = 65;
				break;
			case 3: 
				this.xImagen = 0;
				this.yImagen = 130;
				break;
			case 4:
				this.xImagen = 0;
				this.yImagen = 195;
				break;
			case 5: 
				this.xImagen = 0;
				this.yImagen = 370;
				break;
			case 6: 
				this.xImagen = 0;
				this.yImagen = 435;
				break;
		}
	}
	
	
	
	public int getxImagen() {
		return xImagen;
	}
	public void setxImagen(int xImagen) {
		this.xImagen = xImagen;
	}
	public int getyImagen() {
		return yImagen;
	}
	public void setyImagen(int yImagen) {
		this.yImagen = yImagen;
	}
	
	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(nombreImagen), xImagen, yImagen, ancho,alto,x,y,ancho,alto);
		
	}
	
	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
