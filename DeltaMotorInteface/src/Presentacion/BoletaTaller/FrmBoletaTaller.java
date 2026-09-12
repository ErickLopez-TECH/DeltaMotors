package Presentacion.BoletaTaller;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

//Importaciones de objetos

import Datos.objVehiculo;
import Datos.objAsignacionMante;
import Datos.objMecanicos;
import Logica.logicaMecanicos;
import Logica.logicaVehiculo;
import Logica.logicaAsignacionMante;
import Logica.logicaBoletaTaller;
import javax.swing.JTextField;

public class FrmBoletaTaller extends javax.swing.JFrame {

    // Paleta de Colores Moderna
    private final Color COLOR_PRIMARY = new Color(41, 128, 185);     
    private final Color COLOR_SECONDARY = new Color(52, 152, 219);   
    private final Color COLOR_BACKGROUND = new Color(245, 247, 250); 
    private final Color COLOR_PANEL = new Color(255, 255, 255);      
    private final Color COLOR_TEXT = new Color(44, 62, 80);          

    //variable con uso en diferentes metodos
    private String placa;
    private String nombreMeca;
    
    //array de listas
    private ArrayList<String> listaOriginalVehiculo;
    private ArrayList<String> listaOriginalGMantenimientos;
    private ArrayList<String> listaOriginalMecanicos;
    
    /*Datos relevantes de la interfaz
    private int id;//listo
    
    private String placaVehiculo;//interfaz
    private String nombreMantenimeinto;//interfaz
    private String marcaVehiculo;//Logica de conexion con placa sin visibilidad GUI
    private String modeloVehiculo;//Logica de conexion con placa sin visibilidad GUI
    private double kilometrajeIngreso;//GUI
    private Date fecha;Fecha de hoy
    private String nombreMecanico; //dependencia de mecanicos

    */
    public FrmBoletaTaller(boolean rol) {
        initComponents();
        this.setLocationRelativeTo(null);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        aplicarEstilosModernos();
        
        cargarListasCMB();
        
        configurarAutocompletado(cmbVehiculo, listaOriginalVehiculo);
        configurarAutocompletado(cmbMecanico, listaOriginalMecanicos);
        
        
        // Escuchador exclusivo para actualizar mantenimientos al cambiar de vehículo
    
        cmbVehiculo.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                    actualizarComboMantenimientos();
                }
            }
        });
    }
    
    private void actualizarComboMantenimientos() {
    Object seleccion = cmbVehiculo.getSelectedItem();
    if (seleccion != null) {
        String itemSeleccionado = seleccion.toString();
        
        if (itemSeleccionado.contains(" - ")) {
            String placaSeleccionada = itemSeleccionado.split(" - ")[0].trim();
            
            logicaBoletaTaller logicaBT = new logicaBoletaTaller();
            ArrayList mantenimientos = logicaBT.configuraCMB(placaSeleccionada);
            
            if (mantenimientos != null && !mantenimientos.isEmpty()) {
                configurarAutocompletado(cmbMantenimiento, mantenimientos);
            } else {
                cmbMantenimiento.setEditable(false);
                cmbMantenimiento.setModel(new DefaultComboBoxModel<>(new String[]{"Sin mantenimientos asignados"}));
            }
        }
    }
}
    
    public void cargarListasCMB(){
        listaOriginalVehiculo = new ArrayList<>();
        listaOriginalMecanicos = new ArrayList<>();
        
        
            // Elemento por defecto al inicio
        listaOriginalVehiculo.add("Seleccione...");
        listaOriginalMecanicos.add("Seleccione...");
       // listaOriginalGMantenimientos.add("Seleccione...");
        //funcion para dar la lista al cmb
        logicaVehiculo logicaV = new logicaVehiculo();
        ArrayList<objVehiculo> listaV = logicaV.obtenerListaVehiculos();
        for (objVehiculo vehiculo : listaV) {
            listaOriginalVehiculo.add(vehiculo.getPlaca()+" - " + vehiculo.getMarca() + " "+vehiculo.getModelo());
            placa = vehiculo.getPlaca();
        }
        
        //funcion para dar lista al cmb de mecabicos
        logicaMecanicos logicaM = new logicaMecanicos();
        ArrayList<objMecanicos> listaM = logicaM.obtenerListaMecanicos();
        for (objMecanicos mecanicos : listaM) {
            listaOriginalMecanicos.add(mecanicos.getCedula() + " - "+ mecanicos.getNombre());
            nombreMeca = mecanicos.getNombre();
        }
    }
    
    private void configurarAutocompletado(JComboBox<String> comboBox, ArrayList<String> elementosOriginales){
        if(elementosOriginales == null){
            return;
        }
        
        comboBox.setEditable(true);
        comboBox.setModel(new DefaultComboBoxModel<>(elementosOriginales.toArray(new String[0])));
        
        
        JTextField editorTxt = (JTextField) comboBox.getEditor().getEditorComponent();
        editorTxt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        
        
        
        //funciones de desplazamientos en el combo box
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
    
    private void aplicarEstilosModernos() {
        jPanelIngresar.setBackground(COLOR_PANEL);
        jPanelModificar.setBackground(COLOR_PANEL);
        jTabbedPane1.setBackground(COLOR_BACKGROUND);
        
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
        
        estilizarBoton(btnGuardarAsignacion, COLOR_PRIMARY);
        estilizarBoton(btnModificar, COLOR_SECONDARY);
        estilizarBoton(btnEliminar, new Color(231, 76, 60));
        
        tblBoleta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblBoleta.setRowHeight(28);
        tblBoleta.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblBoleta.getTableHeader().setBackground(COLOR_PRIMARY);
        tblBoleta.getTableHeader().setForeground(Color.WHITE);
        tblBoleta.setSelectionBackground(new Color(189, 195, 199));
        tblBoleta.setSelectionForeground(Color.BLACK);
    }

    private void estilizarBoton(javax.swing.JButton boton, Color colorFondo) {
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelIngresar = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPeriodicidad = new javax.swing.JTextField();
        btnGuardarAsignacion = new javax.swing.JButton();
        cmbVehiculo = new javax.swing.JComboBox<>();
        cmbMantenimiento = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbMecanico = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtKmUltimo = new javax.swing.JTextField();
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
        jLabelTitulo.setText("Registro de Nueva Boleta de Taller");

        jLabel1.setText("Vehículo (Placa):");

        jLabel2.setText("Mantenimiento:");

        jLabel3.setText("Periodicidad (Num):");

        btnGuardarAsignacion.setText("Guardar Boleta");

        cmbVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbMantenimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un vehiculo..." }));

        jLabel4.setText("Mecanico Asignado:");

        jLabel5.setText("Km Ingreso:");

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
                            .addComponent(cmbMecanico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPeriodicidad)
                            .addComponent(txtKmUltimo)))
                    .addComponent(btnGuardarAsignacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(221, Short.MAX_VALUE))
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
                    .addComponent(cmbMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jTabbedPane1.addTab("tab1", jPanelIngresar);

        tblBoleta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblBoleta);

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

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmBoletaTaller(false).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardarAsignacion;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cmbMantenimiento;
    private javax.swing.JComboBox<String> cmbMecanico;
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
    private javax.swing.JTable tblBoleta;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtKmUltimo;
    private javax.swing.JTextField txtPeriodicidad;
    // End of variables declaration//GEN-END:variables
}