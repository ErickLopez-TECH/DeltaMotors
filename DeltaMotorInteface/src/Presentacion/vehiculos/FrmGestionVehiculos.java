package Presentacion.vehiculos; 

import Datos.Estructuras;
import Datos.objVehiculo;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Logica.logicaVehiculo;
import java.util.ArrayList;

public class FrmGestionVehiculos extends javax.swing.JFrame {

    private DefaultTableModel modeloTabla;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmGestionVehiculos.class.getName());
    private boolean rolActual;
    
    public FrmGestionVehiculos(boolean rol) {
        initComponents();
        
        this.rolActual = rol;
        // Pantalla completa idéntica al diseño moderno de usuarios
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        
        inicializarFunciones();
        aplicarDisenoModerno();
        
        panelPersonalizado.setVisible(false);

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

        cmbMarca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                    String marcaSeleccionada = cmbMarca.getSelectedItem().toString();
                    
                    if (marcaSeleccionada.equals("Otra...")) {
                        panelPersonalizado.setVisible(true);
                        cmbModelo.setEnabled(false);
                    } else {
                        panelPersonalizado.setVisible(false);
                        cmbModelo.setEnabled(true);
                        cargarModeloPorMarca(marcaSeleccionada);
                    }
                }
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
        
        // Contenedores y Paneles
        jPanelIngresar.setBackground(colorBlanco);
        jPanelModificar.setBackground(colorBlanco);
        if (panelPersonalizado != null) panelPersonalizado.setBackground(colorBlanco);
        jTabbedPane1.setBackground(colorFondoPrincipal);
        jTabbedPane1.setFont(fuenteNegrita);
        
        // Estilizar campos de texto y comboboxes de forma unificada
        javax.swing.JComponent[] campos = {txtPlaca, cmbMarca, cmbModelo, cmbTipoVehiculo, txtKilometraje, txtAnio, cmbCombustible, txtOtraMarca, txtOtroModelo, txtBuscador};
        for (javax.swing.JComponent c : campos) {
            if (c != null) {
                c.setFont(fuenteGeneral);
                c.setBackground(colorBlanco);
                c.setForeground(colorTexto);
                c.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(colorBorde, 1),
                    javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)
                ));
                
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
        javax.swing.JLabel[] etiquetas = {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10};
        for (javax.swing.JLabel l : etiquetas) {
            if (l != null) {
                l.setFont(fuenteNegrita);
                l.setForeground(colorTexto);
            }
        }
        
        // Botones principales (Guardar y Modificar con estilo Indigo)
        javax.swing.JButton[] botonesPrimarios = {btnGuardarVehiculo, btnGuardarOtraMarca, btnModificar};
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
        tblVehiculos.setFont(fuenteGeneral);
        tblVehiculos.setRowHeight(36); 
        tblVehiculos.getTableHeader().setFont(fuenteNegrita);
        tblVehiculos.getTableHeader().setBackground(new java.awt.Color(241, 245, 249));
        tblVehiculos.getTableHeader().setForeground(colorTexto);
        tblVehiculos.setSelectionBackground(new java.awt.Color(224, 231, 255));
        tblVehiculos.setSelectionForeground(colorTexto);
        tblVehiculos.setGridColor(new java.awt.Color(241, 245, 249));
        tblVehiculos.setShowVerticalLines(false);
        
        jPanelIngresar.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanelModificar.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }
    
    public void filtrarVehiculo(){
        String filtro = txtBuscador.getText().toLowerCase().trim();
        
        logicaVehiculo logica = new logicaVehiculo();
        ArrayList<objVehiculo> lista = logica.obtenerListaVehiculos();
        ArrayList<objVehiculo> listaFiltrada = new ArrayList<>();
        
        for (objVehiculo vehiculo : lista) {
            String id = String.valueOf(vehiculo.getId());
            String placa = vehiculo.getPlaca().toLowerCase();
            String marca = vehiculo.getMarca().toLowerCase();
            
            if((id.contains(filtro)) || (placa.contains(filtro)) || (marca.contains(filtro))){
                listaFiltrada.add(vehiculo);
            }
        }
         
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
        
        DefaultTableModel modeloTablaFiltrada = new DefaultTableModel(Datos, columnas);
        tblVehiculos.setModel(modeloTablaFiltrada);
    }
    
    public void inicializarFunciones(){
        cargarTipoMotor();
        inicializarTabla();
        
        txtBuscador.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrarVehiculo(); }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrarVehiculo(); }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrarVehiculo(); }
        });
        
        cmbCombustible.removeAllItems();
        cmbCombustible.addItem("Seleccione el motor...");
        
        cmbTipoVehiculo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                    cargarCombustibles();
                }
            }
        });
    }

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
    
    public void cargarModeloPorMarca(String marca){
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
        
        modeloTabla = new DefaultTableModel(Datos, columnas);
        tblVehiculos.setModel(modeloTabla);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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

        cmbMarca.setModel(new javax.swing.DefaultComboBoxModel<>());
        cmbModelo.setModel(new javax.swing.DefaultComboBoxModel<>());

        jLabel7.setText("Combustible: ");
        cmbCombustible.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Otra Marca: ");
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
                .addGap(14, 14, 14)
                .addGroup(panelPersonalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(panelPersonalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtOtraMarca)
                    .addComponent(txtOtroModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPersonalizadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardarOtraMarca)
                .addGap(61, 61, 61))
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
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelIngresarLayout = new javax.swing.GroupLayout(jPanelIngresar);
        jPanelIngresar.setLayout(jPanelIngresarLayout);
        jPanelIngresarLayout.setHorizontalGroup(
            jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIngresarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(btnGuardarVehiculo)))
                    .addComponent(panelPersonalizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanelIngresarLayout.setVerticalGroup(
            jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIngresarLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cmbCombustible, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKilometraje, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnGuardarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelPersonalizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ingresar Vehiculo", jPanelIngresar);

        tblVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {}
        ));
        jScrollPane1.setViewportView(tblVehiculos);

        btnModificar.setText("Modificar Seleccionado");
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
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelModificarLayout.setVerticalGroup(
            jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModificarLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Gestión y Búsqueda", jPanelModificar);

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
    }// </editor-fold>                        

    public boolean esNumero(String texto) {
        return texto != null && texto.matches("^\\d+(\\.\\d+)?$");
    }
    
    private void btnGuardarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {
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
        
        boolean exito = vehi.registrarVehiculo(placa, marca, modelo, motor, combustible, kilometraje, anio, estado);
        
        if(exito){
            JOptionPane.showMessageDialog(this, "¡Vehículo registrado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            inicializarTabla();
            txtPlaca.setText("");
            txtKilometraje.setText("");
            txtAnio.setText("");
            cmbMarca.setSelectedIndex(0);
            cmbTipoVehiculo.setSelectedIndex(0);
            cmbCombustible.setSelectedIndex(0);
        }else{
            JOptionPane.showMessageDialog(this, "Sucedió algo inesperado al guardar el vehículo", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public boolean validarCampos() {
        if (txtPlaca.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe digitar la placa del vehículo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtPlaca.requestFocus();
            return false;
        }
        
        logicaVehiculo logica = new logicaVehiculo();
        if (logica.existenciaVehiculo(txtPlaca.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Esta placa ya se encuentra registrada.", "Error", JOptionPane.ERROR_MESSAGE);
            txtPlaca.requestFocus();
            return false; 
        }
        
        if (cmbMarca.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una marca válida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            cmbMarca.requestFocus();
            return false;
        }
        
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
            if (cmbModelo.getSelectedIndex() <= 0) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un modelo válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                cmbModelo.requestFocus();
                return false;
            }
        }
        
        if (cmbTipoVehiculo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar el tipo de motor.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            cmbTipoVehiculo.requestFocus();
            return false;
        }
        
        if (cmbCombustible.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar el tipo de combustible.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            cmbCombustible.requestFocus();
            return false;
        }
        
        if (txtKilometraje.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe digitar el kilometraje.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtKilometraje.requestFocus();
            return false;
        }
        
        String kmTexto = txtKilometraje.getText().trim();
        if (!esNumero(kmTexto)) {
            JOptionPane.showMessageDialog(this, "El kilometraje debe contener únicamente números.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtKilometraje.requestFocus();
            return false;
        }
        
        if (txtAnio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe digitar el año del vehículo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtAnio.requestFocus();
            return false;
        }
        
        String anio = txtAnio.getText().trim();
        if (!esNumero(anio)) {
            JOptionPane.showMessageDialog(this, "El año debe contener únicamente números.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtAnio.requestFocus();
            return false;
        }
        
        return true;
    }

   private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        int filaSeleccionada = tblVehiculos.getSelectedRow();
        
        if(filaSeleccionada == -1){
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un vehículo de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = Integer.parseInt(tblVehiculos.getValueAt(filaSeleccionada, 0).toString());
        double kilometraje = Double.parseDouble(tblVehiculos.getValueAt(filaSeleccionada, 6).toString());
        String estado = tblVehiculos.getValueAt(filaSeleccionada, 8).toString();

        // Instancia y muestra el diálogo pasando los parámetros requeridos
        DlgModificarVehiculo dialogMod = new DlgModificarVehiculo(this, true, id, kilometraje, estado);
        dialogMod.setVisible(true);
        
        // Refresca la tabla automáticamente al cerrar la ventana de modificación
        inicializarTabla();
    }
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        int filaSeleccionada = tblVehiculos.getSelectedRow();
        
        if(filaSeleccionada == -1){
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un vehículo de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int idVehiculo = Integer.parseInt(tblVehiculos.getValueAt(filaSeleccionada, 0).toString());
        String placaVehiculo = tblVehiculos.getValueAt(filaSeleccionada, 1).toString();
        
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar el vehículo con placa: " + placaVehiculo + "?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            Estructuras est = new Estructuras();
            est.leerArchivoVehiculo();
            boolean eliminado = false;
            ArrayList<objVehiculo> lista = est.getListaVehiculo();
            
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId() == idVehiculo) {
                    lista.remove(i);
                    eliminado = true;
                    break;
                }
            }
            
            if (eliminado) {
                est.escribeArchivoVehiculo();
                JOptionPane.showMessageDialog(this, "¡Vehículo eliminado con éxito!", "Información", JOptionPane.INFORMATION_MESSAGE);
                inicializarTabla(); 
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo encontrar el vehículo en los registros.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }                                           

    private void btnGuardarOtraMarcaActionPerformed(java.awt.event.ActionEvent evt) {
        String nuevaMarca = txtOtraMarca.getText().trim();
        String nuevoModelo = txtOtroModelo.getText().trim();
        
        if (nuevaMarca.isEmpty() || nuevoModelo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe digitar tanto la marca como el modelo nuevos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        cmbMarca.insertItemAt(nuevaMarca, cmbMarca.getItemCount() - 1);
        cmbMarca.setSelectedItem(nuevaMarca);
        
        cmbModelo.setEnabled(true);
        cmbModelo.removeAllItems();
        cmbModelo.addItem("Seleccione...");
        cmbModelo.addItem(nuevoModelo);
        cmbModelo.setSelectedItem(nuevoModelo);
        
        txtOtraMarca.setText("");
        txtOtroModelo.setText("");
        panelPersonalizado.setVisible(false);
        
        JOptionPane.showMessageDialog(this, "¡Marca y modelo agregados con éxito al sistema!");
    }

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

        java.awt.EventQueue.invokeLater(() -> new FrmGestionVehiculos(false).setVisible(true));
    }

    // Variables declaration - do not modify                     
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
    // End of variables declaration                   
}