/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion.Usuarios;

import Presentacion.Usuarios.FrmMenuUsuarios;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author triamus
 */
public class FrmRegistrarUsuario extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmRegistrarUsuario.class.getName());

    public FrmRegistrarUsuario() {
        initComponents();
        
        // Centrar la ventana al abrir
        setLocationRelativeTo(null);
        
        // Desactiva la capacidad de maximizar la ventana
setResizable(false);
        // Aplicamos diseño oscuro personalizado al iniciar
        aplicarEstiloOscuro();
    }

    private void aplicarEstiloOscuro() {
        Color fondoOscuro = new Color(18, 22, 33);
        Color panelOscuro = new Color(26, 32, 48);
        Color textoClaro = new Color(220, 225, 235);

        // Fondo general de la ventana y del panel contenedor central
        getContentPane().setBackground(fondoOscuro);
        

        // Ajustar colores de etiquetas
        jLabel1.setForeground(Color.WHITE);
        jLabel2.setForeground(textoClaro);
        jLabel4.setForeground(textoClaro);
        jLabel5.setForeground(textoClaro);
        jLabel6.setForeground(textoClaro);

        // Campos de texto oscuros
        txtUsuario.setBackground(panelOscuro);
        txtUsuario.setForeground(Color.WHITE);
        txtUsuario.setCaretColor(Color.WHITE);

        txtCedula.setBackground(panelOscuro);
        txtCedula.setForeground(Color.WHITE);
        txtCedula.setCaretColor(Color.WHITE);

        txtContrasena.setBackground(panelOscuro);
        txtContrasena.setForeground(Color.WHITE);
        txtContrasena.setCaretColor(Color.WHITE);

        cmbRol.setBackground(panelOscuro);
        cmbRol.setForeground(Color.WHITE);

        // Botones modernos
        btnGuardar.setBackground(new Color(40, 75, 180));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        
        btnRegresar.setBackground(new Color(50, 55, 65));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFocusPainted(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        cmbRol = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("REGISTRO DE USUARIOS");

        jLabel2.setText("Usuario: ");

        txtUsuario.addActionListener(this::txtUsuarioActionPerformed);

        jLabel4.setText("Cedula:");

        txtCedula.addActionListener(this::txtCedulaActionPerformed);

        jLabel5.setText("Contrasena:");

        txtContrasena.addActionListener(this::txtContrasenaActionPerformed);

        cmbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Operador", "Administrador" }));
        cmbRol.addActionListener(this::cmbRolActionPerformed);

        jLabel6.setText("Rol:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel3))
                                    .addComponent(jLabel5))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                    .addComponent(txtContrasena)
                                    .addComponent(txtUsuario))
                                .addGap(28, 28, 28)
                                .addComponent(jLabel6)
                                .addGap(26, 26, 26)
                                .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(btnGuardar)
                                .addGap(39, 39, 39)
                                .addComponent(btnRegresar)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuario))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnRegresar))
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void txtContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaActionPerformed

    private void cmbRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRolActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String usuario = txtUsuario.getText().trim();
        String cedula = txtCedula.getText().trim();
        String password = new String(txtContrasena.getPassword()).trim();
        
        if (usuario.isEmpty() || cedula.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (Logica.Usuarios.usuarioRepetido(usuario) == 1) {
            JOptionPane.showMessageDialog(this, "El usuario ya está registrado. Intente con otro.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (Logica.Usuarios.CedulaRepetido(cedula) == 1) {
            JOptionPane.showMessageDialog(this, "La cédula ya está registrada. Intente con otra.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int opcionRol = cmbRol.getSelectedIndex() + 1; 
        String rolStr = Logica.Usuarios.devolverRol(opcionRol);

        Datos.ObjUsuarios nuevoUsuario = new Datos.ObjUsuarios();
        nuevoUsuario.setUsuario(usuario);
        nuevoUsuario.setCedula(cedula);
        nuevoUsuario.setContrasena(password);
        nuevoUsuario.setRol(rolStr);
        nuevoUsuario.setEstado("Activo");

        Logica.Usuarios.Almacen.agregarUsuarios(nuevoUsuario);
        Logica.Usuarios.Almacen.escribeArchivoUsuarios();

        JOptionPane.showMessageDialog(this, "¡Usuario registrado con éxito!", "Información", JOptionPane.INFORMATION_MESSAGE);

        txtUsuario.setText("");
        txtCedula.setText("");
        txtContrasena.setText("");
        cmbRol.setSelectedIndex(0);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        FrmMenuUsuarios ventanaUsuarios = new FrmMenuUsuarios();
        ventanaUsuarios.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new FrmRegistrarUsuario().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}