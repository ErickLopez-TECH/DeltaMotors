package Presentacion.vehiculos; // Ajusta el paquete según tu estructura (ej: Presentacion)

import Datos.Estructuras;
import Datos.objVehiculo;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Logica.logicaVehiculo;
import java.util.ArrayList;

public class FrmGestionVehiculos extends javax.swing.JFrame {

    // Modelo para la tabla de la segunda pestaña
    private DefaultTableModel modeloTabla;

    public FrmGestionVehiculos() {
        initComponents();
        this.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        
        inicializarFunciones();
        
        
   
        // 1. Forzar que el panel personalizado inicie oculto
    panelPersonalizado.setVisible(false);

    // 2. Llenar el ComboBox de marcas con las opciones predefinidas y "Otra..."
    cmbMarca.removeAllItems();
    cmbMarca.addItem("Seleccione...");
    cmbMarca.addItem("Toyota");
    cmbMarca.addItem("Hyundai");
    cmbMarca.addItem("Nissan");
    cmbMarca.addItem("Mitsubishi");
    cmbMarca.addItem("Suzuki");
    cmbMarca.addItem("Chevrolet");
    cmbMarca.addItem("Ford");
    cmbMarca.addItem("Isusu");
    cmbMarca.addItem("Honda");
    cmbMarca.addItem("Freightliner");
    cmbMarca.addItem("Otra...");

    // 3. Evento inteligente para el ComboBox de marcas
    cmbMarca.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                String marcaSeleccionada = cmbMarca.getSelectedItem().toString();
                
                if (marcaSeleccionada.equals("Otra...")) {
                    // Si elige "Otra...", mostramos el panel personalizado y apagamos el combo de modelos
                    panelPersonalizado.setVisible(true);
                    cmbModelo.setEnabled(false);
                } else {
                    // Si elige una marca normal, ocultamos el panel personalizado, encendemos el combo y cargamos modelos
                    panelPersonalizado.setVisible(false);
                    cmbModelo.setEnabled(true);
                    cargarModeloPorMarca(marcaSeleccionada);
                }
            }
        }
    });
    }
    
    public void filtrarVehiculo(){
        String filtro = txtBuscador.getText().toLowerCase().trim();
        
        //leer los datos actuales
        logicaVehiculo logica = new logicaVehiculo();
        ArrayList<objVehiculo> lista = logica.obtenerListaVehiculos();
        
        ArrayList<objVehiculo> listaFiltrada = new ArrayList<>();
        
        for (objVehiculo vehiculo : lista) {
            
            String id = String.valueOf(vehiculo.getId());
            String placa = vehiculo.getPlaca();
            String marca = vehiculo.getMarca();
            
            if((id.contains(filtro))|| (placa.contains(filtro))||(marca.contains(filtro))){
                listaFiltrada.add(vehiculo);
            }
            
        }
         
        //rellenar datos
        String[] columnas = {"ID", "Placa", "Marca", "Modelo", "Motor", "Combustible", "Kilometraje", "Año", "Estado"};
    
    Object[][] Datos = new Object[listaFiltrada.size()][9];
    
        for (int i = 0; i < listaFiltrada.size(); i++) {
            objVehiculo v = listaFiltrada.get(i);
            
            Datos[i][0] = v.getId();
            Datos[i][1] = v.getPlaca();
            Datos[i][2] = v.getMarca();
            Datos[i][3] = v.getModelo();
            Datos[i][4] = v.getTipoMotor();
            Datos[i][5] = v.getCombustible();
            Datos[i][6] = v.getKilometraje();
            Datos[i][7] = v.getAnio();
            Datos[i][8] = v.getEstado();
        }
    // Creamos el modelo de la tabla sin filas iniciales pero con las columnas definidas
    DefaultTableModel modeloTabla = new DefaultTableModel(Datos, columnas);
    tblVehiculos.setModel(modeloTabla);
        
    }
    
    public void inicializarFunciones(){
        cargarTipoMotor();
        inicializarTabla();
        alternarColoresTabla();
        
        cmbCombustible.removeAllItems();
        cmbCombustible.addItem("Seleccion el motor...");
        //escuchador de cambios
        cmbTipoVehiculo.addItemListener(new java.awt.event.ItemListener() {
    public void itemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            cargarCombustibles();
        }
    }
});
        
        
    }

    //cargar los tipos disponibles
    public void cargarTipoMotor(){
        cmbTipoVehiculo.removeAllItems();
        cmbTipoVehiculo.addItem("Seleccione...");
        cmbTipoVehiculo.addItem("Electrico");
        cmbTipoVehiculo.addItem("Hibrido");
        cmbTipoVehiculo.addItem("Combustion Interna");
        
        
        
    }
    
    public void cargarCombustibles(){
        int indiceMotor = cmbTipoVehiculo.getSelectedIndex();
        
        cmbCombustible.removeAllItems();
        cmbCombustible.addItem("Seleccione...");
        
        
        switch (indiceMotor) {
            
            case 1:
                
                cmbCombustible.addItem("KWH");
              break;
              
            case 2:
                cmbCombustible.addItem("Gasolina & KWH");
                cmbCombustible.addItem("Diesel & KWH");
              break;
              
            case 3:
                cmbCombustible.addItem("Gasolina");
                cmbCombustible.addItem("Diesel");
              break;
        }
        
    }
    
    
    public  void cargarModeloPorMarca(String marca){
        
        cmbModelo.removeAllItems();
        cmbModelo.addItem("Seleccione...");
        
        
        switch (marca) {
        case "Toyota":
            cmbModelo.addItem("Hilux");
            cmbModelo.addItem("Hiace");
            cmbModelo.addItem("Corolla");
            cmbModelo.addItem("Yaris");
            cmbModelo.addItem("RAV4");
            break;
        case "Hyundai":
            cmbModelo.addItem("H-100");
            cmbModelo.addItem("Elantra");
            cmbModelo.addItem("Tucson");
            cmbModelo.addItem("Santa Fe");
            break;
        case "Nissan":
            cmbModelo.addItem("Frontier");
            cmbModelo.addItem("Navara");
            cmbModelo.addItem("Sentra");
            cmbModelo.addItem("Urvan");
            break;
        case "Mitsubishi":
            cmbModelo.addItem("L200");
            cmbModelo.addItem("Montero");
            cmbModelo.addItem("ASX");
            break;
        case "Suzuki":
            cmbModelo.addItem("Vitara");
            cmbModelo.addItem("Jimny");
            cmbModelo.addItem("Swift");
            break;
        case "Chevrolet":
            cmbModelo.addItem("D-Max");
            cmbModelo.addItem("Tracker");
            cmbModelo.addItem("Colorado");
            break;
        case "Ford":
            cmbModelo.addItem("Ranger");
            cmbModelo.addItem("Explorer");
            break;
        case "Isusu":
            cmbModelo.addItem("NPR");
            cmbModelo.addItem("D-Max");
            break;
        case "Honda":
            cmbModelo.addItem("CR-V");
            cmbModelo.addItem("Civic");
            break;
        case "Freightliner":
            cmbModelo.addItem("M2");
            cmbModelo.addItem("Cascadia");
            break;
        default:
            cmbModelo.addItem("General");
            break;
    }
        
     
    }
    private void inicializarTabla() {
        // Configuramos las columnas por si quieres cargar datos de prueba o desde la lógica
        // Definimos los nombres de las columnas que van a aparecer en la tabla
        Estructuras est = new Estructuras();
        est.leerArchivoVehiculo();
        
        ArrayList<Datos.objVehiculo> lista = est.getListaVehiculo();
        
        
    String[] columnas = {"ID", "Placa", "Marca", "Modelo", "Motor", "Combustible", "Kilometraje", "Año", "Estado"};
    
    Object[][] Datos = new Object[lista.size()][9];
    
        for (int i = 0; i < lista.size(); i++) {
            objVehiculo v = lista.get(i);
            
            Datos[i][0] = v.getId();
            Datos[i][1] = v.getPlaca();
            Datos[i][2] = v.getMarca();
            Datos[i][3] = v.getModelo();
            Datos[i][4] = v.getTipoMotor();
            Datos[i][5] = v.getCombustible();
            Datos[i][6] = v.getKilometraje();
            Datos[i][7] = v.getAnio();
            Datos[i][8] = v.getEstado();
        }
    // Creamos el modelo de la tabla sin filas iniciales pero con las columnas definidas
    DefaultTableModel modeloTabla = new DefaultTableModel(Datos, columnas);
    tblVehiculos.setModel(modeloTabla);
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelIngresar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtKilometraje = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbTipoVehiculo = new javax.swing.JComboBox<>();
        btnGuardarVehiculo = new javax.swing.JButton();
        cmbMarca = new javax.swing.JComboBox<>();
        cmbModelo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cmbCombustible = new javax.swing.JComboBox<>();
        panelPersonalizado = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtOtraMarca = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtOtroModelo = new javax.swing.JTextField();
        btnGuardarOtraMarca = new javax.swing.JButton();
        jPanelModificar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVehiculos = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtBuscador = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Vehículos");

        jLabel1.setText("Placa:");

        jLabel2.setText("Marca:");

        jLabel3.setText("Modelo:");

        jLabel4.setText("Año:");

        jLabel5.setText("Kilometraje:");

        jLabel6.setText("Motor");

        cmbTipoVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Motocicleta", "Automóvil", "Camión / Carga" }));

        btnGuardarVehiculo.setText("Guardar Vehículo");
        btnGuardarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarVehiculoActionPerformed(evt);
            }
        });

        jLabel7.setText("Combustible: ");

        cmbCombustible.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Otra Marca: ");

        txtOtraMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOtraMarcaActionPerformed(evt);
            }
        });

        jLabel9.setText("Otro Modelo:");

        btnGuardarOtraMarca.setText("Guardar");
        btnGuardarOtraMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarOtraMarcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPersonalizadoLayout = new javax.swing.GroupLayout(panelPersonalizado);
        panelPersonalizado.setLayout(panelPersonalizadoLayout);
        panelPersonalizadoLayout.setHorizontalGroup(
            panelPersonalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPersonalizadoLayout.createSequentialGroup()
                .addGroup(panelPersonalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPersonalizadoLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panelPersonalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(panelPersonalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtOtraMarca)
                            .addComponent(txtOtroModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)))
                    .addGroup(panelPersonalizadoLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnGuardarOtraMarca)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        panelPersonalizadoLayout.setVerticalGroup(
            panelPersonalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPersonalizadoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelPersonalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtOtraMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelPersonalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtOtroModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnGuardarOtraMarca)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelIngresarLayout = new javax.swing.GroupLayout(jPanelIngresar);
        jPanelIngresar.setLayout(jPanelIngresarLayout);
        jPanelIngresarLayout.setHorizontalGroup(
            jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIngresarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelIngresarLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(panelPersonalizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelIngresarLayout.createSequentialGroup()
                        .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelIngresarLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                .addComponent(cmbTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelIngresarLayout.createSequentialGroup()
                                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(30, 30, 30)
                                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(cmbMarca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbModelo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(61, 61, 61)
                        .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelIngresarLayout.createSequentialGroup()
                                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtAnio, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(txtKilometraje, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(cmbCombustible, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(btnGuardarVehiculo))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanelIngresarLayout.setVerticalGroup(
            jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIngresarLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cmbCombustible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKilometraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelIngresarLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelPersonalizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(jPanelIngresarLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnGuardarVehiculo)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Ingresar Vehiculo", jPanelIngresar);

        tblVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblVehiculos);

        btnModificar.setText("Modificar Seleccionado");

        btnEliminar.setText("Eliminar");

        jLabel10.setText("Buscador: ");

        javax.swing.GroupLayout jPanelModificarLayout = new javax.swing.GroupLayout(jPanelModificar);
        jPanelModificar.setLayout(jPanelModificarLayout);
        jPanelModificarLayout.setHorizontalGroup(
            jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModificarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelModificarLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelModificarLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanelModificarLayout.createSequentialGroup()
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelModificarLayout.setVerticalGroup(
            jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModificarLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar))
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("tab2", jPanelModificar);

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

    public boolean esNumero(String texto) {
    // Valida números enteros o decimales (ej: 10000 o 10000.78)
    return texto != null && texto.matches("^\\d+(\\.\\d+)?$");
}
    
    private void btnGuardarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarVehiculoActionPerformed
            if (!validarCampos()) {
             return; 
         }
            
        logicaVehiculo vehi = new logicaVehiculo();
        
        String placa = txtPlaca.getText().trim();
        String marca = cmbMarca.getSelectedItem().toString();
        String modelo = cmbModelo.getSelectedItem().toString();
        String motor = cmbTipoVehiculo.getSelectedItem().toString();
        String combustible = cmbCombustible.getSelectedItem().toString();
        double kilometraje = Double.parseDouble(txtKilometraje.getText().trim());
        int anio = Integer.parseInt(txtAnio.getText().trim());
        String estado = "Activo";
        
        
        boolean exito = vehi.registrarVehiculo(placa,marca,modelo,motor,combustible,kilometraje,anio,estado);
        
        if(exito){
            
            JOptionPane.showMessageDialog(this, "Vehiculo registrado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            inicializarTabla();
            
        }else{
            JOptionPane.showMessageDialog(this, "Sucedio algo inesperado al guardar el vehiculo", "Error", JOptionPane.WARNING_MESSAGE);
            
        }
    }//GEN-LAST:event_btnGuardarVehiculoActionPerformed

    
    
    public boolean validarCampos() {
    
    if (txtPlaca.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe digitar la placa del vehículo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        txtPlaca.requestFocus(); // Ubica el cursor exactamente aquí
        return false;
    }
    
    logicaVehiculo logica = new logicaVehiculo(); // O usa la instancia de lógica que ya tengas creada
    if (logica.existenciaVehiculo(txtPlaca.getText().trim())) {
        JOptionPane.showMessageDialog(this, "Esta placa ya se encuentra registrada.", "Error", JOptionPane.ERROR_MESSAGE);
        txtPlaca.requestFocus();
        return false; 
    }
    
    // 2. Validar marca (si seleccionó "Seleccione..." que es el índice 0)
    if (cmbMarca.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una marca válida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        cmbMarca.requestFocus();
        return false;
    }
    
    // 3. Validar si escogió "Otra..." y dejó los campos de texto vacíos
    if (cmbMarca.getSelectedItem() != null && cmbMarca.getSelectedItem().toString().equals("Otra...")) {
        if (txtOtraMarca.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe digitar la nueva marca.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtOtraMarca.requestFocus();
            return false;
        }
        if (txtOtroModelo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe digitar el nuevo modelo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtOtroModelo.requestFocus();
            return false;
        }
    } else {
        // Validar modelo normal
        if (cmbModelo.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un modelo válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            cmbModelo.requestFocus();
            return false;
        }
    }
    
    // 4. Validar tipo de vehículo/motor
    if (cmbTipoVehiculo.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar el tipo de motor.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        cmbTipoVehiculo.requestFocus();
        return false;
    }
    
    // 5. Validar combustible
    if (cmbCombustible.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar el tipo de combustible.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        cmbCombustible.requestFocus();
        return false;
    }
    
    
        
    
    // 6. Validar kilometraje
    if (txtKilometraje.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe digitar el kilometraje.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        txtKilometraje.requestFocus();
        return false;
    }
    
        // Validar kilometraje 
    String kmTexto = txtKilometraje.getText().trim();
    if (!esNumero(kmTexto)) {
        JOptionPane.showMessageDialog(this, "El kilometraje debe contener únicamente números. Ejemplo: 10.4 o 10", "Advertencia", JOptionPane.WARNING_MESSAGE);
        txtKilometraje.requestFocus();
        return false;
    }
    
    // 7. Validar año
    if (txtAnio.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe digitar el año del vehículo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        txtAnio.requestFocus();
        return false;
    }
    
    String anio = txtAnio.getText().trim();
    if (!esNumero(anio)) {
            JOptionPane.showMessageDialog(this, "El anio debe contener únicamente números. Ejemplo: 2026", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtAnio.requestFocus();
            return false;
        }
    
    // Si todo está correcto, retorna true
    return true;
}
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtOtraMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOtraMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtraMarcaActionPerformed

    private void btnGuardarOtraMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarOtraMarcaActionPerformed
        // TODO add your handling code here:
        
        // Capturamos lo que el usuario digitó a mano en el panel personalizado
    String nuevaMarca = txtOtraMarca.getText().trim();
    String nuevoModelo = txtOtroModelo.getText().trim();
    
    // Validamos que no estén vacíos
    if (nuevaMarca.isEmpty() || nuevoModelo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe digitar tanto la marca como el modelo nuevos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Inyectamos la nueva marca en el ComboBox de marcas antes de la opcion de otra
    cmbMarca.insertItemAt(nuevaMarca, cmbMarca.getItemCount() - 1);
    
    // Seleccionamos automáticamente esa nueva marca en el ComboBox para que se visualice
    cmbMarca.setSelectedItem(nuevaMarca);
    
    // agregarmodelo
    cmbModelo.setEnabled(true);
    cmbModelo.removeAllItems();
    cmbModelo.addItem("Seleccione...");
    cmbModelo.addItem(nuevoModelo); // Añade el modelo escrito al combo de modelos
    cmbModelo.setSelectedItem(nuevoModelo); // Lo selecciona automáticamente
    
    // 5. Limpiamos los campos del panel y lo ocultamos de nuevo
    txtOtraMarca.setText("");
    txtOtroModelo.setText("");
    panelPersonalizado.setVisible(false);
    
    JOptionPane.showMessageDialog(this, "¡Marca y modelo agregados con éxito al sistema!");
    }//GEN-LAST:event_btnGuardarOtraMarcaActionPerformed

    
    public void alternarColoresTabla() {
    tblVehiculos.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
        @Override
        public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            // Si la fila está seleccionada, mantiene el color de selección por defecto
            if (isSelected) {
                c.setBackground(table.getSelectionBackground());
                c.setForeground(table.getSelectionForeground());
            } else {
                // Si no está seleccionada, alterna entre blanco y un gris muy claro
                if (row % 2 == 0) {
                    c.setBackground(java.awt.Color.WHITE);
                } else {
                    c.setBackground(new java.awt.Color(240, 245, 250)); // Un tono azulado/grisáceo suave
                }
                c.setForeground(java.awt.Color.BLACK);
            }
            return c;
        }
    });
}
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new FrmGestionVehiculos().setVisible(true));
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FrmVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVehiculos().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardarOtraMarca;
    private javax.swing.JButton btnGuardarVehiculo;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cmbCombustible;
    private javax.swing.JComboBox<String> cmbMarca;
    private javax.swing.JComboBox<String> cmbModelo;
    private javax.swing.JComboBox<String> cmbTipoVehiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelIngresar;
    private javax.swing.JPanel jPanelModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelPersonalizado;
    private javax.swing.JTable tblVehiculos;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtKilometraje;
    private javax.swing.JTextField txtOtraMarca;
    private javax.swing.JTextField txtOtroModelo;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}