/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ffmm;

import Clases.Conecta;
import static Clases.Conecta.conexion;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ZuluCorp
 */
public class Saldos extends javax.swing.JInternalFrame {
 
    /**
     * Creates new form Saldos
     */
    public Saldos() {
        initComponents();
        setTitle("Ventana Saldos");
        mostrardatos("");
        anchocolumnas();
        CargarCbma();
        Cargartxtma();
        cbma.setVisible(false);
        calendar.getDateEditor().setEnabled(false);
        txtdiferencia.setText("0");
        /*DefaultTableModel tbsaldos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                if (columna > 3) {
                    return true;
                }
                return false;
            }
            };*/
    }
    void mostrardatos(String valor) {
        /*Date fc = calendar.getDate();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-yyyy");
        String fec = "" + formatofecha.format(fc);*/
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Fecha");
        modelo.addColumn("Monto Saldo");
        modelo.addColumn("Monto Diferencia");
        tbsaldos.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT Fecha,Monto,Diferencia FROM saldos ORDER BY ID desc";
        } else {
            //sql = "SELECT * FROM saldos WHERE Fecha='" + fec + "' ORDER BY ID desc";
        }

        String[] datos = new String[3];
        try {
            conexion = Conecta.getConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                DecimalFormat formatea = new DecimalFormat("###,###.##");
                datos[0] = rs.getString(1);
                datos[1] = "$  "+(formatea.format(rs.getInt(2)));
                datos[2] = "$  "+(formatea.format(rs.getInt(3)));
                modelo.addRow(datos);
            }
            tbsaldos.setModel(modelo);
            //tbsaldos.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage().toString());
        }
    }
    
    void anchocolumnas() {
        tbsaldos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        tbsaldos.getColumnModel().getColumn(0).setWidth(75);
        tbsaldos.getColumnModel().getColumn(0).setMaxWidth(75);
        tbsaldos.getColumnModel().getColumn(0).setMinWidth(75);
        
        tbsaldos.getColumnModel().getColumn(1).setWidth(200);
        tbsaldos.getColumnModel().getColumn(1).setMaxWidth(200);
        tbsaldos.getColumnModel().getColumn(1).setMinWidth(200);
        
        tbsaldos.getColumnModel().getColumn(2).setWidth(200);
        tbsaldos.getColumnModel().getColumn(2).setMaxWidth(200);
        tbsaldos.getColumnModel().getColumn(2).setMinWidth(200);
    }
    private void CargarCbma(){
        //Carga de Combo
        try {
            conexion = Conecta.getConexion();
            Statement st = conexion.createStatement();
            String sql = "SELECT max(ID) FROM saldos";
            //Ejecutar consulta
            ResultSet rs = st.executeQuery(sql);
            //Limpiamos el Combo
            cbma.setModel(new DefaultComboBoxModel());
            //Recorremos los registros traidos
            while (rs.next()) {
                //Agregamos elemento al combo
                cbma.addItem(rs.getObject(1));
            }
            //Cerramos conexión
            conexion.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
        }
    }
     private void Cargartxtma(){
        //Carga de Combo
         try {
             conexion = Conecta.getConexion();
             Statement st1 = conexion.createStatement();
             String sql1 = "SELECT Monto FROM saldos WHERE ID='" + cbma.getSelectedItem() + "'";
             //Ejecutar consulta
             ResultSet rs1 = st1.executeQuery(sql1);
             //Recorremos los registros traidos
             while (rs1.next()) {
                 //Agregamos elemento al text
                 txtsa.setText(rs1.getObject("Monto").toString());
             }
             //Cerramos conexión
             conexion.close();
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toString());
         }
    }
    void calculadiferencia(){
        String ma=txtsa.getText();
        String mn= txtmonto.getText();
        int ent1=Integer.parseInt(ma);
        int ent2=Integer.parseInt(mn);
        int resta = (ent2-ent1);
        txtdiferencia.setText(String.valueOf(resta));
    }
    
    private String validarVacios() {
        String errores="";
        if(calendar.getDate() == null){
            errores+="Por favor ingrese la fecha \n";
        }
        if(txtmonto.getText().equals("")){
            errores+="Por favor ingrese el Monto del Día \n";
        }
        if(txtdiferencia.getText().trim().isEmpty()){
            errores+="El campo diferencia está vacio \n";
        }
        return errores;       
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtmonto = new javax.swing.JTextField();
        txtdiferencia = new javax.swing.JTextField();
        cbma = new javax.swing.JComboBox();
        txtsa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        calendar = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        btinsertar = new javax.swing.JButton();
        btmodificar = new javax.swing.JButton();
        bteliminar = new javax.swing.JButton();
        btlimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btbuscar = new javax.swing.JButton();
        btnuevo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jsp = new javax.swing.JScrollPane();
        tbsaldos = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Saldo del Día", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Fecha");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Monto Anterior");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Diferencia");

        txtmonto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtmonto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmontoFocusLost(evt);
            }
        });

        txtdiferencia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtdiferencia.setEnabled(false);

        cbma.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtsa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtsa.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Monto Actual");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbma, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdiferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtdiferencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btinsertar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btinsertar.setText("Insertar");
        btinsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btinsertarActionPerformed(evt);
            }
        });

        btmodificar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btmodificar.setText("Modificar");

        bteliminar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        bteliminar.setText("Eliminar");

        btlimpiar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btlimpiar.setText("Limpiar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btinsertar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btmodificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bteliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btlimpiar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btinsertar)
                    .addComponent(btmodificar)
                    .addComponent(bteliminar)
                    .addComponent(btlimpiar))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btbuscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btbuscar.setText("Buscar");

        btnuevo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnuevo.setText("Nuevo");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btbuscar)
                    .addComponent(btnuevo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btbuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnuevo)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabla de Saldos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        tbsaldos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };

        tbsaldos.getTableHeader().setReorderingAllowed(false) ;
        tbsaldos.setSelectionBackground(Color.GREEN);
        tbsaldos.setSelectionForeground(Color.RED);
        //rgb(44, 188, 164)
        tbsaldos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jsp.setViewportView(tbsaldos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btinsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btinsertarActionPerformed
        String errores = validarVacios();
        if (errores.equals("")) {
            try {
                Date fc = calendar.getDate();
                DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
                String convertido = fecha.format(fc);
                conexion = Conecta.getConexion();
                //crear la consulta
                Statement st = conexion.createStatement();
                //crear variable tipo string
                String sql = "INSERT INTO saldos (Fecha,Monto,Diferencia) "
                        + "VALUES('" + convertido + "','" + txtmonto.getText() + "','" + txtdiferencia.getText() + "')";
                //ejecutar consulta
                st.executeUpdate(sql);
                //cerrar conexion
                conexion.close();
                //Mostrar mensaje al usuario
                JOptionPane.showMessageDialog(this, "Saldo Ingresado");
                //limpiar los campos
                //calendar.setText("");
                txtmonto.setText("");
                txtdiferencia.setText("");
                mostrardatos("");
                anchocolumnas();
                //dar foco a fecha
                calendar.requestFocus();
            } catch (Exception e) {
                //mensaje de error que declara la base de datos
                JOptionPane.showMessageDialog(this, "ERROR" + e.getMessage().toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, errores);
        }
    }//GEN-LAST:event_btinsertarActionPerformed

    private void txtmontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmontoFocusLost
        calculadiferencia();
    }//GEN-LAST:event_txtmontoFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbuscar;
    private javax.swing.JButton bteliminar;
    private javax.swing.JButton btinsertar;
    private javax.swing.JButton btlimpiar;
    private javax.swing.JButton btmodificar;
    private javax.swing.JButton btnuevo;
    private com.toedter.calendar.JDateChooser calendar;
    private javax.swing.JComboBox cbma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JTable tbsaldos;
    private javax.swing.JTextField txtdiferencia;
    private javax.swing.JTextField txtmonto;
    private javax.swing.JTextField txtsa;
    // End of variables declaration//GEN-END:variables
}
