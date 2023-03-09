package view;

import entities.DurableProducts;
import entities.GroupByTaxId;
import entities.PerishableProducts;
import entities.ProductEntity;
import entities.SelectByCriticalQuantity;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.Pipe;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import oop.persistance.controller.Controller;
import oop.persistance.controller.ControllerFactory;
import oop.persistance.controller.ControllerName;
import oop.persistance.controller.DurableHandler;
import oop.persistance.controller.HandlerFactory;
import oop.persistance.controller.PerishableHandler;

/**
 *
 * @author --G--
 */
public class MainForm extends javax.swing.JFrame {
    
    static PerishableHandler PH;
    static DurableHandler DH;
    private List<PerishableProducts> perishableList;
    private List<DurableProducts> durableList;
    private AbstractTableModel perishableTableAbs;
    private AbstractTableModel durableTableAbs;
    private ProductCreateEventListener Plistener;
    private ProductCreateEventListener Dlistener;
    private QuantityChangeListener updateListener;
    private SaveFormPP PerishableSaveForm;
    private SaveFormDP DurableSaveForm;
    private EditFormDP DpEditorForm;
    private EditFormPP PpEditorForm;
    
    static {
        PH = (PerishableHandler) HandlerFactory.createHandler(
                ControllerName.Perishable);
        DH = (DurableHandler) HandlerFactory.createHandler(
                ControllerName.Durable);
    }

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        criticalQantityCheck();
        perishableList = PH.findAll();
        durableList = DH.findAll();
        perishableTableAbs = new PerishableTableModel(perishableList);
        durableTableAbs = new DurableTableModel(durableList);
        
        tbPerishable.setModel(perishableTableAbs);
        
        tbDurable.setModel(durableTableAbs);
        addMouseListenerToPHeader();
        addMouseListenerToDheader();
        Plistener = new PerishableListener();
        Dlistener = new DurableListener();
        updateListener = new ProductQuantityChangeListener();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane3 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbPerishable = new javax.swing.JTable();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbDurable = new javax.swing.JTable();
        btAddPP = new javax.swing.JButton();
        btSearchP = new javax.swing.JButton();
        btStat = new javax.swing.JButton();
        btAddDP = new javax.swing.JButton();
        btSearchD = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Storage");
        setResizable(false);

        tbPerishable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "name", "brand", "family", "nettoPrice", "taxId", "quantity", "amountUnits", "criticalQuantity", "expDate", "prodDate"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPerishable.getTableHeader().setReorderingAllowed(false);
        tbPerishable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPerishableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbPerishable);

        jTabbedPane3.addTab("Perishables", jScrollPane3);

        tbDurable.setModel(new javax.swing.table.DefaultTableModel(
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
        tbDurable.getTableHeader().setReorderingAllowed(false);
        tbDurable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDurableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbDurable);

        jTabbedPane4.addTab("tab1", jScrollPane4);

        jTabbedPane3.addTab("Durables", jTabbedPane4);

        btAddPP.setText("Add to Perishable");
        btAddPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddPPActionPerformed(evt);
            }
        });

        btSearchP.setText("Search in Perishable");
        btSearchP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchPActionPerformed(evt);
            }
        });

        btStat.setText("Statistics by id in Parishable");
        btStat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStatActionPerformed(evt);
            }
        });

        btAddDP.setText("Add to Durable");
        btAddDP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddDPActionPerformed(evt);
            }
        });

        btSearchD.setText("Search in Durable");
        btSearchD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btStat)
                .addGap(361, 361, 361))
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(btAddPP, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btAddDP, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btSearchP, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btSearchD, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAddPP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSearchP, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAddDP, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSearchD, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btStat, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAddPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddPPActionPerformed
        PerishableSaveForm = new SaveFormPP();
        PerishableSaveForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        PerishableSaveForm.addListener(Plistener);
        PerishableSaveForm.setVisible(true);
        PerishableSaveForm.setAlwaysOnTop(true);
    }//GEN-LAST:event_btAddPPActionPerformed
    
    private void btAddDPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddDPActionPerformed
        DurableSaveForm = new SaveFormDP();
        DurableSaveForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DurableSaveForm.addListener(Dlistener);
        DurableSaveForm.setVisible(true);
        DurableSaveForm.setAlwaysOnTop(true);
    }//GEN-LAST:event_btAddDPActionPerformed
    
    private void btStatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStatActionPerformed
        List<GroupByTaxId> list = (List<GroupByTaxId>) PH.groupByTaxId();
        StatisticForm statForm = new StatisticForm(list);
        statForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        statForm.setVisible(true);
        statForm.setAlwaysOnTop(true);
    }//GEN-LAST:event_btStatActionPerformed
    
    private void btSearchPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchPActionPerformed
        SearchFormPP form = new SearchFormPP();
        form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        form.setVisible(true);
        form.setAlwaysOnTop(true);
    }//GEN-LAST:event_btSearchPActionPerformed
    
    private void btSearchDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchDActionPerformed
        SearchFormDP form = new SearchFormDP();
        form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        form.setVisible(true);
        form.setAlwaysOnTop(true);
    }//GEN-LAST:event_btSearchDActionPerformed
    
    private void tbPerishableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPerishableMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() > 1) {
            int index = tbPerishable.getSelectedRow();
            PpEditorForm = new EditFormPP(perishableList.get(index));
            PpEditorForm.addListener(updateListener);
            PpEditorForm.setVisible(true);
            PpEditorForm.setAlwaysOnTop(true);
            PpEditorForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
        }
    }//GEN-LAST:event_tbPerishableMouseClicked
    
    private void tbDurableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDurableMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() > 1) {
            int index = tbDurable.getSelectedRow();
            EditFormDP form = new EditFormDP(durableList.get(index));
            form.addListener(updateListener);
            form.setVisible(true);
            form.setAlwaysOnTop(true);
            form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
        }
    }//GEN-LAST:event_tbDurableMouseClicked

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
        
    }
    
    private void criticalQantityCheck() {
        List<SelectByCriticalQuantity> critQuant
                = (List<SelectByCriticalQuantity>) PH.selectByCriticalQuantity();
        List<SelectByCriticalQuantity> critQuant1
                = (List<SelectByCriticalQuantity>) DH.selectByCriticalQuantity();
        if (critQuant1.size() > 0 || critQuant.size() > 0) {
            critQuant.addAll(critQuant1);
            CriticalQantityForm critForm = new CriticalQantityForm(critQuant);
            critForm.setVisible(true);
            critForm.setAlwaysOnTop(true);
            critForm.getContentPane().
                    requestFocus();
            
        }
    }
    
    private void addMouseListenerToPHeader() {
        JTableHeader Pheader = tbPerishable.getTableHeader();
        Pheader.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() > 1) {
                    int columnIndex = Pheader.columnAtPoint(e.getPoint());
                    perishableList = PH.orderByColumnName(perishableTableAbs.
                            getColumnName(
                                    columnIndex));
                    perishableTableAbs
                            = new PerishableTableModel(perishableList);
                    tbPerishable.setModel(perishableTableAbs);
                }
            }
        });
    }
    
    private void addMouseListenerToDheader() {
        JTableHeader Dheader = tbDurable.getTableHeader();
        
        Dheader.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() > 1) {
                    int columnIndex = Dheader.columnAtPoint(e.getPoint());
                    durableList = DH.orderByColumnName(durableTableAbs.
                            getColumnName(
                                    columnIndex));
                    durableTableAbs = new DurableTableModel(durableList);
                    tbDurable.setModel(durableTableAbs);
                    
                }
            }
            
        });
    }
    
    private class PerishableListener implements ProductCreateEventListener<PerishableProducts> {
        
        @Override
        public void productCreated(PerishableProducts product) {
            if (!perishableList.contains(product)) {
                perishableList.add(product);
                perishableTableAbs.fireTableDataChanged();
            }
        }
        
    }
    
    private class DurableListener implements ProductCreateEventListener<DurableProducts> {
        
        @Override
        public void productCreated(DurableProducts product) {
            if (!durableList.contains(product)) {
                durableList.add(product);
            }
            durableTableAbs.fireTableDataChanged();
        }
        
    }
    
    private class ProductQuantityChangeListener implements QuantityChangeListener {
        
        @Override
        public void changeQuantity(ProductEntity product) {
            int counter = 0;
            if (product instanceof PerishableProducts pP) {
                while (pP != perishableList.get(counter) && counter < perishableList.
                        size()) {
                    counter++;
                }
                perishableList.set(counter, pP);
                perishableTableAbs.fireTableDataChanged();
            } else if (product instanceof DurableProducts dP) {
                while (dP != durableList.get(counter) && counter < durableList.
                        size()) {
                    counter++;
                }
                durableList.set(counter, dP);
                durableTableAbs.fireTableDataChanged();
            }
            
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddDP;
    private javax.swing.JButton btAddPP;
    private javax.swing.JButton btSearchD;
    private javax.swing.JButton btSearchP;
    private javax.swing.JButton btStat;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable tbDurable;
    private javax.swing.JTable tbPerishable;
    // End of variables declaration//GEN-END:variables

}
