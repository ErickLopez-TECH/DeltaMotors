/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion.Usuarios;

import Datos.Estructuras;
import static Datos.Estructuras.listaUsuarios;
import Datos.ObjUsuarios;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

/**
 *
 * @author triamus
 */
public class FrmConsultarUsuarios extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmConsultarUsuarios.class.getName());

    private DefaultTableModel modeloTabla;
    private TableRowSorter<DefaultTableModel> sorter;

    public FrmConsultarUsuarios() {
        initComponents();
        
        // Desactiva la capacidad de maximizar la ventana
setResizable(false);
        // 1. Aplicamos el estilo oscuro personalizado
        aplicarEstiloOscuro();
        
        // 2. Creamos la instancia 'est' para manejar los archivos
        Estructuras est = new Estructuras();
        
        // 3. Limpiamos la lista en memoria para evitar duplicados si se abre varias veces
        listaUsuarios.clear();
        
        // 4. Leemos el archivo Usuarios.txt para llenar la lista en la RAM
        est.leerArchivoUsuarios();
        
        // 5. Actualizamos los contadores de la aplicación
        Estructuras.actualizarTodosLosContadores();
        
        // Cargamos los datos en la tabla visual
        cargarTablas();
        
        // Escucha en tiempo real para el buscador dinámico
        txtBuscardor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filtrarBuscador(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filtrarBuscador(); }
            @Override
            public void changedUpdate(DocumentEvent e) { filtrarBuscador(); }
        });
    }
    
    private void aplicarEstiloOscuro() {
        Color fondoOscuro = new Color(18, 22, 33);
        Color panelOscuro = new Color(26, 32, 48);
        Color textoClaro = new Color(220, 225, 235);

        getContentPane().setBackground(fondoOscuro);

        // Etiquetas
        jLabel1.setForeground(Color.WHITE);
        jLabel2.setForeground(textoClaro);

        // Buscador
        txtBuscardor.setBackground(panelOscuro);
        txtBuscardor.setForeground(Color.WHITE);
        txtBuscardor.setCaretColor(Color.WHITE);

        // Tabla y contenedor
        tblUsuarios.setBackground(panelOscuro);
        tblUsuarios.setForeground(Color.WHITE);
        tblUsuarios.setGridColor(new Color(45, 55, 80));
        tblUsuarios.setSelectionBackground(new Color(40, 75, 180));
        tblUsuarios.setSelectionForeground(Color.WHITE);
        
        jScrollPane2.getViewport().setBackground(panelOscuro);
        jScrollPane2.setBackground(panelOscuro);
    }
                
    private void cargarTablas(){
        String[] columnas = {"Id", "Usuario", "Cédula", "Rol", "Estado"};
        
        // Definimos el modelo y bloqueamos la edición directa con isCellEditable
        modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Validamos que la lista tenga elementos para pasarlos a la tabla
        if (listaUsuarios != null) {
            for (int i = 0; i < listaUsuarios.size(); i++) {
                ObjUsuarios u = listaUsuarios.get(i);
                
                Object[] fila = {
                    u.getId(),
                    u.getUsuario(),
                    u.getCedula(),
                    u.getRol(),
                    u.getEstado()
                };
                modeloTabla.addRow(fila);
            }
        }
        
        tblUsuarios.setModel(modeloTabla);
        
        // Configuramos el RowSorter para el buscador
        sorter = new TableRowSorter<>(modeloTabla);
        tblUsuarios.setRowSorter(sorter);
    }

    private void filtrarBuscador() {
        String textoBusqueda = txtBuscardor.getText().trim();
        if (textoBusqueda.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscardor = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jbEliminar = new javax.swing.JButton();
        JbModificar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Consultar usuarios");

        jLabel2.setText("Buscar: ");

        txtBuscardor.addActionListener(this::txtBuscardorActionPerformed);

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblUsuarios);

        jbEliminar.setText("Eliminar");
        jbEliminar.addActionListener(this::jbEliminarActionPerformed);

        JbModificar.setText("Modificar");
        JbModificar.addActionListener(this::JbModificarActionPerformed);

        jButton2.setText("Regresar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(282, 282, 282))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscardor, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JbModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBuscardor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbEliminar)
                    .addComponent(JbModificar)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscardorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscardorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscardorActionPerformed

    private void jbEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEliminarActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = tblUsuarios.getSelectedRow();
        
        if(filaSeleccionada == -1){
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor selecione una columna", "Atencion", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //si se filtro debe de busvra el inidece
        int filaModelo = tblUsuarios.convertRowIndexToModel(filaSeleccionada);
        
        // 3. Pedimos confirmación antes de borrar
    int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this, 
        "¿Está seguro de que desea eliminar este usuario?", 
        "Confirmar Eliminación", javax.swing.JOptionPane.YES_NO_OPTION);
    
    if(confirmacion == javax.swing.JOptionPane.YES_OPTION){
        Estructuras.listaUsuarios.remove(filaModelo);
        
        Estructuras est = new Estructuras();
        est.escribeArchivoUsuarios();
        
        cargarTablas();
        
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Usuario eliminado correctamente.", 
            "Información", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    }//GEN-LAST:event_jbEliminarActionPerformed

    private void JbModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbModificarActionPerformed
        // TODO add your handling code here:
        
        int filaSeleccionada = tblUsuarios.getSelectedRow();
        
        if(filaSeleccionada == -1){
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor selecione una columna", "Atencion", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int filaModelo = tblUsuarios.convertRowIndexToModel(filaSeleccionada);
        
        ObjUsuarios usuarioSeleccionado = Estructuras.listaUsuarios.get(filaModelo);
        
        
        javax.swing.JTextField txtNuevoUsuario = new javax.swing.JTextField(usuarioSeleccionado.getUsuario());
        javax.swing.JComboBox<String> cmbNuevoRol = new javax.swing.JComboBox<>(new String[]{"Administrador", "Operador"});
        
    
        Object[] mensajeFlotante ={
            "Modificar Usuario:",
            "Usuario:", txtNuevoUsuario,
            "Rol:", cmbNuevoRol
        };
        
        int opcion = javax.swing.JOptionPane.showConfirmDialog(this, mensajeFlotante,
                "Modificar Usaurio seleccionado", javax.swing.JOptionPane.OK_CANCEL_OPTION);
        
        if (opcion == javax.swing.JOptionPane.OK_OPTION) {
            usuarioSeleccionado.setUsuario(txtNuevoUsuario.getText().trim());
            usuarioSeleccionado.setRol(cmbNuevoRol.getSelectedItem().toString().trim());
           
             Datos.Estructuras est = new Datos.Estructuras();
            est.escribeArchivoUsuarios();
            
            cargarTablas();
            
            javax.swing.JOptionPane.showMessageDialog(this, 
                "¡Usuario modificado con éxito!", 
                "Información", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_JbModificarActionPerformed

    //metodo de regresar
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        FrmMenuUsuarios ventaMenu = new FrmMenuUsuarios();
        ventaMenu.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    
        
    
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

        java.awt.EventQueue.invokeLater(() -> new FrmConsultarUsuarios().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbModificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbEliminar;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtBuscardor;
    // End of variables declaration//GEN-END:variables
}