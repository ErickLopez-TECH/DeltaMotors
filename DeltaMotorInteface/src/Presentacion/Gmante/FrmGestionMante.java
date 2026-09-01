/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion.Gmante;

import Datos.objGestionMante;
import Logica.logicaGestionMante;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Presentacion.MenusPrincipales.FrmMenuAdmin;
import Presentacion.MenusPrincipales.FrmMenuOperador;

/**
 *
 * @author triamus
 */
public class FrmGestionMante extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmGestionMante.class.getName());
    private boolean rolActual;
    private logicaGestionMante logica;

    /**
     * Creates new form FrmGestionMante
     */
    public FrmGestionMante(boolean rol) {
        initComponents();
        this.rolActual = rol;
        logica = new logicaGestionMante();
        metodosCarga();
        aplicarDisenoModerno();

        // Pantalla completa
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

        txtBuscador.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrarMantenimientos();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrarMantenimientos();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrarMantenimientos();
            }
        });
    }

    private void aplicarDisenoModerno() {
        // Paleta de colores corporativa / minimalista elegante (Slate & Deep Indigo)
        java.awt.Color colorFondoPrincipal = new java.awt.Color(248, 250, 252);
        java.awt.Color colorBlanco = new java.awt.Color(255, 255, 255);
        java.awt.Color colorPrimario = new java.awt.Color(79, 70, 229); // Indigo elegante
        java.awt.Color colorPrimarioHover = new java.awt.Color(67, 56, 202);
        java.awt.Color colorPeligro = new java.awt.Color(225, 29, 72); // Rojo elegante para eliminar
        java.awt.Color colorTexto = new java.awt.Color(15, 23, 42); // Slate muy oscuro
        java.awt.Color colorBorde = new java.awt.Color(226, 232, 240);
        java.awt.Color colorBordeFocus = new java.awt.Color(99, 102, 241);

        java.awt.Font fuenteGeneral = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);
        java.awt.Font fuenteNegrita = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14);
        java.awt.Font fuenteTitulo = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18);

        // Contenedores y Paneles
        jPanel1.setBackground(colorFondoPrincipal);
        jPanel2.setBackground(colorBlanco);
        jPanel3.setBackground(colorBlanco);
        jTabbedPane1.setBackground(colorFondoPrincipal);
        jTabbedPane1.setFont(fuenteNegrita);

        // Títulos internos
        if (jLabel7 != null) {
            jLabel7.setFont(fuenteTitulo);
            jLabel7.setForeground(colorTexto);
        }

        // Estilizar campos de texto y combobox de forma unificada
        javax.swing.JComponent[] campos = {txtNombre, txtBuscador, cmbEstado};
        for (javax.swing.JComponent c : campos) {
            if (c != null) {
                c.setFont(fuenteGeneral);
                c.setBackground(colorBlanco);
                c.setForeground(colorTexto);
                c.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(colorBorde, 1),
                    javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)
                ));

                // Efecto visual al enfocar componentes
                c.addFocusListener(new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        c.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                            javax.swing.BorderFactory.createLineBorder(colorBordeFocus, 2),
                            javax.swing.BorderFactory.createEmptyBorder(5, 11, 5, 11)
                        ));
                    }
                    @Override
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        c.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                            javax.swing.BorderFactory.createLineBorder(colorBorde, 1),
                            javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)
                        ));
                    }
                });
            }
        }

        // Estilizar etiquetas de texto
        javax.swing.JLabel[] etiquetas = {jLabel1, jLabel2, jLabel3};
        for (javax.swing.JLabel l : etiquetas) {
            if (l != null) {
                l.setFont(fuenteNegrita);
                l.setForeground(colorTexto);
            }
        }

        // Botón principal de Guardar / Registrar y Modificar (Estilo elegante Indigo)
        javax.swing.JButton[] botonesPrimarios = {btnRegistrar, btnModificar};
        for (javax.swing.JButton b : botonesPrimarios) {
            if (b != null) {
                b.setFont(fuenteNegrita);
                b.setBackground(colorPrimario);
                b.setForeground(colorBlanco);
                b.setFocusPainted(false);
                b.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
                b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                b.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        b.setBackground(colorPrimarioHover);
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        b.setBackground(colorPrimario);
                    }
                });
            }
        }

        // Botón de Eliminar (Estilo de alerta sutil pero profesional)
        if (btnEliminar != null) {
            btnEliminar.setFont(fuenteNegrita);
            btnEliminar.setBackground(colorBlanco);
            btnEliminar.setForeground(colorPeligro);
            btnEliminar.setFocusPainted(false);
            btnEliminar.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(colorPeligro, 1),
                javax.swing.BorderFactory.createEmptyBorder(9, 19, 9, 19)
            ));
            btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

            btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnEliminar.setBackground(new java.awt.Color(254, 242, 242));
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnEliminar.setBackground(colorBlanco);
                }
            });
        }

        // Estilizar Tabla de manera moderna y limpia
        tblMantenimientos.setFont(fuenteGeneral);
        tblMantenimientos.setRowHeight(36);
        tblMantenimientos.getTableHeader().setFont(fuenteNegrita);
        tblMantenimientos.getTableHeader().setBackground(new java.awt.Color(241, 245, 249));
        tblMantenimientos.getTableHeader().setForeground(colorTexto);
        tblMantenimientos.setSelectionBackground(new java.awt.Color(224, 231, 255));
        tblMantenimientos.setSelectionForeground(colorTexto);
        tblMantenimientos.setGridColor(new java.awt.Color(241, 245, 249));
        tblMantenimientos.setShowVerticalLines(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    private void metodosCarga() {
        cargarTabla();
        configurarPlaceholder(txtNombre, "Ingrese Nombre de Mantenimiento");

        // Listener de las pestañas para el botón de Regresar dinámico
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                if (jTabbedPane1.getSelectedIndex() == 2) {
                    if (rolActual) {
                        FrmMenuAdmin panelAdmin = new FrmMenuAdmin(rolActual);
                        panelAdmin.setVisible(true);
                    } else {
                        FrmMenuOperador panelOperador = new FrmMenuOperador(rolActual);
                        panelOperador.setVisible(true);
                    }
                    dispose();
                }
            }
        });
    }

    private void cargarTabla() {
        ArrayList<objGestionMante> lista = logica.obtenerListaMante();
        String[] columnas = {"ID", "Nombre", "Estado"};
        Object[][] datos = new Object[lista.size()][3];

        for (int i = 0; i < lista.size(); i++) {
            objGestionMante m = lista.get(i);
            datos[i][0] = m.getId();
            datos[i][1] = m.getNombre();
            datos[i][2] = m.getEstado();
        }

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
        tblMantenimientos.setModel(modelo);
    }

    private void filtrarMantenimientos() {
        String filtro = txtBuscador.getText().trim();
        ArrayList<objGestionMante> listaFiltrada = logica.filtrarMante(filtro);

        String[] columnas = {"ID", "Nombre", "Estado"};
        Object[][] datos = new Object[listaFiltrada.size()][3];

        for (int i = 0; i < listaFiltrada.size(); i++) {
            objGestionMante m = listaFiltrada.get(i);
            datos[i][0] = m.getId();
            datos[i][1] = m.getNombre();
            datos[i][2] = m.getEstado();
        }

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
        tblMantenimientos.setModel(modelo);
    }

    private void configurarPlaceholder(javax.swing.JTextField textField, String textoPlaceholder) {
        textField.setText(textoPlaceholder);
        textField.setForeground(new java.awt.Color(148, 163, 184));

        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(textoPlaceholder)) {
                    textField.setText("");
                    textField.setForeground(new java.awt.Color(15, 23, 42));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().trim().isEmpty()) {
                    textField.setText(textoPlaceholder);
                    textField.setForeground(new java.awt.Color(148, 163, 184));
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtBuscador = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMantenimientos = new javax.swing.JTable();
        jTabbedPane2 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Mantenimientos - Transportes Los Tres Patitos");

        jLabel7.setText("Registro de Nuevo Mantenimiento");
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel1.setText("Nombre:");

        jLabel2.setText("Estado:");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Activo", "Inactivo" }));

        btnRegistrar.setText("Guardar Mantenimiento");
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addGap(30, 30, 30)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNombre)
                                .addComponent(cmbEstado, 0, 280, Short.MAX_VALUE)))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel7)
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ingresar Mantenimiento", jPanel2);

        jLabel3.setText("Buscar mantenimiento:");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(this::btnModificarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        tblMantenimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tblMantenimientos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Eliminar/Modificar", jPanel3);
        jTabbedPane1.addTab("Regresar", jTabbedPane2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        String nombre = txtNombre.getText().trim();

        if (cmbEstado.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "¡Error! Por favor, seleccione un estado válido.");
            cmbEstado.requestFocus();
            return;
        }

        if (nombre.isEmpty() || nombre.equals("Ingrese Nombre de Mantenimiento")) {
            JOptionPane.showMessageDialog(this, "¡Por favor llene el campo de nombre!", "Información", JOptionPane.ERROR_MESSAGE);
            txtNombre.requestFocus();
            return;
        }

        if (logica.existenciaMante(nombre)) {
            JOptionPane.showMessageDialog(this, "¡Ya existe un mantenimiento registrado con ese nombre!", "Información", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String estado = cmbEstado.getSelectedItem().toString();
        boolean registrado = logica.registrarMante(nombre, estado);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "¡Mantenimiento registrado con éxito!", "Información", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
            txtNombre.setText("Ingrese Nombre de Mantenimiento");
            txtNombre.setForeground(new java.awt.Color(148, 163, 184));
            cmbEstado.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo registrar el mantenimiento.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int filaSeleccionada = tblMantenimientos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un mantenimiento de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = Integer.parseInt(tblMantenimientos.getValueAt(filaSeleccionada, 0).toString());
        String nombre = tblMantenimientos.getValueAt(filaSeleccionada, 1).toString();
        String estado = tblMantenimientos.getValueAt(filaSeleccionada, 2).toString();

        DlgModificarAsigna ventanaMod = new DlgModificarAsigna(this, true, id, nombre, estado);
        ventanaMod.setVisible(true);
        cargarTabla();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = tblMantenimientos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un mantenimiento de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = Integer.parseInt(tblMantenimientos.getValueAt(filaSeleccionada, 0).toString());
        String nombre = tblMantenimientos.getValueAt(filaSeleccionada, 1).toString();

        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea eliminar el mantenimiento: " + nombre + "?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            boolean eliminado = logica.eliminarMante(id);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "¡Mantenimiento eliminado con éxito!", "Información", JOptionPane.INFORMATION_MESSAGE);
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new FrmGestionMante(false).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tblMantenimientos;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}