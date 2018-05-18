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

import br.estacio.prii.copa.persistence.EstadiosDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Estadios", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadios.findAll", query = "SELECT e FROM Estadios e"),
    @NamedQuery(name = "Estadios.findById", query = "SELECT e FROM Estadios e WHERE e.id = :id"),
    @NamedQuery(name = "Estadios.findByNome", query = "SELECT e FROM Estadios e WHERE e.nome = :nome"),
    @NamedQuery(name = "Estadios.findByCidade", query = "SELECT e FROM Estadios e WHERE e.cidade = :cidade")})
public class Estadios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    private int capacidade;
    private String nome;
    private String cidade;

    public Estadios() {
    }

    public Estadios(int capacidade, String nome, String cidade) {
        setCapacidade(capacidade);
        setNome(nome);
        setCidade(cidade);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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
        if (!(object instanceof Estadios)) {
            return false;
        }
        Estadios other = (Estadios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Estadios[ id=" + id + " ][ nome=" + nome + " ][ capacidade=" + capacidade + " ][ cidade=" + cidade + " ]";
    }

    public boolean checkEstadio() throws Exception {
        if (nome.isEmpty()) {
            throw new Exception("Nome inválido");
        }
        if (cidade.isEmpty()) {
            throw new Exception("Cidade inválida");
        }
        return true;
    }
    public boolean Save() throws Exception {
        try {
            EstadiosDAO e = new EstadiosDAO(this);
            e.saveEstadio();
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean Update() throws Exception {
        try {
            EstadiosDAO e = new EstadiosDAO(this);
            e.updateEstadio();
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean Delete() throws Exception {
        try {
            EstadiosDAO e = new EstadiosDAO(this);
            e.deleteEstadio();
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public static List<Estadios> getAll() throws Exception {
         try {
            return EstadiosDAO.getAllEstadios();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
