package Presentacion.AsignacionMante;

import Datos.Estructuras;
import Logica.logicaAsignacionMante;
import Logica.logicaVehiculo;
import javax.swing.JOptionPane;

public class DlgModificarAsigna extends javax.swing.JDialog {

    private int idAsignaEditable;
    private String placaActualizar;

    // Constructor que recibe los datos desde la tabla principal (sin contraseña)
    public DlgModificarAsigna(java.awt.Frame parent, boolean modal, int id, String tipoPeriodo, double periodicidad, double kmUltimo, String placa) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent); // Centra la ventana flotante
        
        this.idAsignaEditable = id;
        this.placaActualizar = placa;
        
        // Rellenamos los campos con los datos actuales del mante seleccionado
        cmbTipoPeriodo.addItem("Dias");
        cmbTipoPeriodo.addItem("Km");
        cmbTipoPeriodo.setSelectedItem(tipoPeriodo);
        
        
        String periodicidadStr = String.valueOf(periodicidad);
        txtPeriodo.setText(periodicidadStr);
        String km = String.valueOf(kmUltimo);
        txtKm.setText(km);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbTipoPeriodo = new javax.swing.JComboBox<>();
        btnGuardarCambios = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtPeriodo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtKm = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Usuario");

        jLabel1.setText("Tipo de periodo:");

        jLabel4.setText("Periodicidad:");

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

        jLabel2.setText("Kilomtraje actual:");

        txtKm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbTipoPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPeriodo, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtKm, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardarCambios)
                                .addGap(59, 59, 59)
                                .addComponent(btnCancelar)))
                        .addContainerGap(10, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbTipoPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCambios)
                    .addComponent(btnCancelar))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    
    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed
        String periodicidad = txtPeriodo.getText().trim();
        String kmUltimo = txtKm.getText().trim();
    
        if (periodicidad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El periodo no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtPeriodo.requestFocus();
            return;
        }
        
        if (kmUltimo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El km no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtKm.requestFocus();
            return;
        }
        
        
        
        double periodoParse = Double.parseDouble(periodicidad);
        double kmUltimoParse = Double.parseDouble(kmUltimo);
        String tipoPeriodo = cmbTipoPeriodo.getSelectedItem().toString();
        
        
        //actualizar modificacion
        Logica.logicaAsignacionMante Logica = new logicaAsignacionMante();
        
        //todo el modificado desde logica --> estructuras
        boolean modificado = Logica.modificarAsignacion(idAsignaEditable, tipoPeriodo, periodoParse, kmUltimoParse);

        if (modificado) {
            
            //configarra lista y luego se escribe
            
            JOptionPane.showMessageDialog(this, "¡Modificación guardada con éxito!", "Información", JOptionPane.INFORMATION_MESSAGE);
            logicaVehiculo actualizarKm = new logicaVehiculo();
            boolean actualizar = actualizarKm.modificarVehiculo(placaActualizar, kmUltimoParse);
            this.dispose(); // Cierra la ventana flotante
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el vehiculo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarCambiosActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose(); // Cierra la ventana sin hacer cambios
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtKmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKmActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardarCambios;
    private javax.swing.JComboBox<String> cmbTipoPeriodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtKm;
    private javax.swing.JTextField txtPeriodo;
    // End of variables declaration//GEN-END:variables
}