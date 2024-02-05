/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package library.forms;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import library.components.BookItem;
import library.event.EventItem;
import library.model.ModelItem;


public class MyLibrary extends javax.swing.JPanel {

   
 

    public void setEvent(EventItem event) {
        this.event = event;
    }

   
    public MyLibrary() {
        initComponents();
        setOpaque(false);
    }
private EventItem event;
  public void addBooks(ModelItem data){
      BookItem item = new BookItem();
      item.setData(data);
      item.addMouseListener(new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
         if (SwingUtilities.isLeftMouseButton(e)) {
                    event.itemClick(item, data);
                }               
                super.mousePressed(e);
          
          }
      });
      panelItem1.add(item);
      repaint();
      revalidate();
 
  }
  public void setSelected(Component item){
        for (Component com : panelItem1.getComponents()) {
            BookItem i =(BookItem)com;
            if (i.isSelected()) {
                i.setSelected(false);
            }
            ((BookItem) item).setSelected(true);
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panelItem1 = new library.swing.PanelItem();

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportView(panelItem1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private library.swing.PanelItem panelItem1;
    // End of variables declaration//GEN-END:variables
}
