package Presentacion.AsignacionMante;

import Logica.logicaAsignacionMante;
import Logica.logicaVehiculo;
import Logica.logicaGestionMante;
import Datos.objAsignacionMante;
import Datos.objVehiculo;
import Datos.objGestionMante;
import Presentacion.MenusPrincipales.FrmMenuAdmin;
import Presentacion.MenusPrincipales.FrmMenuOperador;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Color;
import java.awt.Font;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FrmAsignarMantenimiento extends javax.swing.JFrame {

    private DefaultTableModel modeloTabla;
    private ArrayList<String> listaOriginalVehiculos;
    private ArrayList<String> listaOriginalMantenimientos;
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    private boolean rolActual;
    
    // Paleta de Colores Moderna
    private final Color COLOR_PRIMARY = new Color(41, 128, 185);     // Azul corporativo
    private final Color COLOR_SECONDARY = new Color(52, 152, 219);   // Azul claro
    private final Color COLOR_BACKGROUND = new Color(245, 247, 250); // Gris muy claro
    private final Color COLOR_PANEL = new Color(255, 255, 255);      // Blanco puro
    private final Color COLOR_TEXT = new Color(44, 62, 80);          // Texto oscuro

    public FrmAsignarMantenimiento(boolean rol) {
        initComponents();
        this.rolActual = rol;
        this.setLocationRelativeTo(null);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        aplicarEstilosModernos();
        inicializarFunciones();
        
    }
    
    private void aplicarEstilosModernos() {
        // Aplicar colores de fondo a los paneles principales
        jPanelIngresar.setBackground(COLOR_PANEL);
        jPanelModificar.setBackground(COLOR_PANEL);
        jTabbedPane1.setBackground(COLOR_BACKGROUND);
        
        // Estilo de fuentes para etiquetas principales
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        jLabel1.setFont(labelFont);
        jLabel2.setFont(labelFont);
        jLabel3.setFont(labelFont);
        jLabel4.setFont(labelFont);
        jLabel5.setFont(labelFont);
        jLabel10.setFont(labelFont);
        
        jLabel1.setForeground(COLOR_TEXT);
        jLabel2.setForeground(COLOR_TEXT);
        jLabel3.setForeground(COLOR_TEXT);
        jLabel4.setForeground(COLOR_TEXT);
        jLabel5.setForeground(COLOR_TEXT);
        jLabel10.setForeground(COLOR_TEXT);
        
        // Estilo de Botones
        estilizarBoton(btnGuardarAsignacion, COLOR_PRIMARY);
        estilizarBoton(btnModificar, COLOR_SECONDARY);
        estilizarBoton(btnEliminar, new Color(231, 76, 60)); // Rojo sutil para eliminar
        
        // Estilo de Tabla
        tblAsinsacionesEstilo();
    }

    private void estilizarBoton(javax.swing.JButton boton, Color colorFondo) {
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void tblAsinsacionesEstilo() {
        tblAsignaciones.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblAsignaciones.setRowHeight(28);
        tblAsignaciones.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblAsignaciones.getTableHeader().setBackground(COLOR_PRIMARY);
        tblAsignaciones.getTableHeader().setForeground(Color.WHITE);
        tblAsignaciones.setSelectionBackground(new Color(189, 195, 199));
        tblAsignaciones.setSelectionForeground(Color.BLACK);
    }
    
    public void inicializarFunciones() {
        cargarListasOriginales();
        inicializarTabla();
        alternarColoresTabla();
        
        configurarComboAutocompletado(cmbVehiculo, listaOriginalVehiculos);
        configurarComboAutocompletado(cmbMantenimiento, listaOriginalMantenimientos);
        
        txtBuscador.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrarAsignacion(); }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrarAsignacion(); }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrarAsignacion(); }
        });
        
        jTabbedPane1.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                if(jTabbedPane1.getSelectedIndex() == 2){
                    if(rolActual){
                        FrmMenuAdmin panelAdmin = new FrmMenuAdmin(rolActual);
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

    public void cargarListasOriginales() {
        listaOriginalVehiculos = new ArrayList<>();
        logicaVehiculo logicaV = new logicaVehiculo();
        ArrayList<objVehiculo> listaV = logicaV.obtenerListaVehiculos();
        for (objVehiculo v : listaV) {
            listaOriginalVehiculos.add(v.getPlaca() + " - " + v.getMarca() + " " + v.getModelo());
        }

        listaOriginalMantenimientos = new ArrayList<>();
        logicaGestionMante logicaM = new logicaGestionMante();
        ArrayList<objGestionMante> listaM = logicaM.obtenerListaMante();
        for (objGestionMante m : listaM) {
            listaOriginalMantenimientos.add(m.getId() + " - " + m.getNombre());
        }
    }

    private void configurarComboAutocompletado(javax.swing.JComboBox<String> comboBox, ArrayList<String> elementosOriginales) {
        comboBox.setEditable(true);
        comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(elementosOriginales.toArray(new String[0])));
        
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

    public void inicializarTabla() {
        logicaAsignacionMante logicaAsig = new logicaAsignacionMante();
        ArrayList<objAsignacionMante> lista = logicaAsig.obtenerListaAsignaciones();
        
        String[] columnas = {"ID", "Placa", "Mantenimiento", "Tipo Periodo", "Periodicidad", "Km Último", "Ingreso"};
        Object[][] datos = new Object[lista.size()][7];
        
        for (int i = 0; i < lista.size(); i++) {
            objAsignacionMante a = lista.get(i);
            datos[i][0] = a.getId();
            datos[i][1] = a.getPlacaVehiculo();
            datos[i][2] = a.getNombreMantenimiento();
            datos[i][3] = a.getTipoPeriodo();
            datos[i][4] = a.getNumPeriodicidad();
            datos[i][5] = a.getKmUltimo();
            
            String fechaFmt = "";
            if (a.getIngreso() != null) {
                fechaFmt = formatoFecha.format(a.getIngreso());
            }
            datos[i][6] = fechaFmt;
        }
        
        modeloTabla = new DefaultTableModel(datos, columnas);
        tblAsignaciones.setModel(modeloTabla);
        tblAsinsacionesEstilo();
    }
    
    public void filtrarAsignacion() {
        String filtro = txtBuscador.getText().toLowerCase().trim();
        logicaAsignacionMante logicaAsig = new logicaAsignacionMante();
        ArrayList<objAsignacionMante> lista = logicaAsig.obtenerListaAsignaciones();
        
        String[] columnas = {"ID", "Placa", "Mantenimiento", "Tipo Periodo", "Periodicidad", "Km Último", "Ingreso"};
        DefaultTableModel modeloFiltrado = new DefaultTableModel(new Object[0][7], columnas);
        
        for (objAsignacionMante a : lista) {
            String fechaFmt = a.getIngreso() != null ? formatoFecha.format(a.getIngreso()) : "";
            
            if (String.valueOf(a.getId()).contains(filtro) || 
                a.getPlacaVehiculo().toLowerCase().contains(filtro) || 
                a.getNombreMantenimiento().toLowerCase().contains(filtro) ||
                fechaFmt.contains(filtro)) {
                
                Object[] fila = {
                    a.getId(), a.getPlacaVehiculo(), a.getNombreMantenimiento(), 
                    a.getTipoPeriodo(), a.getNumPeriodicidad(), a.getKmUltimo(), fechaFmt
                };
                modeloFiltrado.addRow(fila);
            }
        }
        tblAsignaciones.setModel(modeloFiltrado);
        tblAsinsacionesEstilo();
    }

    public boolean validarCampos() {
        if (cmbVehiculo.getSelectedItem() == null || cmbVehiculo.getSelectedItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar o digitar un vehículo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            cmbVehiculo.requestFocus();
            return false;
        }
        
        if (cmbMantenimiento.getSelectedItem() == null || cmbMantenimiento.getSelectedItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar o digitar un mantenimiento.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            cmbMantenimiento.requestFocus();
            return false;
        }
        
        if (txtPeriodicidad.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe digitar la periodicidad numérica.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtPeriodicidad.requestFocus();
            return false;
        }
        
        if (!esNumero(txtPeriodicidad.getText().trim())) {
            JOptionPane.showMessageDialog(this, "La periodicidad debe ser un valor numérico.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtPeriodicidad.requestFocus();
            return false;
        }
        
        if (txtKmUltimo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe digitar el último kilometraje.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtKmUltimo.requestFocus();
            return false;
        }
        
        if (!esNumero(txtKmUltimo.getText().trim())) {
            JOptionPane.showMessageDialog(this, "El kilometraje debe ser un valor numérico.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtKmUltimo.requestFocus();
            return false;
        }
        
        return true;
    }

    public boolean esNumero(String texto) {
        return texto != null && texto.matches("^\\d+(\\.\\d+)?$");
    }

    public void alternarColoresTabla() {
        tblAsignaciones.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                    c.setForeground(table.getSelectionForeground());
                } else {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(240, 245, 250));
                    }
                    c.setForeground(COLOR_TEXT);
                }
                return c;
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelIngresar = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbVehiculo = new javax.swing.JComboBox<>();
        cmbMantenimiento = new javax.swing.JComboBox<>();
        cmbTipoPeriodo = new javax.swing.JComboBox<>();
        txtPeriodicidad = new javax.swing.JTextField();
        txtKmUltimo = new javax.swing.JTextField();
        btnGuardarAsignacion = new javax.swing.JButton();
        jPanelModificar = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtBuscador = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsignaciones = new javax.swing.JTable();
        jTabbedPaneRegresar = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión y Asignación de Mantenimientos");

        jTabbedPane1.setFont(new Font("Segoe UI", Font.BOLD, 13));

        jLabelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabelTitulo.setForeground(new java.awt.Color(41, 128, 185));
        jLabelTitulo.setText("Registro de Nueva Asignación de Mantenimiento");

        jLabel1.setText("Vehículo (Placa):");

        jLabel2.setText("Mantenimiento:");

        jLabel4.setText("Tipo Periodo:");

        jLabel3.setText("Periodicidad (Num):");

        jLabel5.setText("Km Último:");

        cmbVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVehiculoActionPerformed(evt);
            }
        });

        cmbMantenimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbTipoPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Km", "Dias" }));

        txtKmUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKmUltimoActionPerformed(evt);
            }
        });

        btnGuardarAsignacion.setText("Guardar Asignación");
        btnGuardarAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarAsignacionActionPerformed(evt);
            }
        });

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
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(30, 30, 30)
                        .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbVehiculo, 0, 280, Short.MAX_VALUE)
                            .addComponent(cmbMantenimiento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbTipoPeriodo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPeriodicidad)
                            .addComponent(txtKmUltimo)))
                    .addComponent(btnGuardarAsignacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(190, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbTipoPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPeriodicidad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtKmUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnGuardarAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ingresar Asignación", jPanelIngresar);

        jLabel10.setText("Buscador: ");

        txtBuscador.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tblAsignaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {}
        ));
        jScrollPane1.setViewportView(tblAsignaciones);

        javax.swing.GroupLayout jPanelModificarLayout = new javax.swing.GroupLayout(jPanelModificar);
        jPanelModificar.setLayout(jPanelModificarLayout);
        jPanelModificarLayout.setHorizontalGroup(
            jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModificarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(jPanelModificarLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
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

        jTabbedPane1.addTab("Tablas / Historial", jPanelModificar);
        jTabbedPane1.addTab("Regresar", jTabbedPaneRegresar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbVehiculoActionPerformed(java.awt.event.ActionEvent evt) {}
    private void txtKmUltimoActionPerformed(java.awt.event.ActionEvent evt) {}

    private void btnGuardarAsignacionActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        if (!validarCampos()) {
            return;
        }
        
        String vehiculoSeleccionado = cmbVehiculo.getSelectedItem().toString();
        String mantenimientoSeleccionado = cmbMantenimiento.getSelectedItem().toString();
        
        String placaVehiculo = vehiculoSeleccionado.split(" - ")[0].trim();
        String mantenimiento = mantenimientoSeleccionado.contains(" - ") ? mantenimientoSeleccionado.split(" - ")[1].trim() : mantenimientoSeleccionado.trim();
        
        String tipoPeriodo = cmbTipoPeriodo.getSelectedItem().toString();
        double periodicidad = Double.parseDouble(txtPeriodicidad.getText().trim());
        double kmUltimo = Double.parseDouble(txtKmUltimo.getText().trim());
        
        Date ingreso = new Date();
        Date vencimiento = new Date(); 
        
        logicaAsignacionMante logica = new logicaAsignacionMante();
        boolean exito = logica.registrarAsignacion(placaVehiculo, mantenimiento, tipoPeriodo, periodicidad, kmUltimo, ingreso, vencimiento);
        
        logicaVehiculo logicaV = new logicaVehiculo();
        boolean actualizarKm = logicaV.modificarVehiculo(placaVehiculo, kmUltimo);
        
        if (actualizarKm) {
            JOptionPane.showMessageDialog(this, "Km actualizado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
        if (exito) {
            JOptionPane.showMessageDialog(this, "Asignación registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            inicializarTabla();
            txtPeriodicidad.setText("");
            txtKmUltimo.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Error: Ya existe una asignación de este mantenimiento para este vehículo.", "Duplicado", JOptionPane.WARNING_MESSAGE);
        }
    }                                                  

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        int filaSeleccionada = tblAsignaciones.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una asignación de la tabla para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = Integer.parseInt(tblAsignaciones.getValueAt(filaSeleccionada, 0).toString());
        String placa = tblAsignaciones.getValueAt(filaSeleccionada, 1).toString();
        String tipoPeriodo = tblAsignaciones.getValueAt(filaSeleccionada, 3).toString();
        double periodicidad = Double.parseDouble(tblAsignaciones.getValueAt(filaSeleccionada, 4).toString());
        double kmUltimo = Double.parseDouble(tblAsignaciones.getValueAt(filaSeleccionada, 5).toString());
        
        DlgModificarAsigna ventana = new DlgModificarAsigna(this, true, id, tipoPeriodo, periodicidad, kmUltimo, placa);
        ventana.setVisible(true);
       
        inicializarTabla();
    }                                          

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        int filaSeleccionada = tblAsignaciones.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una asignación para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = Integer.parseInt(tblAsignaciones.getValueAt(filaSeleccionada, 0).toString());
        logicaAsignacionMante logica = new logicaAsignacionMante();
        boolean eliminado = logica.eliminarAsignacion(id);
        
        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Asignación eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            inicializarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar la asignación.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }                                          

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmAsignarMantenimiento(false).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardarAsignacion;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cmbMantenimiento;
    private javax.swing.JComboBox<String> cmbTipoPeriodo;
    private javax.swing.JComboBox<String> cmbVehiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelIngresar;
    private javax.swing.JPanel jPanelModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPaneRegresar;
    private javax.swing.JTable tblAsignaciones;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtKmUltimo;
    private javax.swing.JTextField txtPeriodicidad;
    // End of variables declaration//GEN-END:variables
}