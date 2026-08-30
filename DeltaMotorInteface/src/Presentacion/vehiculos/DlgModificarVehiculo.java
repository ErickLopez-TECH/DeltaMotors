package Presentacion.vehiculos;

import Datos.Estructuras;
import Datos.objUsuarios;
import Datos.objVehiculo;
import Logica.logicaVehiculo;
import javax.swing.JOptionPane;

public class DlgModificarVehiculo extends javax.swing.JDialog {

    private int idVehiculoEditable;

    // Constructor que recibe los datos desde la tabla principal (sin contraseña)
    public DlgModificarVehiculo(java.awt.Frame parent, boolean modal, int id, double kilometraje, String estado) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent); // Centra la ventana flotante
        
        this.idVehiculoEditable = id;
        
        // Rellenamos los campos con los datos actuales del usuario seleccionado
        String kilometrajeStr = String.valueOf(kilometraje);
        txtKilometraje.setText(kilometrajeStr);
        cmbEstadoMod.setSelectedItem(estado);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtKilometraje = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbEstadoMod = new javax.swing.JComboBox<>();
        btnGuardarCambios = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Usuario");

        jLabel1.setText("Kilometraje");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKilometraje)
                            .addComponent(cmbEstadoMod, 0, 160, Short.MAX_VALUE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKilometraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbEstadoMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCambios)
                    .addComponent(btnCancelar))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public boolean esNumero(String texto) {
        // Valida números enteros o decimales (ej: 10000 o 10000.78)
        return texto != null && texto.matches("^\\d+(\\.\\d+)?$");
    }
    
    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed
        String nuevoKm = txtKilometraje.getText().trim();
        
        
        if (!esNumero(nuevoKm)) {
            JOptionPane.showMessageDialog(this, "El kilometraje debe contener únicamente números. Ejemplo: 10.4 o 10", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtKilometraje.requestFocus();
            return;
        }
    
        if (nuevoKm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El Kilometraje no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (cmbEstadoMod.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un estado válido.");
            return;
        }
        String nuevoEstado = cmbEstadoMod.getSelectedItem().toString();
        double kilometrajeD = Double.parseDouble(nuevoKm);
        
        
        // Actualizamos en el archivo de texto usando tu clase Estructuras
        Estructuras est = new Estructuras();
        est.leerArchivoVehiculo();
        
        Logica.logicaVehiculo Logica = new logicaVehiculo();
        
        boolean modificado = false;
        java.util.ArrayList<objVehiculo> lista = Logica.obtenerListaVehiculos();
        
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == idVehiculoEditable) {
                
                lista.get(i).setKilometraje(kilometrajeD);
                lista.get(i).setEstado(nuevoEstado);
                
                modificado = true;
                break;
            }
        }
        
        if (modificado) {
            
            //configarra lista y luego se escribe
            est.setListaVehiculo(lista);
            est.escribeArchivoVehiculo();
            JOptionPane.showMessageDialog(this, "¡Modificación guardada con éxito!", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // Cierra la ventana flotante
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el vehiculo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarCambiosActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose(); // Cierra la ventana sin hacer cambios
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardarCambios;
    private javax.swing.JComboBox<String> cmbEstadoMod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtKilometraje;
    // End of variables declaration//GEN-END:variables
}