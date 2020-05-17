package br.com.infox.telas;

import br.com.infox.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet resultadoSQL = null;

    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    public void cadastrar_cliente() {

        String sql = "insert into tbclientes(nomecli,enderecocli,fonecli,emailcli) value (?,?,?,?)";

        //Verifica se campos obrigatorios estão sem preencher, e valida se as duas senhas correspondem.
        if (txtCliNome.getText().isEmpty() || txtCliFone.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Algum campo obrigatório não foi preenchido.");

        } else {
            try {

                //Pegar Texto digitado nos campos do cadastro do Usuario 
                pst = conexao.prepareStatement(sql);

                pst.setString(1, txtCliNome.getText());
                pst.setString(2, txtCliEndereco.getText());
                pst.setString(3, txtCliFone.getText());
                pst.setString(4, txtCliEmail.getText());

                //Executar a Quary com comando Update e resultado é gravado na variavel
                int execucaoDoUpdate = pst.executeUpdate();

                //Executar a Quary com comando Update e verificar resultado igual a 1
                if (execucaoDoUpdate > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso.");
                    txtCliNome.setText("");
                    txtCliEndereco.setText("");
                    txtCliFone.setText("");
                    txtCliEmail.setText("");
                    txtCliID.setText("");

                } else {
                    System.out.println("Erro ao cadastrar novo cliente!");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar novo cliente, verifique se o mesmo já esteja cadastrado." + e);
            }
        }
    }

    public void pesquisa_cliente() {

        String sql = "select*from  tbclientes where nomecli like ?";
        try {
            //Pegar Texto digitado no campo ID 
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtclinomepesquisa.getText() + "%");
            //Executar a Quary
            resultadoSQL = pst.executeQuery();

            tabelaClientes.setModel(DbUtils.resultSetToTableModel(resultadoSQL));

        } catch (Exception e) {
            System.out.println("Erro:  " + e);
        }
    }

    public void setar_clientes() {
        int setar = tabelaClientes.getSelectedRow();
        txtCliID.setText(tabelaClientes.getModel().getValueAt(setar, 0).toString());
        txtCliNome.setText(tabelaClientes.getModel().getValueAt(setar, 1).toString());
        txtCliEndereco.setText(tabelaClientes.getModel().getValueAt(setar, 2).toString());
        txtCliFone.setText(tabelaClientes.getModel().getValueAt(setar, 3).toString());
        txtCliEmail.setText(tabelaClientes.getModel().getValueAt(setar, 4).toString());

    }

    public void deletar_cliente() {

        if (txtCliNome.getText().isEmpty() || txtCliFone.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Por favor pesquise um cliente antes de excluir.");

        } else {
            int excluir = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este cliente?", "Atenção", JOptionPane.YES_NO_OPTION);

            if (excluir == JOptionPane.YES_NO_OPTION) {
                try {
                    String sql = "delete from tbclientes where idcli=?";
                    //Pegar Texto digitado no campo ID 
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtCliID.getText());
                    //Executar a Quary
                    int execucaoDelete = 0;
                    execucaoDelete = pst.executeUpdate();
                    //System.out.println(execucaoDelete);

                    if (execucaoDelete > 0) {
                        JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
                        txtCliNome.setText("");
                        txtCliEndereco.setText("");
                        txtCliFone.setText("");
                        txtCliEmail.setText("");
                        txtCliID.setText("");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            }
        }
    }

    public void editar_cliente() {

        String sql = "update tbclientes set nomecli=?, enderecocli=?, fonecli=?, emailcli=? where idcli=?";

        //Verifica se campos obrigatorios estão sem preencher, e valida se as duas senhas correspondem.
        if (txtCliNome.getText().isEmpty() || txtCliFone.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Por favor pesquise um cliente antes de editar.");

        } else {

            try {
                pst = conexao.prepareStatement(sql);
                //Pegar Texto digitado nos campos do cadastro do Usuario             
                pst.setString(1, txtCliNome.getText());
                pst.setString(2, txtCliEndereco.getText());
                pst.setString(3, txtCliFone.getText());
                pst.setString(4, txtCliEmail.getText());
                pst.setString(5, txtCliID.getText());

                int execucaoUpdate = pst.executeUpdate();

                // System.out.println(execucaoUpdate);
                if (execucaoUpdate > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso.");
                    txtCliNome.setText("");
                    txtCliEndereco.setText("");
                    txtCliFone.setText("");
                    txtCliEmail.setText("");
                    txtCliID.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao editar cliente, por favor tente novamnete.");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnCliAdicionar = new javax.swing.JButton();
        btnCliEditar = new javax.swing.JButton();
        btnCliDeletar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        txtCliEndereco = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        txtCliFone = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();
        txtclinomepesquisa = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtCliID = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");
        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(775, 480));
        setPreferredSize(new java.awt.Dimension(640, 480));

        jInternalFrame1.setClosable(true);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setTitle("Usuários");
        jInternalFrame1.setToolTipText("");
        jInternalFrame1.setMinimumSize(new java.awt.Dimension(775, 480));
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(640, 480));

        jLabel7.setText("jLabel1");
        jLabel7.setPreferredSize(new java.awt.Dimension(45, 14));

        jLabel8.setText("jLabel1");
        jLabel8.setPreferredSize(new java.awt.Dimension(45, 14));

        jLabel9.setText("jLabel1");
        jLabel9.setPreferredSize(new java.awt.Dimension(45, 14));

        jLabel10.setText("jLabel1");
        jLabel10.setPreferredSize(new java.awt.Dimension(45, 14));

        jLabel11.setText("jLabel1");
        jLabel11.setPreferredSize(new java.awt.Dimension(45, 14));

        jLabel12.setText("jLabel1");
        jLabel12.setPreferredSize(new java.awt.Dimension(45, 14));

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(207, 207, 207))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCliAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnCliAdicionar.setToolTipText("Adicionar");
        btnCliAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAdicionarActionPerformed(evt);
            }
        });

        btnCliEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnCliEditar.setToolTipText("Editar");
        btnCliEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliEditarActionPerformed(evt);
            }
        });

        btnCliDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnCliDeletar.setToolTipText("Delete");
        btnCliDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliDeletarActionPerformed(evt);
            }
        });

        jLabel1.setText("(*)Campos Obrigatórios");

        jLabel15.setText("*Nome:");

        jLabel16.setText("Endereço:");

        jLabel17.setText("*Telefone:");

        jLabel18.setText("E-mail:");

        txtCliEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliEnderecoActionPerformed(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/pesquisar.png"))); // NOI18N

        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tabelaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaClientes);
        if (tabelaClientes.getColumnModel().getColumnCount() > 0) {
            tabelaClientes.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            tabelaClientes.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            tabelaClientes.getColumnModel().getColumn(2).setHeaderValue("Title 3");
            tabelaClientes.getColumnModel().getColumn(3).setHeaderValue("Title 4");
            tabelaClientes.getColumnModel().getColumn(4).setHeaderValue("Title 5");
        }

        txtclinomepesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtclinomepesquisaActionPerformed(evt);
            }
        });
        txtclinomepesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtclinomepesquisaKeyReleased(evt);
            }
        });

        jLabel20.setText("*Nome:");

        txtCliID.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(txtclinomepesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCliNome)
                                    .addComponent(txtCliEndereco)
                                    .addComponent(txtCliFone)
                                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCliID, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(btnCliAdicionar)
                                .addGap(46, 46, 46)
                                .addComponent(btnCliEditar)
                                .addGap(48, 48, 48)
                                .addComponent(btnCliDeletar)))))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 312, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 312, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtclinomepesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtCliID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtCliFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCliAdicionar)
                    .addComponent(btnCliDeletar)
                    .addComponent(btnCliEditar))
                .addGap(37, 37, 37))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 277, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 278, Short.MAX_VALUE)))
        );

        getAccessibleContext().setAccessibleName("");

        setBounds(0, 0, 640, 585);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCliAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAdicionarActionPerformed
        // TODO add your handling code here:
        cadastrar_cliente();
    }//GEN-LAST:event_btnCliAdicionarActionPerformed

    private void btnCliDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliDeletarActionPerformed
        // TODO add your handling code here:
        deletar_cliente();
    }//GEN-LAST:event_btnCliDeletarActionPerformed

    private void btnCliEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliEditarActionPerformed
        // TODO add your handling code here:        
        editar_cliente();
    }//GEN-LAST:event_btnCliEditarActionPerformed

    private void txtCliEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliEnderecoActionPerformed

    private void txtclinomepesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclinomepesquisaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtclinomepesquisaActionPerformed

    private void txtclinomepesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclinomepesquisaKeyReleased
        // TODO add your handling code here:
        pesquisa_cliente();
    }//GEN-LAST:event_txtclinomepesquisaKeyReleased

    private void tabelaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaClientesMouseClicked
        // TODO add your handling code here:
        setar_clientes();
    }//GEN-LAST:event_tabelaClientesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliAdicionar;
    private javax.swing.JButton btnCliDeletar;
    private javax.swing.JButton btnCliEditar;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliFone;
    private javax.swing.JTextField txtCliID;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtclinomepesquisa;
    // End of variables declaration//GEN-END:variables
}
