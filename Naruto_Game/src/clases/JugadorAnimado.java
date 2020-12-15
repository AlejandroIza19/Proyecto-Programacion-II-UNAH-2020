package clases;

//import java.util.ArrayList;
import java.util.HashMap;
import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class JugadorAnimado extends ObjetoJuego {
	private int vida;
	private HashMap<String, Animacion> animaciones;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private String animacionActual;
	private int direccion = 1; 
	
	public JugadorAnimado(int x, int y, String nombreImagen, int velocidad, int vida, String animacionActual) {
		super(x, y, nombreImagen, velocidad);
		this.vida = vida;
		this.animacionActual = animacionActual;
		animaciones = new HashMap<String, Animacion>();
		inicializarAnimaciones();
	}

	public int getVida() {
		return vida;
	}
	
	public void setVida(int vida) {
		this.vida = vida;
	}
	
	public void inicializarAnimaciones() {
		Rectangle coordenadasCorrer[] = {
			new Rectangle(1,96,90,95),
			new Rectangle(90,96,90,95),
			new Rectangle(180,96,90,95),	
			new Rectangle(1,96,90,95),
			new Rectangle(90,96,90,95),
			new Rectangle(180,96,90,95)
		};
		
		Animacion animacionCorrer = new Animacion(0.05,coordenadasCorrer);
		animaciones.put("correr", animacionCorrer);
		
		Rectangle coordenadasDescanso[] = {
			new Rectangle(0,0,87,97)	
		};
		
		Animacion animacionDescanso = new Animacion(0.1, coordenadasDescanso);
		animaciones.put("descanso", animacionDescanso);
		
		Rectangle coordenadasSaltar[] = {
			new Rectangle(1,189,73,266),
			new Rectangle(102,182,157,266),
			new Rectangle(1,189,73,266),
			new Rectangle(102,182,157,266)
		};
		
		Animacion animacionSaltar = new Animacion(0.01, coordenadasSaltar);
		animaciones.put("saltar", animacionSaltar);
		
		//animaciones de pain
		
		Rectangle CorrerPain[] = {
				new Rectangle(8,98,61,60),
				new Rectangle(92,98,60,60),
				new Rectangle(168,98,66,60),
				new Rectangle(8,98,61,60),
				new Rectangle(92,98,60,60),
				new Rectangle(168,98,66,60)
		};
		
		Animacion RunPain = new Animacion(0.05, CorrerPain);
		animaciones.put("CorrerPain", RunPain);
		
		Rectangle paindes[] = {
				new Rectangle(0,0,87,97)
		};
		
		Animacion descansopain = new Animacion(0.1, paindes);
		animaciones.put("descansopain", descansopain);
		
	}
	
	public void calcularFrame(double t) {
		Rectangle coordenadas = animaciones.get(animacionActual).calcularFrameActual(t);
		this.xImagen = (int)coordenadas.getX();
		this.yImagen = (int)coordenadas.getY();
		this.altoImagen = (int)coordenadas.getWidth();
		this.anchoImagen = (int)coordenadas.getHeight();
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(x, y, (direccion*anchoImagen)-10, altoImagen);
	}
	
	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(nombreImagen),xImagen,yImagen,anchoImagen,altoImagen,x + (direccion==-1?anchoImagen:0), y,direccion*anchoImagen,altoImagen);
		/*graficos.setStroke(Color.RED);
		graficos.strokeRect(x, y, anchoImagen-10, altoImagen);*/
	}
	
	@Override
	public void mover() {
		if(x>700)
			x=-80;
		
		if(Juego.derecha)//mover hacia la derecha 
			x+=velocidad;
		
		if(Juego.izquierda)//reduce la velocidad
			x-=velocidad;
		
		if(Juego.aj2) {
			x-=velocidad;
		}
		if(Juego.dj2) {
			x+=velocidad;
		}
		
		/*if(Juego.arriba)
			y-=velocidad;
		
		if(Juego.abajo)
			y+=velocidad;
		
		if(Juego.salto)
			y-=velocidad*1;*/
		
	}

	
	public String getAnimacionActual() {
		return animacionActual;
	}


	public void setAnimacionActual(String animacionActual) {
		this.animacionActual = animacionActual;
	}
	
	public void verificarColisionesItem(Item item) {
		if(!item.isCapturado()  && this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal())) { 
			this.vida += item.getCantidadVidas();
			item.setCapturado(true);
		}
		
		}
	
	public int getDireccion() {
		return direccion;
	}


	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
}
