package Presentacion.Usuario;

import Datos.Estructuras;
import Datos.objUsuarios;
import javax.swing.JOptionPane;

public class DlgModificarVehiculo extends javax.swing.JDialog {

    private int idUsuarioEditable;

    // Constructor que recibe los datos desde la tabla principal (sin contraseña)
    public DlgModificarVehiculo(java.awt.Frame parent, boolean modal, int id, String nombre, String rol, String estado) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent); // Centra la ventana flotante
        
        this.idUsuarioEditable = id;
        
        // Rellenamos los campos con los datos actuales del usuario seleccionado
        txtUsuarioMod.setText(nombre);
        cmbRolMod.setSelectedItem(rol);
        cmbEstadoMod.setSelectedItem(estado);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUsuarioMod = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbRolMod = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbEstadoMod = new javax.swing.JComboBox<>();
        btnGuardarCambios = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Usuario");

        jLabel1.setText("Usuario:");

        jLabel3.setText("Rol:");

        cmbRolMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Operador", "Administrador" }));

        jLabel4.setText("Estado:");

        cmbEstadoMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Activo", "Inactivo" }));

        btnGuardarCambios.setText("Guardar Cambios");
        btnGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCambiosActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardarCambios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsuarioMod)
                            .addComponent(cmbRolMod, 0, 160, Short.MAX_VALUE)
                            .addComponent(cmbEstadoMod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuarioMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbRolMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbEstadoMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCambios)
                    .addComponent(btnCancelar))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed
        String nuevoNombre = txtUsuarioMod.getText().trim();
        
        if (cmbRolMod.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un rol válido.");
            return;
        }
        String nuevoRol = cmbRolMod.getSelectedItem().toString();
        
        if (cmbEstadoMod.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un estado válido.");
            return;
        }
        String nuevoEstado = cmbEstadoMod.getSelectedItem().toString();
        
        if (nuevoNombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de usuario no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Actualizamos en el archivo de texto usando tu clase Estructuras
        Estructuras est = new Estructuras();
        est.leerArchivoUsuarios();
        
        boolean modificado = false;
        java.util.ArrayList<objUsuarios> lista = est.getListaUsuarios();
        
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == idUsuarioEditable) {
                lista.get(i).setNombre(nuevoNombre);
                lista.get(i).setRol(nuevoRol);
                lista.get(i).setEstado(nuevoEstado);
                // La contraseña original se queda intacta automáticamente
                modificado = true;
                break;
            }
        }
        
        if (modificado) {
            est.escribeArchivoUsuarios();
            JOptionPane.showMessageDialog(this, "¡Modificación guardada con éxito!", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // Cierra la ventana flotante
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarCambiosActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose(); // Cierra la ventana sin hacer cambios
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardarCambios;
    private javax.swing.JComboBox<String> cmbEstadoMod;
    private javax.swing.JComboBox<String> cmbRolMod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtUsuarioMod;
    // End of variables declaration//GEN-END:variables
}