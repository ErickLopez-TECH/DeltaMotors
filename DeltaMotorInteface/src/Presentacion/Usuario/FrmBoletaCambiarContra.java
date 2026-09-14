package Presentacion.Usuario;

import Logica.logicaUsuarios;
import Presentacion.Loggin;
import Presentacion.MenusPrincipales.FrmMenuAdmin;
import Presentacion.MenusPrincipales.FrmMenuOperador;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FrmBoletaCambiarContra extends javax.swing.JFrame {

    // Paleta de Colores Moderna
    private final Color COLOR_PRIMARY = new Color(41, 128, 185);     
    private final Color COLOR_SECONDARY = new Color(52, 152, 219);   
    private final Color COLOR_BACKGROUND = new Color(245, 247, 250); 
    private final Color COLOR_PANEL = new Color(255, 255, 255);      
    private final Color COLOR_TEXT = new Color(44, 62, 80); 
    private String nameUser;
    private boolean rolActual;
    

    public FrmBoletaCambiarContra(boolean rol,String user) {
        initComponents();
        this.rolActual = rol;
        this.nameUser = user;
        this.setLocationRelativeTo(null);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        aplicarEstilosModernos();
        
        
        jTabbedPane1.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                if(jTabbedPane1.getSelectedIndex() == 1){
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
        
        jTabbedPane1.setBackground(COLOR_BACKGROUND);
        
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        jLabel1.setFont(labelFont);
        jLabel2.setFont(labelFont);
        
        
        jLabel1.setForeground(COLOR_TEXT);
        jLabel2.setForeground(COLOR_TEXT);
        txtcontra.setForeground(COLOR_TEXT);
        
        estilizarBoton(btnGuardar, COLOR_PRIMARY);
        
        
        
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
        txtcontra = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        txtContraRepetida = new javax.swing.JTextField();
        jTabbedPaneRegresar = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión y Asignación de Mantenimientos");

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(41, 128, 185));
        jLabelTitulo.setText("Cambiar Contrasena");

        jLabel1.setText("Ingrese nueva contrasena");

        jLabel2.setText("Vuelva a digitar la contrasena");

        btnGuardar.setText("Guardar Nueva contrasena");
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
                .addGap(40, 40, 40)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelTitulo)
                    .addGroup(jPanelIngresarLayout.createSequentialGroup()
                        .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtcontra, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(txtContraRepetida)))
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        jPanelIngresarLayout.setVerticalGroup(
            jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIngresarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabelTitulo)
                .addGap(25, 25, 25)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcontra, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtContraRepetida, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(174, 174, 174)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cambiar", jPanelIngresar);
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String contra = txtcontra.getText().trim();
        
        
        if (!validarCampos()) {
            return;
        }
        
        logicaUsuarios logicaU = new logicaUsuarios();
        boolean actualizarContra = logicaU.cambiarContrasena(this.nameUser, contra);
            
        if (actualizarContra) {
            JOptionPane.showMessageDialog(this, "Password Cambiada con exito: "+ nameUser + "Vuelva a loguearse", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            Loggin regresar = new Loggin();
            regresar.setVisible(true);
            this.dispose();
            
        } else {
            JOptionPane.showMessageDialog(this, "Surgio un error inesperado vuelva a intentar o contacte al equipo de soporte.", "Error", JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private boolean validarCampos(){
        if(txtcontra.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "No se permite espacios en blancos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtcontra.requestFocus();
            return false;
        }
        
        if(!txtcontra.getText().trim().equals(txtContraRepetida.getText().trim())){
            JOptionPane.showMessageDialog(this, "Su contrasena debe de ser igual en ambos espacios, corrija!!", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtcontra.requestFocus();
            return false;
        }
        
        return true;
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmBoletaCambiarContra(false,"").setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelIngresar;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPaneRegresar;
    private javax.swing.JTable tblAsignaciones;
    private javax.swing.JTextField txtContraRepetida;
    private javax.swing.JTextField txtcontra;
    // End of variables declaration//GEN-END:variables
}