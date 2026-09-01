/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion.Usuario;
import Datos.Estructuras;
import Datos.objUsuarios;
import Logica.logicaUsuarios;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Presentacion.MenusPrincipales.FrmMenuAdmin;
import Presentacion.MenusPrincipales.FrmMenuOperador;


/**
 *
 * @author triamus
 */
public class FrmGestionUsuarios extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmGestionUsuarios.class.getName());
    private boolean rolActual;
    /**
     * Creates new form FrmGestionUsuarios
     */
    public FrmGestionUsuarios(boolean rol) {
        initComponents();
        this.rolActual = rol;
        metodosCarga();
        aplicarDisenoModerno();
        
        //pantalla completa
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        
        txtBuscador.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrarUsuarios();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrarUsuarios();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrarUsuarios();
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
        
        // Estilizar campos de texto, combobox y password fields de forma unificada
        javax.swing.JComponent[] campos = {txtUsuario, txtCedula, txtContrasena, txtBuscador, cmbRol};
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
        javax.swing.JLabel[] etiquetas = {jLabel1, jLabel3, jLabel4, jLabel5, jLabel6};
        for (javax.swing.JLabel l : etiquetas) {
            if (l != null) {
                l.setFont(fuenteNegrita);
                l.setForeground(colorTexto);
            }
        }
        
        // Botón principal de Guardar y Modificar (Estilo elegante Indigo)
        javax.swing.JButton[] botonesPrimarios = {btnGuardarUser, btnModificar};
        for (javax.swing.JButton b : botonesPrimarios) {
            if (b != null) {
                b.setFont(fuenteNegrita);
                b.setBackground(colorPrimario);
                b.setForeground(colorBlanco);
                b.setFocusPainted(false);
                b.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
                b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                
                // Animación simple de hover para botones primarios
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
        
        // Estilizar Tabla de manera moderna y limpia (Minimalista sin rejillas invasivas)
        tblUser.setFont(fuenteGeneral);
        tblUser.setRowHeight(36); // Filas más espaciosas y cómodas a la vista
        tblUser.getTableHeader().setFont(fuenteNegrita);
        tblUser.getTableHeader().setBackground(new java.awt.Color(241, 245, 249));
        tblUser.getTableHeader().setForeground(colorTexto);
        tblUser.setSelectionBackground(new java.awt.Color(224, 231, 255));
        tblUser.setSelectionForeground(colorTexto);
        tblUser.setGridColor(new java.awt.Color(241, 245, 249));
        tblUser.setShowVerticalLines(false);
        
        // Bordes redondeados estéticos para los paneles de formularios mediante contenedores interiores o padding visual
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }
    
    private void filtrarUsuarios(){
        String filtro = txtBuscador.getText().trim().toLowerCase();
        
        Estructuras est = new Estructuras();
        est.leerArchivoUsuarios();
        
        ArrayList<Datos.objUsuarios> lista = est.getListaUsuarios();
        ArrayList<Datos.objUsuarios> listaFiltrada = new ArrayList<>();
        
        for (int i = 0; i < lista.size(); i++) {
            objUsuarios u = lista.get(i);
            
            String id = String.valueOf(u.getId());
            String nombre = u.getNombre().toLowerCase();
            String cedula = u.getCedula().toLowerCase();
            String rol = u.getRol().toLowerCase();
            
            if (id.contains(filtro)|| nombre.contains(filtro) || cedula.contains(filtro)|| rol.contains(filtro)){
                listaFiltrada.add(u);
            }
        }
        
        String[] columnas = {"ID", "Usuario","Cédula","Rol", "Estado"};
        Object[][] datos = new Object[listaFiltrada.size()][5];
        
        for (int i = 0; i < listaFiltrada.size(); i++) {
            Datos.objUsuarios u = listaFiltrada.get(i);
            datos[i][0] = u.getId();
            datos[i][1] = u.getNombre();
            datos[i][2] = u.getCedula();
            datos[i][3] = u.getRol();
            datos[i][4] = u.getEstado();
        }
        
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
        tblUser.setModel(modelo);
    }

    private void metodosCarga(){
        cargarTabla();
        configurarPlaceholder(txtUsuario, "Ingrese Usuario");
        configurarPlaceholder(txtCedula, "Cédula");
        configurarPlaceholderPass(txtContrasena, "Ingrese una contraseña");
        
    // listener de las pestañas:
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                if (jTabbedPane1.getSelectedIndex() == 2) {
                    
                    // Evaluamos el booleano guardado para regresar al menú correcto pasándole su rol
                    if (rolActual) {
                        FrmMenuAdmin panelAdmin = new FrmMenuAdmin(rolActual);
                        panelAdmin.setVisible(true);
                    } else {
                        FrmMenuOperador panelOperador = new FrmMenuOperador(rolActual);
                        panelOperador.setVisible(true);
                    }
                    
                    dispose(); // Cierra la ventana actual
                }
            }
        });
    }

    private void cargarTabla(){
        Estructuras est = new Estructuras();
        est.leerArchivoUsuarios();
        
        ArrayList<Datos.objUsuarios> lista = est.getListaUsuarios();
        String[] columnas = {"ID","Usuario","Cédula","Rol","Estado"};
        Object[][] datos = new Object[lista.size()][5];
        
        for (int i = 0; i < lista.size(); i++) {
            Datos.objUsuarios u = lista.get(i);
            datos[i][0] = u.getId();
            datos[i][1] = u.getNombre();
            datos[i][2] = u.getCedula();
            datos[i][3] = u.getRol();
            datos[i][4] = u.getEstado();
        }
        
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
        tblUser.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbRol = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        btnGuardarUser = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtBuscador = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        jTabbedPane2 = new javax.swing.JTabbedPane();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel7.setText("Registro de Nuevos Usuarios");
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel1.setText("Usuario:");

        jLabel3.setText("Cédula:");

        jLabel5.setText("Rol:");

        cmbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Operador", "Administrador" }));

        jLabel4.setText("Contraseña:");

        btnGuardarUser.setText("Guardar Usuario");
        btnGuardarUser.addActionListener(this::btnGuardarUserActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnGuardarUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4))
                            .addGap(30, 30, 30)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtUsuario)
                                .addComponent(txtCedula)
                                .addComponent(cmbRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)))))
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
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnGuardarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ingresar Usuario", jPanel2);

        jLabel6.setText("Buscar usuario:");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(this::btnModificarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblUser);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
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
                    .addComponent(jLabel6)
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

    private void btnGuardarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUserActionPerformed
        String nombreUsuario = txtUsuario.getText().trim();
        String cedula = txtCedula.getText().trim();
        String password = new String(txtContrasena.getPassword()).trim();
        String rol = cmbRol.getSelectedItem().toString();
        String estado = "Activo";
        
        if (cmbRol.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "¡Error! Por favor, seleccione un rol válido.");
            cmbRol.requestFocus();
            return;
        }
        
        logicaUsuarios usuario = new logicaUsuarios();
        
        if(usuario.existenciaUser(nombreUsuario, cedula)){
            JOptionPane.showMessageDialog(this, "¡Usuario o cédula existente en los registros!", "Información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(nombreUsuario.isEmpty() || nombreUsuario.equals("Ingrese Usuario")){
            JOptionPane.showMessageDialog(this, "¡Por favor llene el campo de usuario!", "Información", JOptionPane.ERROR_MESSAGE);
            txtUsuario.requestFocus();
            return;
        }
        if(cedula.isEmpty() || cedula.equals("Cédula")){
            JOptionPane.showMessageDialog(this, "¡Por favor llene el campo de cédula!", "Información", JOptionPane.ERROR_MESSAGE);
            txtCedula.requestFocus();
            return;
        }
        if(password.isEmpty() || password.equals("Ingrese una contraseña")){
            JOptionPane.showMessageDialog(this, "¡Por favor llene el campo de contraseña!", "Información", JOptionPane.ERROR_MESSAGE);
            txtContrasena.requestFocus();
            return;
        }
        
        boolean exito = usuario.registrarUsuario(nombreUsuario, cedula, password, rol, estado);
        
        if(exito){
            JOptionPane.showMessageDialog(this, "¡Usuario registrado con éxito!", "Información", JOptionPane.INFORMATION_MESSAGE);
            metodosCarga();
        }else{
            JOptionPane.showMessageDialog(this, "No se pudo registrar el usuario. Verifique si ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarUserActionPerformed

    private void configurarPlaceholder(javax.swing.JTextField textField, String textoPlaceholder) {
        textField.setText(textoPlaceholder);
        textField.setForeground(new java.awt.Color(148, 163, 184)); // Tono gris neutro elegante

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

    private void configurarPlaceholderPass(javax.swing.JPasswordField passField, String textoPlaceholder) {
        passField.setEchoChar((char) 0); 
        passField.setText(textoPlaceholder);
        passField.setForeground(new java.awt.Color(148, 163, 184));

        passField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                String currentText = new String(passField.getPassword());
                if (currentText.equals(textoPlaceholder)) {
                    passField.setText("");
                    passField.setEchoChar('•'); 
                    passField.setForeground(new java.awt.Color(15, 23, 42));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                String currentText = new String(passField.getPassword());
                if (currentText.trim().isEmpty()) {
                    passField.setEchoChar((char) 0);
                    passField.setText(textoPlaceholder);
                    passField.setForeground(new java.awt.Color(148, 163, 184));
                }
            }
        });
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = tblUser.getSelectedRow();
        
        if(filaSeleccionada == -1){
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int idUsuario = Integer.parseInt(tblUser.getValueAt(filaSeleccionada, 0).toString());
        String nombreUsuario = tblUser.getValueAt(filaSeleccionada, 1).toString();
        
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar al usuario: " + nombreUsuario + "?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            Datos.Estructuras est = new Datos.Estructuras();
            est.leerArchivoUsuarios();
            boolean eliminado = false;
            ArrayList<Datos.objUsuarios> lista = est.getListaUsuarios();
            
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId() == idUsuario) {
                    lista.remove(i);
                    eliminado = true;
                    break;
                }
            }
            
            if (eliminado) {
                est.escribeArchivoUsuarios();
                JOptionPane.showMessageDialog(this, "¡Usuario eliminado con éxito!", "Información", JOptionPane.INFORMATION_MESSAGE);
                cargarTabla(); 
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo encontrar el usuario en los registros.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int filaSeleccionada = tblUser.getSelectedRow();
        
        if(filaSeleccionada == -1){
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = Integer.parseInt(tblUser.getValueAt(filaSeleccionada, 0).toString());
        String nombre = tblUser.getValueAt(filaSeleccionada, 1).toString();
        String rol = tblUser.getValueAt(tblUser.getSelectedRow(), 3).toString();
        String estado = tblUser.getValueAt(tblUser.getSelectedRow(), 4).toString();

        DlgModificarVehiculo ventanaFlotante = new DlgModificarVehiculo(this, true, id, nombre, rol, estado);
        ventanaFlotante.setVisible(true);
        cargarTabla();
    }//GEN-LAST:event_btnModificarActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new FrmGestionUsuarios(false).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardarUser;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}