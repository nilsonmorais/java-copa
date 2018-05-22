/*
This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

For more information, please refer to <http://unlicense.org>
 */
package br.estacio.prii.copa.entidade;

import br.estacio.prii.copa.persistence.UsuariosDAO;
import br.estacio.prii.copa.utils.Utils;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Usuarios", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findById", query = "SELECT u FROM Usuarios u WHERE u.id = :id"),
    @NamedQuery(name = "Usuarios.findByLogin", query = "SELECT u FROM Usuarios u WHERE u.login = :login"),
    @NamedQuery(name = "Usuarios.findByNome", query = "SELECT u FROM Usuarios u WHERE u.nome = :nome"),
    @NamedQuery(name = "Usuarios.findByEmail", query = "SELECT u FROM Usuarios u WHERE u.email = :email"),
    @NamedQuery(name = "Usuarios.findByAdmin", query = "SELECT u FROM Usuarios u WHERE u.admin = :admin"),})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "SENHA")
    private String senha;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CELULAR")
    private String celular;
    @Column(name = "ADMIN")
    private Integer admin;
    @Column(name = "OBS")
    private String obs;

    public Usuarios() {
    }

    public Usuarios(String nome, String login, String email, String senha) throws Exception {
        try {
            setNome(nome);
            setLogin(login);
            setEmail(email);
            setSenha(senha);
            setAdmin(0);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario[ id=" + id + " ]";
    }

    /**
     * setSenha recebe uma string e converte para um hash MD5
     *
     * @param senha
     * @throws Exception
     */
    public void setSenha(String senha) throws Exception {
        try {
            this.senha = Utils.MD5(senha);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public Boolean validaLogin(String senhaValida) throws Exception {
        return senha.equals(Utils.MD5(senhaValida));
    }

    public boolean checkUsuario() throws Exception {
        if (nome.isEmpty()) {
            throw new Exception("Nome de usuário inválido");
        }
        if (email.isEmpty()) {
            throw new Exception("Email inválido");
        }
        if (login.isEmpty()) {
            throw new Exception("Nome de login inválido");
        }
        if (senha.isEmpty()) {
            throw new Exception("Senha inválida");
        }

        return true;
    }

    public boolean Save() throws Exception {
        try {
            UsuariosDAO u = new UsuariosDAO(this);
            u.saveUsuario();
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean Update() throws Exception {
        try {
            UsuariosDAO u = new UsuariosDAO(this);
            u.updateUsuario();
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean Delete() throws Exception {
        try {
            UsuariosDAO u = new UsuariosDAO(this);
            u.deleteUsuario();
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public static List<Usuarios> getAll() throws Exception {
         try {
            return UsuariosDAO.getAllUsuarios();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
   
}
