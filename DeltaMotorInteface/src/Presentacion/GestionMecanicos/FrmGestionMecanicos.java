package Presentacion.GestionMecanicos;


import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import Logica.logicaMecanicos;
import Datos.objMecanicos;
import Presentacion.MenusPrincipales.FrmMenuAdmin;
import Presentacion.MenusPrincipales.FrmMenuOperador;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class FrmGestionMecanicos extends javax.swing.JFrame {

    // Paleta de Colores Moderna
    private final Color COLOR_PRIMARY = new Color(41, 128, 185);     
    private final Color COLOR_SECONDARY = new Color(52, 152, 219);   
    private final Color COLOR_BACKGROUND = new Color(245, 247, 250); 
    private final Color COLOR_PANEL = new Color(255, 255, 255);      
    private final Color COLOR_TEXT = new Color(44, 62, 80);   
    
    private DefaultTableModel modeloTabla;
    private boolean rolActual;
    
    
    public FrmGestionMecanicos(boolean rol) {
        this.rolActual = rol;
    
        initComponents();
        this.setLocationRelativeTo(null);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        aplicarEstilosModernos();
        
        cargarTabla();
        
        
        txtBuscador.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                 filtrarBuscador();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarBuscador();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
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
        
        jLabel10.setFont(labelFont);
        
        jLabel1.setForeground(COLOR_TEXT);
        jLabel2.setForeground(COLOR_TEXT);
        
        jLabel10.setForeground(COLOR_TEXT);
        
        estilizarBoton(btnGuardarBoleta, COLOR_PRIMARY);
        estilizarBoton(btnModificar, COLOR_SECONDARY);
        estilizarBoton(btnEliminar, new Color(231, 76, 60));
        
        
                
        if (tblMecanicos != null) {
            tblMecanicos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            tblMecanicos.setRowHeight(28);
            if (tblMecanicos.getTableHeader() != null) {
                tblMecanicos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
                tblMecanicos.getTableHeader().setBackground(COLOR_PRIMARY);
                tblMecanicos.getTableHeader().setForeground(Color.WHITE);
            }
            tblMecanicos.setSelectionBackground(new Color(189, 195, 199));
            tblMecanicos.setSelectionForeground(Color.BLACK);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelIngresar = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnGuardarBoleta = new javax.swing.JButton();
        txtCedula = new javax.swing.JTextField();
        jPanelModificar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMecanicos = new javax.swing.JTable();
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
        jLabelTitulo.setText("Registro de Nuevo Mecanico");

        jLabel1.setText("Nombre:");

        jLabel2.setText("Cedula:");

        btnGuardarBoleta.setText("Guardar Mecanico");
        btnGuardarBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarBoletaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelIngresarLayout = new javax.swing.GroupLayout(jPanelIngresar);
        jPanelIngresar.setLayout(jPanelIngresarLayout);
        jPanelIngresarLayout.setHorizontalGroup(
            jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIngresarLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardarBoleta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelIngresarLayout.createSequentialGroup()
                        .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTitulo)
                            .addGroup(jPanelIngresarLayout.createSequentialGroup()
                                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(77, 77, 77)))
                .addContainerGap(222, Short.MAX_VALUE))
        );
        jPanelIngresarLayout.setVerticalGroup(
            jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIngresarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabelTitulo)
                .addGap(25, 25, 25)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(157, 157, 157)
                .addComponent(btnGuardarBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Agregar", jPanelIngresar);

        tblMecanicos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblMecanicos);

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

        jTabbedPane1.addTab("Consultar", jPanelModificar);
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

    private void btnGuardarBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarBoletaActionPerformed
        // TODO add your handling code here:
        String nombre = txtNombre.getText().trim();
        String cedula = txtCedula.getText().trim();
        logicaMecanicos logica = new logicaMecanicos();
        //validaciones de vacio
        if(nombre.isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor llene el campo del nombre","Error",JOptionPane.ERROR_MESSAGE);
            txtNombre.requestFocus();
            return;
        }
        
        if(cedula.isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor complete el campo de la cedula","Error",JOptionPane.ERROR_MESSAGE);
            txtCedula.requestFocus();
            return;
        }
        
        //mecanico existente
        if(logica.mecanicoRepetido(cedula)){
            JOptionPane.showMessageDialog(this, "Este mecanico ya existe en los registros");
            return;
        }
        
        boolean exito = logica.registrarMecanico(nombre, cedula);
        
        if(exito){
            JOptionPane.showMessageDialog(this, "El mecanico se ha registrado","Exito",JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
        }else{
            JOptionPane.showMessageDialog(this, "Ha sucedido un error inesperado, Vuelva a intentar","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarBoletaActionPerformed

    
    //boton de elominar
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = tblMecanicos.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un mecanico para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = Integer.parseInt(tblMecanicos.getValueAt(filaSeleccionada, 0).toString());
        logicaMecanicos logica = new logicaMecanicos();
        
        boolean eliminado = logica.eliminarMecanico(id);
        
        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Mecanico eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el mecanico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    
    public void filtrarBuscador(){
        String filtro = txtBuscador.getText().toLowerCase().trim();
        
        logicaMecanicos logica = new logicaMecanicos();
        
        //esta tendra la lista de la boleta
        ArrayList<objMecanicos> lista = logica.obtenerListaMecanicos();
        
        //establecer que se msotrara despuesde hacer el filtro(columnas)
        String[] columnas = {"ID","Nombre","Cedula"};
        DefaultTableModel modeloFiltrado = new DefaultTableModel(new Object[0][3],columnas);
        
        for (objMecanicos M: lista) {
            //el nombre puede estar en minusculas todo y no aparesera(case-sentive)
            String nombre =M.getNombre().toLowerCase();
            if((nombre.contains(filtro))||(M.getCedula().contains(filtro))){
                
                Object[] fila = {
                    M.getId(),
                    M.getNombre(),
                    M.getCedula()
                };
                modeloFiltrado.addRow(fila);
            }
              
        }
        
        tblMecanicos.setModel(modeloFiltrado);
    }
    
    public void cargarTabla() {
    logicaMecanicos logica = new logicaMecanicos();
    ArrayList lista = logica.obtenerListaMecanicos();
    
    String[] columnas = {"ID", "Nombre", "Cedula"};
    Object[][] Datos = new Object[lista.size()][3];
    
    for (int i = 0; i < lista.size(); i++) {
        objMecanicos M = (objMecanicos) lista.get(i);
        Datos[i][0] = M.getId();
        Datos[i][1] = M.getNombre();
        Datos[i][2] = M.getCedula();
    }
    
    modeloTabla = new DefaultTableModel(Datos, columnas);
    
    tblMecanicos.setModel(modeloTabla);
}
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmGestionMecanicos(false).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardarBoleta;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelIngresar;
    private javax.swing.JPanel jPanelModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPaneRegresar;
    private javax.swing.JTable tblMecanicos;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}