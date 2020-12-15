package Implementacion;

import java.util.ArrayList;
import java.util.HashMap;

import clases.Fondo;
import clases.Item;
//import clases.Jugador;
import clases.JugadorAnimado;
import clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Juego extends Application {
	private GraphicsContext graficos;
	private Group root;
	private Scene escena;
	private Canvas lienzo;
	//private Jugador jugador;
	private JugadorAnimado jugadorAnimado;
	private JugadorAnimado jugadorAnimado2;
	private Fondo fondo;
	public static boolean arriba;
	public static boolean abajo;
	public static boolean derecha;
	public static boolean izquierda;
	public static boolean salto;
	public static boolean aj2;
	public static boolean dj2;
	public static HashMap<String, Image> imagenes;
	private Item item;
	private Item item1;
	private Item item2;
	private Item item3;
	private ArrayList<Tile> tiles;
	private int tilemap [][] = {
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1,1,1}
	};
	
	public static void main(String[] args) {
		launch(args); 
	}

	@Override
	public void start(Stage ventana) throws Exception {
		inicializarcomponentes();
		gestionEventos();
		ventana.setScene(escena);
		ventana.setTitle("Naruto Shippuden Game");
		ventana.show();
		cicloJuego();
	}
	
	public void cicloJuego() {
		long tiempoInicial = System.nanoTime();
		AnimationTimer animationTimer = new AnimationTimer() {
			//https://docs.google.com/spreadsheets/d/1jzNw-2yEGOq5Blegk_7K6LhWNpBnHQ5FujBw6xiu900/edit#gid=0
			//Este metodo se ejecuta aprox 60fps
			@Override
			public void handle(long tiempoActual) {
				double t = (tiempoActual - tiempoInicial)/1000000000.0;
				//System.out.println(t);
				actualizarEstado(t);
				pintar();
			}
			
		};
		
		animationTimer.start();
	}
	
	public void actualizarEstado(double t) {
		//jugador.mover();
		jugadorAnimado.verificarColisionesItem(item);
		jugadorAnimado.verificarColisionesItem(item1);
		jugadorAnimado.verificarColisionesItem(item2);
		jugadorAnimado.verificarColisionesItem(item3);
		jugadorAnimado.calcularFrame(t);
		jugadorAnimado2.calcularFrame(t);
		jugadorAnimado.mover();
		jugadorAnimado2.mover();
		fondo.mover();
	}
	
	public void inicializarcomponentes() {
		imagenes = new HashMap<String, Image>();
		cargarImagenes();
		jugadorAnimado = new JugadorAnimado(20,300,"kakashi",3,0,"descanso");
		jugadorAnimado2 = new JugadorAnimado(20,300,"pain",3,0,"descansopain");
	    fondo = new Fondo(0,0,"background","background2", 5);
	    inicializarTiles();
	    item = new Item(150,300,"item",0,1);
	    item1 = new Item(200,300,"item",0,2);
	    item2 = new Item(220,300,"item",0,1);
	    item3 = new Item(240,300,"item",0,1);
	    root = new Group();
		escena = new Scene(root, 740, 414);
		lienzo = new Canvas(740, 414);
		root.getChildren().add(lienzo);
		graficos = lienzo.getGraphicsContext2D();	
	}
	
	public void inicializarTiles() {
		tiles = new ArrayList<Tile>();
		for(int i=0; i<tilemap.length; i++) {
			for(int j=0; j<tilemap[i].length;j++) {
				if(tilemap[i][j]!=0)
					this.tiles.add(new Tile(tilemap[i][j],j*62,i*62,"tilemap",0,64,64));
			}
		}
	}
	
	public void cargarImagenes() {
		imagenes.put("naruto", new Image("kakashi.png"));
		imagenes.put("background", new Image("escenario.jpg"));
		imagenes.put("background2", new Image("escenario2.jpg"));
		imagenes.put("tilemap", new Image("Tile.png"));
		imagenes.put("kakashi", new Image("SpriteKakashi.png"));
		imagenes.put("pain",new Image("SpritesPain.png"));
		imagenes.put("item",new Image("moneda.png"));
	}
	
	public void pintar() {
		//graficos.fillRect(0, 0, 100, 100);
		fondo.pintar(graficos);
		//jugador.pintar(graficos);
		for(int i=0; i<tiles.size();i++)
			tiles.get(i).pintar(graficos);
		jugadorAnimado.pintar(graficos);
		jugadorAnimado2.pintar(graficos);
		item.pintar(graficos);
		item1.pintar(graficos);
		item2.pintar(graficos);
		item3.pintar(graficos);
		graficos.fillText("Puntos Obtenidos: " + jugadorAnimado.getVida(), 20, 20);
	}

	public void gestionEventos() {
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {

			//El metodo Handle se ejecuta cada vez que se presiona una tecla
			@Override
			public void handle(KeyEvent evento) {
				switch(evento.getCode().toString()){
					case "RIGHT":
						derecha = true;
						jugadorAnimado.setDireccion(1);
						jugadorAnimado.setAnimacionActual("correr");
						break;
					case "LEFT":
						izquierda = true;
						jugadorAnimado.setDireccion(-1);
						jugadorAnimado.setAnimacionActual("correr");
						break;
					case "UP":
						arriba = true;
						break;
					case "DOWN": 
						abajo = true;
						break;
					case "SPACE":
						salto = true;
						jugadorAnimado.setDireccion(1);
						jugadorAnimado.setAnimacionActual("saltar");
						break;
					case "A":
						aj2 = true;
						jugadorAnimado2.setDireccion(-1);
						jugadorAnimado2.setAnimacionActual("CorrerPain");
						break;
					case "D":
						dj2 = true;
						jugadorAnimado2.setDireccion(1);
						jugadorAnimado2.setAnimacionActual("CorrerPain");
				}
			}
		});
		
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {
			//Este metodo se ejecuta cuando se suelta una tecla
			@Override
			public void handle(KeyEvent evento) {
				switch(evento.getCode().toString()){
				case "RIGHT":
					derecha = false;
					jugadorAnimado.setAnimacionActual("descanso");
					break;
				case "LEFT":
					izquierda = false;
					jugadorAnimado.setAnimacionActual("descanso");
					break;
				case "UP":
					arriba = false;
					break;
				case "DOWN": 
					abajo = false;
					break;
				case "SPACE":
					salto = false;
					jugadorAnimado.setAnimacionActual("descanso");
					break;
				case "A":
					aj2 = false;
					jugadorAnimado2.setAnimacionActual("descansopain");
					break;
				case "D":
					dj2 = false;
					jugadorAnimado2.setAnimacionActual("descansopain");
					
			}
				
			}
			
		});
	}
	
	
}

