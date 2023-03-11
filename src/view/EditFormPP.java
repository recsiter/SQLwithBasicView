package view;

import entities.PerishableProducts;
import java.util.ArrayList;
import java.util.List;
import Util.ControllerName;
import oop.persistance.controller.HandlerFactory;
import oop.persistance.controller.PerishableHandler;

/**
 *
 * @author --G--
 */
public class EditFormPP extends javax.swing.JFrame {

    private List<QuantityChangeListener> listeners;
    private PerishableProducts product;

    /**
     * Creates new form EditFormPP
     *
     * @param product
     */
    public EditFormPP(PerishableProducts product) {
        initComponents();
        this.product = product;
        jTProductField.setText(product.toString());
        listeners = new ArrayList<>();
        jTBruttoPrice.setText(String.valueOf(product.calculateGrossPrice()));
        jTDayToPerish.setText(String.valueOf(product.getDaysLeftToPerishing()));

    }

    private EditFormPP() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void notifyListeners(PerishableProducts product) {
        for (QuantityChangeListener listener : listeners) {
            listener.changeQuantity(product);

        }
    }

    public void addListener(QuantityChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTProductField = new javax.swing.JTextPane();
        btIncrease = new javax.swing.JButton();
        btDecrease = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTBruttoPrice = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jtSubstractAmount = new javax.swing.JTextField();
        jTAddAmount = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTDayToPerish = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Perishable product editor");
        setResizable(false);

        jTProductField.setEditable(false);
        jScrollPane1.setViewportView(jTProductField);

        btIncrease.setText("+ Increase Quantity");
        btIncrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncreaseActionPerformed(evt);
            }
        });

        btDecrease.setText("- Decrease Quantity");
        btDecrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDecreaseActionPerformed(evt);
            }
        });

        jTBruttoPrice.setEditable(false);
        jScrollPane2.setViewportView(jTBruttoPrice);

        jLabel1.setText("Gross price:");

        jtSubstractAmount.setText("- Amount");

        jTAddAmount.setText("+ Amount");

        jLabel2.setText("Number of days till perishing:");

        jTDayToPerish.setEditable(false);
        jScrollPane3.setViewportView(jTDayToPerish);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTAddAmount, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btIncrease, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtSubstractAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btDecrease))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtSubstractAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTAddAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btIncrease)
                    .addComponent(btDecrease))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btIncreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncreaseActionPerformed

        PerishableHandler handler = (PerishableHandler) HandlerFactory.
                createHandler(ControllerName.Perishable);
        product.quantityAdd(Integer.parseInt(jTAddAmount.getText()));
        handler.update(product);
        notifyListeners(product);
        this.dispose();
    }//GEN-LAST:event_btIncreaseActionPerformed

    private void btDecreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDecreaseActionPerformed

        PerishableHandler handler = (PerishableHandler) HandlerFactory.
                createHandler(ControllerName.Perishable);
        product.quantitySubstract(Integer.parseInt(jtSubstractAmount.getText()));
        handler.update(product);
        notifyListeners(product);
        this.dispose();
    }//GEN-LAST:event_btDecreaseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.
                    getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditFormPP.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditFormPP.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditFormPP.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditFormPP.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditFormPP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDecrease;
    private javax.swing.JButton btIncrease;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTAddAmount;
    private javax.swing.JTextPane jTBruttoPrice;
    private javax.swing.JTextPane jTDayToPerish;
    private javax.swing.JTextPane jTProductField;
    private javax.swing.JTextField jtSubstractAmount;
    // End of variables declaration//GEN-END:variables
}
