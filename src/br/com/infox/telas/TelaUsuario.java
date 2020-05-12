/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import br.com.infox.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet resultadoSQL = null;

    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    public void consultar() {
        if (txtUsoID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor inserir o ID do usuário a ser consultado.");
        } else {

            String sql = "select*from tbusuarios where iduser=?";
            try {
                //Pegar Texto digitado no campo ID 
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsoID.getText());
                //Executar a Quary
                resultadoSQL = pst.executeQuery();

                if (resultadoSQL.next()) {
                    //Recuperar dados e gravar nos campos
                    txtUsoNome.setText(resultadoSQL.getString(2));
                    txtUsoFone.setText(resultadoSQL.getString(3));
                    txtUsologin.setText(resultadoSQL.getString(4));
                    cbxUsoPerfil.setSelectedItem(resultadoSQL.getString(6));
                    txtUsoPrimeiraSenha.setText(resultadoSQL.getString(5));
                    txtUsoSenhaVerificada.setText(resultadoSQL.getString(5));

                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                    txtUsoNome.setText(null);
                    txtUsoFone.setText(null);
                    txtUsologin.setText(null);
                    cbxUsoPerfil.setSelectedItem("user");
                    txtUsoPrimeiraSenha.setText(null);
                    txtUsoSenhaVerificada.setText(null);
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void cadastrar() {
        String sql = "insert into tbusuarios(iduser,usuario,fone,login,senha,perfil) value (?,?,?,?,?,?)";

        //Verifica se campos obrigatorios estão sem preencher, e valida se as duas senhas correspondem.
        if ((txtUsoID.getText().isEmpty() || txtUsoNome.getText().isEmpty() || txtUsologin.getText()
                .isEmpty() || txtUsoPrimeiraSenha.getText().isEmpty()) || (txtUsoSenhaVerificada.getText()
                == null ? txtUsoPrimeiraSenha.getText() != null : !txtUsoSenhaVerificada.getText().equals(txtUsoPrimeiraSenha.getText()))) {

            JOptionPane.showMessageDialog(null, "Algum campo não foi preenchido ou senhas digitadas não correspondem.");

        } else {
            try {

                //Pegar Texto digitado nos campos do cadastro do Usuario 
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsoID.getText());
                pst.setString(2, txtUsoNome.getText());
                pst.setString(3, txtUsoFone.getText());
                pst.setString(4, txtUsologin.getText());
                pst.setString(5, txtUsoSenhaVerificada.getText());
                pst.setString(6, cbxUsoPerfil.getSelectedItem().toString());

                //Executar a Quary com comando Update e resultado é gravado na variavel
                int execucaoDoUpdate = pst.executeUpdate();

                //Executar a Quary com comando Update e verificar resultado igual a 1
                if (execucaoDoUpdate > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário foi cadastrado com sucesso");
                    txtUsoNome.setText(null);
                    txtUsoFone.setText(null);
                    txtUsologin.setText(null);
                    //cbxUsoPerfil.setSelectedItem(null);
                    txtUsoPrimeiraSenha.setText(null);
                    txtUsoSenhaVerificada.setText(null);
                } else {
                    System.out.println("Erro ao cadastrar novo usuário!");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar novo usuário, verifique se o ID já existe! " + e);

            }
        }
    }

    public void deletar() {
        consultar();

        String sql = "delete from  tbusuarios where iduser=?";

        int excluir = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (excluir == JOptionPane.YES_NO_OPTION) {
            try {
                //Pegar Texto digitado no campo ID 
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsoID.getText());
                //Executar a Quary
                int execucaoDelete = 0;
                execucaoDelete = pst.executeUpdate();
                //System.out.println(execucaoDelete);

                if (execucaoDelete == 1) {
                    JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
                    txtUsoID.setText("");
                    txtUsoNome.setText("");
                    txtUsoFone.setText("");
                    txtUsologin.setText("");
                    txtUsoPrimeiraSenha.setText("");
                    txtUsoSenhaVerificada.setText("");
                }

            } catch (Exception e) {
                System.out.println(e);

            }
        }
    }

    public void editar() {
        //consultar();
        if ((txtUsoID.getText().isEmpty() || txtUsoNome.getText().isEmpty() || txtUsologin.getText()
                .isEmpty() || txtUsoPrimeiraSenha.getText().isEmpty()) || (txtUsoSenhaVerificada.getText()
                == null ? txtUsoPrimeiraSenha.getText() != null : !txtUsoSenhaVerificada.getText().equals(txtUsoPrimeiraSenha.getText()))) {

            JOptionPane.showMessageDialog(null, "Algum campo não foi preenchido ou senhas digitadas não correspondem.");
        } else {

            String sql = "update tbusuarios set usuario=?,fone=?,login=?,senha=?,perfil=? where iduser=?";

            try {
                //Pegar Texto digitado nos campos do cadastro do Usuario 
                pst = conexao.prepareStatement(sql);

                pst.setString(1, txtUsoNome.getText());
                pst.setString(2, txtUsoFone.getText());
                pst.setString(3, txtUsologin.getText());
                pst.setString(4, txtUsoSenhaVerificada.getText());
                pst.setString(5, cbxUsoPerfil.getSelectedItem().toString());

                pst.setString(6, txtUsoID.getText());

                int execucaoUpdate = pst.executeUpdate();

                System.out.println(execucaoUpdate);

                if (execucaoUpdate == 1) {
                    JOptionPane.showMessageDialog(null, "Usuário atualizado.");
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtUsoNome = new javax.swing.JTextField();
        txtUsologin = new javax.swing.JTextField();
        txtUsoFone = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbxUsoPerfil = new javax.swing.JComboBox();
        btnUsoPesquisar = new javax.swing.JButton();
        btnUsoAdicionar = new javax.swing.JButton();
        btnUsoEditar = new javax.swing.JButton();
        btnUsoDeletar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtUsoID = new javax.swing.JTextField();
        txtUsoPrimeiraSenha = new javax.swing.JTextField();
        txtUsoSenhaVerificada = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(775, 480));
        setPreferredSize(new java.awt.Dimension(640, 480));

        jLabel2.setText("*Nome:");
        jLabel2.setPreferredSize(new java.awt.Dimension(45, 14));

        jLabel3.setText("Fone:");
        jLabel3.setPreferredSize(new java.awt.Dimension(45, 14));

        jLabel4.setText("*Senha:");
        jLabel4.setPreferredSize(new java.awt.Dimension(45, 14));

        jLabel5.setText("Perfil:");
        jLabel5.setPreferredSize(new java.awt.Dimension(45, 14));

        jLabel6.setText("*Login:");
        jLabel6.setPreferredSize(new java.awt.Dimension(45, 14));

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

        txtUsologin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsologinActionPerformed(evt);
            }
        });

        txtUsoFone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsoFoneActionPerformed(evt);
            }
        });

        jLabel13.setText("*Senha Novamente:");
        jLabel13.setPreferredSize(new java.awt.Dimension(45, 14));

        cbxUsoPerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "user", "admin" }));

        btnUsoPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/read.png"))); // NOI18N
        btnUsoPesquisar.setToolTipText("Pesquisa");
        btnUsoPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoPesquisarActionPerformed(evt);
            }
        });

        btnUsoAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnUsoAdicionar.setToolTipText("Adicionar");
        btnUsoAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoAdicionarActionPerformed(evt);
            }
        });

        btnUsoEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnUsoEditar.setToolTipText("Editar");
        btnUsoEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoEditarActionPerformed(evt);
            }
        });

        btnUsoDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnUsoDeletar.setToolTipText("Delete");
        btnUsoDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsoDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoDeletarActionPerformed(evt);
            }
        });

        jLabel14.setText("*ID:");
        jLabel14.setPreferredSize(new java.awt.Dimension(45, 14));

        jLabel1.setText("(*)Campos Obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnUsoPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addComponent(btnUsoAdicionar))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtUsoFone)
                                .addComponent(cbxUsoPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtUsologin, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnUsoEditar)
                                .addGap(48, 48, 48)
                                .addComponent(btnUsoDeletar))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUsoSenhaVerificada, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsoPrimeiraSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtUsoID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)))
                            .addComponent(txtUsoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsoFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxUsoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsoPrimeiraSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUsologin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUsoSenhaVerificada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUsoAdicionar)
                    .addComponent(btnUsoPesquisar)
                    .addComponent(btnUsoDeletar)
                    .addComponent(btnUsoEditar))
                .addGap(650, 650, 650))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        getAccessibleContext().setAccessibleName("");

        setBounds(0, 0, 640, 608);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsoFoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsoFoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsoFoneActionPerformed

    private void btnUsoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoPesquisarActionPerformed
        // TODO add your handling code here:
        consultar();
        //System.out.println("Botão foi clicado!");
    }//GEN-LAST:event_btnUsoPesquisarActionPerformed

    private void btnUsoAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoAdicionarActionPerformed
        // TODO add your handling code here:
        cadastrar();
    }//GEN-LAST:event_btnUsoAdicionarActionPerformed

    private void txtUsologinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsologinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsologinActionPerformed

    private void btnUsoDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoDeletarActionPerformed
        // TODO add your handling code here:
        if (txtUsoID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor preencher o campo ID do usuário a ser excluído.");
        } else {
            deletar();
        }
    }//GEN-LAST:event_btnUsoDeletarActionPerformed

    private void btnUsoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoEditarActionPerformed
        // TODO add your handling code here:
        if (txtUsoID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor preencher o campo ID do usuário a ser editado.");
        } else {
            editar();
        }
    }//GEN-LAST:event_btnUsoEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsoAdicionar;
    private javax.swing.JButton btnUsoDeletar;
    private javax.swing.JButton btnUsoEditar;
    private javax.swing.JButton btnUsoPesquisar;
    private javax.swing.JComboBox cbxUsoPerfil;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTextField txtUsoFone;
    private javax.swing.JTextField txtUsoID;
    private javax.swing.JTextField txtUsoNome;
    private javax.swing.JTextField txtUsoPrimeiraSenha;
    private javax.swing.JTextField txtUsoSenhaVerificada;
    private javax.swing.JTextField txtUsologin;
    // End of variables declaration//GEN-END:variables
}
