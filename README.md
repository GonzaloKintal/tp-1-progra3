# 2048 — TP Programación 3 (Java Swing)

Implementación del clásico juego 2048 en Java con interfaz gráfica (Swing). Incluye pantalla de bienvenida con ingreso de nombre, tablero jugable con puntaje en tiempo real, pantalla final (ganaste/perdiste), y ranking persistente en archivo.

![2048](images/2048-image.png)

## Características
- Interfaz gráfica en Java Swing.
- Tablero 4x4 con movimientos por flechas del teclado.
- Puntaje acumulado por combinaciones.
- Detección de victoria (llegar a 2048) y derrota (sin movimientos posibles).
- Ranking persistente en `data/ranking.txt` (guarda el mejor puntaje por nombre y lo ordena de mayor a menor).
- Pruebas unitarias del motor de tablero con JUnit 4.

## Controles
- Flecha arriba: mueve hacia arriba.
- Flecha abajo: mueve hacia abajo.
- Flecha izquierda: mueve hacia la izquierda.
- Flecha derecha: mueve hacia la derecha.

Atajos de depuración (en el tablero):
- Tecla 1: forzar victoria.
- Tecla 2: forzar derrota.

## Estructura del proyecto
- `src/interfaz/`
  - `Welcome.java`: pantalla inicial (ingreso de nombre y ranking). Punto de entrada `main`.
  - `Aplicacion.java`: ventana principal del juego (tablero y puntaje).
  - `Final.java`: pantalla final con mensaje y acciones.
  - `Ranking.java`: panel para mostrar el top de puntajes.
- `src/logica/`
  - `Tablero.java`: lógica de movimientos, combinación de celdas y detección de fin del juego.
  - `Juego.java`: fachada del juego, puntaje y reglas de alto nivel.
  - `Archivo.java`: E/S del ranking en archivo de texto.
- `src/utils/`
  - `Config.java`: tamaño del tablero, resolución y paleta de colores.
- `src/tests/`
  - `TableroTest.java`: pruebas de movimientos del tablero (JUnit 4).
- `images/2048-image.png`: imagen usada en la UI.
- `data/ranking.txt`: archivo de ranking (texto plano "nombre puntaje").

## Requisitos
- JDK 8 o superior.
- (Opcional) JUnit 4 para ejecutar las pruebas locales.

## Cómo ejecutar

Opción A — IDE (recomendado)
- Importar como proyecto Java.
- Asegurarse de que el directorio de trabajo sea la raíz del proyecto (para que `data/ranking.txt` se resuelva correctamente).
- Ejecutar `interfaz.Welcome` (método `main`).

Opción B — Línea de comandos (Linux/macOS)

1) Compilar a un directorio de salida:

```
mkdir -p out
javac -d out -cp . $(find src -name "*.java")
```

2) Asegurar recursos en el classpath de ejecución. La imagen se carga con `getResource("/2048-image.png")`, por lo que debe estar en la raíz del classpath. Copiarla a `out/`:

```
cp images/2048-image.png out/
```

3) Ejecutar la app:

```
java -cp out interfaz.Welcome
```

Notas:
- El ranking se guarda/lee desde `data/ranking.txt` relativo al directorio de trabajo. Si ejecutás desde la raíz del repo, no necesitás hacer nada extra.
- Si la imagen no se visualiza, verificá que `2048-image.png` esté en el directorio raíz del classpath al ejecutar (por ejemplo, copiada en `out/`).

## Pruebas (JUnit 4)

Descargar los JAR de JUnit 4.13.2 y Hamcrest 1.3 (por ejemplo, a `lib/`). Luego:

Compilar con dependencias de test:
```
javac -d out -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar $(find src -name "*.java")
```

Ejecutar el test del tablero:
```
java -cp out:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore tests.TableroTest
```

En Windows reemplazar `:` por `;` en el classpath.

## Persistencia del ranking
- Ubicación: `data/ranking.txt`.
- Formato por línea: `NOMBRE PUNTAJE`.
- Al finalizar una partida, si el nombre no existe se agrega una entrada; si existe y el nuevo puntaje es mayor, se actualiza.
- La tabla se muestra ordenada de mayor a menor puntaje.

## Configuración
Editar `utils/Config.java`:
- `TAMAÑO_MATRIZ`: tamaño del tablero (por defecto 4).
- `WIDTH` y `HEIGHT`: tamaño de ventana de juego/interfaz.
- `COLORES`: paleta para las celdas según potencia de 2.

## Estado del proyecto
- Juego funcional con UI, puntaje y ranking.
- Motor probado con JUnit para las operaciones de movimiento.

## Licencia
Uso académico. Ajustar según requiera la cátedra o la institución.
