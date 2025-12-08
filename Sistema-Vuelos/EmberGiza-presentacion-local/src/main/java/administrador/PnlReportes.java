package administrador;

import styles.ContainerPanel;
import styles.CustomButton;
import styles.CustomLabel;
import styles.Style;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PnlReportes extends JPanel {

    Style style = new Style();
    PnlMenuAdmin pnlMenuAdmin;

    int logoX = 30;
    int logoY = 30;

    final int ENCABEZADO_H = 80;
    final int BOTONES_H = 80;
    final int ALTURA_TABLA_FIX = 500;

    String rutaProyecto = "";
    String rutaLogo = rutaProyecto + "logo.png";
    String rutaReportes = "reportes";

    ContainerPanel encabezado = new ContainerPanel(style.frameX, ENCABEZADO_H, style.beigeBase, false);
    ContainerPanel reportesPanel;
    ContainerPanel botones = new ContainerPanel(style.frameX, BOTONES_H, style.beigeBase, false);
    ContainerPanel todo = new ContainerPanel(style.frameX, style.frameY, style.beigeBase, false);

    JLabel logo;

    CustomButton btnVolver = new CustomButton("Volver");
    CustomButton btnGenerarReporte = new CustomButton("Generar reporte");

    JTable tablaReportes;
    JScrollPane scroll;
    DefaultTableModel modeloTabla;

    public PnlReportes(PnlMenuAdmin pnlMenuAdmin) {

        this.pnlMenuAdmin = pnlMenuAdmin;
        setOpaque(false);
        setLayout(new BorderLayout());

        todo.setOpaque(false);
        todo.setLayout(new BoxLayout(todo, BoxLayout.Y_AXIS));

        // ========== ENCABEZADO ======================================
        encabezado.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        encabezado.setOpaque(false);
        encabezado.setPreferredSize(new Dimension(style.frameX, ENCABEZADO_H));
        encabezado.setMaximumSize(new Dimension(style.frameX, ENCABEZADO_H));

        ImageIcon icon = new ImageIcon(rutaLogo);
        Image img = icon.getImage().getScaledInstance(logoX, logoY, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        logo = new JLabel(icon);
        logo.setPreferredSize(new Dimension(logoX, logoY));

        encabezado.add(logo);
        encabezado.add(new CustomLabel("Reportes", 36));

        todo.add(encabezado);

        // ========== TABLA ============================================
        reportesPanel = new ContainerPanel(style.frameX, ALTURA_TABLA_FIX, style.beigeBase, false);
        reportesPanel.setLayout(new BorderLayout());
        reportesPanel.setOpaque(false);
        reportesPanel.setPreferredSize(new Dimension(style.frameX, ALTURA_TABLA_FIX));
        reportesPanel.setMaximumSize(new Dimension(style.frameX, ALTURA_TABLA_FIX));

        // Inicializamos modelo y tabla ANTES de cargar reportes
        modeloTabla = new DefaultTableModel(new String[]{"Nombre del reporte"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaReportes = new JTable(modeloTabla);
        tablaReportes.setOpaque(false);
        tablaReportes.setShowGrid(false);
        tablaReportes.setBorder(null);
        tablaReportes.setFillsViewportHeight(true);

        scroll = new JScrollPane(tablaReportes);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setPreferredSize(new Dimension(style.frameX, ALTURA_TABLA_FIX - 10));
        scroll.setMaximumSize(new Dimension(style.frameX, ALTURA_TABLA_FIX - 10));

        reportesPanel.add(scroll, BorderLayout.CENTER);
        todo.add(reportesPanel);

        // Cargamos los reportes existentes
        cargarReportes();

        // ========== BOTONES ==========================================
        botones.setOpaque(false);
        botones.setLayout(new BorderLayout());

        JPanel leftBtns = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        leftBtns.setOpaque(false);
        leftBtns.add(btnVolver);

        JPanel rightBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        rightBtns.setOpaque(false);
        rightBtns.add(btnGenerarReporte);

        botones.add(leftBtns, BorderLayout.WEST);
        botones.add(rightBtns, BorderLayout.EAST);

        botones.setPreferredSize(new Dimension(style.frameX, BOTONES_H));
        botones.setMaximumSize(new Dimension(style.frameX, BOTONES_H));

        todo.add(botones);

        // ========== EVENTOS ===========================================
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                volver();
            }
        });

        btnGenerarReporte.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                generarReporte();
            }
        });

        tablaReportes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaReportes.getSelectedRow();
                if (fila >= 0) {
                    abrirArchivo((String) modeloTabla.getValueAt(fila, 0));
                }
            }
        });

        add(todo, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void cargarReportes() {
        modeloTabla.setRowCount(0);
        File carpeta = new File(rutaReportes);
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles((dir, name) -> name.endsWith(".txt"));
            if (archivos != null) {
                for (File archivo : archivos) {
                    modeloTabla.addRow(new Object[]{archivo.getName()});
                }
            }
        }
    }

    public void refreshTabla() {
        scroll.setPreferredSize(new Dimension(style.frameX, ALTURA_TABLA_FIX - 10));
        scroll.setMaximumSize(new Dimension(style.frameX, ALTURA_TABLA_FIX - 10));
        revalidate();
        repaint();
    }

    public void generarReporte() {
        try {
            File carpeta = new File(rutaReportes);
            if (!carpeta.exists()) carpeta.mkdir();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            String fechaHora = LocalDateTime.now().format(formatter);
            File archivo = new File(carpeta, "reporte-" + fechaHora + ".txt");

            FileWriter writer = new FileWriter(archivo);
            writer.write("Ejemplo de reporte " + fechaHora);
            writer.close();

            cargarReportes();
            refreshTabla();
            JOptionPane.showMessageDialog(this, "Reporte generado correctamente");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al generar el reporte");
        }
    }

    private void abrirArchivo(String nombreArchivo) {
        try {
            File archivo = new File(rutaReportes, nombreArchivo);
            if (archivo.exists()) {
                Desktop.getDesktop().open(archivo);
            } else {
                JOptionPane.showMessageDialog(this, "El archivo no existe.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "No se pudo abrir el archivo.");
        }
    }

    public void volver() {
        pnlMenuAdmin.remove(this);
        pnlMenuAdmin.mostrarComponentes();
    }
}