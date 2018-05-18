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
package br.estacio.prii.copa.persistence;

import br.estacio.prii.copa.entidade.Usuarios;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UsuariosDAO extends DAO {

    private static final Logger LOG = Logger.getLogger(DAO.class.getName());
    private Usuarios Usuario;

    public UsuariosDAO() throws Exception {
        this(null);
    }

    public UsuariosDAO(Usuarios Usuario) throws Exception {
        super();
        this.Usuario = Usuario;
    }

    public static List<Usuarios> getAllUsuarios() throws Exception {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("copaDaviNilsonPU");
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Usuarios.findAll", Usuarios.class);
            LOG.info(query.toString());
            List results = query.getResultList();
            em.getTransaction().commit();
            return results;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Usuarios getUsuarioByLogin(String login) throws Exception {
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Usuarios.findByLogin", Usuarios.class)
                    .setParameter("login", login);
            LOG.info(query.toString());
            List results = query.getResultList();
            em.getTransaction().commit();
            return (Usuarios) results.get(0);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void saveUsuario() throws Exception {
        try {
            Usuario.checkUsuario();
            super.save(Usuario);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateUsuario() throws Exception {
        try {
            Usuario.checkUsuario();
            super.update(Usuario);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void deleteUsuario() throws Exception {
        try {
            if (Usuario.getId() == null) {
                throw new Exception("Não é possivel remover um registro sem ID.");
            }
            super.delete(Usuario);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
