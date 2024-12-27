package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.apps.windows.view.customization.Color;
import software.ulpgc.minesweeper.architecture.model.Cell;
import software.ulpgc.minesweeper.architecture.view.BoardDisplay;

import java.awt.*;
import java.util.Map;

public class SwingPaintUtilities {
    public static void drawCell(Graphics g, BoardDisplay.PaintOrder p, int size, Map<Integer, Color> colors) {
        g.setColor(p.color().getColor());
        g.fillRect(p.position().x(), p.position().y(), size, size);
        g.setColor(new java.awt.Color(0, 128, 0));
        g.drawRect(p.position().x(), p.position().y(), size, size);
        g.setColor(colors.get(p.number()).getColor());
        if (p.number() != null) g.drawString(String.valueOf(p.number()), p.position().x() + size / 2, p.position().y() + size / 2);
    }

    public static void drawFlag(Graphics g, Cell.Position position, int size) {
        Graphics2D g2d = (Graphics2D) g;
        int cellX = position.x(); // Coordenada x de la celda
        int cellY = position.y(); // Coordenada y de la celda

        // Tamaño de la bandera
        int flagWidth = 20;  // Ancho de la tela de la bandera
        int flagHeight = 13; // Altura total de la bandera (incluyendo el mástil)

        // Coordenadas para centrar la bandera dentro de la celda
        int flagX = cellX + (size - flagWidth) / 2; // Centrado horizontalmente
        int flagY = cellY + (size - flagHeight) / 2; // Centrado verticalmente

        // Dibujar el mástil (rectángulo delgado)
        int mastWidth = 3;  // Ancho del mástil
        int mastHeight = 20; // Altura del mástil (igual al flagHeight)
        g2d.setColor(Color.White.getColor());
        g2d.fillRect(flagX, flagY, mastWidth, mastHeight);

        // Dibujar la tela de la bandera (triángulo)
        g2d.setColor(Color.MineCell.getColor());
        int[] xPoints = {flagX + mastWidth, flagX + mastWidth + flagWidth, flagX + mastWidth};
        int[] yPoints = {flagY, flagY + flagHeight / 2, flagY + flagHeight};
        g2d.fillPolygon(xPoints, yPoints, 3);

        // Dibujar la base del mástil (un rectángulo más ancho)
        int baseWidth = mastWidth + 4;
        int baseHeight = 5;
        g2d.setColor(Color.White.getColor());
        g2d.fillRect(flagX - 2, flagY + mastHeight, baseWidth, baseHeight);
    }
}
