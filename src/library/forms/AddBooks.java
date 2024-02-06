
package library.forms;

import com.kitfox.svg.app.data.Handler;
import com.raven.datechooser.DateChooser;
import java.awt.Cursor;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import library.database.DatabaseConnection;
import library.main.Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import javax.swing.SwingUtilities;
import library.controller.AddBooksController;
import library.controller.PopulateBooksController;
import library.model.ModelItem;



public class AddBooks extends javax.swing.JPanel {
   private AddBooksController addBooksControll;
   private Main main;
   private PopulateBooksController populateBooks;
   private MyLibrary library;
    Connection MyCon;
    
   private DateChooser dateChooser = new DateChooser();
    public AddBooks() {
        initComponents();
        setOpaque(false);
        dateChooser.setTextField(bDate);
        dateChooser.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        library = new MyLibrary();
        
        populateBooks = new PopulateBooksController(library);
        
        
        
        init();
        
    }
 
    private void init() {
       try {
           DatabaseConnection.getInstance().ConnectToDatabase();
       } catch (ClassNotFoundException |SQLException e) {
          e.printStackTrace();
       }
    }
    

  public void addBooksButton() {
    Icon picIcon = pic.getImage();
    
    String selectCategory = bCategory.getSelectedItem().toString();
    String selectLanguage = bLanguage.getSelectedItem().toString();
    String selectFormat = bFormat.getSelectedItem().toString();
    String selectEdition = bEdition.getSelectedItem().toString();
    int addPageCount = Integer.parseInt(pCount.getText());
    int addQuantity = Integer.parseInt(quantity.getText());
    byte[] imageBytes;

    try {
       

        imageBytes = convertImageIconToByteArray(picIcon);

        addBooksControll = new AddBooksController(userId.getText(), bTitle.getText(), bAuthor.getText(),
                bPublisher.getText(), bDate.getText(), bDescription.getText(), selectCategory, selectLanguage,
                selectFormat, selectEdition, addPageCount, addQuantity, new ImageIcon(imageBytes));

        addBooksControll.addBookToDatabase();
      
        

        JOptionPane.showMessageDialog(this, "Successfully Added!");
    } catch (IOException | SQLException | ClassNotFoundException ex) {
        try {
            DatabaseConnection.getInstance().getConnection().rollback();
        } catch (SQLException rollbackException) {
            rollbackException.printStackTrace();
        }

        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } 
    
}

    private byte[] convertImageIconToByteArray(Icon icon) throws IOException {
    BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
    icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "png", outputStream);

    return outputStream.toByteArray();
}
    public void selectImage(){
                JFileChooser imgChooser = new JFileChooser();
        FileNameExtensionFilter fn = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
        imgChooser.addChoosableFileFilter(fn);

        int showOpenDialog = imgChooser.showOpenDialog(null);

        if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
        File selectedFile = imgChooser.getSelectedFile();

    
        String fileName = selectedFile.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("jpeg")) {
        JOptionPane.showMessageDialog(this, "Not an image, Please try again!!");
    } else {
        if (selectedFile != null) {
            try {
                BufferedImage img = ImageIO.read(selectedFile);
                ImageIcon imgIcon = new ImageIcon(img);
                pic.setImage(imgIcon);
                pic.repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No file selected");
        }
    }
}
    }
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bTitle = new textfield.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bAuthor = new textfield.TextField();
        jLabel3 = new javax.swing.JLabel();
        bPublisher = new textfield.TextField();
        jLabel4 = new javax.swing.JLabel();
        bDate = new textfield.TextField();
        jLabel5 = new javax.swing.JLabel();
        pic = new library.components.PictureBox();
        button1 = new button.Button();
        addBook = new button.Button();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        bCategory = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        bLanguage = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        bFormat = new javax.swing.JComboBox<>();
        bEdition = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        userId = new javax.swing.JLabel();
        addImage = new button.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        bDescription = new javax.swing.JTextPane();
        quantity = new textfield.TextField();
        pCount = new textfield.TextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        bTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Book Title*");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Book Author*");

        bAuthor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Publisher*");

        bPublisher.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Publication Date*");

        bDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Description");

        pic.setImage(new javax.swing.ImageIcon(getClass().getResource("/library/image/calculusBook.png"))); // NOI18N

        button1.setBackground(new java.awt.Color(97, 103, 122));
        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setText("Cancel");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        addBook.setBackground(new java.awt.Color(97, 103, 122));
        addBook.setForeground(new java.awt.Color(0, 0, 0));
        addBook.setText("Add Book");
        addBook.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        addBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("STFangsong", 0, 24)); // NOI18N
        jLabel7.setText("Category");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Select Book Category*");

        bCategory.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Select Language");

        bLanguage.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bLanguage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Select Format*");

        bFormat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bFormat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bEdition.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bEdition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Edition*");

        userId.setText("userId");

        addImage.setForeground(new java.awt.Color(56, 122, 159));
        addImage.setText("Click Here to add Image");
        addImage.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addImageMouseEntered(evt);
            }
        });
        addImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addImageActionPerformed(evt);
            }
        });

        bDescription.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bDescription.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        bDescription.setDragEnabled(true);
        bDescription.setPreferredSize(new java.awt.Dimension(25, 25));
        jScrollPane2.setViewportView(bDescription);

        jScrollPane1.setViewportView(jScrollPane2);

        quantity.setText("0");

        pCount.setText("0");

        jLabel6.setText("PageCount:*");

        jLabel9.setText("Quantity:*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bFormat, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bEdition, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pCount, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(74, 74, 74)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(74, 74, 74)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addComponent(bTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bAuthor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bPublisher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(74, 74, 74)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(bDate, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addBook, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(userId)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jScrollPane1)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)))
                        .addComponent(bCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addImage, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bFormat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bEdition, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userId)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
       
   
    }//GEN-LAST:event_button1ActionPerformed

    private void addBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookActionPerformed
      
       addBooksButton();
       
       

    }//GEN-LAST:event_addBookActionPerformed

    private void addImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addImageActionPerformed
        selectImage();
    }//GEN-LAST:event_addImageActionPerformed

    private void addImageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addImageMouseEntered
        addImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_addImageMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button addBook;
    private button.Button addImage;
    private textfield.TextField bAuthor;
    private javax.swing.JComboBox<String> bCategory;
    private textfield.TextField bDate;
    private javax.swing.JTextPane bDescription;
    private javax.swing.JComboBox<String> bEdition;
    private javax.swing.JComboBox<String> bFormat;
    private javax.swing.JComboBox<String> bLanguage;
    private textfield.TextField bPublisher;
    private textfield.TextField bTitle;
    private button.Button button1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private textfield.TextField pCount;
    private library.components.PictureBox pic;
    private textfield.TextField quantity;
    public javax.swing.JLabel userId;
    // End of variables declaration//GEN-END:variables
}
