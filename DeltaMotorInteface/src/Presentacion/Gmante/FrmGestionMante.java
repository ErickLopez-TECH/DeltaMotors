package Presentacion.Gmante;

import javax.swing.JOptionPane;
import Datos.objGestionMante;
import Logica.logicaGestionMante;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;



public class FrmGestionMante extends javax.swing.JFrame {

    public FrmGestionMante() {
        initComponents();
        iniciarFunciones();
        this.setLocationRelativeTo(null);
    }
    
    public void iniciarFunciones(){
        cargarTabla();
        alternarColoresTabla();
        
        
        //escuchadores de filtro
         //buscador 
        txtBuscador.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
    @Override
    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        filtrarMante();
    }

    @Override
    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        filtrarMante();
    }

    @Override
    public void changedUpdate(javax.swing.event.DocumentEvent e) {
        filtrarMante();
    }
    });
        
    }

    public void alternarColoresTabla() {
    tblMante.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
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
    
    public void filtrarMante(){
        // 1. Obtenemos el texto del buscador
    String textoFiltro = txtBuscador.getText();
    
    
    logicaGestionMante logica = new logicaGestionMante();
    ArrayList<objGestionMante> listaFiltrada = logica.filtrarMante(textoFiltro);
    
    // 3. Rellenar datos en la tabla
    String[] columnas = {"Id", "Nombre", "Estado"}; 
    Object[][] datos = new Object[listaFiltrada.size()][columnas.length];
    
    for (int i = 0; i < listaFiltrada.size(); i++) {
        objGestionMante m = listaFiltrada.get(i);
        
        datos[i][0] = m.getId();
        datos[i][1] = m.getNombre();
        datos[i][2] = m.getEstado();
        
    }
    
    DefaultTableModel modeloTabla = new DefaultTableModel(datos, columnas);
    tblMante.setModel(modeloTabla);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelIngresar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jPanelModificar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMante = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtBuscador = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Mantenimiento");

        jLabel1.setText("Nombre Mantenimiento:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelIngresarLayout = new javax.swing.GroupLayout(jPanelIngresar);
        jPanelIngresar.setLayout(jPanelIngresarLayout);
        jPanelIngresarLayout.setHorizontalGroup(
            jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIngresarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar)
                    .addGroup(jPanelIngresarLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanelIngresarLayout.setVerticalGroup(
            jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIngresarLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnGuardar)
                .addContainerGap(222, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ingresar", jPanelIngresar);

        tblMante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblMante);

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

        jLabel2.setText("Buscador:");

        javax.swing.GroupLayout jPanelModificarLayout = new javax.swing.GroupLayout(jPanelModificar);
        jPanelModificar.setLayout(jPanelModificarLayout);
        jPanelModificarLayout.setHorizontalGroup(
            jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModificarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelModificarLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelModificarLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
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
                .addGap(15, 15, 15)
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar))
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Historial / Gestión", jPanelModificar);

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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        
        String nombre = txtnombre.getText().trim();
        String estado = "Activo";
        
        //validacion de vacio
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe digitar el nombre del mantenimiento", "Advertencia", JOptionPane.WARNING_MESSAGE);
        txtnombre.requestFocus(); // Ubica el cursor exactamente aquí
        return;
        }
        
        
        
        //Logica
        logicaGestionMante logica = new logicaGestionMante();
        
        boolean repetido = logica.existenciaMante(nombre);
        
        if(repetido){
            JOptionPane.showMessageDialog(this, "Mantenimiento existente en el registro", "Advertencia", JOptionPane.WARNING_MESSAGE);
        txtnombre.requestFocus(); // Ubica el cursor exactamente aquí
        return;
        }
        
        boolean exito = logica.registrarMante(nombre, estado);
        
        if(exito){
            
            JOptionPane.showMessageDialog(this, "Mantenimiento registrado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
            
            txtnombre.setText("");
            
        }else{
            JOptionPane.showMessageDialog(this, "Sucedio algo inesperado al guardar el Mantenimiento", "Error", JOptionPane.WARNING_MESSAGE);
            
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        
        int filaSeleccionada = tblMante.getSelectedRow();
        
         if(filaSeleccionada == -1){
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
         
         int idMante = Integer.parseInt(tblMante.getValueAt(filaSeleccionada, 0).toString());
         
         int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar el mantenimeinto:?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);
         
         boolean eliminado = false;
         logicaGestionMante logica = new logicaGestionMante();
          if(respuesta == JOptionPane.YES_OPTION){
              
              
               eliminado = logica.eliminarMante(idMante);
              
          }
          
          if (eliminado) {
                // mensaje y actualizar lista
                JOptionPane.showMessageDialog(this, "¡Eliminado con éxito!", "Información", JOptionPane.INFORMATION_MESSAGE);
                
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo encontrar el vehiculo en los registros.", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        
    int filaSeleccionada = tblMante.getSelectedRow();
    
    // 1. Validar que haya seleccionado una fila de la tabla
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un mantenimiento de la tabla para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // 2. Extraer los datos actuales de la fila seleccionada
    int id = Integer.parseInt(tblMante.getValueAt(filaSeleccionada, 0).toString());
    String nombreActual = tblMante.getValueAt(filaSeleccionada, 1).toString();
    String estadoActual = tblMante.getValueAt(filaSeleccionada, 2).toString();
    
    DlgModificarMante ventanaModificar = new DlgModificarMante(this, true, id, nombreActual, estadoActual);
        ventanaModificar.setVisible(true);
        
        cargarTabla();
    }//GEN-LAST:event_btnModificarActionPerformed

    public void cargarTabla(){
        logicaGestionMante logica = new logicaGestionMante();
        ArrayList<objGestionMante> lista = logica.obtenerListaMante();
        
        String[] columnas = {"Id","Nombre","Estado"};
        
        Object[][] Datos = new Object[lista.size()][3];
        
        for (int i = 0; i < lista.size(); i++) {
            objGestionMante m = lista.get(i);
            
            Datos[i][0]= m.getId();
            Datos[i][1]= m.getNombre();
            Datos[i][2]= m.getEstado();
            
        }
        
        DefaultTableModel modeloTabla = new DefaultTableModel(Datos, columnas);
        tblMante.setModel(modeloTabla);
        
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmGestionMante().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelIngresar;
    private javax.swing.JPanel jPanelModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblMante;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}