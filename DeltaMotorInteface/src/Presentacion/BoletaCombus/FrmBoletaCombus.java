package Presentacion.BoletaCombus;

import Datos.objVehiculo;
import Datos.objBoletaCombus;
import Logica.logicaBoletaCombus;
import Logica.logicaVehiculo;
import Presentacion.MenusPrincipales.FrmMenuAdmin;
import Presentacion.MenusPrincipales.FrmMenuOperador;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class FrmBoletaCombus extends javax.swing.JFrame {

    
    // Paleta de Colores Moderna
    private final Color COLOR_PRIMARY = new Color(41, 128, 185);     
    private final Color COLOR_SECONDARY = new Color(52, 152, 219);   
    private final Color COLOR_BACKGROUND = new Color(245, 247, 250); 
    private final Color COLOR_PANEL = new Color(255, 255, 255);      
    private final Color COLOR_TEXT = new Color(44, 62, 80);  
    
    private boolean rolActual;
    private DefaultTableModel modeloTabla;
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    private String placa;
    

    private ArrayList<String> listaOriginalVehiculos;
    
    public FrmBoletaCombus(boolean rol) {
        
        this.rolActual = rol;
        initComponents();
        this.setLocationRelativeTo(null);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        aplicarEstilosModernos();
        panelKWH.setVisible(false);
        cargarListasOriginales();
        cargarTabla();
        
        
        configurarComboAutocompletado(cmbVehiculo, listaOriginalVehiculos);
        
        txtBuscador.getDocument().addDocumentListener(new DocumentListener(){
            
            @Override
            public void insertUpdate(DocumentEvent e){
                filtrarBuscador();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e){
                filtrarBuscador();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e){
                filtrarBuscador();
            }
        });
        
        
        jTabbedPane1.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                if(jTabbedPane1.getSelectedIndex() == 2){
                    if(rolActual){
                        FrmMenuAdmin panelAdmin = new FrmMenuAdmin(rolActual,null);
                        panelAdmin.setVisible(true);
                    }else{
                        FrmMenuOperador panelOperador = new FrmMenuOperador(rolActual);
                        panelOperador.setVisible(true);
                    }
                    
                    dispose();
                }
            }
            
        });
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
        
        if (tblBoleta != null) {
            tblBoleta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            tblBoleta.setRowHeight(28);
            if (tblBoleta.getTableHeader() != null) {
                tblBoleta.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
                tblBoleta.getTableHeader().setBackground(COLOR_PRIMARY);
                tblBoleta.getTableHeader().setForeground(Color.WHITE);
            }
            tblBoleta.setSelectionBackground(new Color(189, 195, 199));
            tblBoleta.setSelectionForeground(Color.BLACK);
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
    
        //busca que combustible el usa segun el guatdado incial del sistema de agestion Vehiculos
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
    cmbTipoCombus.setModel(modeloCombustible);
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
        cmbTipoCombus = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtKmUltimo = new javax.swing.JTextField();
        panelKWH = new javax.swing.JPanel();
        labelKWH = new javax.swing.JLabel();
        txtCantidadKWH = new javax.swing.JTextField();
        jPanelModificar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBoleta = new javax.swing.JTable();
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
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        cmbVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbTipoCombus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                            .addComponent(cmbTipoCombus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(cmbTipoCombus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jTabbedPane1.addTab("Ingresar", jPanelIngresar);

        jScrollPane1.setViewportView(tblBoleta);

        btnModificar.setText("Modificar");

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

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

        jTabbedPane1.addTab("Consultar/Procesos", jPanelModificar);
        jTabbedPane1.addTab("Regresar", jTabbedPaneRegresar);

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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (!validarCampos()) {
            return;
        }
        
        //en vehiculo seleccionado tiene placa, marca y modleo, se tiene que extarer solo placa
        String vehiculoSeleccionado = cmbVehiculo.getSelectedItem().toString().trim();
        String placa = vehiculoSeleccionado.split(" - ")[0].trim();
        
        String tipocombustible = cmbTipoCombus.getSelectedItem().toString().trim();
        
        double cantidadCombus = Double.parseDouble(txtCantidadC.getText().trim());
        double kmUltimo = Double.parseDouble(txtKmUltimo.getText().trim());
        
        //validacion si el campo de kwh es vacio
        String combusKWH = txtCantidadKWH.getText().trim();
        
        //uno para validar si esta vacio y configuarr en 0
        double combusElectrico;
        if(combusKWH.isEmpty()){
            combusElectrico = 0;
        }else{
            
                if(!esNumero(combusKWH)){
                JOptionPane.showMessageDialog(this, "La cantidad de KWH actual debe ser un valor numérico.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                txtCantidadKWH.requestFocus();
                return;
            }else{
                    combusElectrico = Double.parseDouble(txtCantidadKWH.getText().trim());
                }
        }
        
        //configuarr fecha de hoy
        Date ingreso = new Date();
        
        //actualizar km
        logicaVehiculo logicaV = new logicaVehiculo();
        logicaV.modificarVehiculo(placa, kmUltimo);
        
        //sctus;iza en la spartes relevantess del sistema
        logicaBoletaCombus logicaC = new logicaBoletaCombus();
        logicaC.actualizarKmAsigancion(placa, kmUltimo);
        
        //guardqar la boleta
        boolean exito =logicaC.registrarBoletaCombus(placa, tipocombustible, cantidadCombus, combusElectrico, kmUltimo, ingreso);
        
        if (exito) {
            JOptionPane.showMessageDialog(this, "Boleta registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
            txtKmUltimo.setText("");
            txtCantidadKWH.setText("");
            txtCantidadC.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Error: Sucedio un error inesperado.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        
        int filaSeleccionada = tblBoleta.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una Boleta para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = Integer.parseInt(tblBoleta.getValueAt(filaSeleccionada, 0).toString());
        logicaBoletaCombus logica = new logicaBoletaCombus();
        
        boolean eliminado = logica.eliminarBoletaCombus(id);
        
        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Asignación eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar la asignación.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    //validar campos vacios
    public boolean validarCampos(){
        
        if (txtCantidadC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe de digitar la cantidad de combustible actual.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtCantidadC.requestFocus();
            return false;
        }
        
        if(!esNumero(txtCantidadC.getText().trim())){
            JOptionPane.showMessageDialog(this, "La cantidad de combustible actual debe ser un valor numérico.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtCantidadC.requestFocus();
            return false;
        }
        
        if (txtKmUltimo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe de digitar el kilometraje actual.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtKmUltimo.requestFocus();
            return false;
        }

        if(!esNumero(txtKmUltimo.getText().trim())){
            JOptionPane.showMessageDialog(this, "El kilometraje actual debe ser un valor numérico.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtKmUltimo.requestFocus();
            return false;
        }
        
        return true;
    }
    
    
    public void cargarTabla(){
        logicaBoletaCombus logica = new logicaBoletaCombus();
        ArrayList<objBoletaCombus> lista= logica.obtenerListaBoleta();
        
        String[] columnas = {"ID", "Placa", "Combustible", "Cantidad Combustible", "Cantidad KWH", "Kilometraje", "Registrada"};
        Object[][] datos = new Object[lista.size()][7];
        
        for (int i = 0; i < lista.size(); i++) {
            objBoletaCombus b= lista.get(i);
            
            datos[i][0] = b.getId();
            datos[i][1] = b.getPlacaVehiculo();
            datos[i][2] = b.getCombustible();
            datos[i][3] = b.getCantidadCombustible();
            datos[i][4] = b.getCantidadKWH();
            datos[i][5] = b.getKmActual();
            
            String fechaFmt = "";
            if (b.getFecha()!= null) {
                fechaFmt = formatoFecha.format(b.getFecha());
            }
            datos[i][6] = fechaFmt;
            
        }
        
        modeloTabla = new DefaultTableModel(datos, columnas);
        tblBoleta.setModel(modeloTabla);
        
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmBoletaCombus(false).setVisible(true));
    }
    
    public boolean esNumero(String texto) {
        return texto != null && texto.matches("^\\d+(\\.\\d+)?$");
    }
    
    public void filtrarBuscador(){
        String filtro = txtBuscador.getText().toLowerCase().trim();
        //instanciar logica para poder acceder a sus metodos
        logicaBoletaCombus logica = new logicaBoletaCombus();
        
        //teber la lista
        ArrayList<objBoletaCombus> lista = logica.obtenerListaBoleta();
        
        //lo ocupamos para mostra los datos es el molde
        String[] columnas = {"ID", "Placa", "Combustible", "Cantidad Combustible", "Cantidad KWH", "Kilometraje", "Registrada"};
        DefaultTableModel modeloFiltrado = new DefaultTableModel(new Object[0][7],columnas);
        
        for (objBoletaCombus b: lista) {
            //para las fechas
            String fechaFmt = b.getFecha()!= null ? formatoFecha.format(b.getFecha()) : "";
            
            if(b.getPlacaVehiculo().contains(filtro)){
                Object[] fila = {b.getId(),
                                 b.getPlacaVehiculo(),
                                 b.getCombustible(),
                                 b.getCantidadCombustible(),
                                 b.getCantidadKWH(),
                                 b.getKmActual(),
                                 fechaFmt        
            };
                modeloFiltrado.addRow(fila);   
        }
        }
        tblBoleta.setModel(modeloFiltrado);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cmbTipoCombus;
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
    private javax.swing.JTable tblBoleta;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCantidadC;
    private javax.swing.JTextField txtCantidadKWH;
    private javax.swing.JTextField txtKmUltimo;
    // End of variables declaration//GEN-END:variables
}