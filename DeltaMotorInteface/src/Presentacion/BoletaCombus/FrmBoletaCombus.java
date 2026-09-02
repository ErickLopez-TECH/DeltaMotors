package Presentacion.BoletaCombus;

import Datos.objVehiculo;
import Logica.logicaBoletaCombus;
import Logica.logicaVehiculo;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

public class FrmBoletaCombus extends javax.swing.JFrame {

    // Paleta de Colores Moderna
    private final Color COLOR_PRIMARY = new Color(41, 128, 185);     
    private final Color COLOR_SECONDARY = new Color(52, 152, 219);   
    private final Color COLOR_BACKGROUND = new Color(245, 247, 250); 
    private final Color COLOR_PANEL = new Color(255, 255, 255);      
    private final Color COLOR_TEXT = new Color(44, 62, 80);  
    
    private String placa;

    private ArrayList<String> listaOriginalVehiculos;
    
    public FrmBoletaCombus(boolean rol) {
        initComponents();
        this.setLocationRelativeTo(null);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        aplicarEstilosModernos();
        panelKWH.setVisible(false);
        cargarListasOriginales();
        configurarComboAutocompletado(cmbVehiculo, listaOriginalVehiculos);
    }
    
    private void aplicarEstilosModernos() {
        jPanelIngresar.setBackground(COLOR_PANEL);
        jPanelModificar.setBackground(COLOR_PANEL);
        jTabbedPane1.setBackground(COLOR_BACKGROUND);
        
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        jLabel1.setFont(labelFont);
        jLabel2.setFont(labelFont);
        labelCantidadC.setFont(labelFont);
        labelKWH.setFont(labelFont);
        labelCantidadC.setFont(labelFont);
        
        jLabel5.setFont(labelFont);
        jLabel10.setFont(labelFont);
        
        jLabel1.setForeground(COLOR_TEXT);
        jLabel2.setForeground(COLOR_TEXT);
        labelCantidadC.setForeground(COLOR_TEXT);
        
        jLabel5.setForeground(COLOR_TEXT);
        jLabel10.setForeground(COLOR_TEXT);
        
        estilizarBoton(btnGuardar, COLOR_PRIMARY);
        estilizarBoton(btnModificar, COLOR_SECONDARY);
        estilizarBoton(btnEliminar, new Color(231, 76, 60));
        
        if (tblAsignaciones != null) {
            tblAsignaciones.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            tblAsignaciones.setRowHeight(28);
            if (tblAsignaciones.getTableHeader() != null) {
                tblAsignaciones.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
                tblAsignaciones.getTableHeader().setBackground(COLOR_PRIMARY);
                tblAsignaciones.getTableHeader().setForeground(Color.WHITE);
            }
            tblAsignaciones.setSelectionBackground(new Color(189, 195, 199));
            tblAsignaciones.setSelectionForeground(Color.BLACK);
        }
    }

    private void estilizarBoton(javax.swing.JButton boton, Color colorFondo) {
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    public void cargarListasOriginales() {

        listaOriginalVehiculos = new ArrayList<>();
        
        
        logicaVehiculo logicaV = new logicaVehiculo();
        ArrayList<objVehiculo> listaV = logicaV.obtenerListaVehiculos();
        for (objVehiculo v : listaV) {
            listaOriginalVehiculos.add(v.getPlaca() + " - " + v.getMarca() + " " + v.getModelo());
            placa = v.getPlaca();
        }
    }
    
    private void configurarComboAutocompletado(javax.swing.JComboBox<String> comboBox, ArrayList<String> elementosOriginales) {
        if (elementosOriginales == null) {
            return;
        }
        comboBox.setEditable(true);
        comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(elementosOriginales.toArray(new String[0])));
        
        // ==========================================
        // Escuchador para cambair segun el vehiclo sea 
        // ==========================================
        comboBox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Object seleccion = comboBox.getSelectedItem();
                if (seleccion != null) {
                    String itemSeleccionado = seleccion.toString();
                    if (itemSeleccionado.contains(" - ")) {
                        placa = itemSeleccionado.split(" - ")[0];
                        cargarDatosVehiculoSeleccionado(placa);
                    }
                }
            }
        });
        // ==========================================
        
        javax.swing.JTextField editorTxt = (javax.swing.JTextField) comboBox.getEditor().getEditorComponent();
        editorTxt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        
        
        editorTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER || 
                    evt.getKeyCode() == java.awt.event.KeyEvent.VK_UP || 
                    evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
                    return;
                }
                
                String textoBusqueda = editorTxt.getText().toLowerCase();
                javax.swing.DefaultComboBoxModel<String> modeloFiltrado = new javax.swing.DefaultComboBoxModel<>();
                
                for (String item : elementosOriginales) {
                    if (item.toLowerCase().contains(textoBusqueda)) {
                        modeloFiltrado.addElement(item);
                    }
                }
                
                comboBox.setModel(modeloFiltrado);
                editorTxt.setText(textoBusqueda);
                comboBox.hidePopup();
                if (modeloFiltrado.getSize() > 0) {
                    comboBox.showPopup();
                }
            }
        });
    }
    
    private void cargarDatosVehiculoSeleccionado(String placaVehiculo) {
        logicaBoletaCombus logicaB = new logicaBoletaCombus();
    
        int tipo = logicaB.opcionesCombus(placaVehiculo);
        DefaultComboBoxModel<String> modeloCombustible;
        
        switch (tipo) {
            case 1:
                modeloCombustible = new DefaultComboBoxModel<>(new String[] { "Super", "Regular" });
                panelKWH.setVisible(false);
                break;
            case 2:
                modeloCombustible = new DefaultComboBoxModel<>(new String[]{"KWH"});
                panelKWH.setVisible(false);
                break;
            case 3: 
               
                modeloCombustible = new DefaultComboBoxModel<>(new String[]{"Diesel && KWH","Super & KWH", "Regular & KWH"});
                panelKWH.setVisible(true);
                break;
            default:
                throw new AssertionError();
        }
    
    
    
    // Asignas el nuevo modelo al ComboBox de combustible
    cmbMantenimiento.setModel(modeloCombustible);
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelIngresar = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelCantidadC = new javax.swing.JLabel();
        txtCantidadC = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        cmbVehiculo = new javax.swing.JComboBox<>();
        cmbMantenimiento = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtKmUltimo = new javax.swing.JTextField();
        panelKWH = new javax.swing.JPanel();
        labelKWH = new javax.swing.JLabel();
        txtCantidadKWH = new javax.swing.JTextField();
        jPanelModificar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsignaciones = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtBuscador = new javax.swing.JTextField();
        jTabbedPaneRegresar = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión y Asignación de Mantenimientos");

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(41, 128, 185));
        jLabelTitulo.setText("Registro de Nueva Boleta de Combustible");

        jLabel1.setText("Vehículo (Placa):");

        jLabel2.setText("Combustible:");

        labelCantidadC.setText("Cantidad Combustible:");

        txtCantidadC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadCActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar Boleta");

        cmbVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbMantenimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Km Último:");

        labelKWH.setText("Cantidad KWH:");

        txtCantidadKWH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadKWHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelKWHLayout = new javax.swing.GroupLayout(panelKWH);
        panelKWH.setLayout(panelKWHLayout);
        panelKWHLayout.setHorizontalGroup(
            panelKWHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKWHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelKWH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidadKWH, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
        );
        panelKWHLayout.setVerticalGroup(
            panelKWHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKWHLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelKWHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelKWH)
                    .addComponent(txtCantidadKWH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelIngresarLayout = new javax.swing.GroupLayout(jPanelIngresar);
        jPanelIngresar.setLayout(jPanelIngresarLayout);
        jPanelIngresarLayout.setHorizontalGroup(
            jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIngresarLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelTitulo)
                    .addGroup(jPanelIngresarLayout.createSequentialGroup()
                        .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(labelCantidadC)
                            .addComponent(jLabel5))
                        .addGap(30, 30, 30)
                        .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbVehiculo, 0, 280, Short.MAX_VALUE)
                            .addComponent(cmbMantenimiento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCantidadC)
                            .addComponent(txtKmUltimo)))
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelKWH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanelIngresarLayout.setVerticalGroup(
            jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIngresarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabelTitulo)
                .addGap(25, 25, 25)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelIngresarLayout.createSequentialGroup()
                        .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelCantidadC)
                            .addComponent(txtCantidadC, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtKmUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(panelKWH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanelIngresar);

        jScrollPane1.setViewportView(tblAsignaciones);

        btnModificar.setText("Modificar");

        btnEliminar.setText("Eliminar");

        jLabel10.setText("Buscador: ");

        txtBuscador.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanelModificarLayout = new javax.swing.GroupLayout(jPanelModificar);
        jPanelModificar.setLayout(jPanelModificarLayout);
        jPanelModificarLayout.setHorizontalGroup(
            jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModificarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                    .addGroup(jPanelModificarLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanelModificarLayout.setVerticalGroup(
            jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModificarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("tab2", jPanelModificar);
        jTabbedPane1.addTab("tab3", jTabbedPaneRegresar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCantidadCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadCActionPerformed

    private void txtCantidadKWHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadKWHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKWHActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmBoletaCombus(false).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cmbMantenimiento;
    private javax.swing.JComboBox<String> cmbVehiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelIngresar;
    private javax.swing.JPanel jPanelModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPaneRegresar;
    private javax.swing.JLabel labelCantidadC;
    private javax.swing.JLabel labelKWH;
    private javax.swing.JPanel panelKWH;
    private javax.swing.JTable tblAsignaciones;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCantidadC;
    private javax.swing.JTextField txtCantidadKWH;
    private javax.swing.JTextField txtKmUltimo;
    // End of variables declaration//GEN-END:variables
}